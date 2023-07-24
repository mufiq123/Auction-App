package Client;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegisterGuestController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	TextField user1;
	
	@FXML
	Text invalid;
	
	public void but(ActionEvent event) throws IOException {
		int flag = 0;

		String u = user1.getText();
		String[] ls = LoginController.d.split("%");

		
		for(String p: ls) {
			String[] cc = p.split(",");
			
			System.out.println(cc[0]);

			
			if(cc[0].equals(u)) {
				invalid.setVisible(true);
				flag = 1;
			}
		}
		if(flag==0) {
			String c = u+",temp";
			invalid.setVisible(false);
			
			//SEND THE DATA
			
			Client.sendToServer("dataentry");
			Client.sendToServer(c);
			
			URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\ItemPicker.FXML");

			FXMLLoader loader = new FXMLLoader(cc);			root = loader.load();
			
			ItemPickerController IPC = loader.getController();
			
			IPC.present(u);
			
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		}
		else {
			invalid.setVisible(true);
		}
			
		
		
	}
}
