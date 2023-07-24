package Client;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Item3Controller {

	
	double bidamount3;
	double maxAmount3 = 0;
	double currentAmount3;
	private Stage stage;
	private Scene scene;
	private Parent root;
	int id3val;
	public static String name;
	public static List<String> asdas = new ArrayList<String>();
	public static List<Map<String, String>> bids_individual = new ArrayList<Map<String,String>>();
	
	
	List<String> s = new ArrayList<String>();
	
	@FXML
	Label bidAmountBox3;
	
	@FXML
	Label nameLabel;
	
	@FXML
	Label description3;
	
	@FXML
	Label id3;
	
	@FXML	
	Label price3;
	
	static int sec = 59;
	@FXML
	public static Label static_description3;
	@FXML
	public static Label static_end3;
	@FXML
	public static Button static_bidButton3;
	@FXML
	public static TextField static_bid3;
	@FXML
	public static Label static_bidAmountBox3;
	@FXML
	public static Label static_price3;
	@FXML
	public static Label static_invalid;
	@FXML
	public static Label static_seconds;
	
	@FXML
	Label end3;
	
	@FXML
	TextField bid3;
	
	@FXML
	Label invalid1;
	
	@FXML
	Label seconds;
	
	@FXML
	Button bidButton3;
		
	@FXML
	ImageView auction2;
	
	public static String tot;
	public static String  pp = " ";
	int num = 2;
	String t;
	
	public static Label s_soldOut;
	double endprice;
	
	@FXML
	Label soldOut;
	
	public void initialize() {
		static_price3 = price3;
		static_invalid = invalid1;
		static_description3 = description3;
		static_bidAmountBox3 = bidAmountBox3;
		static_end3 = end3;
		static_bidButton3 = bidButton3;
		static_bid3 = bid3;
		static_seconds = seconds;
		s_soldOut=soldOut;
		
		nameLabel.setText(("Hello: " + name));
	}
	
	public void displayName(String username) {
		this.name = username;
		nameLabel.setText("Hello: " + username);
		//setTime(sec);
	}
	
	public void displayItem(int id) {
		
		this.id3val = id;
		try {
			id3.setText(Integer.toString(this.id3val));
			synchronized(Client.items) {
			if(Client.items.get(id).getStatus()) {
				description3.setText("SOLD OUT");
				price3.setText("SOLD OUT");
				end3.setText("SOLD OUT");
				bid3.setVisible(false);
				bidAmountBox3.setVisible(false);
				bidButton3.setVisible(false);
				soldOut.setText("User '"+name+"' buys item "+(id3val)+" at a price of $"+Client.items.get(id3val).getEnd()+"\n");
				soldOut.setVisible(true);
			}
			else {
				description3.setText(Client.items.get(id).getDescription());
				price3.setText("$"+Double.toString(Client.items.get(id).getStart()));
				end3.setText("$"+Double.toString(Client.items.get(id).getEnd()));
			}
			maxAmount3 = Client.items.get(id).getEnd();
			currentAmount3 = (Client.items.get(id).getStart());
			}
		} catch(NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void quitPressed(ActionEvent event) {
		Platform.exit();
	}
	
	
	public void bid2_pressed(ActionEvent event) {
		
		static_price3 = price3;

		
		try {
			bidamount3 = Double.parseDouble(bid3.getText());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if(bidamount3<=Client.items.get(id3val).getStart()) {
			invalid1.setVisible(true);
			pp="?";
			try {
				File sound = new File("C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\error.wav");
				Clip c = AudioSystem.getClip();
				c.open(AudioSystem.getAudioInputStream(sound));
				c.start();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else if(bidamount3 >= Client.items.get(id3val).getEnd()) {
			invalid1.setVisible(false);
			soldOut.setText("User '"+name+"' buys item "+(id3val)+" at a price of $"+Client.items.get(id3val).getEnd()+"\n");
			soldOut.setVisible(true);
			Map<String, String> ss = new HashMap<String,String>();
			pp = ("User '"+name+"' buys item "+(id3val)+" at a price of $"+(Client.items.get((id3val)).getEnd())+"*");
			String ind = ("User '"+name+"' buys item "+(id3val)+" at a price of $"+(Client.items.get((id3val)).getEnd())+"\n");

			try {
				File sound = new File("C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\sold.wav");
				Clip c = AudioSystem.getClip();
				c.open(AudioSystem.getAudioInputStream(sound));
				c.start();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ss.put(name, ind);
			
			AuctionController.bids_individual.add(ss);
		}
		else if(bidamount3>Client.items.get(id3val).getStart() && Client.items.get(id3val).getStatus() == false) {
			invalid1.setVisible(false);
			
			try {
				File sound = new File("C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\ding.wav");
				Clip c = AudioSystem.getClip();
				c.open(AudioSystem.getAudioInputStream(sound));
				c.start();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Map<String, String> ss = new HashMap<String,String>();
			pp = ("User '"+name+"' bid's on item: "+(id3val)+" at a price of $"+(bidamount3)+"*");
			String i = ("User '"+name+"' bid's on item: "+(id3val)+" at a price of $"+(bidamount3)+"\n");

			ss.put(name, i);
			
			AuctionController.bids_individual.add(ss);
			
		}
		
		Client.sendToServer(Integer.toString(id3val));
		Client.sendToServer(Double.toString(bidamount3));
		//String pp = (name+" bid's on item: "+Integer.toString(id1val)+" at a price of $"+Double.toString(bidamount1)+"\n");
		Client.sendToServer(name);
		Client.sendToServer(this.getClass().getName());
		Client.sendToServer(pp);
	}
	
	
	
	@FXML
	public void totBid2(ActionEvent event) throws IOException {
		URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\BidHistory.FXML");

		
		FXMLLoader loader = new FXMLLoader(cc);
		root = loader.load();
		HistoryController HC = loader.getController();
		HC.setName(name,this.getClass().getName(),num);
		//HC.setBids(bids);
/*		
		System.out.println("****\n");

		for(String p:sl) {
			System.out.println(p);
		}
		
		System.out.println("\n****");
*/
	//	HC.display("Total Bid History",bids, true, 0, name, "");
		System.out.println("M");
		System.out.println(AuctionController.t);
		System.out.println("M");
	    HC.display("Total Bid History",AuctionController.bids_individual, true, 0, name, tot,AuctionController.t);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void userBidPressed(ActionEvent event) throws IOException {
		
		URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\BidHistory.FXML");

		
		FXMLLoader loader = new FXMLLoader(cc);		
		root = loader.load();
		HistoryController HC = loader.getController();
		
		HC.setName(name,this.getClass().getName(),num);
		HC.display(""+name+"'s Bid History",AuctionController.bids_individual, false, id3val, name, tot,t);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void reInit() {
		static_price3 = price3;
		static_invalid = invalid1;
		static_description3 = description3;
		static_bidAmountBox3 = bidAmountBox3;
		static_end3 = end3;
		static_bidButton3 = bidButton3;
		static_bid3 = bid3;
		static_seconds = seconds;
		s_soldOut=soldOut;
		
		nameLabel.setText("Hello: " + name);
	}
	
	
	public void item1Pressed(ActionEvent event) throws IOException {
		
		URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\Auction.FXML");

		FXMLLoader loader = new FXMLLoader(cc);
		root = loader.load();
		
		AuctionController AC = loader.getController();
		
		AC.displayName(name);
		AC.initialize();
		AC.displayItem(Client.ids.get(0));

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void setT(String s) {
	
		AuctionController.setT(s);

	}
	public void item2Pressed(ActionEvent event) throws IOException {
		
		URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\Item2Controller.FXML");

		FXMLLoader loader = new FXMLLoader(cc);
				root = loader.load();
		
		Item2Controller I2C = loader.getController();
		
		I2C.displayName(name);
		I2C.initialize();
		I2C.displayItem(Client.ids.get(1));

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	
	public void item4Pressed(ActionEvent event) throws IOException {
		
		URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\Item4Controller.FXML");

		FXMLLoader loader = new FXMLLoader(cc);
				root = loader.load();
		
		Item4Controller I4C = loader.getController();
		
		I4C.displayName(name);
		I4C.initialize();
		I4C.displayItem(Client.ids.get(3));

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void item5Pressed(ActionEvent event) throws IOException {
	
		URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\Item5Controller.FXML");

		FXMLLoader loader = new FXMLLoader(cc);
				root = loader.load();
		
		Item5Controller I5C = loader.getController();
		
		I5C.displayName(name);
		I5C.initialize();
		I5C.displayItem(Client.ids.get(4));

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void item6Pressed(ActionEvent event) throws IOException {
		
		URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\Item6Controller.FXML");

		FXMLLoader loader = new FXMLLoader(cc);
				root = loader.load();
		
		Item6Controller I6C = loader.getController();
		
		I6C.displayName(name);
		I6C.initialize();
		I6C.displayItem(Client.ids.get(5));

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
