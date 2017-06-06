package controllers;

	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	
	//Static root passes to controller
	private static BorderPane root = new BorderPane();
	
	//Getter for controller
	public static BorderPane getRoot(){
		return root;
	}
	
	@Override
	public void start(Stage primaryStage) {
		//Parent root = null;
		try {
			
			MenuBar bar = FXMLLoader.load(getClass().getResource("/views/Menus.fxml"));
			AnchorPane paneMain = FXMLLoader.load(getClass().getResource("/views/MainView.fxml"));
			root.setTop(bar);
			root.setCenter(paneMain);
			
			//root = FXMLLoader.load(getClass().getClassLoader().getResource("views/CompanyInfoEditView.fxml"));
//			root = FXMLLoader.load(getClass().getClassLoader().getResource("views/ClientEdit.fxml"));
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.show();
//			Parent root = FXMLLoader.load(getClass().getResource("HelloWorld.fxml"));
//		    
//	        Scene scene = new Scene(root, 300, 275);
//	    
//	        stage.setTitle("FXML Welcome");
//	        stage.setScene(scene);
//	        stage.show();
//			
			//CompanyInfoEditView customControl = new CompanyInfoEditView();
			//stage.setScene(new Scene(customControl));
			//stage.setTitle("Custom Control");
			//stage.setWidth(300);
			//stage.setHeight(200);
			//stage.show();	        
	        
			//BorderPane root = new BorderPane();
			//Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
			//primaryStage.setTitle("ACME Couriers Delivery Tracker");
			
//			MenuBar menuBar = new MenuBar();
//	        Menu menuMaintenance = new Menu("Maintenance");
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
	        	 
//	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HelloWorld.fxml"));
////	        fxmlLoader.setRoot(this);
//	        fxmlLoader.setController(this);
//
//	        try {
//	            fxmlLoader.load();
//	        } catch (IOException exception) {
//	            throw new RuntimeException(exception);
//	        }
//			
//			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
