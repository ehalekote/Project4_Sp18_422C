package assignment5;
/* CRITTERS CritterA.java
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

import assignment5.Critter.CritterShape;

/**
 * This Critter only fights critters of the same type and only walks left and right in the same two spots
 * @author ehalekote
 *
 */
public class CritterA extends Critter {
	private int direction = 0;
	private int numShufflesFlag = 0;
	int numShuffles = 0;
	
	/**
	 * This critter shuffles back and forth from the spot it was first spawned.
	 */
	@Override
	public void doTimeStep() {
		
		if(direction == 0) {
			walk(0);
			direction = 1;
			if(numShufflesFlag == 0) {
				numShufflesFlag = 1;
			}
			else {
				numShuffles++;
			}
		}
		else {
			walk(4);
			direction = 0;
		}
		
	}

	/**
	 * This critter only fights critters of the same type
	 */
	@Override
	public boolean fight(String oponent) {
		if(oponent == "$") {
			return true;
		}
		return false;
	}

	/**
	 * CritterA is $
	 */
	@Override
	public String toString() { return "$"; }
	
	/**
	 * Checks for number of total shuffles
	 * @param critterList
	 */
	public static String runStats(java.util.List<Critter> critterList) {
		//System.out.print("" + critterList.size() + " total CritterAs   ");	
		String aStats = "" + critterList.size() + " total CritterAs\r\n";
		
		int totalShuffles = 0;
		for (Object obj : critterList) {
			CritterA c = (CritterA) obj;
			totalShuffles = totalShuffles + c.numShuffles;
		}
		//System.out.println("" + totalShuffles + " total shuffles   ");
		aStats += "" + totalShuffles + " total shuffles";
		return aStats;
	}
	
	@Override
	public CritterShape viewShape() { return CritterShape.DIAMOND; }

	@Override
	public javafx.scene.paint.Color viewFillColor() { return javafx.scene.paint.Color.PURPLE; }
	
}
