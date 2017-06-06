package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class EditCourier implements Initializable {
	@FXML
    private TextField textFieldCourierNumber;

    @FXML
    private CheckBox checkBoxCourierIsActive;

    @FXML
    private TextField textFieldCourierName;

    @FXML
    private TextField textFieldCourierPhoneNumber;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
