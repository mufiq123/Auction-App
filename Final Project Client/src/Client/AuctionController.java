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
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;
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
import javafx.scene.control.Label;

public class AuctionController {

	
	double bidamount1;
	double maxAmount1 = 0;
	double currentAmount1;
	private Stage stage;
	private Scene scene;
	private Parent root;
	int id1val;
	public static String name;
	public static List<String> sl = new ArrayList<String>();
	public static List<Map<String, String>> bids_individual = new ArrayList<Map<String,String>>();

	
	
	List<String> s = new ArrayList<String>();
	
	@FXML
	Label bidAmountBox1;
	
	@FXML
	Label nameLabel;
	
	@FXML
	Label description1;
	
	@FXML
	Label id1;
	
	@FXML	
	Label price1;
	
	static int sec = 59;
	
	@FXML
	public static Label static_description1;
	
	@FXML
	public static Label static_end1;
	
	@FXML
	public static Button static_bidButton1;
	
	@FXML
	public static TextField static_bid1;
	
	@FXML
	public static Label static_bidAmountBox1;
	@FXML
	public static Label static_price1;
	@FXML
	public static Label static_invalid1;
	@FXML
	public static Label static_seconds;
	
	@FXML
	Label end1;
	
	@FXML
	TextField bid1;
	
	@FXML
	Label invalid1;
	
	@FXML
	Label seconds;
	
	@FXML
	Button bidButton1;
		
	@FXML
	ImageView auction1;
	
	public static String tot;
	public static String  pp = "";
	int num = 0;
	public static String t;
	public static Label s_soldOut;
	double endprice;
	
	@FXML
	Label soldOut;
	
	
	public void initialize() {
		static_price1 = price1;
		static_invalid1 = invalid1;
		static_description1 = description1;
		static_bidAmountBox1 = bidAmountBox1;
		static_end1 = end1;
		static_bidButton1 = bidButton1;
		static_bid1 = bid1;
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

		this.id1val = id;
		try {
			id1.setText(Integer.toString(this.id1val));
			synchronized(Client.items) {
			if(Client.items.get(id).getStatus()) {
				description1.setText("SOLD OUT");
				price1.setText("SOLD OUT");
				end1.setText("SOLD OUT");
				bid1.setVisible(false);
				bidAmountBox1.setVisible(false);
				bidButton1.setVisible(false);
				soldOut.setText("User '"+name+"' buys item "+(id1val)+" at a price of $"+Client.items.get(id).getEnd()+"\n");
				soldOut.setVisible(true);
			}
			else {
				description1.setText(Client.items.get(id).getDescription());
				price1.setText("$"+Double.toString(Client.items.get(id).getStart()));
				end1.setText("$"+Double.toString(Client.items.get(id).getEnd()));
			}
			maxAmount1 = Client.items.get(id).getEnd();
			currentAmount1 = (Client.items.get(id).getStart());
			}
		} catch(NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public void bid1_pressed(ActionEvent event) {
				
		static_price1 = price1;
		try {
			bidamount1 = Double.parseDouble(bid1.getText());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if(bidamount1<=Client.items.get(id1val).getStart()) {
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
		else if(bidamount1 >= Client.items.get(id1val).getEnd()) {
			invalid1.setVisible(false);
			soldOut.setText("User '"+name+"' buys item "+(id1val)+" at a price of $"+(Client.items.get((id1val)).getEnd())+"\n");
			soldOut.setVisible(true);
			Map<String, String> ss = new HashMap<String,String>();
			//Client.sendToServer(("User '"+name+"' buys item "+(id1val)+" at a price of $"+(Client.items.get((id1val)).getEnd())));
			pp = ("User '"+name+"' buys item "+(id1val)+" at a price of $"+(Client.items.get((id1val)).getEnd())+"*");
			String ind = ("User '"+name+"' buys item "+(id1val)+" at a price of $"+(Client.items.get((id1val)).getEnd())+"\n");

			
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
			
			endprice = Client.items.get((id1val)).getEnd();

			ss.put(name, ind);
			
			bids_individual.add(ss);
		}
		else if(bidamount1>Client.items.get(id1val).getStart() && Client.items.get(id1val).getStatus() == false) {
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
			
			pp = ("User '"+name+"' bid's on item: "+(id1val)+" at a price of $"+(bidamount1)+"*");
			String ind = ("User '"+name+"' bid's on item: "+(id1val)+" at a price of $"+(bidamount1)+"\n");
			ss.put(name, ind);
			
			bids_individual.add(ss);
			
		}
		
		Client.sendToServer(Integer.toString(id1val));
		Client.sendToServer(Double.toString(bidamount1));
		//String pp = (name+" bid's on item: "+Integer.toString(id1val)+" at a price of $"+Double.toString(bidamount1)+"\n");
		Client.sendToServer(name);
		Client.sendToServer(this.getClass().getName());

		Client.sendToServer(pp);
		
		
	}
	
	

	public void allBidPressed(ActionEvent event) throws IOException {
		
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
	
	    HC.display("Total Bid History",bids_individual, true, 0, name, tot,t);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void userBidPressed(ActionEvent event) throws IOException {
		
		URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\BidHistory.FXML");

		
		FXMLLoader loader = new FXMLLoader(cc);
		root = loader.load();
		HistoryController HC = loader.getController();
		
		HC.setName(name,this.getClass().getName(),num);
		//HC.setBids(bids);
		
		HC.display(""+name+"'s Bid History",bids_individual, false, id1val, name, tot,t);
	    //tot = HC.display(""+name+"'s Bid History",bids_individual, false, id1val, name, "");
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void reInit() {
		static_price1 = price1;
		static_invalid1 = invalid1;
		static_description1 = description1;
		static_bidAmountBox1 = bidAmountBox1;
		static_end1 = end1;
		static_seconds = seconds;
		static_bidButton1 = bidButton1;
		static_bid1 = bid1;
		s_soldOut=soldOut;
		
		nameLabel.setText("Hello: " + name);
		//this.bids = bids;
	}
	
	public static void setT(String s) {
		
		t = s;

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
	
	public void quitPressed(ActionEvent event) {
		Platform.exit();
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
