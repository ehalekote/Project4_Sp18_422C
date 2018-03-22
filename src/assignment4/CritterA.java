package assignment4;

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
}
