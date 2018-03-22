package assignment4;

/**
 * This Critter has a 1/3 chance of fighting other critters and only moves in an upward zig-zag patter
 * @author ehalekote
 *
 */
public class CritterD extends Critter{
	private int direction = 0;
	int numInitiatedFights = 0;
	
	@Override
	public void doTimeStep() {
		if(direction == 0) {
			walk(1);
		}
		else {
			walk(3);
		}
		
	}

	@Override
	public boolean fight(String oponent) {
		numInitiatedFights++;
		int random = Critter.getRandomInt(3);
		if(random == 0) {
			return true;
		}
		return false;
		
	}
	
	@Override
	public String toString() { return "#"; }

}
