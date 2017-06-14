package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dtDAO.ClientDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import model.CityMap;
import model.Client;
import model.DeliveryTracker;
import model.Intersection;
import model.Street;

public class EditClient implements javafx.fxml.Initializable {

	@FXML
    private TextField textFieldClientNumber;

    @FXML
    private TextField textFieldClientName;

    @FXML
    private TextField textFieldDeliveryInstructions;

    @FXML
    private TextField textFieldPhoneNumber;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private ComboBox<Street> comboBoxAvenue;

    @FXML
    private ComboBox<Street> comboBoxStreet;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonCancel;
	    
	Client client;
	CityMap cityMap;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cityMap = CityMap.getCityMap();

		if(client != null) {
			textFieldClientNumber.setText(Integer.toString(client.getClientNumber()));
			textFieldClientName.setText(client.getName());
			textFieldDeliveryInstructions.setText(client.getDeliveryDetails());
			textFieldPhoneNumber.setText(client.getPhoneNumber());
			textFieldEmail.setText(client.getEmail());
			comboBoxStreet.setValue(client.getLocation().getStreet());
			comboBoxAvenue.setValue(client.getLocation().getAvenue());
		}
		buttonSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                	if(save()) {
	    	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/SelectClient.fxml"));
	    	    		BorderPane border = Main.getRoot();
	    	    		border.setCenter(currentPane);
                	}
    	    	} catch(IOException e){
    	    		e.printStackTrace();
    	    	}
            }
        });
		buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
    	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/SelectClient.fxml"));
    	    		BorderPane border = Main.getRoot();
    	    		border.setCenter(currentPane);
    	    	} catch(IOException e){
    	    		e.printStackTrace();
    	    	}
            }
        });
		
		comboBoxStreet.setConverter(
	            new StringConverter<Street>() {
	                @Override
	                public Street fromString(String s) {
                        return null;
	                }

					@Override
					public String toString(Street object) {
						if (object == null) {
	                        return "";
	                    } else {
	                        return object.getName();
	                    }
					}
	            });
		
		comboBoxAvenue.setConverter(
	            new StringConverter<Street>() {
	                @Override
	                public Street fromString(String s) {
                        return null;
	                }

					@Override
					public String toString(Street object) {
						if (object == null) {
	                        return "";
	                    } else {
	                        return object.getName();
	                    }
					}
	            });
		
		updateImpedimentStreetsList();
		updateImpedimentAvenuesList();
	}
	
	public void setClient(Client c) {
		this.client = c;
	}
	
	public void updateImpedimentStreetsList () {
		ObservableList<Street> streets = FXCollections.observableArrayList();
		streets.addAll(cityMap.getWholeStreets());
		comboBoxStreet.setItems(streets);
	}
	
	public void updateImpedimentAvenuesList () {
		ObservableList<Street> avenues = FXCollections.observableArrayList();
		avenues.addAll(cityMap.getWholeAvenues());
		comboBoxAvenue.setItems(avenues);
	}
	
	private boolean save() {
		if(!validate()) {
			Alert a = new Alert(AlertType.ERROR);
	        a.setTitle("Error");
	        a.setHeaderText("Missing Information");
	        a.setContentText("Please complete all required fields and try again.");
	        a.showAndWait();
			return false;
		}
		if(client == null) {
    		client = new Client();
    		DeliveryTracker deliveryTracker = DeliveryTracker.getDeliveryTracker();
    		deliveryTracker.addClient(client);
		}
		client.setClientNumber(Integer.parseInt(textFieldClientNumber.getText().trim()));
		client.setName(textFieldClientName.getText().trim());
		client.setDeliveryDetails(textFieldDeliveryInstructions.getText().trim());
		client.setEmail(textFieldEmail.getText().trim());
		client.setPhoneNumber(textFieldPhoneNumber.getText().trim());
		client.setLocation(new Intersection(comboBoxStreet.getValue(), comboBoxAvenue.getValue()));
		ClientDAO.saveClient(client);
		return true;
	}
	
	private boolean validate() {
		try {
			Integer.parseInt(textFieldClientNumber.getText().trim());
		} catch(Exception e) {
				return false;
		}
		if(textFieldClientName.getText().trim().isEmpty()) 
			return false;
		if(textFieldEmail.getText().trim().isEmpty())
			return false;
		if(textFieldPhoneNumber.getText().trim().isEmpty())
			return false;
		if(comboBoxStreet.getValue() == null)
			return false;
		if(comboBoxAvenue.getValue() == null)
			return false;
		return true;
	}
}
