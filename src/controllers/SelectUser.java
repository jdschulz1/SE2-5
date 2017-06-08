package controllers;


import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import model.User;
import model.DeliveryTracker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class SelectUser implements Initializable {

	DeliveryTracker deliveryTracker;
	
    @FXML
    private ComboBox<User> comboBoxUser;

    @FXML
    private Button buttonUserAdd;

    @FXML
    private Button buttonUserUpdate;

    @FXML
    private Button buttonUserDelete;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		deliveryTracker = DeliveryTracker.getDeliveryTracker();
		updateUsersList();
		comboBoxUser.setConverter(
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
		
			buttonUserAdd.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Add");
	                try {
//	    	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/EditClient.fxml"));
	    	    		
	                	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/EditUser.fxml"));
	                	EditUser controller = new EditUser();
	                	fxmlLoader.setController(controller);
	                	AnchorPane currentPane = fxmlLoader.load();
	                	BorderPane border = Main.getRoot();
	    	    		border.setCenter(currentPane);
	    	    	} catch(IOException e){
	    	    		e.printStackTrace();
	    	    	}
	            }
	        });
			
			buttonUserUpdate.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	User u = comboBoxUser.getValue();
	                System.out.println("Update " + u.getName());
	                
	                try {
	                	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/EditUser.fxml"));
	                	EditUser controller = new EditUser();
	                	controller.setUser(u);
	                	fxmlLoader.setController(controller);
	                	AnchorPane currentPane = fxmlLoader.load();
	    	    		BorderPane border = Main.getRoot();
	    	    		border.setCenter(currentPane);
	    	    	} catch(IOException e){
	    	    		e.printStackTrace();
	    	    	}
	            }
	        });
			
			buttonUserDelete.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	User u = comboBoxUser.getValue();
	                System.out.println("Delete " + u.getName());
	                deliveryTracker.deleteUser(u);
	                updateUsersList();
	            }
	        });
	}
	
	private void updateUsersList() {
		ObservableList<User> users = FXCollections.observableArrayList();
		users.addAll(deliveryTracker.getUsers());
		comboBoxUser.setItems(users);

	}

}
