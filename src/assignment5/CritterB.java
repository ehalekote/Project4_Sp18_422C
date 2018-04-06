package assignment5;
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

import assignment5.Critter.CritterShape;

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
	
	/**
	 * CritterB's doTimeStep. Walks in circles.
	 */
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
	/**
	 * CritterB always fights
	 */
	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		return true;
	}
	
	/**
	 * CritterB is !
	 */
	@Override
	public String toString() { 
		return "!"; 
		}
	
	/**
	 * Checks number of counter-clockwise vs. clockwise walks
	 * @param critterList
	 */
	public static String runStats(java.util.List<Critter> critterList) {
		//System.out.print("" + critterList.size() + " total CritterBs   ");	
		String bStats = "" + critterList.size() + " total CritterBs\r\n";
		
		int totalCircles = 0;
		int clockwise = 0;
		int counter = 0;
		for (Object obj : critterList) {
			CritterB c = (CritterB) obj;
			totalCircles = totalCircles + c.numCircles;
			if(c.angle == 0) {
				clockwise = clockwise + c.numCircles;
			}
			else {
				counter = counter + c.numCircles;
			}
		}
		//System.out.print("" + totalCircles + "   ");
		//System.out.print("" + clockwise + " total clockwise circles   ");
		//System.out.println("" + counter + " total counter-clockwise circles   ");
		bStats += "" + totalCircles + " total circles\r\n";
		bStats += "" + clockwise + "total clockwise circles \r\n";
		bStats += "" + counter + "total counter-clockwise circles";
		return bStats;
	}
	
	@Override
	public CritterShape viewShape() { return CritterShape.STAR; }

	@Override
	public javafx.scene.paint.Color viewFillColor() { return javafx.scene.paint.Color.BLACK; }
	
	@Override
	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.BLACK; }

}