package application;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			Parent parent = loader.load();
			mainScene = new Scene(parent);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Controle de presen�as - Curso de Franc�s");
			
			URL cssURL = getClass().getResource("aplication.css");
            if (cssURL != null) {
            	mainScene.getStylesheets().add(cssURL.toExternalForm());
            }
			
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
