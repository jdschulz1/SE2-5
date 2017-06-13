package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dtDAO.ClientDAO;
import dtDAO.CourierDAO;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.Courier;
import model.DeliveryTracker;
import model.User;

public class EditCourier implements Initializable {
	@FXML
    private TextField textFieldCourierNumber;

    @FXML
    private CheckBox checkBoxCourierIsActive;

    @FXML
    private TextField textFieldCourierName;

    @FXML
    private TextField textFieldCourierPhoneNumber;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;
    
    private Courier courier;
	public Courier getCourier() {
		return courier;
	}
	public void setCourier(Courier courier) {
		this.courier = courier;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if(courier != null){
			textFieldCourierName.setText(courier.getName());
			textFieldCourierNumber.setText(Integer.toString(courier.getCourierNumber()));
			textFieldCourierPhoneNumber.setText(courier.getPhoneNumber());
			checkBoxCourierIsActive.setSelected(courier.getIsActive());
		}	
	
	btnSave.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            	try {
        			if(save()) {
        				Integer.parseInt(textFieldCourierNumber.getText());
                		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/SelectCourier.fxml"));
        	    		BorderPane border = Main.getRoot();
        	    		border.setCenter(currentPane);
                	}
        		} catch(Exception e){
        			Alert a = new Alert(AlertType.ERROR);
        	        a.setTitle("Error");
        	        a.setHeaderText("Incorrect Information");
        	        a.setContentText("Please change the Courier Number to a number.");
        	        a.showAndWait();        	
	    	}
        }
    });
	btnCancel.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            try {
	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/SelectCourier.fxml"));
	    		BorderPane border = Main.getRoot();
	    		border.setCenter(currentPane);
	    	} catch(IOException e){
	    		e.printStackTrace();
	    	}
        }
    });

	}
	
	public void setCouriers(Courier c) {
		this.courier = c;
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
		if(courier == null){
    		courier = new Courier();
    		DeliveryTracker deliveryTracker = DeliveryTracker.getDeliveryTracker();
    		deliveryTracker.addCourier(courier);
		}
		courier.setName(textFieldCourierName.getText().trim());
		courier.setCourierNumber(Integer.parseInt(textFieldCourierNumber.getText().trim()));
		courier.setPhoneNumber(textFieldCourierPhoneNumber.getText().trim());
		courier.setIsActive(checkBoxCourierIsActive.isSelected());
		CourierDAO.saveCourier(courier);
		return true;
	}
	
	private boolean validate() {
		try {
			textFieldCourierName.getText().trim();
		} catch(Exception e) {
				return false;
		}
		if(textFieldCourierNumber.getText().trim().isEmpty()) 
			return false;
		if(textFieldCourierPhoneNumber.getText().trim().isEmpty()) 
			return false;
		return true;
	}

}
