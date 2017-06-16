package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import dtDAO.DeliveryTicketDAO;
import javafx.fxml.Initializable;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
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
    private TableView<DeliveryTicket> tableDeliveryTickets;
    
    @FXML
    private TableColumn<DeliveryTicket, String> tableColumnDeliveryClient;

    @FXML
    private TableColumn<DeliveryTicket, String> tableColumnPickupClient;

    @FXML
    private TableColumn<DeliveryTicket, String> tableColumnPayingClient;

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
    DateTimeFormatter timeFormatter;
    Client selectedClient;
	LocalDateTime selectedDateTime;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		deliveryTracker = DeliveryTracker.getDeliveryTracker();
		
		formatter = DateTimeFormatter.ofPattern("MMM dd YYYY");
		timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
		
		
		updateClientsList();
		tableColumnDeliveryClient.setCellValueFactory(t ->{
			DeliveryTicket ticket = t.getValue();
			return new ReadOnlyStringWrapper(ticket.getDeliveryClient().getName());
			
		});
		tableColumnPickupClient.setCellValueFactory(t ->{
			DeliveryTicket ticket = t.getValue();
			return new ReadOnlyStringWrapper(ticket.getPickupClient().getName());
		});
		tableColumnPayingClient.setCellValueFactory(t ->{
			DeliveryTicket ticket = t.getValue();
			return new ReadOnlyStringWrapper(ticket.getPayingClient().getName());
		});
		tableColumnCourier.setCellValueFactory(t -> {
			DeliveryTicket ticket = t.getValue();
			return new ReadOnlyStringWrapper(ticket.getCourier().getName());
		});
		tableColumnOrderDate.setCellValueFactory(t -> {
			DeliveryTicket ticket = t.getValue();
			return new ReadOnlyStringWrapper(ticket.getOrderDateTime().format(formatter));
		});
		tableColumnPackageID.setCellValueFactory(t -> {
			DeliveryTicket ticket = t.getValue();
			return new ReadOnlyStringWrapper(Integer.toString(ticket.getPackageID()));
		});
		
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
            	try{
            		selectedClient = comboBoxClient.getValue();
            		selectedDateTime = LocalDateTime.of(datePickerSearchDate.getValue(), LocalDateTime.now().toLocalTime());
            		
            	}catch(Exception e)
            	{
            		
            			Alert a = new Alert(AlertType.ERROR);
            	        a.setTitle("Error");
            	        a.setHeaderText("Missing Information");
            	        a.setContentText("Please complete all required fields and try again.");
            	        a.showAndWait();
            			            	
            	}
            tickets = getTickets();
              
            ObservableList<DeliveryTicket> ticketsList = FXCollections.observableArrayList();
      		ticketsList.addAll(tickets);
          	tableDeliveryTickets.setItems(ticketsList);
            }
        });
		
		btnDeliveryTicketUpdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	
            	DeliveryTicket selectedTicket = tableDeliveryTickets.getSelectionModel().getSelectedItem();
            	
            	try {
    	    	   	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/DeliveryTicket.fxml"));
                	DeliveryTicketController controller = new DeliveryTicketController();
                	controller.setDeliveryTicket(selectedTicket);
                	
                	fxmlLoader.setController(controller);
                	
                	AnchorPane currentPane = fxmlLoader.load();
                	BorderPane border = Main.getRoot();
    	    		border.setCenter(currentPane);
    	    	} catch(IOException e){
    	    		e.printStackTrace();
    	    	}
            }
        });
	}



private void updateClientsList() {
	
	ObservableList<Client> clients = FXCollections.observableArrayList();
	clients.addAll(deliveryTracker.getClients());
	comboBoxClient.setItems(clients);
	}
private List<DeliveryTicket> getTickets() {
	
	LocalDateTime startDate = LocalDateTime.of(selectedDateTime.toLocalDate(), LocalTime.MIN);
	LocalDateTime endDate = LocalDateTime.of(selectedDateTime.toLocalDate(), LocalTime.MAX);
	List<DeliveryTicket> tickets = DeliveryTicketDAO.findDeliveryTicketsByClientByDate(selectedClient, startDate, endDate); 
	return tickets;
}

private boolean validate(){
	return true;
}
}
