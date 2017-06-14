package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import model.Client;
import model.DeliveryTicket;
import model.DeliveryTracker;
import dtDAO.ClientDAO;
import dtDAO.DeliveryTicketDAO;

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
	            	if(c == null) {
	            		Alert a = new Alert(AlertType.ERROR);
	        	        a.setTitle("Error");
	        	        a.setHeaderText("No Client Selected");
	        	        a.setContentText("Please select a client and try again.");
	        	        a.showAndWait();
	            	}
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
	                List<DeliveryTicket> ticketsForClient = DeliveryTicketDAO.findDeliveryTicketsByClient(c);
	                if(ticketsForClient.size() > 0)
	                {
	                	Alert a = new Alert(AlertType.ERROR);
	        	        a.setContentText("Cannot delete " + c.getName() + " - they have existing delivery tickets.");
		                a.showAndWait();
	                } else {
		                Alert a = new Alert(AlertType.CONFIRMATION);
	        	        a.setTitle("Confirm Deletion");
	        	        a.setContentText("Are you sure you want to delete " + c.getName() + "?");
		                a.showAndWait()
		                	.filter(response -> response == ButtonType.OK)
		                	.ifPresent(response -> ClientDAO.removeClient(c));
		                updateClientsList();
	                }
	            }
	        });
	}
	
	private void updateClientsList() {
		ObservableList<Client> clients = FXCollections.observableArrayList();
		clients.addAll(deliveryTracker.getClients());
		comboBoxClient.setItems(clients);
		if(clients.size() > 0)
			comboBoxClient.setValue(clients.get(0));
	}
}
