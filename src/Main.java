
	
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import views.CompanyInfoEditView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//BorderPane root = new BorderPane();
			//Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
			//primaryStage.setTitle("ACME Couriers Delivery Tracker");
			
//			MenuBar menuBar = new MenuBar();
//	        Menu menuMaintenance = new Menu("Maintainance");
//	        MenuItem menuEditCompanyInformation = new MenuItem("Edit Company Information");
//	        menuEditCompanyInformation.setOnAction(
//	        		new EventHandler<ActionEvent>() {
//	        			public void handle(ActionEvent t) {
//	        				CompanyInfoEditView view = new CompanyInfoEditView();
//	            }});
//	        menuMaintenance.getItems().add(menuEditCompanyInformation);
//	        Menu menuDeliveryTracker = new Menu("Delivery Tracker");
//	        Menu menuReports = new Menu("Reports");
//	        Menu menuAccount = new Menu("Account");
//	 
//	        menuBar.getMenus().addAll(menuMaintenance, menuDeliveryTracker, menuReports, menuAccount);
	        //root.setTop(menuBar);
	        	 
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HelloWorld.fxml"));
//	        fxmlLoader.setRoot(this);
	        fxmlLoader.setController(this);

	        try {
	            fxmlLoader.load();
	        } catch (IOException exception) {
	            throw new RuntimeException(exception);
	        }
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
