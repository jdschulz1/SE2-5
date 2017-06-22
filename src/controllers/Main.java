package controllers;
	
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.TypedQuery;

import dtDAO.IntersectionDAO;
import dtDAO.StreetDAO;
import dtDAO.emDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CityMap;
import model.Client;
import model.Courier;
import model.DeliveryTicket;
import model.User;
import model.Vertex;
import model.DeliveryTracker;
import model.Graph;
import model.Instruction;
import model.Intersection;
import model.Route;
import model.Street;
import model.TrafficImpediment;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	DeliveryTracker deliveryTracker;
	
	//Static root passes to controller
	private static BorderPane root = new BorderPane();
	
	//Getter for controller
	public static BorderPane getRoot(){
		return root;
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			deliveryTracker = DeliveryTracker.getDeliveryTracker();
			MenuBar bar = FXMLLoader.load(getClass().getResource("/views/Menus.fxml"));
			AnchorPane paneMain = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
			root.setTop(bar);
			bar.setDisable(true);
			root.setCenter(paneMain);
			

			Scene scene = new Scene(root, 1000, 900);
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
		
			//test shortest path
			Intersection src = IntersectionDAO.findIntersectionByStreets("F Street", "3rd Ave"), dest = IntersectionDAO.findIntersectionByStreets("G Street", "6th Ave");
			ArrayList<Vertex> shortest_path;
			
			Route test = new Route(src, dest);
			shortest_path = test.getPath();
//			System.out.println("route length =" + test.getRouteDistance());
			
//			for(Vertex v : shortest_path){
//				System.out.print(v.getIntersection().getName() +  " shortest dist: ");
//				System.out.print(v.getShortestDist() + " prev intersection: ");
//				System.out.println(v.getPrevious().getIntersection().getName());
//				if(v.getIntersection() == dest)break;
//			}
			
//			for(Instruction i : test.getInstructionsList()){
//				System.out.println(i.CreateInstruction());
//			}
			
			primaryStage.show();
			//bar.setDisable(true);
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/Login.fxml"));
//			Login controller = new Login();
//			fxmlLoader.setController(controller);
        	AnchorPane currentPane = fxmlLoader.load();
        	BorderPane border = Main.getRoot();
    		border.setCenter(currentPane);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
