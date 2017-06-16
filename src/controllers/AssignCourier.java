package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dtDAO.CourierDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.Courier;
import model.DeliveryTicket;

public class AssignCourier implements Initializable{

    @FXML
    private ComboBox<Courier> comboBoxAssignCourier;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;
    
    DeliveryTicket deliveryTicket;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Courier> courierList = FXCollections.observableArrayList();
		courierList.addAll(getAvailableCouriers());
		comboBoxAssignCourier.setItems(courierList);
		
		btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
	            		if(comboBoxAssignCourier.getValue() != null)
	            		{
		            		deliveryTicket.setCourier(comboBoxAssignCourier.getValue());
		            		AnchorPane currentPane;
							currentPane = FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
		    	    		BorderPane border = Main.getRoot();
		    	    		border.setCenter(currentPane);
		            	}
		            	else {
		            		Alert a = new Alert(AlertType.ERROR);
		        	        a.setTitle("Error");
		        	        a.setHeaderText("No Courier Selected");
		        	        a.setContentText("Please select a courier and try again.");
		        	        a.showAndWait();
		        	        return;
		            	}
            	} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
		
		btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
    	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
    	    		BorderPane border = Main.getRoot();
    	    		border.setCenter(currentPane);
    	    	} catch(IOException e){
    	    		e.printStackTrace();
    	    	}
            }
        });
	}
	
	private List<Courier> getAvailableCouriers() {
		List<Courier> allCouriers = CourierDAO.listCourier();
		System.out.println(allCouriers);
		List<Courier> avaiableCouriers = new ArrayList<Courier>();
		for(Courier courier: allCouriers) {
			System.out.println(courier.getName());
			if(courier.getIsActive() == true && courier.isAvailable())
				avaiableCouriers.add(courier);
		}
		return avaiableCouriers;
	}
	
	public void setDeliveryTicket(DeliveryTicket dt) {
		this.deliveryTicket = dt;
	}

}
