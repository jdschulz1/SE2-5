package controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import dtDAO.DeliveryTicketDAO;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.CityMap;
import model.Client;
import model.DeliveryTicket;
import model.DeliveryTracker;
import utils.CSVWriter;

public class Invoice implements Initializable {

	DeliveryTracker deliveryTracker;
	
	@FXML
    private Button btnPreview;

    @FXML
    private DatePicker datePickerStartDate;

    @FXML
    private Label labelClient;

    @FXML
    private Label labelDate;

    @FXML
    private Label labelAddress;
    
    @FXML
    private TableView<DeliveryTicket> tableInvoices;
    
    @FXML
    private TableColumn<DeliveryTicket, String> tableColumnInvoiceOrderDate;

    @FXML
    private TableColumn<DeliveryTicket, String> tableColumnInvoicePickupClient;

    @FXML
    private TableColumn<DeliveryTicket, String> tableColumnInvoiceDeliveryClient;

    @FXML
    private TableColumn<DeliveryTicket, String> tableColumnInvoicePrice;

    @FXML
    private Button btnCreateInvoice;
    
    @FXML
    private Button btnPrevious;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnCancel;

    @FXML
    private Label labelClientCounter;
    
    @FXML
    private Label labelTotal;

	
    List<DeliveryTicket> tickets;
    DateTimeFormatter formatter;
    LinkedList<Client> clientList;
    Map<Client, List<DeliveryTicket>> ticketsByClient;
    int currentClient;
    int numClients;
    LocalDateTime startDate;
    LocalDateTime endDate;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		deliveryTracker = DeliveryTracker.getDeliveryTracker();
		
		formatter = DateTimeFormatter.ofPattern("MMM dd YYYY");
    			
		btnPrevious.setDisable(true);
		btnNext.setDisable(true);
		
		tableColumnInvoiceOrderDate.setCellValueFactory(t -> {
    		DeliveryTicket ticket = t.getValue();
            return new ReadOnlyStringWrapper(ticket.getOrderDateTime().format(formatter));
        });
    	
    	tableColumnInvoicePickupClient.setCellValueFactory(t -> {
    		DeliveryTicket ticket = t.getValue();
            return new ReadOnlyStringWrapper(ticket.getPickupClient().getName());
        });
    	
    	tableColumnInvoiceDeliveryClient.setCellValueFactory(t -> {
    		DeliveryTicket ticket = t.getValue();
            return new ReadOnlyStringWrapper(ticket.getDeliveryClient().getName());
        });
    	
    	tableColumnInvoicePrice.setCellValueFactory(t -> {
    		DeliveryTicket ticket = t.getValue();
            return new ReadOnlyStringWrapper(stringFromBigDecimal(ticket.getPrice()));
        });
		
