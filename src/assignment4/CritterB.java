package assignment4;
/* CRITTERS CritterB.java
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
 * This Critter walks in circles and always fights
 * @author ehalekote
 *
 */
public class CritterB extends Critter{
	private int direction = 0;
	private int numCirclesFlag = 0;
	int numCircles = 0;
	int angle = Critter.getRandomInt(2);
	
	@Override
	public void doTimeStep() {
		if(angle == 0) {
			if(direction == 0) {
				walk(0);
				direction = 1;
				if(numCirclesFlag == 0) {
					numCirclesFlag = 1;
				}
				else {
					numCircles++;
				}
			}
			else if(direction == 1) {
				walk(6);
				direction = 2;
			}
			else if(direction == 2) {
				walk(4);
				direction = 3;
			}
			else { //direction == 3
				walk(2);
				direction = 0;
			}
		}
		else {
			if(direction == 0) {
				walk(4);
				direction = 1;
				if(numCirclesFlag == 0) {
					numCirclesFlag = 1;
				}
				else {
					numCircles++;
				}
			}
			else if(direction == 1) {
				walk(6);
				direction = 2;
			}
			else if(direction == 2) {
				walk(0);
				direction = 3;
			}
			else { //direction == 3
				walk(2);
				direction = 0;
			}
		}
		
	}

	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public String toString() { return "!"; }

}