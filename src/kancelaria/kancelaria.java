package kancelaria;
import kancelaria.kurier;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import java.awt.TextField;
import javafx.fxml.FXMLLoader;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.*;
import javafx.collections.*;
import javafx.stage.Stage;
import javafx.scene.text.Text.*;
import javafx.scene.text.*;
import javafx.beans.value.*;

/** the main class*/
public class kancelaria extends Application {
	public static void main(String[] args) throws InterruptedException {
		Application.launch();		
			}
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("fxml1.fxml"));//loading a fxml file of a authorization window
		primaryStage.setTitle("Very bad programm");
		primaryStage.setScene(new Scene(root, 600,400));
		primaryStage.show();
}}