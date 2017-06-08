package controllers;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.DeliveryTracker;

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
    private ComboBox<?> comboBoxStreet;

    @FXML
    private ComboBox<?> comboBoxAvenue;
    
    DeliveryTracker deliveryTracker;
	
	@Override	
	public void initialize(URL location, ResourceBundle resources) {		
		deliveryTracker = DeliveryTracker.getDeliveryTracker();
		//TODO: populate fields on entry
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
	
	private boolean validate() {
		if(textFieldCompanyName.getText().trim().isEmpty())
			return false;
		try {
			Integer i = Integer.getInteger(textFieldPickupOverhead.getText().trim());
			i = Integer.getInteger(textFieldDeliveryOverhead.getText().trim());
			i = Integer.getInteger(textFieldBlocksPerMile.getText().trim());
			i = Integer.getInteger(textFieldAvgCourierSpeed.getText().trim());
			i = Integer.getInteger(textFieldOnTimeAllowableVariance.getText().trim());
			//TODO: handle as money
			BigDecimal bd = new BigDecimal(textFieldBillRateBase.getText().trim());
			bd = new BigDecimal(textFieldBillRatePerBlock.getText().trim());
			bd = new BigDecimal(textFieldOnTimeBonusAmount.getText().trim());
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
		//TODO: save street & avenue
		deliveryTracker.setPickupOverheadTime(5);

		deliveryTracker.setPickupOverheadTime(Integer.getInteger(textFieldPickupOverhead.getText().trim()).intValue());
		deliveryTracker.setDeliveryOverheadTime(Integer.getInteger(textFieldDeliveryOverhead.getText().trim()));
		deliveryTracker.setBlocksToMile(Integer.getInteger(textFieldBlocksPerMile.getText().trim()));
		//TODO: convert to money
		deliveryTracker.setBillRateBase(new BigDecimal(textFieldBillRateBase.getText().trim()));
		deliveryTracker.setBillRatePerBlock(new BigDecimal(textFieldBillRatePerBlock.getText().trim()));
		deliveryTracker.setCourierSpeed(Integer.getInteger(textFieldAvgCourierSpeed.getText().trim()));
		//TODO: convert to money
		deliveryTracker.setBonusAmount(new BigDecimal(textFieldOnTimeBonusAmount.getText().trim()));
		deliveryTracker.setBonusTimeVariance(Integer.getInteger(textFieldOnTimeAllowableVariance.getText().trim()));
		return true;
	}
}
