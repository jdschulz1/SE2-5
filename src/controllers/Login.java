package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.persistence.criteria.Root;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.DeliveryTracker;
import model.User;

public class Login implements Initializable {
	
	@FXML
    private Button btnLogin;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private TextField textFieldPassword;

    User user;
    DeliveryTracker deliveryTracker;
    Main main;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		deliveryTracker = DeliveryTracker.getDeliveryTracker();
		ObservableList<User> users = FXCollections.observableArrayList();
		users.addAll(deliveryTracker.getUsers());
		
		MenuBar bar = (MenuBar)Main.getRoot().getTop();
//		System.out.println("Bar" + bar);
//		bar.setDisable(true);
		//Main.getRoot().getTop().setDisable(true);
		
		btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                	boolean userCheck = false;
                	for(int i = 0; i < users.size(); i++) {
                		user = users.get(i);
	                	if(textFieldUsername.getText().equals(user.getUserName())  && textFieldPassword.getText().equals(user.getPassword())) {
	                		//set current user
	                		deliveryTracker.setCurrentUser(user);
	                		Main.getRoot().getTop().setDisable(false);
	                		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
	        	    		BorderPane border = Main.getRoot();
	        	    		border.setCenter(currentPane);
	        	    		userCheck = true;
                		}
                	}
                	if(userCheck == false) {
                		Alert no = new Alert(AlertType.ERROR);
            	        no.setTitle("Incorrect Username/Password");
            	        no.setHeaderText("The Username or Password do not match a current user");
            	        no.setContentText("Please re-input the username and password and try again.");
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
    	    		System.exit(0);
            }
        });
		

	}

}
