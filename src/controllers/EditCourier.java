package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import model.Courier;

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
    
    private Courier courier;
	public Courier getCourier() {
		return courier;
	}
	public void setCourier(Courier courier) {
		this.courier = courier;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if(courier != null){
			textFieldCourierName.setText(courier.getName());
			textFieldCourierNumber.setText(Integer.toString(courier.getCourierNumber()));
			textFieldCourierPhoneNumber.setText(courier.getPhoneNumber());
			checkBoxCourierIsActive.setSelected(courier.getIsActive());
		}
	}

}
