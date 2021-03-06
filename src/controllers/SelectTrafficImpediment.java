package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import dtDAO.TrafficImpedimentDAO;
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
import model.DeliveryTracker;
import model.TrafficImpediment;

public class SelectTrafficImpediment implements Initializable {

	DeliveryTracker deliveryTracker;
	
	@FXML
    private ComboBox<TrafficImpediment> comboBoxTrafficImpediment;

    @FXML
    private Button buttonTrafficImpedimentAdd;

    @FXML
    private Button buttonTrafficImpedimentUpdate;

    @FXML
    private Button buttonTrafficImpedimentDelete;
	
	DateTimeFormatter formatter;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		deliveryTracker = DeliveryTracker.getDeliveryTracker();
		formatter = DateTimeFormatter.ofPattern("MMM dd YYYY");
		updateTrafficImpedimentsList();
		comboBoxTrafficImpediment.setConverter(
		            new StringConverter<TrafficImpediment>() {
		                @Override
		                public TrafficImpediment fromString(String s) {
	                        return null;
		                }

						@Override
						public String toString(TrafficImpediment object) {
							if (object == null) {
		                        return "";
		                    } else {
		                        return object.getIntersection().getName() + " (" + object.getStartDate().format(formatter) + " - " + object.getEndDate().format(formatter) + ")";
		                    }
						}
		            });
		
			buttonTrafficImpedimentAdd.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                try {	    	    		
	                	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/EditTrafficImpediment.fxml"));
	                	EditTrafficImpediment controller = new EditTrafficImpediment();
	                	fxmlLoader.setController(controller);
	                	AnchorPane currentPane = fxmlLoader.load();
	                	BorderPane border = Main.getRoot();
	    	    		border.setCenter(currentPane);
	    	    	} catch(IOException e){
	    	    		e.printStackTrace();
	    	    	}
	            }
	        });
			
			buttonTrafficImpedimentUpdate.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	TrafficImpediment ti = comboBoxTrafficImpediment.getValue();
	                
	                try {
	                	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/EditTrafficImpediment.fxml"));
	                	EditTrafficImpediment controller = new EditTrafficImpediment();
	                	controller.setTrafficImpediment(ti);
	                	fxmlLoader.setController(controller);
	                	AnchorPane currentPane = fxmlLoader.load();
	    	    		BorderPane border = Main.getRoot();
	    	    		border.setCenter(currentPane);
	    	    	} catch(IOException e){
	    	    		e.printStackTrace();
	    	    	}
	            }
	        });
			
			buttonTrafficImpedimentDelete.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	boolean success;
	            	TrafficImpediment ti = comboBoxTrafficImpediment.getValue();
	                Alert a = new Alert(AlertType.CONFIRMATION);
        	        a.setTitle("Confirm Deletion");
        	        a.setContentText("Are you sure you want to delete " + ti.getIntersection().getName() + "?");
	                a.showAndWait()
	                	.filter(response -> response == ButtonType.OK)
	                	.ifPresent(response -> TrafficImpedimentDAO.removeTrafficImpediment(ti));
	                updateTrafficImpedimentsList();
	            }
	        });
	}
	
	private void updateTrafficImpedimentsList(){
		ObservableList<TrafficImpediment> trafficImpediments = FXCollections.observableArrayList();
		trafficImpediments.addAll(deliveryTracker.getTrafficImpediments());
		comboBoxTrafficImpediment.setItems(trafficImpediments);
		if(trafficImpediments.size() > 0)
			comboBoxTrafficImpediment.setValue(trafficImpediments.get(0));
	}

}
