package controllers;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	
	@Override	
	public void initialize(URL location, ResourceBundle resources) {		
		deliveryTracker = DeliveryTracker.getDeliveryTracker();
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
		//TODO: handle money
//		textFieldBillRateBase.setText(BigDecimal.toString(deliveryTracker.getBillRateBase()));
//		textFieldBillRatePerBlock.setText(Integer.toString(deliveryTracker.getBillRatePerBlock()));
		textFieldBlocksPerMile.setText(Integer.toString(deliveryTracker.getBlocksToMile()));
		textFieldAvgCourierSpeed.setText(Double.toString(deliveryTracker.getCourierSpeed()));
		//TODO: handle money
//		textFieldOnTimeBonusAmount.setText(Integer.toString(deliveryTracker.getBonusAmount()));
		textFieldOnTimeAllowableVariance.setText(Integer.toString(deliveryTracker.getBonusTimeVariance()));

		btnSave.setOnAction(new EventHandler() {
		   @Override
			public void handle(Event event) {
			   save();
			}
		});		
		btnCancel.setOnAction(new EventHandler() {
			@Override
			public void handle(Event event) {
				//TODO: cancel out
			}
		});
	}
	
	private BigDecimal decimalFromString(String input) {
		DecimalFormat d = new DecimalFormat("'$'0.00");
		try {
			BigDecimal bd = new BigDecimal((double) d.parse(input));
			return bd;
		} catch (Exception e) {
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
		streets.addAll(CityMap.getStreets());
		comboBoxStreet.setItems(streets);
	}
	
	public void updateImpedimentAvenuesList () {
		ObservableList<Street> avenues = FXCollections.observableArrayList();
		avenues.addAll(CityMap.getAvenues());
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
			Integer i = Integer.parseInt(textFieldPickupOverhead.getText().trim());
			i = Integer.parseInt(textFieldDeliveryOverhead.getText().trim());
			i = Integer.parseInt(textFieldBlocksPerMile.getText().trim());
			i = Integer.parseInt(textFieldAvgCourierSpeed.getText().trim());
			i = Integer.parseInt(textFieldOnTimeAllowableVariance.getText().trim());
			//TODO: handle as money
//			BigDecimal bd = decimalFromString(textFieldBillRateBase.getText().trim());//new BigDecimal(textFieldBillRateBase.getText().trim());
//			bd = new BigDecimal(textFieldBillRatePerBlock.getText().trim());
//			bd = new BigDecimal(textFieldOnTimeBonusAmount.getText().trim());
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
		deliveryTracker.setCompanyLocation(new Intersection(comboBoxStreet.getValue(), comboBoxAvenue.getValue()));
		deliveryTracker.setPickupOverheadTime(Integer.parseInt(textFieldPickupOverhead.getText().trim()));
		deliveryTracker.setDeliveryOverheadTime(Integer.parseInt(textFieldDeliveryOverhead.getText().trim()));
		deliveryTracker.setBlocksToMile(Integer.parseInt(textFieldBlocksPerMile.getText().trim()));
		//TODO: convert to money
//		deliveryTracker.setBillRateBase(decimalFromString(textFieldBillRateBase.getText().trim()));
//		deliveryTracker.setBillRatePerBlock(new BigDecimal(textFieldBillRatePerBlock.getText().trim()));
		deliveryTracker.setCourierSpeed(Integer.parseInt(textFieldAvgCourierSpeed.getText().trim()));
		//TODO: convert to money
//		deliveryTracker.setBonusAmount(new BigDecimal(textFieldOnTimeBonusAmount.getText().trim()));
		deliveryTracker.setBonusTimeVariance(Integer.parseInt(textFieldOnTimeAllowableVariance.getText().trim()));
		return true;
	}
}
