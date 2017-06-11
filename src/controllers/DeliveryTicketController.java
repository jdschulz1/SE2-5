package controllers;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.util.converter.LocalTimeStringConverter;

public class DeliveryTicketController implements javafx.fxml.Initializable {

    @FXML
    private ComboBox<?> comboBoxOrderTaker;

    @FXML
    private ComboBox<?> comboBoxPickupClient;

    @FXML
    private ComboBox<?> comboBoxDeliveryClient;

    @FXML
    private ComboBox<?> comboBoxPayingClient;

    @FXML
    private ComboBox<?> comboBoxCourier;

    @FXML
    private DatePicker dateTimePickerOrderDate;

    @FXML
    private TextArea textAreaSpecialRemarks;

    @FXML
    private Spinner<LocalTime> spinnerBoxRequestedPickupTime;

    @FXML
    private Label labelEstimatedDeliveryTime;

    @FXML
    private Label labelPackageID;

    @FXML
    private Label labelCalculatedDepartureTime;

    @FXML
    private Label labelTotalDistance;

    @FXML
    private Label labelPrice;

    @FXML
    private CheckBox chkBoxDeliveryConfirmed;

    @FXML
    private Spinner<?> spinnerActualDepartureTime;

    @FXML
    private ComboBox<?> comboBoxAMPMActualDepartureTime;

    @FXML
    private Spinner<?> spinnerActualPickUpTime;

    @FXML
    private ComboBox<?> comboBoxAMPMActualPickupTime;

    @FXML
    private Spinner<?> spinnerActualDeliveryTime;

    @FXML
    private ComboBox<?> comboBoxAMPMActualDeliveryTime;

    @FXML
    private Spinner<?> spinnerActualReturnTIme;

    @FXML
    private ComboBox<?> comboBoxAMPMActualReturnTIme;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonGenerateQuote;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	
	}

}
