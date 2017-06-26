package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import dtDAO.IntersectionDAO;
import dtDAO.StreetDAO;
import dtDAO.TrafficImpedimentDAO;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import model.CityMap;
import model.DeliveryTracker;
import model.Intersection;
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
		citymap = CityMap.getCityMap();
		
		if(trafficImpediment != null) {
			comboBoxTrafficImpedimentStreet.setValue(trafficImpediment.getIntersection().getStreet());
			comboBoxTrafficImpedimentAvenue.setValue(trafficImpediment.getIntersection().getAvenue());
			datePickerTrafficImpedimentStart.setValue(trafficImpediment.getStartDate().toLocalDate());
			datePickerTrafficImpedimentEnd.setValue(trafficImpediment.getEndDate().toLocalDate());
		}
		
		updateImpedimentStreetsList();
		updateImpedimentAvenuesList();
		
		btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                	if(save()){                	
    	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/SelectTrafficImpediment.fxml"));
    	    		BorderPane border = Main.getRoot();
    	    		border.setCenter(currentPane);
    	    		}
    	    	} catch(IOException e){
    	    		e.printStackTrace();
    	    	}
            }
        });
		
		comboBoxTrafficImpedimentAvenue.setConverter(
	            new StringConverter<Street>() {
	                @Override
	                public Street fromString(String s) {
                        return null;
	                }

					@Override
					public String toString(Street object) {
						if (object == null) {
	                        return "";
	                    } else {
	                        return object.getName();
	                    }
					}
	            });
		
		comboBoxTrafficImpedimentStreet.setConverter(
	            new StringConverter<Street>() {
	                @Override
	                public Street fromString(String s) {
                        return null;
	                }

					@Override
					public String toString(Street object) {
						if (object == null) {
	                        return "";
	                    } else {
	                        return object.getName();
	                    }
					}
	            });
		
		btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Cancel");
                try {
    	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/SelectTrafficImpediment.fxml"));
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
		trafficImpedimentStreets.addAll(citymap.getWholeStreets());
		comboBoxTrafficImpedimentStreet.setItems(trafficImpedimentStreets);
	}
	
	public void updateImpedimentAvenuesList () {
		ObservableList<Street> trafficImpedimentAvenues = FXCollections.observableArrayList();
		trafficImpedimentAvenues.addAll(citymap.getWholeAvenues());
		comboBoxTrafficImpedimentAvenue.setItems(trafficImpedimentAvenues);
	}
	
	public void setTrafficImpediment(TrafficImpediment ti) {
		this.trafficImpediment = ti;
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
		if(datePickerTrafficImpedimentStart.getValue().isAfter(datePickerTrafficImpedimentEnd.getValue())){
			Alert a = new Alert(AlertType.ERROR);
	        a.setTitle("Error");
	        a.setHeaderText("Incorrect Date Range");
	        a.setContentText("The Start Date is after the selected End Date");
	        a.showAndWait();
			return false;
		}
		if(datePickerTrafficImpedimentEnd.getValue().isBefore(LocalDate.now())){
			Alert a = new Alert(AlertType.ERROR);
	        a.setTitle("Error");
	        a.setHeaderText("Traffic Impediment Expired");
	        a.setContentText("The End Date of the Traffic Impediment is before Today's Date.");
	        a.showAndWait();
			return false;
		}
		if(trafficImpediment == null) {
			trafficImpediment = new TrafficImpediment(null, null, null);
			DeliveryTracker deliveryTracker = DeliveryTracker.getDeliveryTracker();
			Intersection newIntersection = IntersectionDAO.findIntersectionByStreets(comboBoxTrafficImpedimentStreet.getValue(), comboBoxTrafficImpedimentAvenue.getValue());
			
			trafficImpediment.setIntersection(newIntersection);
			trafficImpediment.setStartDate(datePickerTrafficImpedimentStart.getValue().atStartOfDay());
			trafficImpediment.setEndDate(datePickerTrafficImpedimentEnd.getValue().atTime(23, 59));
			deliveryTracker.addTrafficImpediment(trafficImpediment);
			return true;
		}
		else{
			DeliveryTracker deliveryTracker = DeliveryTracker.getDeliveryTracker();
			Intersection newIntersection = IntersectionDAO.findIntersectionByStreets(comboBoxTrafficImpedimentStreet.getValue(), comboBoxTrafficImpedimentAvenue.getValue());
			Intersection oldIntersection = trafficImpediment.getIntersection();
			oldIntersection.setAvailable(true);
			IntersectionDAO.saveIntersection(oldIntersection);
			List<Street> affectedStreets = StreetDAO.affectedStreets(trafficImpediment.getIntersection());
			for(Street s : affectedStreets){
				Street updatedStreet = s;
				s.setWeight(1);
				StreetDAO.saveStreet(s);
			}
			trafficImpediment.setIntersection(newIntersection);
			trafficImpediment.setStartDate(datePickerTrafficImpedimentStart.getValue().atStartOfDay());
			trafficImpediment.setEndDate(datePickerTrafficImpedimentEnd.getValue().atTime(23,59));

			TrafficImpedimentDAO.saveTrafficImpediment(trafficImpediment);
			return true;
		}
	}
	
	private boolean validate() {
		
		if(comboBoxTrafficImpedimentStreet.getValue() == null) 
			return false;
		if(comboBoxTrafficImpedimentAvenue.getValue() == null)
			return false;
		if(datePickerTrafficImpedimentStart.getValue() == null)
			return false;
		if(datePickerTrafficImpedimentEnd.getValue() == null)
			return false;
		return true;
	}
}
