package views;

import java.text.DecimalFormat;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CompanyInfoEditView {

	public CompanyInfoEditView () {
		
		DecimalFormat df; 
		df = new DecimalFormat("$#,##0.00");
		
		Stage stage = new Stage();
		stage.setTitle("Edit Company Information");
		BorderPane root = new BorderPane();
		
		Scene scene = new Scene(root, 300, 200);
		stage.setScene(scene);
		
	    Button saveButton = new Button("Save");
	    		
		saveButton.setPrefWidth(scene.getWidth());
		saveButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		root.setBottom(saveButton);
			
		stage.show();
	}
	
}
