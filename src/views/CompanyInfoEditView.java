package views;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CompanyInfoEditView implements javafx.fxml.Initializable {

	@FXML 
	private Button btnSave;
	@FXML 
	private Button btnCancel;
	@FXML 
	private TextField textFieldCompanyName;
	@FXML 
	private TextField textFieldStreet;
	
	@Override	
	public void initialize(URL location, ResourceBundle resources) {		
		btnSave.setOnAction(new EventHandler() {
		   @Override
			public void handle(Event event) {
				textFieldCompanyName.setText("ACME");
				try {
					Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("views/ClientEdit.fxml"));
					Scene parentScene = new Scene(parent);
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					stage.setScene(parentScene);
					stage.show();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});		
	}
}
