package assignment5;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller extends Application {
	@Override
	public void start (Stage stage) {
		//need quit, show, step, seed, make, stats
		
		//creating show button
		Button showButton = new Button("Show");
		
		//creating seed button and text field for # entry
		Button seedButton = new Button("Seed");
		TextField seedNumber = new TextField();
		
		//creating step button and drop-down menu for # of steps
		Button stepButton = new Button("Step");
		ChoiceBox stepChoiceBox = new ChoiceBox();
		stepChoiceBox.getItems().addAll("1", "5", "10", "25", "100", "500", "1000");
		
		//creating make button, drop-down menu for critter selection, and text field for # entry
		Button makeButton = new Button("Make");
		ChoiceBox makeChoiceBox = new ChoiceBox(); //write code to retrieve list of critters
		stepChoiceBox.getItems().addAll("Test1", "Test2", "Test3");
		TextField makeNumber = new TextField();
		
		//creating runStats button and drop-down menu for critter selection
		Button statsButton = new Button("Run Statistics");
		ChoiceBox statsChoiceBox = new ChoiceBox();
		
		//creating quit button
		Button quitButton = new Button("Quit");
	}
}
