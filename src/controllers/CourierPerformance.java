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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.Courier;
import model.DeliveryTicket;
import model.DeliveryTracker;
import utils.CSVWriter;

public class CourierPerformance implements Initializable {

	@FXML
    private Button btnPreview;

    @FXML
    private RadioButton radioButtonMonth;

    @FXML
    private ToggleGroup reportingTimeFrame;

    @FXML
    private RadioButton radioButtonWeek;

    @FXML
    private Label labelCourierName;

    @FXML
    private Label labelDate;

    @FXML
    private Label labelNumberofDeliveries;

    @FXML
    private Label labelOnTimeDeliveryRate;

    @FXML
    private Label labelTotalBonusEarned;

    @FXML
    private DatePicker datePickerStartDate;
    
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
    private TableColumn<DeliveryTicket, String> tableColumnBonusEarned;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnCreateReport;

    @FXML
    private Button btnPrevious;

    @FXML
    private Button btnNext;

    @FXML
    private Label labelCourierCounter;
    
    DeliveryTracker deliveryTracker;
    List<DeliveryTicket> tickets;
    DateTimeFormatter formatter;
    DateTimeFormatter timeFormatter;
    DecimalFormat df;
    LinkedList<Courier> courierList;
    Map<Courier, List<DeliveryTicket>> ticketsByCourier;
    int currentCourier;
    int numCouriers;
    LocalDateTime startDate;
    LocalDateTime endDate;

    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	deliveryTracker = DeliveryTracker.getDeliveryTracker();
		
		formatter = DateTimeFormatter.ofPattern("MMM dd YYYY");
		timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
		df = new DecimalFormat("##.##%");
    			
