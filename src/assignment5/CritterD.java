package assignment5;
/* CRITTERS CritterD.java
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
 * This Critter has a 1/3 chance of fighting other critters and only moves in an upward zig-zag patter
 * @author ehalekote
 *
 */
public class CritterD extends Critter{
	private int direction = 0;
	int numInitiatedFights = 0;
	
	/**
	 * CritterD's override of Critter doTimeStep. Zigzags upwards.
	 */
	@Override
	public void doTimeStep() {
		if(direction == 0 && this.look(direction, false) != null) {
			walk(1);
			direction = 1;
		}
		else {
			walk(3);
			direction = 0;
		}
		
	}
	
	/**
	 * CritterD randomly fights other Critters with a 1/3 chance
	 */
	@Override
	public boolean fight(String oponent) {
		numInitiatedFights++;
		int random = Critter.getRandomInt(3);
		if(random == 0) {
			return true;
		}
		return false;
		
	}
	
	/**
	 * CritterD is #
	 */
	@Override
	public String toString() { return "#"; }
	
	/**
	 * Checks for number of initiated fights
	 * @param critterList
	 */
	public static String runStats(java.util.List<Critter> critterList) {
		//System.out.print("" + critterList.size() + " total CritterDs   ");	
		String dStats = "" + critterList.size() + " total CritterDs\r\n";
		
		int totalFights = 0;
		for (Object obj : critterList) {
			CritterD c = (CritterD) obj;
			totalFights = totalFights + c.numInitiatedFights;
		}
		//System.out.println("" + totalFights + " total number of fights initiated   ");	
		dStats += "" + totalFights + " total number of fights initiated";
		return dStats;
	}

	@Override
	public CritterShape viewShape() { return CritterShape.TRIANGLE; }

	@Override
	public javafx.scene.paint.Color viewFillColor() { return javafx.scene.paint.Color.SALMON; }
}
