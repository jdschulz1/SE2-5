package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import model.Client;
import model.DeliveryTicket;
import model.DeliveryTracker;

public class AddSearchDeliveryTicket implements Initializable {

    @FXML
    private Button buttonNewDeliveryTicket;
    
    @FXML
    private ComboBox<Client> comboBoxClient;
    
    @FXML
    private DatePicker datePickerSearchDate;

    @FXML
    private TableColumn<DeliveryTicket, String> tableColumnClient;

    @FXML
    private TableColumn<DeliveryTicket, String> tableColumnCourier;

    @FXML
    private TableColumn<DeliveryTicket, String> tableColumnOrderDate;

    @FXML
    private TableColumn<DeliveryTicket, String> tableColumnPackageID;
   
    @FXML
    private Button btnDeliveryTicketUpdate;

    @FXML
    private Button btnDeliveryTicketCancel;
    

    @FXML
    private Button buttonSearchDeliveryTickets;
    
    DeliveryTracker deliveryTracker;
    List<DeliveryTicket> tickets;
    DateTimeFormatter formatter;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
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
			
		buttonNewDeliveryTicket.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              
                try {
    	    	   	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/DeliveryTicket.fxml"));
                	DeliveryTicketController controller = new DeliveryTicketController();
                	fxmlLoader.setController(controller);
                	AnchorPane currentPane = fxmlLoader.load();
                	BorderPane border = Main.getRoot();
    	    		border.setCenter(currentPane);
    	    	} catch(IOException e){
    	    		e.printStackTrace();
    	    	}
            }
        });
		
		buttonSearchDeliveryTickets.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              
            }
        });
		
	}



private void updateClientsList() {
	
	ObservableList<Client> clients = FXCollections.observableArrayList();
	clients.addAll(deliveryTracker.getClients());
	comboBoxClient.setItems(clients);
	}
	
}
