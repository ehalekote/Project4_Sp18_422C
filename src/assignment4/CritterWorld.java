package assignment4;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;


public class CritterWorld {
	
	static ArrayList<ArrayList<LinkedList<Critter>>> worldModel = new ArrayList<ArrayList<LinkedList<Critter>>>();		//Represents the game board, indexed as X, Y, list of critters in the spot
	
	/**
	 * Generates an empty world
	 */
	CritterWorld(){
		for(int x=0; x<Params.world_width; x++) {
			worldModel.add(new ArrayList<LinkedList<Critter>>(Params.world_height));
			for(int y=0; y<Params.world_height; y++) {
				worldModel.get(x).add(new LinkedList());
			}
		}
	}
	
	static void displayWorld() {
		//Upper Boarder
		System.out.print("+"); 
		for(int upper=0;upper<Params.world_width;upper++) {
			System.out.print("-");
		}
		System.out.println("+");
		
		//Middle Sections
		for(int y=0;y<Params.world_height;y++) {
			System.out.print("|");
			for(int x=0;x<Params.world_width;x++) {
				if(!worldModel.get(x).get(y).isEmpty() && worldModel.get(x).get(y).size() == 1) {
					System.out.print(worldModel.get(x).get(y).get(0).toString());
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
	
}
