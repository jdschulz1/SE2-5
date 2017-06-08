package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import model.Client;
import model.DeliveryTracker;

public class SelectClient implements Initializable {
 
	DeliveryTracker deliveryTracker;
	
    @FXML
    private ComboBox<Client> comboBoxClient;

    @FXML
    private Button buttonClientAdd;

    @FXML
    private Button buttonClientUpdate;

    @FXML
    private Button buttonClientDelete;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		deliveryTracker = DeliveryTracker.getDeliveryTracker();
		updateClientsList();
		comboBoxClient.setConverter(
		            new StringConverter<Client>() {
		                @Override
		                public Client fromString(String s) {
	                    	//TODO: get client by name
	                        return null;
		                }

						@Override
						public String toString(Client object) {
							if (object == null) {
		                        return "";
		                    } else {
		                        return object.getName();
		                    }
						}
		            });
		
			buttonClientAdd.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Add");
	                try {
//	    	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/EditClient.fxml"));
	    	    		
	                	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/EditClient.fxml"));
	                	EditClient controller = new EditClient();
	                	fxmlLoader.setController(controller);
	                	AnchorPane currentPane = fxmlLoader.load();
	                	BorderPane border = Main.getRoot();
	    	    		border.setCenter(currentPane);
	    	    	} catch(IOException e){
	    	    		e.printStackTrace();
	    	    	}
	            }
	        });
			
			buttonClientUpdate.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	Client c = comboBoxClient.getValue();
	                System.out.println("Update " + c.getName());
	                
	                try {
	                	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/EditClient.fxml"));
	                	EditClient controller = new EditClient();
	                	controller.setClient(c);
	                	fxmlLoader.setController(controller);
	                	AnchorPane currentPane = fxmlLoader.load();
	    	    		BorderPane border = Main.getRoot();
	    	    		border.setCenter(currentPane);
	    	    	} catch(IOException e){
	    	    		e.printStackTrace();
	    	    	}
	            }
	        });
			
			buttonClientDelete.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	Client c = comboBoxClient.getValue();
	                System.out.println("Delete " + c.getName());
	                deliveryTracker.deleteClient(c);
	                updateClientsList();
	            }
	        });
	}
	
	private void updateClientsList() {
		ObservableList<Client> clients = FXCollections.observableArrayList();
		clients.addAll(deliveryTracker.getClients());
		comboBoxClient.setItems(clients);
	}
}
