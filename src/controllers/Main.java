package controllers;

	
import java.time.LocalDateTime;

import dtDAO.IntersectionDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CityMap;
import model.Client;
import model.Courier;
import model.User;
import model.DeliveryTracker;
import model.Street;
import model.TrafficImpediment;
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
			
			DeliveryTracker deliveryTracker = DeliveryTracker.getDeliveryTracker();
			Street street1 = new Street();
			Client client = new Client(1, "Bickers & Bickers", street1, street1, "2nd Floor", "(505)445-9832", "office@bickersbickers.com");
			deliveryTracker.addClient(client);
			
			User user1 = new User("Barry", "The Flash", "password", "Admin", "Lightning@flashmail.com");
			deliveryTracker.addUser(user1);
			User user2 = new User("Burt", "Zoom", "password", "User", "Zoomies@flashmail.com");
			deliveryTracker.addUser(user2);
			System.out.println(deliveryTracker.getUsers().size());
			
			//setup dummy couriers
			Courier courier = new Courier(1, "Sally Sassalot", "4055555353", true);
			deliveryTracker.addCourier(courier);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
