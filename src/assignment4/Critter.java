package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */


import java.util.List;
import java.util.Random;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	//Test
	public static void addCritterTest(Critter c) {
		c.x_coord = 0;
		c.y_coord = 0;
		Critter.population.add(c);
		CritterWorld.worldModel.get(c.x_coord).get(c.y_coord).add(c);
	}
	//
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

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
	private boolean exists() {
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
			
		//Add Critter to position of updated x and y coordinates
		CritterWorld.worldModel.get(this.x_coord).get(y_coord).add(this);
	}
	
	/**
	 * Critter takes one step
	 * @param direction Direction to move critter
	 */
	protected final void walk(int direction) {
		if(exists()) {
			this.energy = this.energy - Params.walk_energy_cost;
			oneStep(direction);
		}
		else {
			System.out.println("This Critter doesn't exist in the world/population and so it cannot walk");
		}
	}
	
	/**
	 * Makes a critter take two steps in the specified direction
	 * @param direction Direction to run in
	 */
	protected final void run(int direction) {
		if(exists()) {
			this.energy = this.energy - Params.run_energy_cost;
			oneStep(direction);
			oneStep(direction);
		}
		else {
			System.out.println("This Critter doesn't exist in the world/population and so it cannot run");
		}
	}
	
	protected final void reproduce(Critter offspring, int direction) {
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
			Random rand = new Random();
			crit.x_coord = rand.nextInt(Params.world_width);
			crit.y_coord = rand.nextInt(Params.world_height);
			
			//Place critter into population and worldModel
			population.add(crit);
			CritterWorld.worldModel.get(crit.x_coord).get(crit.y_coord).add(crit);
			
		} catch(Exception e) {
			System.out.println("YOU MESSED UP");
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
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
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
		// Complete this method.
	}
	
	public static void worldTimeStep() {
		// Complete this method.
	}
	
	public static void displayWorld() {
		CritterWorld.displayWorld();
	}
}
