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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
    private Button btnCancel;
	
    List<DeliveryTicket> tickets;
    DateTimeFormatter formatter;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		deliveryTracker = DeliveryTracker.getDeliveryTracker();
		
		formatter = DateTimeFormatter.ofPattern("MMM dd YYYY");
    			
		btnPreview.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	System.out.println("Preview");
            	ObservableList<DeliveryTicket> ticketsList = FXCollections.observableArrayList();
            	ticketsList.addAll(getTickets());
            	
            	tableColumnInvoiceOrderDate.setCellValueFactory(t -> {
            		DeliveryTicket ticket = t.getValue();
                    return new ReadOnlyStringWrapper(ticket.getOrderDateTime().format(formatter));
                });
            	
            	tableColumnInvoicePickupClient.setCellValueFactory(t -> {
            		DeliveryTicket ticket = t.getValue();
            		//TODO: use real client
                    return new ReadOnlyStringWrapper("Bob");//ticket.getPickupClient().getName());
                });
            	
            	tableColumnInvoiceDeliveryClient.setCellValueFactory(t -> {
            		DeliveryTicket ticket = t.getValue();
            		//TODO: use real client
                    return new ReadOnlyStringWrapper("Not Bob");//ticket.getDeliveryClient().getName());
                });
            	
            	tableColumnInvoicePrice.setCellValueFactory(t -> {
            		DeliveryTicket ticket = t.getValue();
                    return new ReadOnlyStringWrapper(stringFromBigDecimal(ticket.getPrice()));
                });
            	
            	tableInvoices.setItems(ticketsList);
            }
        });
		btnCreateInvoice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	FileWriter writer;
                try {
                	String csvFile = "C:\\Users\\jenni\\mars_workspace\\CSVTest.csv";
                    writer = new FileWriter(csvFile);
                    CSVWriter.writeLine(writer, Arrays.asList("Order Date", "Pickup Client", "Delivery Client", "Price"));
				
                    for (DeliveryTicket d : getTickets()) {

                        List<String> list = new ArrayList<>();
                        list.add(d.getOrderDateTime().format(formatter));
                        //TODO: use real clients
                        list.add("Bob");//d.getPickupClient().getName().toString());
                        list.add("Not Bob");//d.getDeliveryClient().getName().toString());
                        list.add(stringFromBigDecimal(d.getPrice()));
                        CSVWriter.writeLine(writer, list);
    					
                    }
                    writer.flush();
					writer.close();
                
                } catch (IOException e) {
					// TODO Auto-generated catch block
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
	
	private List<DeliveryTicket> getTickets() {
		LocalDateTime startDate = LocalDateTime.of(datePickerStartDate.getValue(), LocalDateTime.now().toLocalTime());
    	LocalDateTime endDate = startDate.plusWeeks(1);
    	tickets = DeliveryTicketDAO.findDeliveryTicketByDateRange(startDate, endDate);
    	System.out.println(tickets.size());
    	return tickets;
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
