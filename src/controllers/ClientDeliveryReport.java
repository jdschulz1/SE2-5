package controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.CityMap;
import model.Client;
import model.DeliveryTicket;
import model.DeliveryTracker;
import utils.CSVWriter;

public class ClientDeliveryReport implements Initializable {

	@FXML
    private Button btnPreview;

    @FXML
    private ComboBox<?> comboBoxClient;
    
    @FXML
    private DatePicker datePickerStartDate;

    @FXML
    private RadioButton radioButtonMonth;

    @FXML
    private RadioButton radioButtonWeek;

    @FXML
    private Label labelClient;

    @FXML
    private Label labelDate;

    @FXML
    private Label labelNumberofDeliveries;

    @FXML
    private Label labelOnTimeDeliveries;

    @FXML
    private TableView<DeliveryTicket> tableReport;

    @FXML
    private TableColumn<DeliveryTicket, String> tableColumnOrderDate;

    @FXML
    private TableColumn<DeliveryTicket, String> tableColumnPickupClient;

    @FXML
    private TableColumn<DeliveryTicket, String> tableColumnDeliveryClient;

    @FXML
    private TableColumn<DeliveryTicket, String> tableColumnEstimatedDeliveryTime;

    @FXML
    private TableColumn<DeliveryTicket, String> tableColumnActualDeliveryTime;

    @FXML
    private Button btnCreateReport;

    @FXML
    private Button btnCancel;

    DeliveryTracker deliveryTracker;
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
    			
//		btnPrevious.setDisable(true);
//		btnNext.setDisable(true);
		
		tableColumnOrderDate.setCellValueFactory(t -> {
    		DeliveryTicket ticket = t.getValue();
            return new ReadOnlyStringWrapper(ticket.getOrderDateTime().format(formatter));
        });
    	
    	tableColumnPickupClient.setCellValueFactory(t -> {
    		DeliveryTicket ticket = t.getValue();
            return new ReadOnlyStringWrapper(ticket.getPickupClient().getName());
        });
    	
    	tableColumnDeliveryClient.setCellValueFactory(t -> {
    		DeliveryTicket ticket = t.getValue();
            return new ReadOnlyStringWrapper(ticket.getDeliveryClient().getName());
        });
    	
    	tableColumnEstimatedDeliveryTime.setCellValueFactory(t -> {
    		DeliveryTicket ticket = t.getValue();
            return new ReadOnlyStringWrapper(ticket.getEstimatedDeliveryTime().format(formatter));
        });
    	
    	tableColumnActualDeliveryTime.setCellValueFactory(t -> {
    		DeliveryTicket ticket = t.getValue();
            return new ReadOnlyStringWrapper(ticket.getActualDeliveryTime().format(formatter));
        });
		
		btnPreview.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
            		LocalDateTime.of(datePickerStartDate.getValue(), LocalDateTime.now().toLocalTime());
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
		
//		btnPrevious.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) { 
//            	currentClient -= 1;
//            	updatePreviewPane();
//            }
//        });
//		
//		btnNext.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//            	currentClient += 1;
//            	updatePreviewPane();
//            }
//        });
		
		btnCreateReport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
            		LocalDateTime.of(datePickerStartDate.getValue(), LocalDateTime.now().toLocalTime());
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
	                    	String csvFile = System.getProperty("user.home") + "\\Desktop\\CSVTest-" + c.getName() + "-" + startDate.format(formatter) + ".csv";
	                        writer = new FileWriter(csvFile);
	                        CSVWriter.writeLine(writer,Arrays.asList("Invoice"));
	
	                        CSVWriter.writeLine(writer,Arrays.asList("Client Name", c.getName()));
	                        CSVWriter.writeLine(writer,Arrays.asList("Invoice Date", startDate.format(formatter) + " - " + endDate.format(formatter)));
	
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
		startDate = LocalDateTime.of(datePickerStartDate.getValue(), LocalDateTime.now().toLocalTime());
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
			List <DeliveryTicket> ticketsList = ticketsByClient.get(clientList.get(currentClient));
	    	labelNumberofDeliveries.setText(ticketsByClient.get(ticketsList.size()) + " ");
	    	labelOnTimeDeliveries.setText(getNumOnTimeDeliveries(ticketsList) + " ");
	    	labelDate.setText(startDate.format(formatter) + " - " + endDate.format(formatter));
	    	tableReport.setItems(null);
//	    	labelClientCounter.setText("Client 0 of 0");
//	    	
//	    	btnPrevious.setDisable(true);
//	    	btnNext.setDisable(true);
	    	
		}
		else {
			ObservableList<DeliveryTicket> ticketsList = FXCollections.observableArrayList();
	    	Client client = clientList.get(currentClient);
			ticketsList.addAll(ticketsByClient.get(client));

			labelClient.setText(client.getName());
	    	labelDate.setText(startDate.format(formatter) + " - " + endDate.format(formatter));
	    	tableReport.setItems(ticketsList);
//	    	labelClientCounter.setText("Client " + (currentClient + 1) + " of " + numClients);
//	    	
//	    	if(currentClient == 0)
//	    		btnPrevious.setDisable(true);
//	    	else if(currentClient > 0 && numClients > 0)
//	    		btnPrevious.setDisable(false);
//	    	
//	    	if(currentClient < (numClients-1))
//	    		btnNext.setDisable(false);
//	    	else 
//	    		btnNext.setDisable(true);
		}
	}
	
	private int getNumOnTimeDeliveries(List<DeliveryTicket> ticketsList) {
		int numOnTimeDeliveries = 0;
		for(DeliveryTicket t:ticketsList) {
    		if(t.isOnTime())
    			numOnTimeDeliveries++;
    	}
    	return numOnTimeDeliveries;
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
