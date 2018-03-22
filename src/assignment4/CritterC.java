package assignment4;
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

/**
 * This critter moves diagonally to the up and right and won't fight critters of the same type
 * @author ehalekote
 *
 */
public class CritterC extends Critter{
	int numWalks = 0;
	int numRuns = 0;
	int directionToMove = Critter.getRandomInt(8);
	
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
	}

	@Override
	public boolean fight(String oponent) {
		if(oponent == "&") {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() { return "&"; }
	
	public static void runStats(java.util.List<Critter> critterList) {
		System.out.print("" + critterList.size() + " total CritterCs");	
		
		int totalWalks = 0;
		int totalRuns = 0;
		for (Object obj : critterList) {
			CritterC c = (CritterC) obj;
			totalWalks = totalWalks + c.numWalks;
			totalRuns = totalRuns + c.numRuns;
		}
		System.out.print("" + totalWalks + " total number of walks");
		System.out.print("" + totalRuns + " total number of runs");		
		
	}

}
