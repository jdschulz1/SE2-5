package views;

import java.io.IOException;
import java.text.DecimalFormat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CompanyInfoEditView extends AnchorPane {

	public CompanyInfoEditView () {
		
//	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HelloWorld.fxml"));
//	    fxmlLoader.setRoot(this);
//	    fxmlLoader.setController(this);
	    
	    try {
			Parent root = FXMLLoader.load(getClass().getResource("HelloWorld.fxml"));

//	        fxmlLoader.load();            
	    } catch (IOException exception) {
	        throw new RuntimeException(exception);
	    }
	}
}
		
		
//		Parent root = FXMLLoader.load(getClass().getResource("HelloWorld.fxml"));
//	    
//        Scene scene = new Scene(root, 300, 275);
//		Stage stage = new Stage();
//        stage.setTitle("FXML Welcome");
//        stage.setScene(scene);
//        stage.show();
		
//		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
//				"HelloWorld.fxml"));
//				        fxmlLoader.setRoot(this);
//				        fxmlLoader.setController(this);
//
//				        try {
//				            fxmlLoader.load();
//				        } catch (IOException exception) {
//				            throw new RuntimeException(exception);
//				        }
//				    }
		
//		DecimalFormat df; 
//		df = new DecimalFormat("$#,##0.00");
//		
//		Stage stage = new Stage();
//		stage.setTitle("Edit Company Information");
//		BorderPane root = new BorderPane();
//		
//		Scene scene = new Scene(root, 300, 200);
//		stage.setScene(scene);
//		
//	    Button saveButton = new Button("Save");
//	    		
//		saveButton.setPrefWidth(scene.getWidth());
//		saveButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//		root.setBottom(saveButton);
//			
//		stage.show();
//	}
	
//}
