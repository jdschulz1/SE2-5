package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.CityMap;
import model.Client;
import model.DeliveryTracker;
import model.Street;
import model.TrafficImpediment;
public class EditTrafficImpediment implements Initializable {

	CityMap citymap;
	
	@FXML
    private ComboBox<Street> comboBoxTrafficImpedimentStreet;

    @FXML
    private ComboBox<Street> comboBoxTrafficImpedimentAvenue;

    @FXML
    private DatePicker datePickerTrafficImpedimentStart;

    @FXML
    private DatePicker datePickerTrafficImpedimentEnd;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;
    
    TrafficImpediment trafficImpediment;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println("Traffic Impediment Edit");
		citymap = CityMap.getCityMap();
		
		if(trafficImpediment != null) {
			datePickerTrafficImpedimentStart.setValue(trafficImpediment.getStartDate().toLocalDate());
			datePickerTrafficImpedimentEnd.setValue(trafficImpediment.getEndDate().toLocalDate());
			updateImpedimentStreetsList();
			updateImpedimentAvenuesList();
		}
		btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Cancel");
                try {
                	//TODO: Save Traffic Impediment data
    	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/SelectClient.fxml"));
    	    		BorderPane border = Main.getRoot();
    	    		border.setCenter(currentPane);
    	    	} catch(IOException e){
    	    		e.printStackTrace();
    	    	}
            }
        });
		btnCancel.setOnAction(new EventHandler<ActionEvent>() {
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
	
	public void updateImpedimentStreetsList () {
		ObservableList<Street> trafficImpedimentStreets = FXCollections.observableArrayList();
		trafficImpedimentStreets.addAll(citymap.getStreets());
		comboBoxTrafficImpedimentStreet.setItems(trafficImpedimentStreets);
	}
	
	public void updateImpedimentAvenuesList () {
		ObservableList<Street> trafficImpedimentAvenues = FXCollections.observableArrayList();
		trafficImpedimentAvenues.addAll(citymap.getAvenues());
		comboBoxTrafficImpedimentAvenue.setItems(trafficImpedimentAvenues);
	}
	
	public void setTrafficImpediment(TrafficImpediment ti) {
		this.trafficImpediment = ti;
		System.out.println("Setting traffic impediment to " + ti.getIntersection().getName());
	}

}
