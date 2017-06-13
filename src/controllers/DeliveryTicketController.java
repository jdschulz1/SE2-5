package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.sun.corba.se.spi.orbutil.proxy.DelegateInvocationHandlerImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
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
    private ComboBox<Courier> comboBoxCourier;

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
    
    
    DeliveryTicket deliveryTicket;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		deliveryTracker = DeliveryTracker.getDeliveryTracker();
		spinnerRequestedPickupMinute = new Spinner<Integer>(0, 60, 0, 1);
		//spinnerRequestedPickupMinute.setValueFactory();
		if(deliveryTicket != null){
			comboBoxDeliveryClient.setValue(deliveryTicket.getDeliveryClient());
			comboBoxPickupClient.setValue(deliveryTicket.getPickupClient());
			comboBoxPayingClient.setValue(deliveryTicket.getPayingClient());
			comboBoxCourier.setValue(deliveryTicket.getCourier());
			dateTimePickerOrderDate.setValue(deliveryTicket.getOrderDateTime().toLocalDate());
			textAreaSpecialRemarks.setText(deliveryTicket.getSpecialRemarks());
			labelCalculatedDepartureTime.setText(deliveryTicket.getCalculatedDepartureTime().toLocalTime().toString());
			labelEstimatedDeliveryTime.setText(deliveryTicket.getEstimatedDeliveryTime().toLocalTime().toString());
			labelPackageID.setText(Integer.toString(deliveryTicket.getPackageID()));
			labelPrice.setText(deliveryTicket.getPrice().toString());
			labelTotalDistance.setText(Integer.toString(deliveryTicket.calculateTotalDistance()));
			
			spinnerRequestedPickupHour.getValueFactory().setValue(deliveryTicket.getRequestedPickupTime().getHour());
			spinnerRequestedPickupMinute.getValueFactory().setValue(deliveryTicket.getRequestedPickupTime().getMinute());
			comboBoxRequestedPickupAMPM.setValue(getAMPM(deliveryTicket.getRequestedPickupTime()));
									
			spinnerActualPickupTimeHour.getValueFactory().setValue(deliveryTicket.getActualPickupTime().getHour());
			spinnerActualPickupTimeMinute.getValueFactory().setValue(deliveryTicket.getActualPickupTime().getMinute());
			comboBoxActualPickupTimeAMPM.setValue(getAMPM(deliveryTicket.getActualPickupTime()));
			
			spinnerActualDepartureTimeHour.getValueFactory().setValue(deliveryTicket.getActualDepartureTime().getHour());
			spinnerActualDepartureTimeMinute.getValueFactory().setValue(deliveryTicket.getActualDepartureTime().getMinute());
			comboBoxActualDepartureTimeAMPM.setValue(getAMPM(deliveryTicket.getActualDepartureTime()));
			
			spinnerActualDeliveryTimeHour.getValueFactory().setValue(deliveryTicket.getActualDeliveryTime().getHour());
			spinnerActualDeliveryTimeMinute.getValueFactory().setValue(deliveryTicket.getActualDeliveryTime().getMinute());
			comboBoxActualDeliveryTimeAMPM.setValue(getAMPM(deliveryTicket.getActualDeliveryTime()));
			
			spinnerActualReturnTimeHour.getValueFactory().setValue(deliveryTicket.getActualReturnTime().getHour());
			spinnerActualReturnTimeMinute.getValueFactory().setValue(deliveryTicket.getActualReturnTime().getMinute());
			comboBoxActualReturnTimeAMPM.setValue(getAMPM(deliveryTicket.getActualReturnTime()));
	
			chkBoxDeliveryConfirmed.setSelected(deliveryTicket.isDeliveryConfirmed());
			
			
		}
			
	  //client Lists
	  updateClientsList();
	  //User Lists
	  updateUserList();
	  //CourierLists
	  updateCourierList();
	  //set comboBox Converters
	  setComboBoxConverters(); 
	  ObservableList<String> AMPMList = FXCollections.observableArrayList();
	  AMPMList.add("AM");
	  AMPMList.add("PM");
	  
	  comboBoxActualDeliveryTimeAMPM.setItems(AMPMList);
	  comboBoxActualDepartureTimeAMPM.setItems(AMPMList);
	  comboBoxActualPickupTimeAMPM.setItems(AMPMList);
	  comboBoxActualReturnTimeAMPM.setItems(AMPMList);
	  comboBoxRequestedPickupAMPM.setItems(AMPMList);
	  
	}
	private String getAMPM (LocalDateTime date){
			if(date.getHour() < 12){
				return "AM";
				
			}else return "PM";
		}
	private void updateCourierList() {
		ObservableList<Courier> couriers = FXCollections.observableArrayList();
		couriers.addAll(deliveryTracker.getCouriers());
		comboBoxCourier.setItems(couriers);
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
	
		comboBoxCourier.setConverter(
			new StringConverter<Courier>() {
                @Override
                public Courier fromString(String s) {
                	//TODO: get client by name
                    return null;
                }

				@Override
				public String toString(Courier object) {
					if (object == null) {
                        return "";
                    } else {
                        return object.getName();
                    }
				}
            });	
	}
	
	
	
}
