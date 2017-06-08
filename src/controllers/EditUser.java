package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.User;

public class EditUser implements Initializable {
	  @FXML
	    private ComboBox<String> comboBoxUserRole;

	    @FXML
	    private TextField textFieldUserUsername;

	    @FXML
	    private TextField textFieldUserPassword;

	    @FXML
	    private TextField textFieldUserEmail;

	    @FXML
	    private TextField textFieldUserName;
	    
	    @FXML
	    private TextField textFieldUserPasswordConf;

	    @FXML
	    private Button btnSave;

	    @FXML
	    private Button btnCancel;
	    
	    User user;
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ObservableList<String> roles = FXCollections.observableArrayList();
		roles.addAll("Admin", "User");
		comboBoxUserRole.setItems(roles);
		if(user != null) {
			textFieldUserName.setText(user.getName());
			textFieldUserUsername.setText(user.getUserName());
			textFieldUserPassword.setText(user.getPassword());
			comboBoxUserRole.setPromptText(user.getRole());
			textFieldUserEmail.setText(user.getEmail());
			//TODO: Populate fields
		}
		btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                	if(textFieldUserPassword.getText().equals(textFieldUserPasswordConf.getText())){
                		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/SelectUser.fxml"));
        	    		BorderPane border = Main.getRoot();
        	    		border.setCenter(currentPane);
                	}
                	else {
                		System.out.println("Shit don't match");
                		System.out.println("Password1 = " + textFieldUserPassword.getText());
                		System.out.println("Password2 = " + textFieldUserPasswordConf.getText());
                	}
    	    	} catch(IOException e){
    	    		e.printStackTrace();
    	    	}
            }
        });
		btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
    	    		AnchorPane currentPane = FXMLLoader.load(getClass().getResource("/views/SelectUser.fxml"));
    	    		BorderPane border = Main.getRoot();
    	    		border.setCenter(currentPane);
    	    	} catch(IOException e){
    	    		e.printStackTrace();
    	    	}
            }
        });

	}
	
	public void setUser(User u) {
		this.user = u;
		System.out.println("Setting client to " + u.getName());
	}

}
