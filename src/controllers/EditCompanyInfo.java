package controllers;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private ChoiceBox<?> choiceBoxAvenue;

    @FXML
    private ChoiceBox<?> choiceBoxStreet;

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
	
	@Override	
	public void initialize(URL location, ResourceBundle resources) {		
		btnSave.setOnAction(new EventHandler() {
		   @Override
			public void handle(Event event) {
				textFieldCompanyName.setText("ACME");
				try {
					Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("/views/EditClient.fxml"));
					Scene parentScene = new Scene(parent);
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//					Scene oldScene = stage.getScene();
					stage.setScene(parentScene);
					stage.show();
					
					save();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});		
	}
	
	private boolean validate() {
		try {
			BigDecimal billRateBase = new BigDecimal(textFieldBillRateBase.getText());
		}
		catch(Exception e) {
			return false;
		}
			
		return true;
	}
	
	private void save() {
		if (validate()) {
			DeliveryTracker companyInfo = new DeliveryTracker();
			companyInfo.setBillRateBase(new BigDecimal(textFieldBillRateBase.getText()));
			System.out.println(companyInfo.getBillRateBase());
		}
		else {
			System.out.println("Validation Error");
		}
	}
}