		btnPrevious.setDisable(true);
		btnNext.setDisable(true);
		
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
            return new ReadOnlyStringWrapper(ticket.getEstimatedDeliveryTime().format(timeFormatter));
        });
    	
    	tableColumnActualDeliveryTime.setCellValueFactory(t -> {
    		DeliveryTicket ticket = t.getValue();
            return new ReadOnlyStringWrapper(ticket.getActualDeliveryTime().format(timeFormatter));
        });
    	
    	tableColumnBonusEarned.setCellValueFactory(t -> {
    		DeliveryTicket ticket = t.getValue();
            return new ReadOnlyStringWrapper(stringFromBigDecimal(ticket.getBonusAmount()));
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
                ticketsByCourier = getTickets();
                numCouriers = ticketsByCourier.keySet().size();
                Set<Courier> set = ticketsByCourier.keySet();
                courierList = new LinkedList();
                courierList.addAll(set);
            	currentCourier = 0;
            	updatePreviewPane();
            }
        });
		
		btnPrevious.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { 
            	currentCourier -= 1;
            	updatePreviewPane();
            }
        });
		
		btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	currentCourier += 1;
            	updatePreviewPane();
            }
        });
		
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
                    ticketsByCourier = getTickets();
                    numCouriers = ticketsByCourier.keySet().size();
                    if(numCouriers > 0) {
	                    for(Courier c:ticketsByCourier.keySet()) {
	                    	String csvFile = System.getProperty("user.home") + "\\Desktop\\CourierPerformanceReport-" + c.getName() + "-" + startDate.format(formatter) + ".csv";
	                        writer = new FileWriter(csvFile);
	                        CSVWriter.writeLine(writer,Arrays.asList("Courier Performance Report"));
	
	                        List<DeliveryTicket> ticketsList = ticketsByCourier.get(c);
	                        
	                        CSVWriter.writeLine(writer,Arrays.asList("Courier Name", c.getName()));
	                        CSVWriter.writeLine(writer,Arrays.asList("Date", startDate.format(formatter) + " - " + endDate.format(formatter)));
	                        CSVWriter.writeLine(writer,Arrays.asList("Number of Deliveries", ticketsList.size() + ""));
	                        CSVWriter.writeLine(writer,Arrays.asList("On Time Delivery Rate", df.format(getOnTimeDeliveryRate(ticketsList))));
	                        CSVWriter.writeLine(writer, Arrays.asList("Total Bonus Earned", stringFromBigDecimal(totalBonuses(ticketsList))));
	                        
	                        CSVWriter.writeLine(writer,Arrays.asList(""));

	                        CSVWriter.writeLine(writer,Arrays.asList("Details"));
	                        CSVWriter.writeLine(writer, Arrays.asList("Order Date", "Pickup Client", "Delivery Client", "Estimated Delivery Time", "Actual Delivery Time", "Bonus Earned"));
					
		                    for (DeliveryTicket d : ticketsByCourier.get(c)) {
		
		                        List<String> list = new ArrayList<>();
		                        list.add(d.getOrderDateTime().format(formatter));
		                        list.add(d.getPickupClient().getName().toString());
		                        list.add(d.getDeliveryClient().getName().toString());
		                        list.add(d.getEstimatedDeliveryTime().format(timeFormatter));
		                        list.add(d.getActualDeliveryTime().format(timeFormatter));
		                        list.add(stringFromBigDecimal(d.getBonusAmount()));
		                        CSVWriter.writeLine(writer, list);
		                    }
	
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

	private Map<Courier, List<DeliveryTicket>> getTickets() {
		startDate = LocalDateTime.of(datePickerStartDate.getValue(), LocalDateTime.now().toLocalTime());
    	if(radioButtonMonth.isSelected())
    		endDate = startDate.plusMonths(1);
    	else if(radioButtonWeek.isSelected())
    		endDate = startDate.plusWeeks(1);
    	tickets = DeliveryTicketDAO.findDeliveryTicketByDateRange(startDate, endDate);
    	
    	Map<Courier, List<DeliveryTicket>> ticketsByCourier = new LinkedHashMap<Courier, List<DeliveryTicket>>();
    	
    	for(DeliveryTicket ticket:tickets) {
    		Courier courier = ticket.getCourier();
    		ticket.calculateBonus();
    		if(ticketsByCourier.containsKey(courier)) {
    			ticketsByCourier.get(courier).add(ticket);
    		} else {
    			List<DeliveryTicket> ticketList = new ArrayList<DeliveryTicket>();
    			ticketList.add(ticket);
    			ticketsByCourier.put(courier, ticketList);
    		}
    	}
    	return ticketsByCourier;
	}
	
	private void updatePreviewPane() {
		if(numCouriers == 0)
		{
			labelCourierName.setText("None");
	    	labelNumberofDeliveries.setText("None");
	    	labelOnTimeDeliveryRate.setText("None");
	    	labelDate.setText(startDate.format(formatter) + " - " + endDate.format(formatter));
	    	labelTotalBonusEarned.setText("$0.00");
	    	tableReport.setItems(null);
	    	labelCourierCounter.setText("Client 0 of 0");
	    	
	    	btnPrevious.setDisable(true);
	    	btnNext.setDisable(true);
	    	
		}
		else {
			ObservableList<DeliveryTicket> ticketsList = FXCollections.observableArrayList();
			Courier courier = courierList.get(currentCourier);
			ticketsList.addAll(ticketsByCourier.get(courier));

			labelCourierName.setText(courier.getName());
	    	labelDate.setText(startDate.format(formatter) + " - " + endDate.format(formatter));
	    	labelNumberofDeliveries.setText(ticketsList.size() + "");
	    	labelOnTimeDeliveryRate.setText(df.format(getOnTimeDeliveryRate(ticketsList)));
	    	labelTotalBonusEarned.setText(stringFromBigDecimal(totalBonuses(ticketsList)));
	    	tableReport.setItems(ticketsList);
	    	labelCourierCounter.setText("Courier " + (currentCourier + 1) + " of " + numCouriers);
	    	
	    	if(currentCourier == 0)
	    		btnPrevious.setDisable(true);
	    	else if(currentCourier > 0 && numCouriers > 0)
	    		btnPrevious.setDisable(false);
	    	
	    	if(currentCourier < (numCouriers-1))
	    		btnNext.setDisable(false);
	    	else 
	    		btnNext.setDisable(true);
		}
	}
	
	private double getOnTimeDeliveryRate(List<DeliveryTicket> ticketsList) {
		int numOnTimeDeliveries = 0;
		for(DeliveryTicket t:ticketsList) {
    		if(t.isOnTime())
    			numOnTimeDeliveries++;
    	}
		return (double)numOnTimeDeliveries/(double)ticketsList.size();
    }
	
	private BigDecimal totalBonuses(List<DeliveryTicket> ticketsList) {
		BigDecimal total = new BigDecimal(0);
    	for(DeliveryTicket t:ticketsList) {
    		total = total.add(t.getBonusAmount());
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

