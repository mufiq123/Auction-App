package Client;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.effect.*;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.fxml.FXML;

public class Item2Controller {

	
	double bidamount2;
	double maxAmount2 = 0;
	double currentAmount2;
	private Stage stage;
	private Scene scene;
	private Parent root;
	int id2val;
	public static String name;
	public static List<String> asdas = new ArrayList<String>();
	public static List<Map<String, String>> bids_individual = new ArrayList<Map<String,String>>();
	
	
	List<String> s = new ArrayList<String>();
	
	@FXML
	Label bidAmountBox2;
	
	@FXML
	Label nameLabel;
	
	@FXML
	Label description2;
	
	@FXML
	Label id2;
	
	@FXML	
	Label price2;
	
	static int sec = 59;
	@FXML
	public static Label static_description2;
	@FXML
	public static Label static_end2;
	@FXML
	public static Button static_bidButton2;
	@FXML
	public static TextField static_bid2;
	@FXML
	public static Label static_bidAmountBox2;
	@FXML
	public static Label static_price2;
	@FXML
	public static Label static_invalid;
	@FXML
	public static Label static_seconds;
	
	@FXML
	Label end2;
	
	@FXML
	TextField bid2;
	
	@FXML
	Label invalid1;
	
	@FXML
	Label seconds;
	
	@FXML
	Button bidButton2;
		
	@FXML
	ImageView auction2;
	
	public static String tot;
	public static String  pp = "";
	int num = 1;
	String t;
	public static Label s_soldOut;

	
	@FXML
	Label soldOut;
	
	public void initialize() {
		static_price2 = price2;
		static_invalid = invalid1;
		static_description2 = description2;
		static_bidAmountBox2 = bidAmountBox2;
		static_end2 = end2;
		static_bidButton2 = bidButton2;
		static_bid2 = bid2;
		static_seconds = seconds;
		s_soldOut=soldOut;

		
		nameLabel.setText(("Hello: " + name));
	}
	
	public void displayName(String username) {
		this.name = username;
		nameLabel.setText("Hello: " + username);
		//setTime(sec);
	}
	
	public void quitPressed(ActionEvent event) {
		Platform.exit();
	}
	
	
	public void displayItem(int id) {
		
		this.id2val = id;
		try {
			id2.setText(Integer.toString(this.id2val));
			synchronized(Client.items) {
			if(Client.items.get(id).getStatus()) {
				description2.setText("SOLD OUT");
				price2.setText("SOLD OUT");
				end2.setText("SOLD OUT");
				bid2.setVisible(false);
				bidAmountBox2.setVisible(false);
				bidButton2.setVisible(false);
				soldOut.setText("User '"+name+"' buys item "+(id2val)+" at a price of $"+(Client.items.get((id2val)).getEnd())+"\n");
				soldOut.setVisible(true);
			}
			else {
				description2.setText(Client.items.get(id).getDescription());
				price2.setText("$"+Double.toString(Client.items.get(id).getStart()));
				end2.setText("$"+Double.toString(Client.items.get(id).getEnd()));
			}
			maxAmount2 = Client.items.get(id).getEnd();
			currentAmount2 = (Client.items.get(id).getStart());
			}
		} catch(NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public void bid2_pressed(ActionEvent event) {
		
		try {
			bidamount2 = Double.parseDouble(bid2.getText());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if(bidamount2<=Client.items.get(id2val).getStart()) {
			invalid1.setVisible(true);
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
			pp="?";
		}
		else if(bidamount2 >= Client.items.get(id2val).getEnd()) {
			invalid1.setVisible(false);
			soldOut.setText("User '"+name+"' buys item "+(id2val)+" at a price of $"+(Client.items.get((id2val)).getEnd())+"\n");
			soldOut.setVisible(true);
			Map<String, String> ss = new HashMap<String,String>();
			pp = ("User '"+name+"' buys item "+(id2val)+" at a price of $"+(Client.items.get((id2val)).getEnd())+"*");
			String ind = ("User '"+name+"' buys item "+(id2val)+" at a price of $"+(Client.items.get((id2val)).getEnd())+"\n");

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
		else if(bidamount2>Client.items.get(id2val).getStart() && Client.items.get(id2val).getStatus() == false) {
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
			pp = ("User '"+name+"' bid's on item: "+(id2val)+" at a price of $"+(bidamount2)+"*");
			String i = ("User '"+name+"' bid's on item: "+(id2val)+" at a price of $"+(bidamount2)+"\n");

			ss.put(name, i);
			
			AuctionController.bids_individual.add(ss);
			
		}
		
		Client.sendToServer(Integer.toString(id2val));
		Client.sendToServer(Double.toString(bidamount2));
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
		HC.display(""+name+"'s Bid History",AuctionController.bids_individual, false, id2val, name, tot,t);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void reInit() {
		static_price2 = price2;
		static_invalid = invalid1;
		static_description2 = description2;
		static_bidAmountBox2 = bidAmountBox2;
		static_end2 = end2;
		static_bidButton2 = bidButton2;
		static_bid2 = bid2;
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
	
	public void item3Pressed(ActionEvent event) throws IOException {
		
		URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\Item3Controller.FXML");

		FXMLLoader loader = new FXMLLoader(cc);
		root = loader.load();
		
		Item3Controller I3C = loader.getController();
		
		I3C.displayName(name);
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

		FXMLLoader loader = new FXMLLoader(cc);		root = loader.load();
		
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

		FXMLLoader loader = new FXMLLoader(cc);		root = loader.load();
		
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
