package assignment5;

/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Eshan Halekote
 * eh23427
 * 15460
 * Benjamin Guo
 * bzg74
 * 15460
 * Slip days used: 0
 * Spring 2018
 */

import java.util.List;
import java.util.ResourceBundle.Control;
import java.util.Scanner;
import java.io.*;
import java.lang.reflect.Method;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.*;

/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main extends Application{

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
    
    static GridPane grid = new GridPane();

    //HI
    static String[] arg2 = {};

    @Override
    public void start(Stage primaryStage) {
    		try {
    			CritterWorld temp = new CritterWorld();	//Needed to instantiate WorldModel 
    			Critter.makeCritter("CritterA");
//    			Critter.makeCritter("CritterA");
//    			Critter.makeCritter("CritterA");
//    			Critter.makeCritter("CritterB");
//    			Critter.makeCritter("CritterB");
    			Critter.makeCritter("CritterB");
    			Critter.makeCritter("CritterC");
    			Critter.makeCritter("CritterD");
    			Critter.makeCritter("Craig");
    			
        if (arg2.length != 0) {
            try {
                inputFile = arg2[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
            }
            if (arg2.length >= 2) {
                if (arg2[1].equals("test")) { // if the word "test" is the second argument to java
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
        
        //creating show button that creates new window which displays critter world
        Alert intAlert = new Alert(AlertType.ERROR, "You must enter an integer value.", ButtonType.OK);
        
        Button showButton = new Button("Show");
      	showButton.setOnAction(new EventHandler<ActionEvent>() {
      		
      		boolean showFlag = true;
      		
      		@Override
      		public void handle(ActionEvent event) {
      	             	        
      			if(showFlag) {
      				 Stage viewStage = new Stage();
           	        viewStage.setTitle("Critter World");
           	        viewStage.setScene(new Scene(grid, 400, 400));
           	        viewStage.setX(primaryStage.getX() + 200);
           	        viewStage.setY(primaryStage.getY() + 200);
           	        viewStage.show();
           	        
           	        showFlag = false;
      			}
      	       
      			CritterWorld.displayWorld();

      		}
      	});
      		
      	//creating seed button and text field for # entry
      	Button seedButton = new Button("Seed");
      	TextField seedNumber = new TextField();
      	seedButton.setOnAction(new EventHandler<ActionEvent>() {
      		@Override
      		public void handle(ActionEvent event) {
      			String possibleSeed = seedNumber.getText();
      			try {
      				long seed = Long.parseLong(possibleSeed);
      				Critter.setSeed(seed);
      			}
      			catch (NumberFormatException e) {
      				intAlert.showAndWait();
      				
      				if (intAlert.getResult() == ButtonType.OK) {
      					intAlert.close();
      				}
      			}
      		}
      	});
      		
      	//creating step button and drop-down menu for # of steps
      	Button stepButton = new Button("Step");
      	TextField stepNumber = new TextField();
      	//ChoiceBox stepChoiceBox = new ChoiceBox();
      	//stepChoiceBox.getItems().addAll("1", "5", "10", "25", "100", "500", "1000");
      	//stepChoiceBox.getSelectionModel().selectFirst();
      	stepButton.setOnAction(new EventHandler<ActionEvent>() {
      		public void handle(ActionEvent event) {
      			String possibleStep = stepNumber.getText();
      			try {
      				int step = Integer.parseInt(possibleStep);
      				for (int i = 0; i < step; i +=1) {
      					System.out.println("step " + (i + 1));
      					Critter.worldTimeStep();
      					CritterWorld.displayWorld();
      				}
      			}
      			catch (NumberFormatException e) {
      				intAlert.showAndWait();
      				
      				if (intAlert.getResult() == ButtonType.OK) {
      					intAlert.close();
      				}
      			}
      		}
      	});
      		
      	//creating make button, drop-down menu for critter selection, and text field for # entry
      	Button makeButton = new Button("Make");
      	ChoiceBox makeChoiceBox = new ChoiceBox(); //write code to retrieve list of critters
      	makeChoiceBox.getItems().addAll("Test1", "Test2", "Test3");
      	TextField makeNumber = new TextField();
      		
      	//creating runStats button and drop-down menu for critter selection
      	Button statsButton = new Button("Run Statistics");
      	ChoiceBox statsChoiceBox = new ChoiceBox();
      	statsChoiceBox.getItems().addAll("Test1", "Test2", "Test3");
      		
      	//creating quit button
      	Button quitButton = new Button("Quit");
      	quitButton.setOnAction(actionEvent -> Platform.exit());
      	
      	//creating a grid pane
      	GridPane controlGrid = new GridPane();
      	controlGrid.setMinSize(200, 250);
      	controlGrid.setAlignment(Pos.CENTER);
      	controlGrid.setPadding(new Insets(10, 10, 10, 10));
      	controlGrid.setVgap(5);
      	controlGrid.setHgap(5);
      	
      	//arranging the UI elements
      	controlGrid.add(showButton, 1, 0);
      	controlGrid.add(quitButton, 2, 0);
      	
      	controlGrid.add(seedButton, 0, 1);
      	controlGrid.add(seedNumber, 1, 1);
      	
      	controlGrid.add(makeButton, 0, 2);
      	controlGrid.add(makeNumber, 1, 2);
      	controlGrid.add(makeChoiceBox, 2, 2);
      	
      	controlGrid.add(stepButton, 0, 3);
      	controlGrid.add(stepNumber, 1, 3);
      	
      	controlGrid.add(statsButton, 0, 4);
      	controlGrid.add(statsChoiceBox, 1, 4);
      	
      	//styling the UI elements
      	showButton.setStyle("-fx-background-color: seagreen; -fx-text-fill: white;");
      	seedButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        makeButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        stepButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        statsButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        quitButton.setStyle("-fx-background-color: crimson; -fx-text-fill: white;");
      	
      	Scene controlScene = new Scene(controlGrid);
      	primaryStage.setTitle("Controller");
      	primaryStage.setScene(controlScene);
      	primaryStage.show();
      	

    		}
     		catch(Exception e) {
     			e.printStackTrace();		
     		}
    }
    		
    
    public static void main(String[] args) {
    		arg2 = args;
    		launch(args);
    }
    
}
