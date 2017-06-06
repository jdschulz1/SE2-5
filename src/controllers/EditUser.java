package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class EditUser implements Initializable {
	  @FXML
	    private ChoiceBox<?> choiceBoxUserRole;

	    @FXML
	    private TextField textFieldUserUsername;

	    @FXML
	    private TextField textFieldUserPassword;

	    @FXML
	    private TextField textFieldUserEmail;

	    @FXML
	    private TextField textFieldUserName;

	    @FXML
	    private Button btnSave;

	    @FXML
	    private Button btnCancel;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
