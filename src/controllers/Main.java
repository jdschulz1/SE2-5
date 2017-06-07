package controllers;

	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	//Static root passes to controller
	private static BorderPane root = new BorderPane();
	
	//Getter for controller
	public static BorderPane getRoot(){
		return root;
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			MenuBar bar = FXMLLoader.load(getClass().getResource("/views/Menus.fxml"));
			AnchorPane paneMain = FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
			root.setTop(bar);
			root.setCenter(paneMain);

			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
