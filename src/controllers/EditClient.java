package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
    private ComboBox<?> comboBoxStreet;

    @FXML
    private ComboBox<?> comboBoxAvenue;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonCancel;
	    
	    Client client;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Client Edit");
		System.out.println(client.getName());
		if(client != null) {
			textFieldClientName.setText(client.getName());
			//TODO: Populate fields
		}
	}
	
	public void setClient(Client c) {
		this.client = c;
		System.out.println("Setting client to " + c.getName());
	}
}
