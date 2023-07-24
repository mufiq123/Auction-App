package Client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HistoryController 
{	
	@FXML
	Label username;
	
	@FXML
	Label bidList;
	
	int itemNum;
	//public static Label s_bidList;
	String total = "";
	String individual = "";
	private Stage stage;
	private Scene scene;
	private Parent root;
	private String className;
	private String s = "12";
	private String user = "";
	List<Map<String, String>> bids;

	@FXML
	Label titleForHistory;
	
	@FXML
	ListView<String> listV;
	
	@FXML
	TextField searchBar;
	
	@FXML
	Button search;
	
	ArrayList<String> words = new ArrayList<>();
	
	public void back(ActionEvent event) throws IOException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException {
		//String user = username.getText();
		
	/*	Class<?> control = Class.forName(className);
		System.out.println(className);
		Method[] meth = control.getMethods();
		int ac = 0;
		Method re = null;
		Method disN = null;
		Method it = null;
		
		for(Method m: meth) {
			System.out.println(m.getName()+ac);
			
			if(m.getName().equals("reInit")) {
				
				re = control.getDeclaredMethod(m.getName());
			}
			if(m.getName().equals("displayName")) {
				disN = control.getDeclaredMethod(m.getName(), String.class);
			}
			if(m.getName().equals("displayItem")) {
				it = control.getDeclaredMethod(m.getName(), int.class);
			}
			ac++;
		}
		
		if(className.equals("Client.AuctionController")) {
			loader = new FXMLLoader(getClass().getResource("Auction.FXML"));
		}
		else {
			String[] pop = className.split(".");
			loader = new FXMLLoader(getClass().getResource(pop[1]+".FXML"));
		}
		root = loader.load();
		
		System.out.println(user);
		
		Object v = control.newInstance();
		
		disN.invoke(v, "dsaa");
		re.invoke(v);
		it.invoke(v, Client.ids.get(itemNum));
		*/
		
		FXMLLoader loader;
		
		
		if(itemNum == 0) {
			URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\Auction.FXML");

			
			loader = new FXMLLoader(cc);
			root = loader.load();
			
			AuctionController AC = loader.getController();
			AC.displayName(user);//1
			AC.initialize();//4
			AC.displayItem(Client.ids.get(itemNum));//2
		}
		else if(itemNum==1){
			
			URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\Item2Controller.FXML");

			
			loader = new FXMLLoader(cc);
			root = loader.load();
			
			Item2Controller I2C = loader.getController();
			
			I2C.displayName(user);
			I2C.initialize();
			I2C.displayItem(Client.ids.get(itemNum));

		}
		else if(itemNum==2){
			
			URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\Item3Controller.FXML");

		    loader = new FXMLLoader(cc);
			root = loader.load();
			
			Item3Controller I3C = loader.getController();
			
			I3C.displayName(user);
			I3C.initialize();
			I3C.displayItem(Client.ids.get(itemNum));

		}
		else if(itemNum==3){
			
			URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\Item4Controller.FXML");

		    loader = new FXMLLoader(cc);
			root = loader.load();
			
			Item4Controller I4C = loader.getController();
			
			I4C.displayName(user);
			I4C.initialize();
			I4C.displayItem(Client.ids.get(itemNum));
		
		}
		else if(itemNum==4){
			
			URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\Item5Controller.FXML");

		    loader = new FXMLLoader(cc);
		    root = loader.load();
			
			Item5Controller I5C = loader.getController();
			
			I5C.displayName(user);
			I5C.initialize();
			I5C.displayItem(Client.ids.get(itemNum));
		
		}
		else if(itemNum==5){
			
			URL cc = new URL("file:\\C:\\Users\\mufiq\\OneDrive\\Desktop\\ECE 422C\\Final Project Client\\src\\Client\\Item6Controller.FXML");

		    loader = new FXMLLoader(cc);
		    root = loader.load();
			
			Item6Controller I6C = loader.getController();
			
			I6C.displayName(user);
			I6C.initialize();
			I6C.displayItem(Client.ids.get(itemNum));

		}
		
		

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void setName(String n, String cn, int nb) {
		this.itemNum = nb;
		//s_bidList = bidList;
		this.className = cn;
		this.user = n;
	}
	
	public void setBids(List<Map<String, String>> x) {
		bids = x;
	}
	
	public String display(String s, List<Map<String, String>> bids, boolean all, int cc, String n, String tot, String f) {
		
		titleForHistory.setText(s);
		individual = "";
		String sf="";
		String temp;
		
		if(all) {
			/*for(int i = 0; i<bids.size(); i++) {
				for(String p:bids.get(i).keySet()) {
					tot += bids.get(i).get(p);
					total += bids.get(i).get(p);
				}
			}
			//bidList.setText(tot);*/
			f.replace("?", "");
			if(f!=null) {
				String[] c = f.split("\\*");
				System.out.println(Arrays.toString(c));

				for(int i = 0; i<c.length; i++) {
					sf += c[i]+"\n";
					temp = c[i];
					listV.getItems().add(temp);
					words.add(temp);
				}
				//bidList.setText(sf);
			}
			
		}
		else {
			
			for(int i = 0; i<bids.size(); i++) {
				for(String p:bids.get(i).keySet()) {
					if(p.equals(n)) {
						temp = bids.get(i).get(p);
						listV.getItems().add(temp);
						words.add(temp);
						individual += bids.get(i).get(p);
					}
				}
			}
			//bidList.setText(individual);
			return "";

		}
		return tot;
		
	}
	
	public void clear(ActionEvent event) {
		listV.getItems().clear();
		listV.getItems().addAll(words);
		searchBar.setText("");
	}
	
	public void searchPressed(ActionEvent event) {
		listV.getItems().clear();
		listV.getItems().addAll(s(searchBar.getText(),words));
	}
	
	public List<String> s(String sW, List<String> lst){
		
		List<String> sWA = Arrays.asList(sW.trim().split(" "));
		
		return lst.stream().filter(input->{ return sWA.stream().allMatch(word->input.toLowerCase().contains(word.toLowerCase()));}).collect(Collectors.toList());
	}
	
}
