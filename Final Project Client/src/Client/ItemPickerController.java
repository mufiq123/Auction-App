package Client;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ItemPickerController {
	
	@FXML
	TextField mainHeader;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	String nn;
	
	public void displayItem1(ActionEvent event) throws IOException {
		
URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\Auction.FXML");

		
		FXMLLoader loader = new FXMLLoader(cc);		root = loader.load();
		
		AuctionController AC = loader.getController();
		
		AC.displayName(nn);
		AC.initialize();
		AC.displayItem(Client.ids.get(0));

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void present(String s) {
		this.nn = s;
		mainHeader.setText("Hello "+s+", please pick what item you want to see!");
	}
	
	public void displayItem2(ActionEvent event) throws IOException {
		
		URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\Item2Controller.FXML");

		FXMLLoader loader = new FXMLLoader(cc);
		root = loader.load();
		
		Item2Controller I2C = loader.getController();
		
		I2C.displayName(nn);
		I2C.initialize();
		I2C.displayItem(Client.ids.get(1));

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	public void item3Pressed(ActionEvent event) throws IOException {
		
		URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\Item3Controller.FXML");

		FXMLLoader loader = new FXMLLoader(cc);		root = loader.load();
		
		Item3Controller I3C = loader.getController();
		
		I3C.displayName(nn);
		I3C.initialize();
		I3C.displayItem(Client.ids.get(2));

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void item4Pressed(ActionEvent event) throws IOException {
		
		URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\Item4Controller.FXML");

		FXMLLoader loader = new FXMLLoader(cc);		root = loader.load();
		
		Item4Controller I4C = loader.getController();
		
		I4C.displayName(nn);
		I4C.initialize();
		I4C.displayItem(Client.ids.get(3));

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void item5Pressed(ActionEvent event) throws IOException {
	
		URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\Item5Controller.FXML");

		FXMLLoader loader = new FXMLLoader(cc);		root = loader.load();
		
		Item5Controller I5C = loader.getController();
		
		I5C.displayName(nn);
		I5C.initialize();
		I5C.displayItem(Client.ids.get(4));

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void item6Pressed(ActionEvent event) throws IOException {
		
		URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\Item6Controller.FXML");

		FXMLLoader loader = new FXMLLoader(cc);		root = loader.load();
		
		Item6Controller I6C = loader.getController();
		
		I6C.displayName(nn);
		I6C.initialize();
		I6C.displayItem(Client.ids.get(5));

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
