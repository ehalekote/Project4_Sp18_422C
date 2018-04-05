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

import assignment5.Critter.TestCritter;

import java.util.ArrayList;
import java.util.LinkedList;
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
	/**
	 * Prints the world to the console
	 */
	public static void displayWorld() {
		 //Clear critters grid
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
