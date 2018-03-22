package assignment4;

/* CRITTERS Main.java
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

import java.util.List;
import java.util.Scanner;
import java.io.*;

/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        
        CritterWorld world = new CritterWorld();
        boolean exit = false; // quit command will set to true
        while(!exit) {
        	System.out.print("critters> ");
        	String wholeCommand = kb.nextLine();
        	String[] splitCommands = wholeCommand.split(" ");
        	
        	switch(splitCommands[0]) {
        		case "quit":
        			if (splitCommands.length > 1) {
        				System.out.println("error processing: " + wholeCommand);
        				break;
        			}
        			System.out.println("quit");
        			exit = true;
        			break;
        			
        		case "show":
        			if (splitCommands.length > 1) {
        				System.out.println("error processing: " + wholeCommand);
        				break;
        			}
        			System.out.println("Display of world:");
        			CritterWorld.displayWorld();
        			break;
        			
        		case "step":
        			//case when user wants to advance world by 1 time step
        			if (splitCommands.length == 1) {
            			System.out.println("World has been advanced by 1 time step.");
            			Critter.worldTimeStep(); ////WORLDTIMESTEP - Everyone moves, Critters can reproduce at this point and add offspring to babies array
        			}
        			
        			//case when user wants to advance world by specified amount of time steps
        			else if (splitCommands.length == 2) {
        				try {
        					int stepCount = Integer.parseInt(splitCommands[1]);
        					for (int i = 0; i < stepCount; i += 1) {
        						Critter.worldTimeStep();
        					}
        				}
        				catch (NumberFormatException e) {
        					System.out.println("error processing" + wholeCommand);
        				}
        			}
        			
        			//user inputs something after step that is not valid
        			else {
        				System.out.println("error processing" + wholeCommand);
        			}
        			
        			break;
        			
        		case "seed":
        			if (splitCommands.length > 2) {
        				System.out.println("error processing: " + wholeCommand);
        			}
        			else if (splitCommands.length > 1) {
        				try {
        					long seed = Long.parseLong(splitCommands[1]);
        					System.out.println("Seed is set at " + splitCommands[1] + ".");
        					Critter.setSeed(seed);
        				}
        				catch (NumberFormatException e) {
        					System.out.println("error processing: " + wholeCommand);
        				}
        			}
        			else {
        				System.out.println("error processing:" + wholeCommand);
        			}
        			break;
        			
        		case "make":
        			//case when user enters make by itself or too many items following make
        			if (splitCommands.length == 1 || splitCommands.length > 3) {
        				System.out.println("error processing: " + wholeCommand);
        			}
        			
        			//case when user wants to make just one critter of specified type
        			else if (splitCommands.length == 2) {
        				try {
        					String critterClass = "assignment4." +  splitCommands[1];
        					Class c = Class.forName(critterClass);
        					Critter crit = (Critter)c.newInstance();
        					Critter.makeCritter(splitCommands[1]);
        				}
        				catch (Exception | NoClassDefFoundError e) {
        					System.out.println("error processing: " + wholeCommand);
        				}
        			}
        			
        			//case when user wants to make a specified amount of specified critter type
        			else {
        				try {
        					String critterClass = "assignment4." +  splitCommands[1];
        					Class c = Class.forName(critterClass);
        					Critter crit = (Critter)c.newInstance();
        					int makeCount = Integer.parseInt(splitCommands[2]);
        					System.out.println("You are going to make " + makeCount + " critters of " + splitCommands[1] + " type.");
        					for (int i = 0; i < makeCount; i += 1) {
        						Critter.makeCritter(splitCommands[1]);
        					}
        				}
        				catch (Exception | NoClassDefFoundError e) {
        					System.out.println("error processing: " + wholeCommand);
        				}
        			}
        			
        			break;
        			
        		default:
        			System.out.println("invalid command: " + wholeCommand);
        			break;
        	}
        }
        
        //While (Command is not EXIT):
	        //do {
	        		//Get and execute user input commands
	        		//for each step:
		        		//WORLDTIMESTEP - Everyone moves, Critters can reproduce at this point and add offspring to babies array
			        //Critter.worldTimeStep();
			        
			        //RESOLVE ENCOUNTERS
			        
			        //ADD BABIES TO WORLDMODEL AND POPULATION
			        
			        //CULL DEAD CRITTERS
			        //CritterWorld.cullDeadCritters();
			        
		        		//ADD ALGAE
	        //}
	        //while(false); //Should be while user input != "quit"
        /* Write your code above */
        
        System.out.flush();

    }
}
