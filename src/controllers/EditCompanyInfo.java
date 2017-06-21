package controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import dtDAO.ClientDAO;

import dtDAO.DeliveryTrackerDAO;
import dtDAO.IntersectionDAO;
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
import javafx.util.StringConverter;
import model.CityMap;
import model.DeliveryTracker;
import model.Intersection;
import model.Street;

public class EditCompanyInfo implements javafx.fxml.Initializable {

	@FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField textFieldPickupOverhead;

    @FXML
    private TextField textFieldCompanyName;

    @FXML
    private TextField textFieldDeliveryOverhead;

    @FXML
    private TextField textFieldBillRateBase;

    @FXML
    private TextField textFieldBillRatePerBlock;

    @FXML
    private TextField textFieldBlocksPerMile;

    @FXML
    private TextField textFieldAvgCourierSpeed;

    @FXML
    private TextField textFieldOnTimeBonusAmount;

    @FXML
    private TextField textFieldOnTimeAllowableVariance;

    @FXML
    private ComboBox<Street> comboBoxStreet;

    @FXML
    private ComboBox<Street> comboBoxAvenue;
    
    DeliveryTracker deliveryTracker;
    CityMap cityMap;
	
	@Override	
	public void initialize(URL location, ResourceBundle resources) {		
		deliveryTracker = DeliveryTracker.getDeliveryTracker();
		cityMap = CityMap.getCityMap();
		if(deliveryTracker.getCurrentUser().getRole().equals("User")){
			btnSave.setDisable(true);
		}
		else {
			btnSave.setDisable(false);
		}
		comboBoxStreet.setConverter(
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
		
		comboBoxAvenue.setConverter(
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
		updateImpedimentStreetsList();
		updateImpedimentAvenuesList();
		
		textFieldCompanyName.setText(deliveryTracker.getCompanyName());
		if(deliveryTracker.getCompanyLocation() != null) {
			comboBoxStreet.setValue(deliveryTracker.getCompanyLocation().getStreet());
			comboBoxAvenue.setValue(deliveryTracker.getCompanyLocation().getAvenue());
		}
		textFieldPickupOverhead.setText(Integer.toString(deliveryTracker.getPickupOverheadTime()));
		textFieldDeliveryOverhead.setText(Integer.toString(deliveryTracker.getDeliveryOverheadTime()));
		textFieldBillRateBase.setText(stringFromBigDecimal(deliveryTracker.getBillRateBase()));
		textFieldBillRatePerBlock.setText(stringFromBigDecimal(deliveryTracker.getBillRatePerBlock()));
		textFieldBlocksPerMile.setText(Integer.toString(deliveryTracker.getBlocksToMile()));
		textFieldAvgCourierSpeed.setText(Double.toString(deliveryTracker.getCourierSpeed()));
		textFieldOnTimeBonusAmount.setText(stringFromBigDecimal(deliveryTracker.getBonusAmount()));
		textFieldOnTimeAllowableVariance.setText(Integer.toString(deliveryTracker.getBonusTimeVariance()));

		btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                	if(save()) {
	    	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
	    	    		BorderPane border = Main.getRoot();
	    	    		border.setCenter(currentPane);
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
    	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
    	    		BorderPane border = Main.getRoot();
    	    		border.setCenter(currentPane);
    	    	} catch(IOException e){
    	    		e.printStackTrace();
    	    	}
            }
        });
	}
	
	private BigDecimal decimalFromString(String input) {
		try {
			String str=input.replaceAll(Pattern.quote("$"),"");
			BigDecimal bd=new BigDecimal(str);
			return bd;
		} catch (Exception e) {
			System.out.println("Error");
			return null;
		}
	}
	
	private String stringFromBigDecimal(BigDecimal input) {
		DecimalFormat d = new DecimalFormat("'$'0.00");
		try {
			String s = d.format(input.doubleValue());
			return s;
		} catch (Exception e) {
			return null;
		}
	}
	
	public void updateImpedimentStreetsList () {
		ObservableList<Street> streets = FXCollections.observableArrayList();
		streets.addAll(cityMap.getWholeStreets());
		comboBoxStreet.setItems(streets);
	}
	
	public void updateImpedimentAvenuesList () {
		ObservableList<Street> avenues = FXCollections.observableArrayList();
		avenues.addAll(cityMap.getWholeAvenues());
		comboBoxAvenue.setItems(avenues);
	}
	
	private boolean validate() {
		if(textFieldCompanyName.getText().trim().isEmpty())
			return false;
		if(comboBoxStreet.getValue() == null)
			return false;
		if(comboBoxAvenue.getValue() == null)
			return false;
		try {
			Integer.parseInt(textFieldPickupOverhead.getText().trim());
			Integer.parseInt(textFieldDeliveryOverhead.getText().trim());
			Integer.parseInt(textFieldBlocksPerMile.getText().trim());
			Integer.parseInt(textFieldOnTimeAllowableVariance.getText().trim());
			Double.parseDouble(textFieldAvgCourierSpeed.getText().trim());
			
			BigDecimal bd = decimalFromString(textFieldBillRateBase.getText().trim());
			if(bd == null) 
				return false;
			bd = decimalFromString(textFieldBillRatePerBlock.getText().trim());
			if(bd == null) 
				return false;
			bd = decimalFromString(textFieldOnTimeBonusAmount.getText().trim());
			if(bd == null) 
				return false;
		}
		catch(Exception e) {
			return false;
		}
					
		return true;
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
		deliveryTracker.setCompanyName(textFieldCompanyName.getText().trim());
		deliveryTracker.setCompanyLocation(IntersectionDAO.findIntersectionByStreets(comboBoxStreet.getValue(), comboBoxAvenue.getValue()));
		deliveryTracker.setPickupOverheadTime(Integer.parseInt(textFieldPickupOverhead.getText().trim()));
		deliveryTracker.setDeliveryOverheadTime(Integer.parseInt(textFieldDeliveryOverhead.getText().trim()));
		deliveryTracker.setBlocksToMile(Integer.parseInt(textFieldBlocksPerMile.getText().trim()));
		deliveryTracker.setBillRateBase(decimalFromString(textFieldBillRateBase.getText().trim()));
		deliveryTracker.setBillRatePerBlock(decimalFromString(textFieldBillRatePerBlock.getText().trim()));
		deliveryTracker.setCourierSpeed(Double.parseDouble(textFieldAvgCourierSpeed.getText().trim()));
		deliveryTracker.setBonusAmount(decimalFromString(textFieldOnTimeBonusAmount.getText().trim()));
		deliveryTracker.setBonusTimeVariance(Integer.parseInt(textFieldOnTimeAllowableVariance.getText().trim()));
		DeliveryTrackerDAO.save(deliveryTracker);
		return true;
	}
}
