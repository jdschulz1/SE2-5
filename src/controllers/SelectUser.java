package controllers;


import java.io.IOException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dtDAO.ClientDAO;
import dtDAO.DeliveryTicketDAO;
import dtDAO.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import model.User;
import model.Courier;
import model.DeliveryTicket;
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
		if(deliveryTracker.getCurrentUser().getRole().equals("User")){
			buttonUserAdd.setDisable(true);
			buttonUserDelete.setDisable(true);
		}
		else {
			buttonUserAdd.setDisable(false);
			buttonUserDelete.setDisable(false);
		}
		comboBoxUser.setConverter(
		            new StringConverter<User>() {
		                @Override
		                public User fromString(String s) {
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
	            	
	            	List<DeliveryTicket> ticketsForUser = DeliveryTicketDAO.findDeliveryTicketsByUser(u);
	                if(ticketsForUser.size() > 0)
	                {
	                	Alert a = new Alert(AlertType.ERROR);
	        	        a.setContentText("Cannot delete " + u.getName() + " - they have existing delivery tickets.");
		                a.showAndWait();
	                } else {
		                Alert a = new Alert(AlertType.CONFIRMATION);
	        	        a.setTitle("Confirm Deletion");
	        	        a.setContentText("Are you sure you want to delete " + u.getName() + "?");
		                a.showAndWait()
		                	.filter(response -> response == ButtonType.OK)
		                	.ifPresent(response -> UserDAO.removeUser(u));
		                updateUsersList();
	                }
	            }
	        });
	}
	
	private void updateUsersList() {
		ObservableList<User> users = FXCollections.observableArrayList();
		if(deliveryTracker.getCurrentUser().getRole().equals("User")){
			users.addAll(deliveryTracker.getCurrentUser());
		}
		else {
			users.addAll(deliveryTracker.getUsers());
		}
//		ObservableList<User> users = FXCollections.observableArrayList();
//		users.addAll(deliveryTracker.getUsers());
		comboBoxUser.setItems(users);
		if(users.size() > 0)
			comboBoxUser.setValue(users.get(0));
	}

}
