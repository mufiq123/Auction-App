package Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.Observable;
import java.sql.*;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import com.google.gson.Gson;

class Server extends Observable implements Serializable, KeySpec{
	
	
	String pp = "";
	static String hash = "mufiq123,saint123";
	String x = "";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/logs";
	private static final String USER = "root";
	private static final String PASSWORD = "saints123";
	
	static Connection conn;
	
  public static void main(String[] args) throws SQLException {
	Server s = new Server();
	System.out.println(System.getProperty("java.runtime.version"));
	String x = "";
	
	conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
    Statement stmt = conn.createStatement();
	
    String selectQuery = "SELECT * FROM temp";
    ResultSet rs = stmt.executeQuery(selectQuery);
    while (rs.next()) {
    	String id = rs.getString("user");
        String name = rs.getString("pass");
        x += id+","+name+"%";
    }
    hash = x;
    
	s.readFile();
    s.runServer();
  }
  
  
  Map<Integer, Items> items = new HashMap<Integer, Items>();
  List<Integer> ids = new ArrayList<Integer>();

  public void readFile() {
	  int itemID = 10;
		String description;
		double start;
		double end;
		String img;
		int time;
	    String input;
	    try(BufferedReader fromClient = new BufferedReader(new FileReader("C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Server\\src\\Server\\AuctionList.txt"))) 
	    {
	      while ((fromClient.readLine()).equals("END") == false) {
	        	itemID = Integer.parseInt(fromClient.readLine());
	        	ids.add(itemID);
	        	description = fromClient.readLine();
	        	start = Double.parseDouble(fromClient.readLine());
	        	end = Double.parseDouble(fromClient.readLine());
	        	img = fromClient.readLine();
	        	time = Integer.parseInt(fromClient.readLine());
	        	Items ss = new Items(description,start,end,img,time);
	        	
	         	items.put(itemID, ss);
	        
	        }
	        //server.processRequest(input);
	    }
	    catch (IOException e) {
	    	  System.out.println("DFF");
	    }
  }
  
  private void runServer() {
    try {
      setUpNetworking();
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }
  }

  private void setUpNetworking() throws Exception {
    @SuppressWarnings("resource")
    ServerSocket serverSock = new ServerSocket(4242);
    while (true) {
      Socket clientSocket = serverSock.accept();
      System.out.println("Connecting to... " + clientSocket);

      ClientHandler handler = new ClientHandler(this, clientSocket,items);
      this.addObserver(handler);

      Thread t = new Thread(handler);
      t.start();
    }
  }
  protected void processData(String data) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
	  
	  String trap = "";
	  System.out.println(data);
	  String[] sp = data.split(",");
	  
	  String unen = sp[1];
	  //trap+=sp[0]+","+sp[1]+"%";
	  //System.out.println("THIS IS POP:"+trap);
	  SecureRandom rand = new SecureRandom();
	  byte[] salt = new  byte[16];
	  rand.nextBytes(salt);
	  
	  KeySpec spec = new PBEKeySpec(unen.toCharArray(),salt,65536,128);
	  SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

	  byte[] h = factory.generateSecret(spec).getEncoded();
	  
	  System.out.println(h.toString());
	  
	  Statement stmt = conn.createStatement();
      String createTableQuery = "CREATE TABLE IF NOT EXISTS temp (user VARCHAR(255), pass VARCHAR(255))";
      stmt.execute(createTableQuery);
      String insertQuery = "INSERT INTO temp (user, pass) VALUES ('"+sp[0]+"', '"+h.toString()+"')";
      stmt.execute(insertQuery);
      String i = "INSERT INTO temp (user, pass) VALUES ('"+sp[0]+"', '"+sp[1]+"')";
      stmt.execute(i);

      
      String selectQuery = "SELECT * FROM temp";
      ResultSet rs = stmt.executeQuery(selectQuery);
      while (rs.next()) {
          String id = rs.getString("user");
          String name = rs.getString("pass");
          trap += id+","+name+"%";
      }
      
	  hash = trap;
	 
	  String[] px = {"now",hash};
	  this.setChanged();
	  this.notifyObservers(px);
  }


  protected void processRequest(String input) {
	  
	  String[] p = input.split(",");
	  String id = p[0];
	  String bid = p[1];
	  String hist = p[2];
	  String className = p[3];
	  //x = "";
	  if(p[4].equals("?")) {
		  x+="";
	  }
	  else {
		  x += p[4];
	  }
	  //String cache = p[4];
	  
	  System.out.println("\n****"+x+"\n****");
	  
	  
	  String[] obj = new String[6];
	  
	  System.out.println(Arrays.toString(p));
	  
	  
	  if(Double.parseDouble(bid)>items.get(Integer.parseInt(id)).getStart() && Double.parseDouble(bid)<items.get(Integer.parseInt(id)).getEnd()) {
		  items.get(Integer.parseInt(id)).setPrice(Double.parseDouble(bid));
		  obj[0] = "0";
		  obj[1] = id;
		  obj[2] = bid;
		  obj[3] = className;
		  obj[4] = hist;
		  obj[5] = x;
	      this.setChanged();
		  this.notifyObservers(obj);
	  }
	  else if(items.get(Integer.parseInt(id)).getStatus()) {
		  obj[0] = "3";
		  obj[1] = id;
		  obj[2] = "+";
		  obj[3] = className;
	      this.setChanged();
		  this.notifyObservers(obj);
	  }
		  
	  else if(Double.parseDouble(bid)<=items.get(Integer.parseInt(id)).getStart()) {
		  obj[0] = "1";
		  obj[1] = id;
		  obj[2] = "-1";
		  obj[3] = className;
	      this.setChanged();
		  this.notifyObservers(obj);
	  }
	  else if(Double.parseDouble(bid)>=items.get(Integer.parseInt(id)).getEnd()){
		  obj[0] = "2";
		  obj[1] = id;
		  obj[2] = "*";
		  obj[3] = className;
		  obj[4] = hist;
		  obj[5] = x;
		  items.get(Integer.parseInt(id)).setSoldOut(true);
	      this.setChanged();
		  this.notifyObservers(obj);
	  }
  }

}