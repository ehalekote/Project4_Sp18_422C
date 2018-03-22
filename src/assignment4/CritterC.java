package assignment4;

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

}
