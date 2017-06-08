package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
		if(client != null) {
			textFieldClientName.setText(client.getName());
			//TODO: Populate fields
		}
		buttonSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Cancel");
                try {
                	//TODO: Save Client data
    	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/SelectClient.fxml"));
    	    		BorderPane border = Main.getRoot();
    	    		border.setCenter(currentPane);
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
}
