package Client;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.*;
import java.net.URL;

public class LoginController {
	
	@FXML
	TextField username;
	
	@FXML
	TextField password;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	String name;
	public static String d = "mufiq123,saints123";
	int flag = 0;
	
    @FXML
    Text invalid;
    
	public void login(ActionEvent event) throws IOException {
		
		String user = username.getText();
		String pass= password.getText();
		
		if(username.getText().isEmpty() || password.getText().isEmpty()) {
			Platform.runLater(() -> invalid.setVisible(true));
			return;
		}
		else {
			user = username.getText();
			name = user;
			pass = password.getText();
			String c = user+","+pass;
			System.out.println(c);
			System.out.println("Here is my :" +d);
			if(d.contains(c)) {
				Platform.runLater(() -> invalid.setVisible(false));
			}	
			else {
				Platform.runLater(() -> invalid.setVisible(true));

				return;
			}
	
		}
		//create passwordfield and get the password
		
		//check to see if the user is in the data
			//if yes then check to see if the user and the password match
				//if not then ask for input again
		
		//if not then ask for input again
			
			
		URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\ItemPicker.FXML");

		
		FXMLLoader loader = new FXMLLoader(cc);
		root = loader.load();
		
		ItemPickerController IPC = loader.getController();
		
		IPC.present(user);
		
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void register(ActionEvent event) throws IOException {
		//get username field
		// if username field is in data then ask for new username
		// else join the username and password and send it to the server
		
		//System.out.println(getClass().getResource("Register.FXML"));
		
		URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\Register.FXML");
		
		FXMLLoader loader = new FXMLLoader(cc);
		root = loader.load();
		
		//RegisterController IPC = loader.getController();
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void registerasguest(ActionEvent event) throws IOException {
		//get username field
		//if in data ask again
		//send username to dat
		
		URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\RegisterGuest.FXML");

		FXMLLoader loader = new FXMLLoader(cc);
		root = loader.load();
		
		//RegisterController IPC = loader.getController();
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}


