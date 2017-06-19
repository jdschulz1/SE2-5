package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dtDAO.ClientDAO;
import dtDAO.UserDAO;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.Client;
import model.DeliveryTracker;
import model.User;

public class EditUser implements Initializable {
	  @FXML
	    private ComboBox<String> comboBoxUserRole;

	    @FXML
	    private TextField textFieldUserUsername;

	    @FXML
	    private TextField textFieldUserPassword;

	    @FXML
	    private TextField textFieldUserEmail;

	    @FXML
	    private TextField textFieldUserName;
	    
	    @FXML
	    private TextField textFieldUserPasswordConf;

	    @FXML
	    private Button btnSave;

	    @FXML
	    private Button btnCancel;
	    
	    User user;
	    DeliveryTracker deliveryTracker;
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		deliveryTracker = DeliveryTracker.getDeliveryTracker();
		ObservableList<String> roles = FXCollections.observableArrayList();
		roles.addAll("Admin", "User");
		comboBoxUserRole.setItems(roles);
		if(deliveryTracker.getCurrentUser().getRole().equals("User")){
			comboBoxUserRole.setDisable(true);
		}
		else {
			comboBoxUserRole.setDisable(false);
		}
		if(user != null) {
			textFieldUserName.setText(user.getName());
			textFieldUserUsername.setText(user.getUserName());
			textFieldUserPassword.setText(user.getPassword());
			comboBoxUserRole.setValue(user.getRole());
			textFieldUserEmail.setText(user.getEmail());
		}
		btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                	if(textFieldUserPassword.getText().equals(textFieldUserPasswordConf.getText())){
                    	if(save()) {
	                		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/SelectUser.fxml"));
	        	    		BorderPane border = Main.getRoot();
	        	    		border.setCenter(currentPane);
                    	}
                	}
                	else {
                		Alert no = new Alert(AlertType.ERROR);
            	        no.setTitle("Passwords Don't Match");
            	        no.setHeaderText("The passwords you entered do not match");
            	        no.setContentText("Please re-input the passwords and try again.");
            	        no.showAndWait();
                	}
    	    	} catch(IOException e){
    	    		e.printStackTrace();
    	    	}
            }
        });
		btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
    	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/SelectUser.fxml"));
    	    		BorderPane border = Main.getRoot();
    	    		border.setCenter(currentPane);
    	    	} catch(IOException e){
    	    		e.printStackTrace();
    	    	}
            }
        });

	}
	
	public void setUser(User u) {
		this.user = u;
		System.out.println("Setting client to " + u.getName());
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
		if(user == null){
    		user = new User();
    		DeliveryTracker deliveryTracker = DeliveryTracker.getDeliveryTracker();
    		deliveryTracker.addUser(user);
		}
		user.setName(textFieldUserName.getText().trim());
		user.setUserName(textFieldUserUsername.getText().trim());
		user.setPassword(textFieldUserPassword.getText().trim());
		user.setEmail(textFieldUserEmail.getText().trim());
		user.setRole(comboBoxUserRole.getValue().toString());
		UserDAO.saveUser(user);
		return true;
	}
	
	private boolean validate() {
		try {
			textFieldUserName.getText().trim();
		} catch(Exception e) {
				return false;
		}
		if(textFieldUserName.getText().trim().isEmpty()) 
			return false;
		if(textFieldUserUsername.getText().trim().isEmpty()) 
			return false;
		if(textFieldUserEmail.getText().trim().isEmpty())
			return false;
		if(comboBoxUserRole.getValue() == null )
			return false;
		return true;
	}

}
