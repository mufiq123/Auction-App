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

public class RegisterController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	TextField pass1;
	
	@FXML
	TextField pass2;
	
	@FXML
	TextField user1;
	
	@FXML
	Text invalid;
	
	@FXML 
	Text invalidpass;
	
	public void but(ActionEvent event) throws IOException {
		int flag = 0;

		String u = user1.getText();
		String p1 = pass1.getText();
		String p2 = pass2.getText();
		
		
		if(p1.equals(p2) && p1.length()>1 && u.length()>=1) {
			
			String[] ls = LoginController.d.split("%");
			
			
			for(String p:ls) {
				
				
				String[] cc = p.split(",");
				
				System.out.println(cc[0]);

				
				if(cc[0].equals(u)) {
					invalid.setVisible(true);
					flag = 1;
				}
				
			}
			if(flag==0) {
				String c = u+","+p1;
				invalid.setVisible(false);
				invalidpass.setVisible(false);
				
				//SEND THE DATA
				
				Client.sendToServer("dataentry");
				Client.sendToServer(c);
				
				URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\ItemPicker.FXML");

				FXMLLoader loader = new FXMLLoader(cc);				root = loader.load();
				
				ItemPickerController IPC = loader.getController();
				
				IPC.present(u);
				
				
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				
			}
		}
		else {
			invalidpass.setVisible(true);
		}
		
	}
}
