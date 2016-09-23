package com.starterkit.javafx;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Application entry point.
 */
public class Startup extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("StarterKit-ImageViewer");

		Parent Root = FXMLLoader.load(getClass().getResource("/com/starterkit/javafx/view/profile-base.fxml"), //
				ResourceBundle.getBundle("com/starterkit/javafx/bundle/base"));

		Scene scene = new Scene(Root);

		primaryStage.setScene(scene);

		primaryStage.show();
	}
}
