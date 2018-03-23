package assignment4;
/* CRITTERS Critter.java
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


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	private boolean hasMoved = false;

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	/**
	 * Checks to see if a Critter Exists in the world (i.e. If it is still alive)
	 * @return
	 */
	protected boolean exists() {
		if(CritterWorld.worldModel.get(this.x_coord).get(this.y_coord).contains(this) && population.contains(this)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Moves a critter from one position to one step in the given direction on the World
	 * @param direction Direction to move critter one step
	 */
	private void oneStep(int direction) {
		//Remove Critter from it's current position
		CritterWorld.worldModel.get(this.x_coord).get(this.y_coord).remove(this);
			
		//Update this.x_coord and this.y_coord
		updateCoordsFromStep(direction);
		
		//Add Critter to position of updated x and y coordinates
		CritterWorld.worldModel.get(this.x_coord).get(y_coord).add(this);
	}
	
	/**
	 * Updates a Critter's x_coord & y_coord given a direction to take a single step
	 * @param direction Direction to step
	 */
	private void updateCoordsFromStep(int direction) {
		//Update x and y coords
				if(direction == 0) { //walk right
					this.x_coord = (this.x_coord + 1) % Params.world_width;
				}
				else if(direction == 1) { //walk diagonally up-right
					this.x_coord = (this.x_coord + 1) % Params.world_width;
					if(this.y_coord - 1 < 0) {
						this.y_coord = Params.world_height -1;
					}else {
						this.y_coord = this.y_coord - 1;
					}
				}
				else if(direction == 2) { //walk up
					if(this.y_coord - 1 < 0) {
						this.y_coord = Params.world_height -1;
					}else {
						this.y_coord = this.y_coord - 1;
					}
					
				}
				else if(direction == 3) { // walk diagonally up-left
					if(this.x_coord - 1 < 0) {
						this.x_coord = Params.world_width -1;
					}else {
						this.x_coord = this.x_coord - 1;
					}
					
					if(this.y_coord - 1 < 0) {
						this.y_coord = Params.world_height - 1;
					}else {
						this.y_coord = this.y_coord - 1;
					}
					
				}
				else if(direction == 4) { //walk left
					if(this.x_coord - 1 < 0) {
						this.x_coord = Params.world_width -1;
					}else {
						this.x_coord = this.x_coord - 1;
					}
					
				}
				else if(direction == 5) { //walk left down
					if(this.x_coord - 1 < 0) {
						this.x_coord = Params.world_width -1;
					}else {
						this.x_coord = this.x_coord - 1;
					}
					
					this.y_coord = (this.y_coord + 1) % Params.world_height;
					
				}
				else if(direction == 6) { //walk down
					this.y_coord = (this.y_coord + 1) % Params.world_height;
					
				}
				else {  //walk right down, direction == 7 
					this.x_coord = (this.x_coord + 1) % Params.world_width;
					this.y_coord = (this.y_coord + 1) % Params.world_height;
				}
	}
	
	/**
	 * Critter takes one step
	 * @param direction Direction to move critter
	 */
	protected final void walk(int direction) {
		if(this.hasMoved == false) {
			if(exists()) {
				this.energy = this.energy - Params.walk_energy_cost;
				oneStep(direction);
				this.hasMoved = true;
			}
			else {
				System.out.println("This Critter doesn't exist in the world/population and so it cannot walk");
			}
		}
		else {
			this.energy = this.energy - Params.walk_energy_cost;			
		}
	}	
	
	/**
	 * Makes a critter take two steps in the specified direction
	 * @param direction Direction to run in
	 */
	protected final void run(int direction) {
		if(this.hasMoved == false) {
			if(exists()) {
				this.energy = this.energy - Params.run_energy_cost;
				oneStep(direction);
				oneStep(direction);
				this.hasMoved = true;
			}
			else {
				System.out.println("This Critter doesn't exist in the world/population and so it cannot run");
			}
		}
		else {
			this.energy = this.energy - Params.run_energy_cost;		
		}
	}
	
	/**
	 * Generates a new offspring for a Critter if it has at least min_reproduce_energy 
	 * @param offspring New offspring to be created
	 * @param direction Direction to spawn new offspring
	 */
	protected final void reproduce(Critter offspring, int direction) {
		if(this.energy >= Params.min_reproduce_energy) {
			//Subtract energy cost of offspring
			offspring.energy = this.energy/2;
			if(this.energy % 2 == 0) {this.energy /= 2;}
			else {this.energy = (this.energy/2) + 1;}
			
			//Set offspring coordinates
			offspring.x_coord = this.x_coord;
			offspring.y_coord = this.y_coord;
			offspring.updateCoordsFromStep(direction);
			
			babies.add(offspring);
			//At this point offSpring is on babies ArrayList but not yet on the WorldModel
		}
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {	
		try {
			//Generate new critter from string critter_class_name
			critter_class_name = "assignment4." +  critter_class_name;
			Class c = Class.forName(critter_class_name);
			Critter crit = (Critter)c.newInstance();
			
			//Set critter energy
			crit.energy = Params.start_energy;
			
			//Generate random critter position
			crit.x_coord = Critter.getRandomInt(Params.world_width);
			crit.y_coord = Critter.getRandomInt(Params.world_height);
			
			//Place critter into population and worldModel
			population.add(crit);
			CritterWorld.worldModel.get(crit.x_coord).get(crit.y_coord).add(crit);
			
		} catch(Exception e) {
			System.out.println("error processing: " + "make" );
			System.out.println(e);
			
		}
		

	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		System.out.print(critter_class_name);
		try {
			for(Critter crit: population) {
				System.out.println(crit.getClass().getSimpleName());
				if(crit.getClass().getSimpleName().equals(critter_class_name)) {
					result.add(crit);
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
			return result;
		}
		
		return result;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		/**
		 * sets the energy value of a TestCritter
		 * @param new_energy_value Value to set TestCritter energy to
		 */
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
			if(super.energy <= 0) {
				if(exists()) {
					population.remove(this);
					CritterWorld.worldModel.get(super.x_coord).get(super.y_coord).remove(this);
				}
				else {
					System.out.println("A TestCritter that was killed via setEnergy does not exist in the board");
				}
				
			}
		}
		
		/**
		 * Sets x_coord of a TestCritter
		 * @param new_x_coord Value to set
		 */
		protected void setX_coord(int new_x_coord) {
			if(this.exists()) {
				CritterWorld.worldModel.get(super.x_coord).get(super.y_coord).remove(this);
				super.x_coord = new_x_coord;
				CritterWorld.worldModel.get(super.x_coord).get(super.y_coord).add(this);
			}
			else {
				System.out.println("setX_coord in TestCritter - exists() == false - TestCritter location in model not updated but x_coord was");
				super.x_coord = new_x_coord;
			}
			
			
		}
		
		/**
		 * Sets y_coord of a TestCritter
		 * @param new_y_coord Value to set
		 */
		protected void setY_coord(int new_y_coord) {
			if(this.exists()) {
				CritterWorld.worldModel.get(super.x_coord).get(super.y_coord).remove(this);
				super.y_coord = new_y_coord;
				CritterWorld.worldModel.get(super.x_coord).get(super.y_coord).add(this);
			}
			else {
				System.out.println("setY_coord in TestCritter - exists() == false - TestCritter location in model not updated but y_coord was");
				super.y_coord = new_y_coord;
			}
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		for(Critter crit: population) {
			CritterWorld.worldModel.get(crit.x_coord).get(crit.y_coord).remove(crit);
		}
		population.clear();
	}
	
	
	public static void worldTimeStep() {
		//calls doTimeStep for every living critter
		for(Critter crit: population) {
			crit.doTimeStep();
		}
		
		//critters who are out of energy at this point are culled
		CritterWorld.cullDeadCritters();
		
		//critters occupying the same space after moving must fight with one another
		//dead critters are automatically removed in this step
		resolveEncounters();
		
		//flag for checking if critter has moved during a time step is reset
		for(Critter crit: population) {
			crit.hasMoved = false;
		}
		
		//rest energy is deducted from every critter, regardless if they moved or not
		//critters who are out of energy at this point are culled
		for(Critter crit: population) {
			crit.energy -= Params.rest_energy_cost;
		}		
		CritterWorld.cullDeadCritters();
		
		//algae of a particular amount (specified in Params.java) are generated and immediately added to the population
		for(int i = 0; i < Params.refresh_algae_count; i += 1) {
			try {
				makeCritter("Algae");
			}
			catch (InvalidCritterException e) {
				System.out.println("You tried to generate " + Params.refresh_algae_count + " algae, but you messed up.");
			}
		}
		
		//add babies to population and world model, then clear from babies list
		population.addAll(babies);
		for (Critter baby: babies) {
			CritterWorld.worldModel.get(baby.x_coord).get(baby.y_coord).add(baby);
		}
		babies.clear();
	}
	
	/**
	 * Displays worldModel via the console
	 */
	public static void displayWorld() {
		CritterWorld.displayWorld();
	}
	
	/**
	 * Resolves encounters between living critters that occupy the same space
	 */
	private static void resolveEncounters() {
		//creates an arraylist of linked lists containing groups of critters occupying the same space
		ArrayList<LinkedList<Critter>> encounters = new ArrayList<LinkedList<Critter>>();
		for (int x = 0; x < Params.world_width; x++) {
			for (int y = 0; y < Params.world_height; y++) {
				if (!CritterWorld.worldModel.get(x).get(y).isEmpty() && CritterWorld.worldModel.get(x).get(y).size() > 1) {
						encounters.add(CritterWorld.worldModel.get(x).get(y));
				}
			}
		}
		
		//iterate through each group of critters, each group will be called fighting
		for (int index = 0; index < encounters.size(); index += 1) {
			LinkedList<Critter> fighting = encounters.get(index);
			
			//the first critter in the linked list will fight all other critters in the linked list in order
			while (fighting.size() >= 2) {
				Critter a = fighting.get(0);
				Critter b = fighting.get(1);
				
				//critters attempt to run away if they choose not to fight
				//the boolean ran represents whether a critter successfully moved when trying to run away (opposite of hasMoved)
				boolean ran = false;
				boolean aChoice = a.fight(b.toString());
				if (aChoice == false) {
					a.doTimeStep();
					ran = !a.hasMoved;
				}
				boolean bChoice = b.fight(a.toString());
				if (bChoice == false) {
					b.doTimeStep();
					ran = !b.hasMoved;
				}
				
				//critters who die attempting to run away (whether successful or not) are removed, no fight occurs
				if(a.energy <= 0) {
					fighting.remove(a);
					population.remove(a);
					CritterWorld.worldModel.get(a.x_coord).get(a.y_coord).remove(a);
					break;
				}
				if(b.energy <= 0) {
					fighting.remove(b);
					population.remove(b);
					CritterWorld.worldModel.get(b.x_coord).get(b.y_coord).remove(b);
					break;
				}
				
				//if critters are alive and still in the same space after they make a choice to fight or not, they fight
				if (ran = true) {
					break;
				}
				int aRoll = 0;
				int bRoll = 0;
				if (aChoice = true) {
					aRoll = Critter.getRandomInt(a.getEnergy());
				}
				if (bChoice = true) {
					bRoll = Critter.getRandomInt(b.getEnergy());
				}
				
				//a survives encounter and gets half of b's energy
				//b dies and is removed from the population (and worldModel)
				int energyWon;
				if (aRoll >= bRoll) {
					energyWon = (int)(b.energy / 2);
					a.energy += energyWon;
					
					fighting.remove(b);
					population.remove(b);
					CritterWorld.worldModel.get(b.x_coord).get(b.y_coord).remove(b);
				}
				
				//b survives encounter and gets half of a's energy
				//a dies and is removed from the population (and WorldModel)
				else {
					energyWon = (int)(a.energy / 2);
					b.energy += energyWon;
					
					fighting.remove(a);
					population.remove(a);
					CritterWorld.worldModel.get(a.x_coord).get(a.y_coord).remove(a);
				}
			}
		}
	}
}
