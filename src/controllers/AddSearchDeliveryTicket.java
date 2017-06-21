package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import dtDAO.ClientDAO;
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
import javafx.scene.control.ButtonType;
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
    private Button btnDeliveryTicketDelete;
    

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
			if(ticket.getCourier() != null)
    			return new ReadOnlyStringWrapper(ticket.getCourier().getName());
    		else 
    			return new ReadOnlyStringWrapper("");		
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
            	
            searchTickets();
            }
        });
		
		btnDeliveryTicketUpdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	
            	DeliveryTicket selectedTicket = tableDeliveryTickets.getSelectionModel().getSelectedItem();
            	if (selectedTicket == null){
            		Alert a = new Alert(AlertType.ERROR);
        	        a.setTitle("Error");
        	        a.setHeaderText("Select Ticket");
        	        a.setContentText("Please select a ticket.");
        	        a.showAndWait();
            	}else{
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
            }
        });
		
		btnDeliveryTicketDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	DeliveryTicket selectedTicket = tableDeliveryTickets.getSelectionModel().getSelectedItem();
            	if (selectedTicket == null){
            		Alert a = new Alert(AlertType.ERROR);
        	        a.setTitle("Error");
        	        a.setHeaderText("Select Ticket");
        	        a.setContentText("Please select a ticket.");
        	        a.showAndWait();
            	} else if(selectedTicket.getActualDepartureTime() != null) {
            		Alert a = new Alert(AlertType.ERROR);
        	        a.setTitle("Error");
        	        a.setHeaderText("Cannot Delete Ticket ");
        	        a.setContentText("Courier has already started delivery - cannot delete ticket.");
        	        a.showAndWait();
            	}else{
            		 Alert a = new Alert(AlertType.CONFIRMATION);
	        	        a.setTitle("Confirm Deletion");
	        	        a.setContentText("Are you sure you want to delete selected ticket?");
		                a.showAndWait()
		                	.filter(response -> response == ButtonType.OK)
		                	.ifPresent(response -> DeliveryTicketDAO.removeDeliveryTicket(selectedTicket));
		            //refresh table
		             searchTickets();    
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
	List<DeliveryTicket> tickets = null;
	if (selectedClient == null && selectedDateTime == null){
		//find all delivery tickets
		tickets = DeliveryTicketDAO.listDeliveryTicket();
	}else if(selectedClient != null && selectedDateTime == null  ){
		//search by client
		tickets = DeliveryTicketDAO.findDeliveryTicketsByClient(selectedClient);
		
	}else if(selectedClient == null && selectedDateTime != null){
		//search by date
		LocalDateTime startDate = LocalDateTime.of(selectedDateTime.toLocalDate(), LocalTime.MIN);
		LocalDateTime endDate = LocalDateTime.of(selectedDateTime.toLocalDate(), LocalTime.MAX);
		tickets = DeliveryTicketDAO.findDeliveryTicketByDateRange(startDate, endDate);
	}else if(selectedClient != null && selectedDateTime != null){
		//search by client by date
		LocalDateTime startDate = LocalDateTime.of(selectedDateTime.toLocalDate(), LocalTime.MIN);
		LocalDateTime endDate = LocalDateTime.of(selectedDateTime.toLocalDate(), LocalTime.MAX);
		tickets = DeliveryTicketDAO.findDeliveryTicketsByClientByDate(selectedClient, startDate, endDate);
	}
	
	 
	return tickets;
}
private void searchTickets(){
	//get input
    selectedClient = comboBoxClient.getValue();
    if (datePickerSearchDate.getValue() != null){
    	selectedDateTime = LocalDateTime.of(datePickerSearchDate.getValue(), LocalDateTime.now().toLocalTime());
    }
        
    //display tickets in table            	
    tickets = getTickets();
    ObservableList<DeliveryTicket> ticketsList = FXCollections.observableArrayList();
		ticketsList.addAll(tickets);
  	tableDeliveryTickets.setItems(ticketsList);
}
private boolean validate(){
	if(comboBoxClient.getValue() == null)return false;
	if(datePickerSearchDate.getValue() == null)return false;
	return true;
}
}
