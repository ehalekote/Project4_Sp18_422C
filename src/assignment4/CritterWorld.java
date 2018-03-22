package assignment4;
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

import assignment4.Critter.TestCritter;

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
	/**
	 * Prints the world to the console
	 */
	public static void displayWorld() {
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
