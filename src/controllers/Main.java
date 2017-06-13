package controllers;
	
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
import model.DeliveryTracker;
import model.Intersection;
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
			
			
			Client client1 = new Client(1, "Bickers & Bickers", StreetDAO.findStreetById(2), StreetDAO.findStreetById(3), "2nd Floor", "(505)445-9832", "office@bickersbickers.com");
			deliveryTracker.addClient(client1);
			
			Client client2 = new Client(1, "Morison and Reyes", StreetDAO.findStreetById(5), StreetDAO.findStreetById(7), "A place", "(505)888-3333", "Overwatch@gmail.com");
			deliveryTracker.addClient(client2);
			
			Client client3 = new Client(1, "Hana & Lucio", StreetDAO.findStreetById(9), StreetDAO.findStreetById(11), "Dance Floor", "(505)345-1234", "SickBeatz@hotmail.com");
			deliveryTracker.addClient(client3);
			
			User user1 = new User("Barry", "The Flash", "password", "Admin", "Lightning@flashmail.com");
			deliveryTracker.addUser(user1);
			User user2 = new User("Burt", "Zoom", "password", "User", "Zoomies@flashmail.com");
			deliveryTracker.addUser(user2);
			System.out.println(deliveryTracker.getUsers().size());
			
			DeliveryTicket deliveryTicket1 = new DeliveryTicket();
			deliveryTicket1.setPayingClient(client1);
			deliveryTicket1.setPickupClient(client2);
			deliveryTicket1.setDeliveryClient(client3);
			deliveryTicket1.setPrice(new BigDecimal(2.00));
			deliveryTicket1.setOrderDateTime(LocalDateTime.now().plusDays(4));
			
			DeliveryTicket deliveryTicket2 = new DeliveryTicket();
			deliveryTicket1.setPayingClient(client3);
			deliveryTicket1.setPickupClient(client2);
			deliveryTicket1.setDeliveryClient(client1);
			deliveryTicket1.setPrice(new BigDecimal(10.00));
			deliveryTicket1.setOrderDateTime(LocalDateTime.now().plusDays(2));
			
			DeliveryTicket deliveryTicket3 = new DeliveryTicket();
			deliveryTicket1.setPayingClient(client2);
			deliveryTicket1.setPickupClient(client3);
			deliveryTicket1.setDeliveryClient(client2);
			deliveryTicket1.setPrice(new BigDecimal(20.00));
			deliveryTicket1.setOrderDateTime(LocalDateTime.now().plusDays(30));
			
			emDAO.getEM().getTransaction().begin();
			emDAO.getEM().persist(deliveryTicket1);
			emDAO.getEM().persist(deliveryTicket2);
			emDAO.getEM().persist(deliveryTicket3);
			emDAO.getEM().getTransaction().commit();
			
			
			
			//setup dummy couriers
			Courier courier = new Courier(1, "Sally Sassalot", "4055555353", true);
			deliveryTracker.addCourier(courier);
		
			System.out.println(CityMap.getCityMap().getIntersections().size());
			System.out.println(CityMap.getIntersections().size());
			System.out.println(deliveryTracker.getMap().getIntersections().size());
			System.out.println(deliveryTracker.getMap().getCityMap().getIntersections().size());
			System.out.println(IntersectionDAO.listIntersection().size());
			//dummy streets and impediments
			//deliveryTracker.addTrafficImpediment(new TrafficImpediment(CityMap.getIntersections().get(1),LocalDateTime.now(),LocalDateTime.now().plusDays(1)));
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
