package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import model.Client;

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
    private ComboBox<?> comboBoxAvenue;

    @FXML
    private ComboBox<?> comboBoxStreet;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonCancel;
	    
	Client client;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Client Edit");
		if(client != null) {
			textFieldClientNumber.setText(Integer.toString(client.getClientNumber()));
			textFieldClientName.setText(client.getName());
			textFieldDeliveryInstructions.setText(client.getDeliveryDetails());
			textFieldPhoneNumber.setText(client.getPhoneNumber());
			textFieldEmail.setText(client.getEmail());
			//TODO: Populate street & avenue
		}
		buttonSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Cancel");
                try {
                	if(client == null)
                		client = new Client();
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
                System.out.println("Cancel");
                try {
    	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/SelectClient.fxml"));
    	    		BorderPane border = Main.getRoot();
    	    		border.setCenter(currentPane);
    	    	} catch(IOException e){
    	    		e.printStackTrace();
    	    	}
            }
        });
	}
	
	public void setClient(Client c) {
		this.client = c;
		System.out.println("Setting client to " + c.getName());
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
		client.setClientNumber(Integer.parseInt(textFieldClientNumber.getText().trim()));
		client.setName(textFieldClientName.getText().trim());
		client.setDeliveryDetails(textFieldDeliveryInstructions.getText().trim());
		client.setEmail(textFieldEmail.getText().trim());
		client.setPhoneNumber(textFieldPhoneNumber.getText().trim());
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
		return true;
	}
}
