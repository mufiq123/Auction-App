package Server;

import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Observer;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Observable;

class ClientHandler implements Runnable, Observer, Serializable {
  private Server server;
  private Socket clientSocket;
  Map<Integer, Items> items;
  File file = new File("C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Server\\src\\Server");
  private BufferedReader fromClient = null;
  private PrintWriter toClient;


  protected ClientHandler(Server server, Socket clientSocket, Map<Integer, Items> i) {
    this.server = server;
    this.clientSocket = clientSocket;
    this.items = i;
    try {
      fromClient = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
      toClient = new PrintWriter(this.clientSocket.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected void sendToClient(String string) {
    System.out.println("Sending to client: " + string);
    toClient.println(string);
    toClient.flush();
  }

  @Override
  public void run() {
	  
	  String newPrice = null;
	  String itemID = null ;
	  this.sendToClient("test");
	  
	  for(int x:items.keySet()) {
		  this.sendToClient(Integer.toString(x));
		  this.sendToClient(items.get(x).getDescription());
		  this.sendToClient(items.get(x).getIMG());
		  this.sendToClient(Double.toString(items.get(x).getEnd()));
		  this.sendToClient(Double.toString(items.get(x).getStart()));
		  this.sendToClient(Integer.toString(items.get(x).getTime()));
		  this.sendToClient(" ");
		 
	  }
	  this.sendToClient("-1");
	  this.sendToClient(server.hash);
	 
	  
	  String ID;
	  String price;
	  String className;
	  String hist;
	  String data;
	    try {
	      while ((ID = fromClient.readLine()) != null) {
	    	  
	    	  if(ID.equals("dataentry")) {
	    		  data = fromClient.readLine();
	    		  server.processData(data);
	    	  }
	    	  else {
	    		  price = fromClient.readLine();
		    	  hist = fromClient.readLine();
		    	  className = fromClient.readLine();
		    	  server.pp = fromClient.readLine();
		    	  //data = fromClient.readLine();
		    	  
		    	  server.processRequest(ID+","+price+","+hist+","+className+","+server.pp);
		    	  //server.processData(data);
	    	  }
	    	  
	      }
	    } catch (IOException | SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
	      e.printStackTrace();
	    }
	  
  }


  @Override
  public void update(Observable o, Object arg) 
  {
	  
	  if(((String[])arg).length<3){
		  this.sendToClient("dataentry");
		  this.sendToClient(((String[])arg)[1]);
	  }
	  else {
	  String t = "&";
	 if(((String[])arg)[0].equals("0") || ((String[])arg)[0].equals("2")) {
		 t = ((String[])arg)[4];
	 }
	 this.sendToClient(((String[])arg)[1]);
	 this.sendToClient(((String[])arg)[2]);
	 this.sendToClient(t);
	 this.sendToClient(((String[])arg)[3]);
	 this.sendToClient(((String[])arg)[5]);
	  }
  }
}