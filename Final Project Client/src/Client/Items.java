package Client;

import java.io.Serializable;

public class Items implements Serializable{
	
	private String description;
	private double start;
	private double end;
	private String img;
	private int time;
	private boolean soldOut;
	
	public Items(String d, double ss, double e, String i, int time) {
		
		this.description = d;
		this.start = ss;
		this.end = e;
		this.img = i;
		this.time = time;
		this.soldOut = false;
		
	}
	public void setEnd(double s) {
		this.end = s;
	}
	public String getDescription() {
		return description;
	}
	public double getStart() {
		return start;
	}
	public double getEnd() {
		return end;
	}
	public String getIMG() {
		return img;
	}
	public boolean getStatus() {
		return this.soldOut;
	}
	public void setSoldOut(boolean b) {
		this.soldOut = b;
	}
	
	public String toString() {
		return description;
	}
	
	public void setPrice(double s) {
		this.start = s;
	}
	
	public int getTime() {
		return time;
	}

}
