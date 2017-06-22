package controllers;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ResourceBundle;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import com.sun.corba.se.spi.orbutil.proxy.DelegateInvocationHandlerImpl;
import com.sun.org.apache.xpath.internal.operations.Bool;

import dtDAO.DeliveryTicketDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import model.Client;
import model.Courier;
import model.DeliveryTicket;
import model.DeliveryTracker;
import model.User;

public class DeliveryTicketController implements javafx.fxml.Initializable {

	DeliveryTracker deliveryTracker;
    @FXML
    private ComboBox<User> comboBoxOrderTaker;

    @FXML
    private ComboBox<Client> comboBoxPickupClient;

    @FXML
    private ComboBox<Client> comboBoxDeliveryClient;

    @FXML
    private ComboBox<Client> comboBoxPayingClient;

    @FXML
    private Label labelCourierName;

    @FXML
    private DatePicker dateTimePickerOrderDate;

    @FXML
    private TextArea textAreaSpecialRemarks;

    @FXML
    private Spinner<Integer> spinnerRequestedPickupHour;

    @FXML
    private Spinner<Integer> spinnerRequestedPickupMinute;

    @FXML
    private ComboBox<String> comboBoxRequestedPickupAMPM;

    @FXML
    private Label labelEstimatedDeliveryTime;

    @FXML
    private Label labelPackageID;

    @FXML
    private Label labelCalculatedDepartureTime;

    @FXML
    private Label labelTotalDistance;

    @FXML
    private Label labelPrice;

    @FXML
    private CheckBox chkBoxDeliveryConfirmed;

    @FXML
    private Spinner<Integer> spinnerActualPickupTimeHour;

    @FXML
    private Spinner<Integer> spinnerActualPickupTimeMinute;

    @FXML
    private ComboBox<String> comboBoxActualPickupTimeAMPM;

    @FXML
    private Spinner<Integer> spinnerActualDeliveryTimeHour;
    
    @FXML
    private Spinner<Integer> spinnerActualDeliveryTimeMinute;

    @FXML
    private ComboBox<String> comboBoxActualDeliveryTimeAMPM;

    @FXML
    private Spinner<Integer> spinnerActualDepartureTimeHour;

    @FXML
    private Spinner<Integer> spinnerActualDepartureTimeMinute;

    @FXML
    private ComboBox<String> comboBoxActualDepartureTimeAMPM;

    @FXML
    private Spinner<Integer> spinnerActualReturnTimeHour;

    @FXML
    private Spinner<Integer> spinnerActualReturnTimeMinute;

    @FXML
    private ComboBox<String> comboBoxActualReturnTimeAMPM;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonGenerateQuote;
    
    @FXML
    private Button buttonGenerateCourierPackage;
    

    @FXML
    private CheckBox checkBoxActualDepartureTime;

    @FXML
    private CheckBox checkBoxActualPickupTime;

    @FXML
    private CheckBox checkBoxActualDeliveryTime;

    @FXML
    private CheckBox checkBoxActualReturnTime;
    
    DeliveryTicket deliveryTicket;
    DateTimeFormatter formatter;
    DateTimeFormatter timeFormatter;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		deliveryTracker = DeliveryTracker.getDeliveryTracker();
		formatter = DateTimeFormatter.ofPattern("MMM dd YYYY");
		timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
	
