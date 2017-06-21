package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import dtDAO.DeliveryTicketDAO;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.DeliveryTicket;

public class Home implements javafx.fxml.Initializable {

	@FXML
    private TableView<DeliveryTicket> tableDeliveries;

    @FXML
    private TableColumn<DeliveryTicket, String> tableColumnDepartureTime;

    @FXML
    private TableColumn<DeliveryTicket, String> tableColumnPickupClient;

    @FXML
    private TableColumn<DeliveryTicket, String> tableColumnRequestedPickupTime;

    @FXML
    private TableColumn<DeliveryTicket, String> tableColumnDeliveryClient;

    @FXML
    private TableColumn<DeliveryTicket, String> tableColumnCourier;

    @FXML
    private Button buttonViewTicket;

    @FXML
    private Button buttonAssignCourier;
	
	List<DeliveryTicket> tickets;

    DateTimeFormatter formatter;
    DateTimeFormatter timeFormatter;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		tickets = getTickets();
		
		formatter = DateTimeFormatter.ofPattern("MMM dd YYYY");
		timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
		
		tableColumnDepartureTime.setCellValueFactory(t -> {
    		DeliveryTicket ticket = t.getValue();
            return new ReadOnlyStringWrapper(ticket.getCalculatedDepartureTime().format(timeFormatter));
        });
    	
    	tableColumnPickupClient.setCellValueFactory(t -> {
    		DeliveryTicket ticket = t.getValue();
            return new ReadOnlyStringWrapper(ticket.getPickupClient().getName());
        });
    	
    	tableColumnDeliveryClient.setCellValueFactory(t -> {
    		DeliveryTicket ticket = t.getValue();
            return new ReadOnlyStringWrapper(ticket.getDeliveryClient().getName());
        });
    	
    	tableColumnRequestedPickupTime.setCellValueFactory(t -> {
    		DeliveryTicket ticket = t.getValue();
            return new ReadOnlyStringWrapper(ticket.getRequestedPickupTime().format(timeFormatter));
        });
    	
    	tableColumnCourier.setCellValueFactory(t -> {
    		DeliveryTicket ticket = t.getValue();
            return new ReadOnlyStringWrapper(ticket.getCourier().getName());
        });
    	
    	buttonViewTicket.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { 
            	try {
        			DeliveryTicket selectedTicket = tableDeliveries.getSelectionModel().getSelectedItem();

            		if(selectedTicket != null)
            		{
	    	    	   	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/DeliveryTicket.fxml"));
	                	DeliveryTicketController controller = new DeliveryTicketController();
	                	controller.setDeliveryTicket(selectedTicket);
	                	fxmlLoader.setController(controller);
	                	AnchorPane currentPane = fxmlLoader.load();
	                	BorderPane border = Main.getRoot();
	    	    		border.setCenter(currentPane);
            		}
            		else {
            			Alert a = new Alert(AlertType.ERROR);
            	        a.setTitle("Error");
            	        a.setHeaderText("No Ticket Selected");
            	        a.setContentText("Please select a ticket and try again.");
            	        a.showAndWait();
            	        return;
            		}
    	    	} catch(IOException e){
    	    		e.printStackTrace();
    	    	}
            }
        });
		
		buttonAssignCourier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
        			DeliveryTicket selectedTicket = tableDeliveries.getSelectionModel().getSelectedItem();

            		if(selectedTicket != null)
            		{
	    	    	   	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/AssignCourier.fxml"));
	                	AssignCourier controller = new AssignCourier();
	                	controller.setDeliveryTicket(selectedTicket);
	                	fxmlLoader.setController(controller);
	                	AnchorPane currentPane = fxmlLoader.load();
	                	BorderPane border = Main.getRoot();
	    	    		border.setCenter(currentPane);
            		}
            		else {
            			Alert a = new Alert(AlertType.ERROR);
            	        a.setTitle("Error");
            	        a.setHeaderText("No Ticket Selected");
            	        a.setContentText("Please select a ticket and try again.");
            	        a.showAndWait();
            	        return;
            		}
    	    	} catch(IOException e){
    	    		e.printStackTrace();
    	    	}
            }
        });
    	
    	ObservableList<DeliveryTicket> ticketsList = FXCollections.observableArrayList();
		ticketsList.addAll(tickets);
    	tableDeliveries.setItems(ticketsList);
	}
	
	private List<DeliveryTicket> getTickets() {
		//TODO: Do we want more specific criteria than "all of todays tickets?" or an ordering?
		LocalDateTime startDate = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MIN);
		LocalDateTime endDate = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MAX);
    	List<DeliveryTicket> tickets = DeliveryTicketDAO.findDeliveryTicketByDateRange(startDate, endDate);
    	return tickets;
	}
}
