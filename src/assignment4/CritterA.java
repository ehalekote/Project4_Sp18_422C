package assignment4;
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

/**
 * This Critter only fights critters of the same type and only walks left and right in the same two spots
 * @author ehalekote
 *
 */
public class CritterA extends Critter {
	private int direction = 0;
	private int numShufflesFlag = 0;
	int numShuffles = 0;
	
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

	@Override
	public boolean fight(String oponent) {
		if(oponent == "$") {
			return true;
		}
		return false;
	}

	@Override
	public String toString() { return "$"; }
	
	public static void runStats(java.util.List<Critter> critterList) {
		System.out.print("" + critterList.size() + " total CritterAs");	
		
		int totalShuffles = 0;
		for (Object obj : critterList) {
			CritterA c = (CritterA) obj;
			totalShuffles = totalShuffles + c.numShuffles;
		}
		System.out.print("" + totalShuffles + " total shuffles");
	}
	
}
