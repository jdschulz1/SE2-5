package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
public class EditTrafficImpeditment implements Initializable {

	@FXML
    private ChoiceBox<?> choiceBoxTrafficImpedimentStreet;

    @FXML
    private ChoiceBox<?> choiceBoxTrafficImpedimentAvenue;

    @FXML
    private DatePicker datePickerTrafficImpedimentStart;

    @FXML
    private DatePicker datePickerTrafficImpedimentEnd;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
