package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class AddSearchDeliveryTicket implements Initializable {

    @FXML
    private Button buttonNewDeliveryTicket;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		buttonNewDeliveryTicket.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              
                try {
    	    	   	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/DeliveryTicket.fxml"));
                	DeliveryTicketController controller = new DeliveryTicketController();
                	fxmlLoader.setController(controller);
                	AnchorPane currentPane = fxmlLoader.load();
                	BorderPane border = Main.getRoot();
    	    		border.setCenter(currentPane);
    	    	} catch(IOException e){
    	    		e.printStackTrace();
    	    	}
            }
        });
	}

}
