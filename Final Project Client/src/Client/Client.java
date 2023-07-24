package Client;

import java.io.*;
import java.lang.reflect.*;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.Stage;


public class Client extends Application implements Serializable{

	
  private static String host = "127.0.0.1";
  AuctionController auctionController = new AuctionController();
  private BufferedReader fromServer;
  public static Map<Integer, Items> items = new HashMap<Integer, Items>();
  public static List<Integer> ids = new ArrayList<Integer>();
  public static PrintWriter toServer;
  private Scanner consoleInput = new Scanner(System.in);
  GridPane grid = new GridPane();
  int sec = 59;
  
  private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/logs";
  private static final String USER = "root";
  private static final String PASSWORD = "saints123";
  
  public static void main(String[] args) throws SQLException {
	  
    try {
      new Client().setUpNetworking();
      launch(args);
    } catch (Exception e) {
      e.printStackTrace();
    }
   }

  private void setUpNetworking() throws Exception {
    @SuppressWarnings("resource")
	final Socket socket = new Socket(host, 4242);
    System.out.println("Connecting to... " + socket);
    
    fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    toServer = new PrintWriter(socket.getOutputStream());
    
    
    String in;
	InputStream input = socket.getInputStream();
	
	int itemID = 10;
	String description;
	double start;
	double end;
	String img;
	int time;
	
	while ((in = fromServer.readLine()).equals("END") == false) {
		
		System.out.println(in);
		itemID = Integer.parseInt(fromServer.readLine());
		if(itemID < 0) {
			break;
		}
    	ids.add(itemID);
    	description = fromServer.readLine();
    	img = fromServer.readLine();
    	end = Double.parseDouble(fromServer.readLine());
    	start = Double.parseDouble(fromServer.readLine());

    	time = Integer.parseInt(fromServer.readLine());
    	//space = fromClient.readLine();
    	Items ss = new Items(description,start,end,img,time);
     	items.put(itemID, ss);
		
	}
	
	String ddt = fromServer.readLine();
	LoginController.d=ddt;
	
    Thread readerThread = new Thread(new Runnable() {
		@Override
		public void run() {	
			
			while(true) {
				String ID;			
				
				try {
					
		            // Class cls = Class.forName("AuctionController");
		            
		           // Method m = cls.getMethod("displayInvalid");
		            
		            
					while((ID = fromServer.readLine()) != null) {
						
						
						if(ID.equals("dataentry")) {
							String data = fromServer.readLine();
							LoginController.d = data;
						}
						else {
						String price = fromServer.readLine();
						String temp = fromServer.readLine();
						String className = fromServer.readLine();
						String pp = fromServer.readLine();
						
						int as = 0;
						int vvv=0;
						
						Class control = Class.forName(className);
						Field[] fields = control.getDeclaredFields();
						Method setT = control.getDeclaredMethod("setT",String.class);
						//Label out = null;
						
						for(int i = 0; i<fields.length;i++) 
						{
							fields[i].setAccessible(true);
							System.out.println(fields[i].getName()+i);
							if(fields[i].getName().equals("s_soldOut")) {
								vvv=i;
							}
							
						}
						
						
						
						String n = (String) fields[7].get(control);
						//List<Map<String, String>> b = (List<Map<String, String>>) fields[8].get(control);
						List<String> aa = (List<String>) fields[8].get(control);
						Label price_change = (Label) fields[22].get(control);
						Label end_change = (Label) fields[18].get(control);
						Label description_change = (Label) fields[17].get(control);
						TextField bid_change = (TextField) fields[20].get(control);
						Button bidButton1_change = (Button) fields[19].get(control);
						Label bidAmountBox_change = (Label) fields[21].get(control);
						Label out = (Label) fields[vvv].get(control);

						if(price.equals("-1")) {
						}
						else if(price.equals("*")) {
							
							Client.items.get(Integer.parseInt(ID)).setSoldOut(true);
														
							Platform.runLater(() -> price_change.setText("SOLD OUT!")); // 22
							Platform.runLater(() -> end_change.setText("SOLD OUT!")); // 18
							Platform.runLater(() -> description_change.setText("SOLD OUT!")); //17
							Platform.runLater(() -> bid_change.setVisible(false)); //20
							Platform.runLater(() -> bidButton1_change.setVisible(false)); //19
							Platform.runLater(() -> bidAmountBox_change.setVisible(false)); //21
							Platform.runLater(() -> out.setVisible(true));

							Map<String,String> l = new HashMap<String,String>();
							String ppp = ("User '"+temp+"' buys item "+(ID)+" at a price of $"+(Client.items.get(Integer.parseInt(ID)).getEnd())+"\n");
							
							Platform.runLater(() -> out.setText(ppp));

							
							
							if(pp.equals("8")==false) {
								l.put(n, pp+"\n"); // name is 7
								//b.add(l); //bids is 8
								aa.add(pp);
							}
							//Client.items.get(Integer.parseInt(ID)).setEnd(-1);

						}
						else if(price.equals("+")) {
							
						}
						else {
							
							Client.items.get(Integer.parseInt(ID)).setPrice(Double.parseDouble(price));
				
							try {
								Platform.runLater(() -> price_change.setText("$"+price)); //22
								//price_change.setText("$"+price);
							} catch (NullPointerException e) {
								//e.printStackTrace();
							}
							if(temp.equals("&")==false) {
								Map<String,String> l = new HashMap<String,String>();
								//String pp = ("User '"+temp+"' bid's on item: "+(ID)+" at a price of $"+(price)+"\n");
									l.put(n, pp+"\n"); // name is 7
									//System.out.println(pp);			

									aa.add((pp)); //bids is 8
									String v = "";
									System.out.println("HERE IS THE NAME: "+aa.get(aa.size()-1));
									setT.invoke(control.newInstance(), pp);

							}
						}
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				} catch (NoSuchMethodException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				} catch (SecurityException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}

			}

		}
			
		});
    
    
    
    Thread writerThread = new Thread(new Runnable() {
      @Override
      public void run() {
        while (true) {
            
        }
      }
    });

    readerThread.start();
    writerThread.start();
  }
 
  @Override
  public void start(Stage primaryStage) {
	  
	  try {
		  
		URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\LoginController.FXML");

		//FXMLLoader loader = new FXMLLoader(cc);  
		Parent root = FXMLLoader.load(cc);
		Scene login = new Scene(root);
		primaryStage.setScene(login);
		primaryStage.show();
	} catch (IOException e) {
		e.printStackTrace();
	}
	  
	  
  }
  protected void processRequest(String input) {
    return;
  }
  
  public void updatePric2e1(double bid) {
	  System.out.println("DFDIJFDIDJF");
	  return;
  }
  public static void sendToServer(String string) {
    System.out.println("Sending to server: " + string);
    toServer.println(string);
    toServer.flush();
  }

}