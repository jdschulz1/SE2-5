package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class EditClient implements javafx.fxml.Initializable {

	  @FXML	
	    private TextField textFieldClientNumber;

	    @FXML
	    private TextField textFieldClientName;

	    @FXML
	    private TextField textFieldDeliveryInstructions;

	    @FXML
	    private ChoiceBox<?> choiceBoxStreet;

	    @FXML
	    private ChoiceBox<?> choiceBoxAvenue;

	    @FXML
	    private TextField textFieldPhoneNumber;

	    @FXML
	    private Button btnSave;

	    @FXML
	    private Button btnCancel;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Client Edit");
	}
}
