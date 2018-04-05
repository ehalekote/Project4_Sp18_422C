package assignment5;
/* CRITTERS CritterWorld.java
 * EE422C Project 4 submission by
 * Eshan Halekote
 * eh23427
 * 15460
 * Benjamin Guo
 * bzg74
 * 15460
 * Slip days used: <0
 * Spring 2018
 */
import java.util.List;
import java.util.ResourceBundle.Control;

import assignment5.Critter.TestCritter;

import java.util.ArrayList;
import java.util.LinkedList;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Polygon;


public class CritterWorld {
	
	static ArrayList<ArrayList<LinkedList<Critter>>> worldModel = new ArrayList<ArrayList<LinkedList<Critter>>>();		//Represents the game board, indexed as X, Y, list of critters in the spot
	//static String[][] copyWorld = new String[Params.world_width][Params.world_height];
	static ArrayList<ArrayList<LinkedList<Critter>>> copyWorld = new ArrayList<ArrayList<LinkedList<Critter>>>();
	
	
	/**
	 * Generates an empty world
	 */
	CritterWorld(){
		for(int x=0; x<Params.world_width; x++) {
			worldModel.add(new ArrayList<LinkedList<Critter>>(Params.world_height));
			copyWorld.add(new ArrayList<LinkedList<Critter>>(Params.world_height));
			for(int y=0; y<Params.world_height; y++) {
				worldModel.get(x).add(new LinkedList());
				copyWorld.get(x).add(new LinkedList());
			}
		}
	}
	
	static boolean rebaseFlag = true;
	private static void rebaseWorld() {
		
		ArrayList<Node> temp = new ArrayList<Node>();
 		for(Node node: Main.grid.getChildren()) {
 			if(node instanceof Shape) {
 				temp.add(node);
 			}
			
		}
 		
 		for(Node remove: temp) {
 			Main.grid.getChildren().remove(remove);
 		}
 		temp.clear();
		
		if(rebaseFlag) {
			 Main.grid.setGridLinesVisible(true);
		        for (int row = 0; row < Params.world_width; row++) {
		            for (int col = 0; col < Params.world_height; col ++) {
		                StackPane square = new StackPane();
		                //square.setStyle("-fx-border-color: black");
		                Main.grid.add(square, col, row);
		                
		            }
		        }
		        
		        for (int i = 0; i < Params.world_width; i++) {
		        		Main.grid.getRowConstraints().add(new RowConstraints(5, Control.TTL_DONT_CACHE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
		        }
		        for (int i = 0; i < Params.world_height; i++) {
		        		Main.grid.getColumnConstraints().add(new ColumnConstraints(5, Control.TTL_DONT_CACHE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
		        }
			}
		
			rebaseFlag = false;
		}
	
	/**
	 * Prints the world to the console
	 */
	public static void displayWorld() {
		 //Clear critters grid
		rebaseWorld();
		paintWorld();
		
		//Upper Border
		System.out.print("+"); 
		for(int upper=0;upper<Params.world_width;upper++) {
			System.out.print("-");
		}
		System.out.println("+");
		
		//Middle Sections
		for(int y=0;y<Params.world_height;y++) {
			System.out.print("|");
			for(int x=0;x<Params.world_width;x++) {
				if(!worldModel.get(x).get(y).isEmpty() && worldModel.get(x).get(y).size() >=1) {
					System.out.print(worldModel.get(x).get(y).get(0).toString());
					//ADD TO JAVAFX PANE
				}
				else {System.out.print(" ");}
			}
			System.out.println("|");
		}
		
		//Bottom Border
		System.out.print("+"); 
		for(int bottom=0;bottom<Params.world_width;bottom++) {
			System.out.print("-");
		}
		System.out.println("+"); 
	}
	
	static Shape getIcon(int shapeIndex) {
		Shape s = null;
		int size = 10;
		
		shapeIndex = Critter.getRandomInt(2);
		
		switch(shapeIndex) {
		case 0: s = new Rectangle(size, size); 
			s.setFill(javafx.scene.paint.Color.RED); break;
		case 1: s = new Circle(size/2); 
			s.setFill(javafx.scene.paint.Color.GREEN); break;
		}
		// set the outline of the shape
		s.setStroke(javafx.scene.paint.Color.BLUE); // outline
		return s;
	}
	
	public static void paintWorld() {		//UPDATE TO CYCLE THROUGH ALL CRITTERS IN GRID
		for (int i = 0; i <= 1; i++) {
			Shape s = getIcon(i);	// convert the index to an icon.
			Main.grid.add(s, i, i); // add the shape to the grid.
		}
		//Iterate through all critters in worldModel
		//get their corresponding Shape value
		//Add the shape to the grid
		
		
	}
	
	/**
	 * Removes all "dead" critters (critters with energy <= 0) from population and worldModel
	 */
	public static void cullDeadCritters() {
		List<Critter> population = TestCritter.getPopulation();
		List<Critter> dead = new java.util.ArrayList<Critter>(); 
		 
		//Find dead Critters
		for(Critter crit: population) {
			if(crit.getEnergy() <= 0) {
				dead.add(crit);
			}
		}
		
		//Remove from population
		population.removeAll(dead);
		//Remove from worldModel
		for(Critter deadCrit: dead) { 
			for(int y=0;y<Params.world_height;y++) {
				for(int x=0;x<Params.world_width;x++) {
					if(worldModel.get(x).get(y).contains(deadCrit)) {
						worldModel.get(x).get(y).remove(deadCrit);
					}
				}
			}
		}
	}
	
	
	
}