		if(deliveryTicket != null){
			comboBoxDeliveryClient.setValue(deliveryTicket.getDeliveryClient());
			comboBoxPickupClient.setValue(deliveryTicket.getPickupClient());
			comboBoxPayingClient.setValue(deliveryTicket.getPayingClient());
			if(deliveryTicket.getCourier() != null) {
				labelCourierName.setText(deliveryTicket.getCourier().getName());
			}
			comboBoxOrderTaker.setValue(deliveryTicket.getOrderTaker());
			dateTimePickerOrderDate.setValue(deliveryTicket.getOrderDateTime().toLocalDate());
			textAreaSpecialRemarks.setText(deliveryTicket.getSpecialRemarks());
			if (deliveryTicket.getCalculatedDepartureTime() != null){
				labelCalculatedDepartureTime.setText(deliveryTicket.getCalculatedDepartureTime().format(timeFormatter));
			}
			if (deliveryTicket.getEstimatedDeliveryTime() != null){
				labelEstimatedDeliveryTime.setText(deliveryTicket.getEstimatedDeliveryTime().format(timeFormatter));
			}
			
			labelPackageID.setText(Integer.toString(deliveryTicket.getPackageID()));
			if (deliveryTicket.getPrice() != null){
				labelPrice.setText(stringFromBigDecimal(deliveryTicket.getPrice()));
			}
			
			if(deliveryTicket.getDeliveryRoute() != null && deliveryTicket.getPickupRoute() != null && deliveryTicket.getReturnRoute() != null) {
				labelTotalDistance.setText(deliveryTicket.calculateTotalDistance() + " blocks");
			}
			
			if(deliveryTicket.getPickupRoute() != null && deliveryTicket.getDeliveryRoute() != null && deliveryTicket.getReturnRoute() != null ){
				labelTotalDistance.setText(Integer.toString(deliveryTicket.calculateTotalDistance()));
			}
			
			this.buttonGenerateCourierPackage.setDisable(this.deliveryTicket.getCourier() == null);
			
			spinnerRequestedPickupHour.getValueFactory().setValue(deliveryTicket.getRequestedPickupTime().getHour());
			spinnerRequestedPickupMinute.getValueFactory().setValue(deliveryTicket.getRequestedPickupTime().getMinute());
			comboBoxRequestedPickupAMPM.setValue(getAMPM(deliveryTicket.getRequestedPickupTime()));
			
			//Actual Values
		    if (deliveryTicket.getActualPickupTime() == null){ 
		    	checkBoxActualPickupTime.setSelected(false);
		    	spinnerActualPickupTimeHour.setDisable(true);
		    	spinnerActualPickupTimeMinute.setDisable(true);
		    	comboBoxActualPickupTimeAMPM.setDisable(true);
		    }else{
		    	checkBoxActualPickupTime.setSelected(true);
		    	spinnerActualPickupTimeHour.getValueFactory().setValue(deliveryTicket.getActualPickupTime().getHour());
				spinnerActualPickupTimeMinute.getValueFactory().setValue(deliveryTicket.getActualPickupTime().getMinute());
				comboBoxActualPickupTimeAMPM.setValue(getAMPM(deliveryTicket.getActualPickupTime()));
		    }
			
		    if (deliveryTicket.getActualDepartureTime() == null){
		    	checkBoxActualDepartureTime.setSelected(false);
		    	spinnerActualDepartureTimeHour.setDisable(true);
		    	spinnerActualDepartureTimeMinute.setDisable(true);
		    	comboBoxActualDepartureTimeAMPM.setDisable(true);
		    }else{
		    	checkBoxActualDepartureTime.setSelected(true);
		    	spinnerActualDepartureTimeHour.getValueFactory().setValue(deliveryTicket.getActualDepartureTime().getHour());
				spinnerActualDepartureTimeMinute.getValueFactory().setValue(deliveryTicket.getActualDepartureTime().getMinute());
				comboBoxActualDepartureTimeAMPM.setValue(getAMPM(deliveryTicket.getActualDepartureTime()));
		    }
		    
		    if (deliveryTicket.getActualDeliveryTime() == null){
		    	checkBoxActualDeliveryTime.setSelected(false);
		    	spinnerActualDeliveryTimeHour.setDisable(true);
		    	spinnerActualDeliveryTimeMinute.setDisable(true);
		    	comboBoxActualDeliveryTimeAMPM.setDisable(true);
		    }else{
		    	checkBoxActualDeliveryTime.setSelected(true);
		    	spinnerActualDeliveryTimeHour.getValueFactory().setValue(deliveryTicket.getActualDeliveryTime().getHour());
				spinnerActualDeliveryTimeMinute.getValueFactory().setValue(deliveryTicket.getActualDeliveryTime().getMinute());
				comboBoxActualDeliveryTimeAMPM.setValue(getAMPM(deliveryTicket.getActualDeliveryTime()));
		    }
			
		    if (deliveryTicket.getActualReturnTime() == null){
		    	checkBoxActualReturnTime.setSelected(false);
		    	spinnerActualReturnTimeHour.setDisable(true);
		    	spinnerActualReturnTimeMinute.setDisable(true);
		    	comboBoxActualReturnTimeAMPM.setDisable(true);
		    }else{
		    	checkBoxActualReturnTime.setSelected(true);
		    	spinnerActualReturnTimeHour.getValueFactory().setValue(deliveryTicket.getActualReturnTime().getHour());
				spinnerActualReturnTimeMinute.getValueFactory().setValue(deliveryTicket.getActualReturnTime().getMinute());
				comboBoxActualReturnTimeAMPM.setValue(getAMPM(deliveryTicket.getActualReturnTime()));
		    }

			chkBoxDeliveryConfirmed.setSelected(deliveryTicket.isDeliveryConfirmed());
		}
		else {
			this.buttonGenerateCourierPackage.setDisable(true);
			spinnerActualPickupTimeHour.setDisable(true);
	    	spinnerActualPickupTimeMinute.setDisable(true);
	    	comboBoxActualPickupTimeAMPM.setDisable(true);
			spinnerActualDepartureTimeHour.setDisable(true);
	    	spinnerActualDepartureTimeMinute.setDisable(true);
	    	comboBoxActualDepartureTimeAMPM.setDisable(true);
			spinnerActualDeliveryTimeHour.setDisable(true);
	    	spinnerActualDeliveryTimeMinute.setDisable(true);
	    	comboBoxActualDeliveryTimeAMPM.setDisable(true);
			spinnerActualReturnTimeHour.setDisable(true);
	    	spinnerActualReturnTimeMinute.setDisable(true);
	    	comboBoxActualReturnTimeAMPM.setDisable(true);
		}
			
