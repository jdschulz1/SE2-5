package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import model.Client;
import model.DeliveryTracker;
import model.Street;

public class SelectClient implements Initializable {
 
	DeliveryTracker deliveryTracker;
	
    @FXML
    private ChoiceBox<String> choiceBoxClient;

    @FXML
    private Button buttonClientAdd;

    @FXML
    private Button buttonClientUpdate;

    @FXML
    private Button buttonClientDelete;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		deliveryTracker = DeliveryTracker.getDeliveryTracker();
//		Street street1 = new Street();
//		Client client = new Client(1, "Bob", street1, street1, "asdf", "aadsf");
//		deliveryTracker.addClient(client);
//		System.out.println(deliveryTracker.getClients().size());
		List<Client> clients = deliveryTracker.getClients();
		System.out.println(clients.size());
//		System.out.println("asdf" + deliveryTracker.getClients().size());
//		ObservableList<String> names = FXCollections.observableArrayList();
//		for(Client c: deliveryTracker.getClients()) {
//			names.add(c.getName());
//		}
//		choiceBoxClient.setItems(names);
	}
}
