package assignment4;

import java.util.List;
import java.util.ArrayList;


public class CritterWorld {
	
	static ArrayList<ArrayList<ArrayList<Critter>>> worldModel = new ArrayList<ArrayList<ArrayList<Critter>>>();		//Represents the game board, indexed as X, Y, list of critters in the spot
	
	/**
	 * Generates an empty world
	 */
	CritterWorld(){
		for(int x=0; x<Params.world_width; x++) {
			worldModel.add(new ArrayList<ArrayList<Critter>>());
			for(int y=0; y<Params.world_height; y++) {
				worldModel.get(x).add(null);
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
				if(worldModel.get(x) != null && worldModel.get(x).get(y) != null && worldModel.get(x).get(y).size() == 1) {
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
	
	/**
	 * Updates the position of a Critter on the worldModel
	 * @param crit Critter whose position is to be updated
	 * @param x_coord new x position
	 * @param y_coord new y position
	 */
	public void updateCritterPosition(Critter crit, int x_coord, int y_coord){
		
	}
}