	  //client Lists
	  updateClientsList();
	  //User Lists
	  updateUserList();
	  //set comboBox Converters
	  setComboBoxConverters(); 
	  ObservableList<String> AMPMList = FXCollections.observableArrayList();
	  AMPMList.add("AM");
	  AMPMList.add("PM");
	  
	  comboBoxActualDeliveryTimeAMPM.setItems(AMPMList);
	  comboBoxActualDeliveryTimeAMPM.getSelectionModel().selectFirst();
	  comboBoxActualDepartureTimeAMPM.setItems(AMPMList);
	  comboBoxActualDepartureTimeAMPM.getSelectionModel().selectFirst();
	  comboBoxActualPickupTimeAMPM.setItems(AMPMList);
	  comboBoxActualPickupTimeAMPM.getSelectionModel().selectFirst();
	  comboBoxActualReturnTimeAMPM.setItems(AMPMList);
	  comboBoxActualReturnTimeAMPM.getSelectionModel().selectFirst();
	  comboBoxRequestedPickupAMPM.setItems(AMPMList);
	  comboBoxRequestedPickupAMPM.getSelectionModel().selectFirst();
	  
	  buttonSave.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	
	            	try {
	            		if(deliveryTicket == null || deliveryTicket.getPrice() == null) {
		            		Alert a = new Alert(AlertType.ERROR);
		        	        a.setTitle("Error");
	        	        	a.setHeaderText("No Quote Calculated");
	        		        a.setContentText("Please calculate quote and try again.");
		        	        a.showAndWait();
		        			return;
	            		}
	        			
	        			if(save()) {
	        				
	                		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/AddSearchDeliveryTickets.fxml"));
	        	    		BorderPane border = Main.getRoot();
	        	    		border.setCenter(currentPane);
	                	}
	        		} catch(Exception e){
	        		    System.out.println("Save Failed"); 
	        		    e.printStackTrace();
		    	}
	        }
	    });
	  buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	
	            	try {
	        			
	        				
	                		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/AddSearchDeliveryTickets.fxml"));
	        	    		BorderPane border = Main.getRoot();
	        	    		border.setCenter(currentPane);
	                
	        		} catch(Exception e){
	        		       	
		    	}
	        }
	    });
	  
	  buttonGenerateQuote.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
			//save ticket
			try{
				if(save()){
					//get package id
					deliveryTicket.setPackageID((int) deliveryTicket.getTicketId());
					labelPackageID.setText(Integer.toString(deliveryTicket.getPackageID()));
					
					//calculate and set price
					BigDecimal price = deliveryTicket.calculatePrice();
					deliveryTicket.setPrice(price);
					labelPrice.setText(stringFromBigDecimal(deliveryTicket.getPrice()));
					//Estimated Delivery Time
					LocalDateTime estDeliveryTime = deliveryTicket.calculateDeliveryTime();
					deliveryTicket.setEstimatedDeliveryTime(estDeliveryTime);
					labelEstimatedDeliveryTime.setText(deliveryTicket.getEstimatedDeliveryTime().format(timeFormatter));
					
					//Estimated Departure Time
					LocalDateTime estDepartTime = deliveryTicket.calculateDepartureTime();
					deliveryTicket.setCalculatedDepartureTime(estDepartTime);
					labelCalculatedDepartureTime.setText(estDepartTime.format(timeFormatter));
					//Total Distance
					int totalDistance = deliveryTicket.calculateTotalDistance();
					labelTotalDistance.setText(Integer.toString(totalDistance) + " blocks");
	
					DeliveryTicketDAO.saveDeliveryTicket(deliveryTicket);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		  
	  });
	  
	  buttonGenerateCourierPackage.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try{
					if(save()){
						DeliveryTicketDAO.saveDeliveryTicket(deliveryTicket);
						
						//generate courier package and set price if not set
						deliveryTicket.generateCourierPackage();
		
						DeliveryTicketDAO.saveDeliveryTicket(deliveryTicket);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		  });
	  
	  checkBoxActualDeliveryTime.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
		if(checkBoxActualDeliveryTime.isSelected()){
			spinnerActualDeliveryTimeHour.setDisable(false);
			spinnerActualDeliveryTimeMinute.setDisable(false);
	    	comboBoxActualDeliveryTimeAMPM.setDisable(false);
		}else{
			spinnerActualDeliveryTimeHour.setDisable(true);
			spinnerActualDeliveryTimeMinute.setDisable(true);
	    	comboBoxActualDeliveryTimeAMPM.setDisable(true);
		}			
		}});
	  
	  checkBoxActualDepartureTime.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
			if(checkBoxActualDepartureTime.isSelected()){
				spinnerActualDepartureTimeHour.setDisable(false);
				spinnerActualDepartureTimeMinute.setDisable(false);
		    	comboBoxActualDepartureTimeAMPM.setDisable(false);
			}else{
				spinnerActualDepartureTimeHour.setDisable(true);
				spinnerActualDepartureTimeMinute.setDisable(true);
		    	comboBoxActualDepartureTimeAMPM.setDisable(true);
			}			
			}});
	  
	  checkBoxActualPickupTime.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
			if(checkBoxActualPickupTime.isSelected()){
				spinnerActualPickupTimeHour.setDisable(false);
				spinnerActualPickupTimeMinute.setDisable(false);
		    	comboBoxActualPickupTimeAMPM.setDisable(false);
			}else{
				spinnerActualPickupTimeHour.setDisable(true);
				spinnerActualPickupTimeMinute.setDisable(true);
		    	comboBoxActualPickupTimeAMPM.setDisable(true);
			}			
			}});
	  checkBoxActualReturnTime.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
			if(checkBoxActualReturnTime.isSelected()){
				spinnerActualReturnTimeHour.setDisable(false);
				spinnerActualReturnTimeMinute.setDisable(false);
		    	comboBoxActualReturnTimeAMPM.setDisable(false);
			}else{
				spinnerActualReturnTimeHour.setDisable(true);
				spinnerActualReturnTimeMinute.setDisable(true);
		    	comboBoxActualReturnTimeAMPM.setDisable(true);			
			}
			
			}});
	}

	private void updateClientsList() {
		
		ObservableList<Client> clients = FXCollections.observableArrayList();
		clients.addAll(deliveryTracker.getClients());
		comboBoxPickupClient.setItems(clients);
		comboBoxPayingClient.setItems(clients);
		comboBoxDeliveryClient.setItems(clients);
		
	}
	
	private void updateUserList(){
		ObservableList<User> users = FXCollections.observableArrayList();
		users.addAll(deliveryTracker.getUsers());
		comboBoxOrderTaker.setItems(users);
	}
	private void setComboBoxConverters(){
		comboBoxPickupClient.setConverter(
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
		comboBoxPayingClient.setConverter(
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
		comboBoxDeliveryClient.setConverter(
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
		comboBoxOrderTaker.setConverter(
			new StringConverter<User>() {
                @Override
                public User fromString(String s) {
                	//TODO: get client by name
                    return null;
                }

				@Override
				public String toString(User object) {
					if (object == null) {
                        return "";
                    } else {
                        return object.getName();
                    }
				}
            });
		
	}

	private boolean save() {
		if(!validate()) {
			Alert a = new Alert(AlertType.ERROR);
	        a.setTitle("Error");
	        if(comboBoxPickupClient.getValue() == comboBoxDeliveryClient.getValue()){
	        	a.setHeaderText("Duplicate Client");
		        a.setContentText("Pickup and Delivery Clients are the same.");
	        }
	        else{
	        	a.setHeaderText("Missing Information");
		        a.setContentText("Please complete all required fields and try again.");
	        }
	        
	        a.showAndWait();
			return false;
		}
		if(deliveryTicket == null){
    		deliveryTicket = new DeliveryTicket();
    		DeliveryTracker deliveryTracker = DeliveryTracker.getDeliveryTracker();
    		deliveryTracker.addDeliveryTicket(deliveryTicket);
		}
		//save fields
		deliveryTicket.setOrderDateTime(LocalDateTime.of(dateTimePickerOrderDate.getValue(), LocalDateTime.now().toLocalTime()));
		deliveryTicket.setOrderTaker(comboBoxOrderTaker.getValue());
		deliveryTicket.setPickupClient(comboBoxPickupClient.getValue());
		deliveryTicket.setDeliveryClient(comboBoxDeliveryClient.getValue());
		deliveryTicket.setPayingClient(comboBoxPayingClient.getValue());
		
		
		int requestedPickupHour = spinnerRequestedPickupHour.getValueFactory().getValue();
		int requestedPickupMin = spinnerRequestedPickupMinute.getValueFactory().getValue();
		String AMORPM = comboBoxRequestedPickupAMPM.getValue();
		LocalDateTime requestedPickupTime = deliveryTicket.getOrderDateTime().toLocalDate().atTime(hourCombobulator(requestedPickupHour, AMORPM), requestedPickupMin); 
		deliveryTicket.setRequestedPickupTime(requestedPickupTime);
		
		deliveryTicket.setSpecialRemarks(textAreaSpecialRemarks.getText());

		if (checkBoxActualPickupTime.isSelected()){
			int actualPickupTimeHour = spinnerActualPickupTimeHour.getValueFactory().getValue();
			int actualPickupTimeMin = spinnerActualPickupTimeMinute.getValueFactory().getValue();
			String amOrPmActualPickup = comboBoxActualPickupTimeAMPM.getValue();
			if(amOrPmActualPickup != null){
				LocalDateTime actualPickupTime = deliveryTicket.getOrderDateTime().toLocalDate().atTime(hourCombobulator(actualPickupTimeHour, amOrPmActualPickup), actualPickupTimeMin);	
				deliveryTicket.setActualPickupTime(actualPickupTime);
			}
		}else{
			
			deliveryTicket.setActualPickupTime(null);
			
		}
		
		if(checkBoxActualDepartureTime.isSelected()){
			int actualDepartureTimeHour = spinnerActualDepartureTimeHour.getValueFactory().getValue();
			int actualDepartureTimeMin = spinnerActualDepartureTimeMinute.getValueFactory().getValue();
			String amOrPmActualDeparture = comboBoxActualDepartureTimeAMPM.getValue();
			if(amOrPmActualDeparture != null){
				LocalDateTime actualDepartureTime = deliveryTicket.getOrderDateTime().toLocalDate().atTime(hourCombobulator(actualDepartureTimeHour, amOrPmActualDeparture), actualDepartureTimeMin);
				deliveryTicket.setActualDepartureTime(actualDepartureTime);
			}
		}else{
			deliveryTicket.setActualDepartureTime(null);
		}
		
		if(checkBoxActualDeliveryTime.isSelected()){
			int actualDeliveryTimeHour = spinnerActualDeliveryTimeHour.getValueFactory().getValue();
			int actualDeliveryTimeMinute = spinnerActualDeliveryTimeMinute.getValueFactory().getValue();
			String amOrPmActualDelivery = comboBoxActualDeliveryTimeAMPM.getValue();
			if(amOrPmActualDelivery != null){
				LocalDateTime actualDeliveryTime = deliveryTicket.getOrderDateTime().toLocalDate().atTime(hourCombobulator(actualDeliveryTimeHour, amOrPmActualDelivery), actualDeliveryTimeMinute);
				deliveryTicket.setActualDeliveryTime(actualDeliveryTime);
			}
		}else{
			deliveryTicket.setActualDeliveryTime(null);
		}
		
		if(checkBoxActualReturnTime.isSelected()){
			int actualReturnTimeHour = spinnerActualReturnTimeHour.getValueFactory().getValue();
			int actualReturnTimeMinute = spinnerRequestedPickupMinute.getValueFactory().getValue();
			String amOrPmActualReturn = comboBoxActualReturnTimeAMPM.getValue();
			if(amOrPmActualReturn != null){
				LocalDateTime actualReturnTime = deliveryTicket.getOrderDateTime().toLocalDate().atTime(hourCombobulator(actualReturnTimeHour, amOrPmActualReturn), actualReturnTimeMinute);
				deliveryTicket.setActualReturnTime(actualReturnTime);
				
			}
		}else{
			deliveryTicket.setActualReturnTime(null);
		}
			
		if (deliveryTicket.getCourier() != null) {
			if(deliveryTicket.getActualDepartureTime() != null && deliveryTicket.getActualReturnTime() == null)
				deliveryTicket.getCourier().setAvailable(false);
			else 
				deliveryTicket.getCourier().setAvailable(true);
		}
		
		boolean isConfirmed = chkBoxDeliveryConfirmed.isSelected();
		deliveryTicket.setDeliveryConfirmed(isConfirmed);
		
		DeliveryTicketDAO.saveDeliveryTicket(deliveryTicket);
		return true;
	}
	
	private boolean validate() {
		if(comboBoxDeliveryClient.getValue() == null) return false;
		if(comboBoxPayingClient.getValue() == null) return false;
		if(comboBoxPickupClient.getValue() == null) return false;
		if(comboBoxPickupClient.getValue() == comboBoxDeliveryClient.getValue()) return false;
		if (comboBoxRequestedPickupAMPM.getValue() == null)return false;
		if (dateTimePickerOrderDate.getValue() == null) return false;
		return true;
	}
	
	private int hourCombobulator(int hour, String amOrPm){
		if(amOrPm.equals("PM")){
			if(hour == 12){
				hour = 0;
			
			}else{
				hour +=12;
			}		
		}
		return hour;
	}
	
	private String getAMPM (LocalDateTime date){
		if(date.getHour() < 12){
			return "AM";
			
		}else return "PM";
	}
	
	public void setDeliveryTicket(DeliveryTicket dt) {
		this.deliveryTicket = dt;
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
