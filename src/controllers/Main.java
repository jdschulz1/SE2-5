package controllers;

	
import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CityMap;
import model.Client;
import model.Courier;
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
		
			//dummy streets and impediments
			ArrayList<Street> streets = new ArrayList<Street>(), avenues = new ArrayList<Street>();
			Street A = new Street(), B = new Street(), C = new Street(), D = new Street(), E = new Street(), F = new Street(), G = new Street();
			A.setName("A Street"); A.setDirection("East");A.setWeight(1);
			B.setName("B Street"); B.setDirection("East");B.setWeight(1);
			C.setName("C Street"); C.setDirection("East");C.setWeight(1);
			D.setName("D Street"); D.setDirection("East");D.setWeight(1);
			E.setName("E Street"); E.setDirection("East");E.setWeight(1);
			F.setName("F Street"); F.setDirection("East");F.setWeight(1);
			G.setName("G Street"); G.setDirection("East");G.setWeight(1);
			streets.add(A);
			streets.add(B);
			streets.add(C);
			streets.add(D);
			streets.add(E);
			streets.add(F);
			streets.add(G);
			Street first = new Street(), second = new Street(), third = new Street(), fourth = new Street(), fifth = new Street(), sixth = new Street(), seventh = new Street();
			first.setName("1st Ave"); first.setDirection("North");first.setWeight(1);
			second.setName("2nd Ave"); second.setDirection("North");second.setWeight(1);
			third.setName("3rd Ave"); third.setDirection("North");third.setWeight(1);
			fourth.setName("4th Ave"); fourth.setDirection("North");fourth.setWeight(1);
			fifth.setName("5th Ave"); fifth.setDirection("North");fifth.setWeight(1);
			sixth.setName("6th Ave"); sixth.setDirection("North");sixth.setWeight(1);
			seventh.setName("7th Ave"); seventh.setDirection("North");seventh.setWeight(1);
			avenues.add(first);
			avenues.add(second);
			avenues.add(third);
			avenues.add(fourth);
			avenues.add(fifth);
			avenues.add(sixth);
			avenues.add(seventh);
			CityMap.setStreets(streets);
			CityMap.setAvenues(avenues);
			ArrayList<Intersection> intersections = new ArrayList<Intersection>();
			intersections.add(new Intersection(A,first));
			intersections.add(new Intersection(B,first));
			intersections.add(new Intersection(C,first));
			intersections.add(new Intersection(D,first));
			intersections.add(new Intersection(E,first));
			intersections.add(new Intersection(F,first));
			intersections.add(new Intersection(G,first));
			intersections.add(new Intersection(A,second));
			intersections.add(new Intersection(B,second));
			intersections.add(new Intersection(C,second));
			intersections.add(new Intersection(D,second));
			intersections.add(new Intersection(E,second));
			intersections.add(new Intersection(F,second));
			intersections.add(new Intersection(G,second));
			intersections.add(new Intersection(A,third));
			intersections.add(new Intersection(B,third));
			intersections.add(new Intersection(C,third));
			intersections.add(new Intersection(D,third));
			intersections.add(new Intersection(E,third));
			intersections.add(new Intersection(F,third));
			intersections.add(new Intersection(G,third));
			intersections.add(new Intersection(A,fourth));
			intersections.add(new Intersection(B,fourth));
			intersections.add(new Intersection(C,fourth));
			intersections.add(new Intersection(D,fourth));
			intersections.add(new Intersection(E,fourth));
			intersections.add(new Intersection(F,fourth));
			intersections.add(new Intersection(G,fourth));
			intersections.add(new Intersection(A,fifth));
			intersections.add(new Intersection(B,fifth));
			intersections.add(new Intersection(C,fifth));
			intersections.add(new Intersection(D,fifth));
			intersections.add(new Intersection(E,fifth));
			intersections.add(new Intersection(F,fifth));
			intersections.add(new Intersection(G,fifth));
			intersections.add(new Intersection(A,sixth));
			intersections.add(new Intersection(B,sixth));
			intersections.add(new Intersection(C,sixth));
			intersections.add(new Intersection(D,sixth));
			intersections.add(new Intersection(E,sixth));
			intersections.add(new Intersection(F,sixth));
			intersections.add(new Intersection(G,sixth));
			intersections.add(new Intersection(A,seventh));
			intersections.add(new Intersection(B,seventh));
			intersections.add(new Intersection(C,seventh));
			intersections.add(new Intersection(D,seventh));
			intersections.add(new Intersection(E,seventh));
			intersections.add(new Intersection(F,seventh));
			intersections.add(new Intersection(G,seventh));
			CityMap.setIntersections(intersections);
			deliveryTracker.addTrafficImpediment(new TrafficImpediment(intersections.get(1),LocalDateTime.now(),LocalDateTime.now().plusDays(1)));
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
