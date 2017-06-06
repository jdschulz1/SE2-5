package controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Menus implements Initializable {
	  @FXML // ResourceBundle that was given to the FXMLLoader
	    private ResourceBundle resources;

	    @FXML // URL location of the FXML file that was given to the FXMLLoader
	    private URL location;

	    @FXML // fx:id="displayMain"
	    private MenuItem displayMain; // Value injected by FXMLLoader

	    @FXML // fx:id="displayAddSearch"
	    private MenuItem displayAddSearch; // Value injected by FXMLLoader

	    @FXML // fx:id="displayCourierPerformance"
	    private MenuItem displayCourierPerformance; // Value injected by FXMLLoader

	    @FXML // fx:id="displayInvoice"
	    private MenuItem displayInvoice; // Value injected by FXMLLoader

	    @FXML // fx:id="displayClientDeliveries"
	    private MenuItem displayClientDeliveries; // Value injected by FXMLLoader

	    @FXML // fx:id="displayCompanyInfoMaintenance"
	    private MenuItem displayCompanyInfoMaintenance; // Value injected by FXMLLoader

	    @FXML // fx:id="displayClientMaintenance"
	    private MenuItem displayClientMaintenance; // Value injected by FXMLLoader

	    @FXML // fx:id="displayCourierMaintenance"
	    private MenuItem displayCourierMaintenance; // Value injected by FXMLLoader

	    @FXML // fx:id="displayUserMaintenance"
	    private MenuItem displayUserMaintenance; // Value injected by FXMLLoader

	    @FXML // fx:id="displayTrafficImpedimentsMaintenance"
	    private MenuItem displayTrafficImpedimentsMaintenance; // Value injected by FXMLLoader

	    @FXML // fx:id="displayLogout"
	    private MenuItem displayLogout; // Value injected by FXMLLoader

	    @FXML
	    void switchToAddSearch(ActionEvent event) {
	    	try {
	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/AddSearchDeliveryTickets.fxml"));
	    		BorderPane border = Main.getRoot();
	    		border.setCenter(currentPane);
	    	} catch(IOException e){
	    		e.printStackTrace();
	    	}
	    }

	    @FXML
	    void switchToClientDeliveries(ActionEvent event) {
	    	try {
	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/ClientDeliveryReport.fxml"));
	    		BorderPane border = Main.getRoot();
	    		border.setCenter(currentPane);
	    	} catch(IOException e){
	    		e.printStackTrace();
	    	}
	    }

	    @FXML
	    void switchToClientMaintenance(ActionEvent event) {
	    	try {
	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/SelectClient.fxml"));
	    		BorderPane border = Main.getRoot();
	    		border.setCenter(currentPane);
	    	} catch(IOException e){
	    		e.printStackTrace();
	    	}
	    }

	    @FXML
	    void switchToCompanyInfoMaintenance(ActionEvent event) {
	    	try {
	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/EditCompanyInfo.fxml"));
	    		BorderPane border = Main.getRoot();
	    		border.setCenter(currentPane);
	    	} catch(IOException e){
	    		e.printStackTrace();
	    	}
	    }

	    @FXML
	    void switchToCourierMaintenance(ActionEvent event) {
	    	try {
	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/SelectCourier.fxml"));
	    		BorderPane border = Main.getRoot();
	    		border.setCenter(currentPane);
	    	} catch(IOException e){
	    		e.printStackTrace();
	    	}
	    }

	    @FXML
	    void switchToCourierPerformance(ActionEvent event) {
	    	try {
	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/CourierPerformance.fxml"));
	    		BorderPane border = Main.getRoot();
	    		border.setCenter(currentPane);
	    	} catch(IOException e){
	    		e.printStackTrace();
	    	}
	    }

	    @FXML
	    void switchToInvoice(ActionEvent event) {
	    	try {
	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/Invoice.fxml"));
	    		BorderPane border = Main.getRoot();
	    		border.setCenter(currentPane);
	    	} catch(IOException e){
	    		e.printStackTrace();
	    	}
	    }

	    @FXML
	    void switchToLogout(ActionEvent event) {
	    	try {
	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
	    		BorderPane border = Main.getRoot();
	    		border.setCenter(currentPane);
	    	} catch(IOException e){
	    		e.printStackTrace();
	    	}
	    }

	    @FXML
	    void switchToMain(ActionEvent event) {
	    	try {
	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
	    		BorderPane border = Main.getRoot();
	    		border.setCenter(currentPane);
	    	} catch(IOException e){
	    		e.printStackTrace();
	    	}
	    }

	    @FXML
	    void switchToTrafficImpedimentsMaintenance(ActionEvent event) {
	    	try {
	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/SelectTrafficImpediment.fxml"));
	    		BorderPane border = Main.getRoot();
	    		border.setCenter(currentPane);
	    	} catch(IOException e){
	    		e.printStackTrace();
	    	}
	    }

	    @FXML
	    void switchToUserMaintenance(ActionEvent event) {
	    	try {
	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/SelectUser.fxml"));
	    		BorderPane border = Main.getRoot();
	    		border.setCenter(currentPane);
	    	} catch(IOException e){
	    		e.printStackTrace();
	    	}
	    }
	    

	    @FXML // This method is called by the FXMLLoader when initialization is complete
	    void initialize() {
	        assert displayMain != null : "fx:id=\"displayMain\" was not injected: check your FXML file 'Menus.fxml'.";
	        assert displayAddSearch != null : "fx:id=\"displayAddSearch\" was not injected: check your FXML file 'Menus.fxml'.";
	        assert displayCourierPerformance != null : "fx:id=\"displayCourierPerformance\" was not injected: check your FXML file 'Menus.fxml'.";
	        assert displayInvoice != null : "fx:id=\"displayInvoice\" was not injected: check your FXML file 'Menus.fxml'.";
	        assert displayClientDeliveries != null : "fx:id=\"displayClientDeliveries\" was not injected: check your FXML file 'Menus.fxml'.";
	        assert displayCompanyInfoMaintenance != null : "fx:id=\"displayCompanyInfoMaintenance\" was not injected: check your FXML file 'Menus.fxml'.";
	        assert displayClientMaintenance != null : "fx:id=\"displayClientMaintenance\" was not injected: check your FXML file 'Menus.fxml'.";
	        assert displayCourierMaintenance != null : "fx:id=\"displayCourierMaintenance\" was not injected: check your FXML file 'Menus.fxml'.";
	        assert displayUserMaintenance != null : "fx:id=\"displayUserMaintenance\" was not injected: check your FXML file 'Menus.fxml'.";
	        assert displayTrafficImpedimentsMaintenance != null : "fx:id=\"displayTrafficImpedimentsMaintenance\" was not injected: check your FXML file 'Menus.fxml'.";
	        assert displayLogout != null : "fx:id=\"displayLogout\" was not injected: check your FXML file 'Menus.fxml'.";

	    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
