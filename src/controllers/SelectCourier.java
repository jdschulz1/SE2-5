package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dtDAO.ClientDAO;
import dtDAO.CourierDAO;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import model.Client;
import model.Courier;
import model.DeliveryTracker;
import model.User;

public class SelectCourier implements Initializable {
	DeliveryTracker deliveryTracker;
	
    @FXML
    private ComboBox<Courier> comboBoxCourier;

    @FXML
    private Button buttonCourierAdd;

    @FXML
    private Button buttonCourierUpdate;

    @FXML
    private Button buttonCourierDelete;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		deliveryTracker = DeliveryTracker.getDeliveryTracker();
		updateCourierList();
		
		comboBoxCourier.setConverter(
				new StringConverter<Courier>() {
					@Override
					public Courier fromString(String s){
						return null;
					}

					@Override
					public String toString(Courier object) {
						// TODO Auto-generated method stub
						if (object == null){
							return "";
						}else {
							return object.getName();
						}
					}
				});
		
		buttonCourierAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				try{
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/EditCourier.fxml"));
					EditCourier controller = new EditCourier();
					fxmlLoader.setController(controller);
					AnchorPane currentPane = fxmlLoader.load();			
					BorderPane border = Main.getRoot();
					border.setCenter(currentPane);
				}catch (IOException e){
					e.printStackTrace();
				}
			}
		});
		
		buttonCourierUpdate.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				Courier c = comboBoxCourier.getValue();
				
				try {
                	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/EditCourier.fxml"));
                	EditCourier controller = new EditCourier();
                	controller.setCourier(c);
                	fxmlLoader.setController(controller);
                	AnchorPane currentPane = fxmlLoader.load();
    	    		BorderPane border = Main.getRoot();
    	    		border.setCenter(currentPane);
    	    	} catch(IOException e){
    	    		e.printStackTrace();
    	    	}
			}
		});
		
		buttonCourierDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Courier c = comboBoxCourier.getValue();
                Alert a = new Alert(AlertType.CONFIRMATION);
    	        a.setTitle("Confirm Deletion");
    	        a.setContentText("Are you sure you want to delete " + c.getName() + "?");
                a.showAndWait()
                	.filter(response -> response == ButtonType.OK)
                	.ifPresent(response -> CourierDAO.removeCourier(c));
                updateCourierList();
            }
        });
		
	}	

	private void updateCourierList() {
		ObservableList<Courier> couriers = FXCollections.observableArrayList();
		couriers.addAll(deliveryTracker.getCouriers());
		comboBoxCourier.setItems(couriers);
		if(couriers.size() > 0)
			comboBoxCourier.setValue(couriers.get(0));
	}

}