		btnPreview.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
            		LocalDateTime.of(datePickerStartDate.getValue(), LocalTime.MIN);
            	} catch(Exception ex) {
            		Alert a = new Alert(AlertType.ERROR);
        	        a.setTitle("Error");
        	        a.setHeaderText("No Date Selected");
        	        a.setContentText("Please select a starting date and try again.");
        	        a.showAndWait();
        	        return;
            	}
                ticketsByClient = getTickets();
                numClients = ticketsByClient.keySet().size();
                Set<Client> set = ticketsByClient.keySet();
                clientList = new LinkedList();
                clientList.addAll(set);
            	currentClient = 0;
            	updatePreviewPane();
            }
        });
		
		btnPrevious.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { 
            	currentClient -= 1;
            	updatePreviewPane();
            }
        });
		
		btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	currentClient += 1;
            	updatePreviewPane();
            }
        });
		
		btnCreateInvoice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
            		LocalDateTime.of(datePickerStartDate.getValue(), LocalTime.MIN);
            	} catch(Exception ex) {
            		Alert a = new Alert(AlertType.ERROR);
        	        a.setTitle("Error");
        	        a.setHeaderText("No Date Selected");
        	        a.setContentText("Please select a starting date and try again.");
        	        a.showAndWait();
        	        return;
            	}
            	
            	FileWriter writer;
                try {
                    ticketsByClient = getTickets();
                    numClients = ticketsByClient.keySet().size();
                    if(numClients > 0) {
	                    for(Client c:ticketsByClient.keySet()) {
	                    	String csvFile = System.getProperty("user.home") + "\\Desktop\\Invoice-" + c.getName() + "-" + startDate.format(formatter) + ".csv";
	                        writer = new FileWriter(csvFile);
	                        CSVWriter.writeLine(writer,Arrays.asList("Invoice"));
	
	                        CSVWriter.writeLine(writer, Arrays.asList("Company Name", deliveryTracker.getCompanyName()));
	                        CSVWriter.writeLine(writer, Arrays.asList("Company Address", deliveryTracker.getCompanyLocation().getName()));

	                        CSVWriter.writeLine(writer,Arrays.asList("Client Name", c.getName()));
	                        CSVWriter.writeLine(writer,Arrays.asList("Invoice Date", startDate.format(formatter) + " - " + endDate.format(formatter)));
	                        
	                        CSVWriter.writeLine(writer,Arrays.asList(""));

	                        CSVWriter.writeLine(writer,Arrays.asList("Details"));
	                        CSVWriter.writeLine(writer, Arrays.asList("Order Date", "Pickup Client", "Delivery Client", "Price"));
					
		                    for (DeliveryTicket d : ticketsByClient.get(c)) {
		
		                        List<String> list = new ArrayList<>();
		                        list.add(d.getOrderDateTime().format(formatter));
		                        list.add(d.getPickupClient().getName().toString());
		                        list.add(d.getDeliveryClient().getName().toString());
		                        list.add(stringFromBigDecimal(d.getPrice()));
		                        CSVWriter.writeLine(writer, list);
		    					
		                    }
	                        CSVWriter.writeLine(writer, Arrays.asList("Total", "", "",stringFromBigDecimal(totalTickets(ticketsByClient.get(c)))));
	
		                    writer.flush();
							writer.close();
	                    }
	                    Alert a = new Alert(AlertType.INFORMATION);
	        	        a.setTitle("File Saved");
	        	        a.setContentText("Your invoices have been saved to " + System.getProperty("user.home") + "\\Desktop");
		                a.showAndWait();
                    }
                    else {
                    	Alert a = new Alert(AlertType.INFORMATION);
	        	        a.setTitle("No Invoices to Generate");
	        	        a.setContentText("There are no invoices to generate for the selected date range. Please select a new starting date and try again.");
		                a.showAndWait();
                    }
                
                } catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
		
		btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
    	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
    	    		BorderPane border = Main.getRoot();
    	    		border.setCenter(currentPane);
    	    	} catch(IOException e){
    	    		e.printStackTrace();
    	    	}
            }
        });
	}
	
	private Map<Client, List<DeliveryTicket>> getTickets() {
		startDate = LocalDateTime.of(datePickerStartDate.getValue(), LocalTime.MIN);
    	endDate = startDate.plusWeeks(1);
    	tickets = DeliveryTicketDAO.findDeliveryTicketByDateRange(startDate, endDate);
    	
    	Map<Client, List<DeliveryTicket>> ticketsByClient = new LinkedHashMap<Client, List<DeliveryTicket>>();
    	
    	for(DeliveryTicket ticket:tickets) {
    		Client client = ticket.getPayingClient();
    		if(ticketsByClient.containsKey(client)) {
    			ticketsByClient.get(client).add(ticket);
    		} else {
    			List<DeliveryTicket> ticketList = new ArrayList<DeliveryTicket>();
    			ticketList.add(ticket);
    			ticketsByClient.put(client, ticketList);
    		}
    	}
    	return ticketsByClient;
	}
	
	private void updatePreviewPane() {
		if(numClients == 0)
		{
			labelClient.setText("None");
	    	labelAddress.setText("None");
	    	labelDate.setText(startDate.format(formatter) + " - " + endDate.format(formatter));
	    	tableInvoices.setItems(null);
	    	labelTotal.setText("$0.00");
	    	labelClientCounter.setText("Client 0 of 0");
	    	
	    	btnPrevious.setDisable(true);
	    	btnNext.setDisable(true);
	    	
		}
		else {
			ObservableList<DeliveryTicket> ticketsList = FXCollections.observableArrayList();
	    	Client client = clientList.get(currentClient);
			ticketsList.addAll(ticketsByClient.get(client));
	    	
			System.out.println(client);
			
	    	labelClient.setText(client.getName());
	    	labelAddress.setText(client.getLocation().getName());
	    	labelDate.setText(startDate.format(formatter) + " - " + endDate.format(formatter));
	    	tableInvoices.setItems(ticketsList);
	    	labelTotal.setText(stringFromBigDecimal(totalTickets(ticketsByClient.get(client))));
	    	labelClientCounter.setText("Client " + (currentClient + 1) + " of " + numClients);
	    	
	    	if(currentClient == 0)
	    		btnPrevious.setDisable(true);
	    	else if(currentClient > 0 && numClients > 0)
	    		btnPrevious.setDisable(false);
	    	
	    	if(currentClient < (numClients-1))
	    		btnNext.setDisable(false);
	    	else 
	    		btnNext.setDisable(true);
		}
	}
	
	private BigDecimal totalTickets(List<DeliveryTicket> ticketsList) {
		BigDecimal total = new BigDecimal(0);
    	for(DeliveryTicket t:ticketsList) {
    		total = total.add(t.getPrice());
    	}
    	return total;
	}
	
	private String stringFromBigDecimal(BigDecimal input) {
		DecimalFormat d = new DecimalFormat("'$'0.00");
		try {
			String s = d.format(input.doubleValue());
			return s;
		} catch (Exception e) {
			return null;
		}
	}
}
