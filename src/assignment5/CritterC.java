package assignment5;
/* CRITTERS CritterC.java
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
 * This critter moves diagonally to the up and right and won't fight critters of the same type
 * @author ehalekote
 *
 */
public class CritterC extends Critter{
	int numWalks = 0;
	int numRuns = 0;
	int directionToMove = Critter.getRandomInt(8);
	
	/**
	 * CritterC's override of Critter doTimeStep. Randomly walks or runs in a predetermined direction (determined upon creation). Reproduces if it has more than twice required minimum reproduction energy.
	 */
	@Override
	public void doTimeStep() {
		int random = Critter.getRandomInt(2);
		
		if(random == 0) {
			walk(directionToMove);
			numWalks++;
		}
		else {
			run(directionToMove);
			numRuns++;
		}
		
		if(this.getEnergy() > 2*Params.min_reproduce_energy) {
			CritterC offspring = new CritterC(); 
			this.reproduce(offspring, (directionToMove+4)%8);
		}
	}

	/**
	 * Always fights unless it is fighting an enemy of the same CritterC type
	 */
	@Override
	public boolean fight(String oponent) {
		if(oponent == "&" && this.look(directionToMove, false) != null) {
			return false;
		}
		return true;
	}
	
	/**
	 * CritterC is &
	 */
	@Override
	public String toString() { return "&"; }
	
	/**
	 * Checks for number of runs vs walks for CritterC
	 * @param critterList
	 */
	public static String runStats(java.util.List<Critter> critterList) {
		//System.out.print("" + critterList.size() + " total CritterCs   ");
		String cStats = "" + critterList.size() + " total CritterCs\r\n";
		
		int totalWalks = 0;
		int totalRuns = 0;
		for (Object obj : critterList) {
			CritterC c = (CritterC) obj;
			totalWalks = totalWalks + c.numWalks;
			totalRuns = totalRuns + c.numRuns;
		}
		//System.out.print("" + totalWalks + " total number of walks   ");
		//System.out.println("" + totalRuns + " total number of runs   ");		
		cStats += "" + totalWalks + " total number of walks\r\n";
		cStats += "" + totalRuns + " total number of runs";
		return cStats;
	}
	
	@Override
	public CritterShape viewShape() { return CritterShape.TRIANGLE; }

	@Override
	public javafx.scene.paint.Color viewFillColor() { return javafx.scene.paint.Color.YELLOW; }
	
	@Override
	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.BLACK; }

}
