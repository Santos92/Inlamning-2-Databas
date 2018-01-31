package Main;

import javax.swing.JOptionPane;

import Database.Database;
import GUI.GUI;
import GUI.Pages;

public class OnlineShoeShop {	
	private Database database;
	private GUI gui;
	private Pages pages;
	
	private boolean Logedin = false;
	private int CustomerID;
	private String name;
	
	public OnlineShoeShop(){
		database = new Database(this);
		
		gui = new GUI(database, this);
	}
	
	public void setLogedin(int cID, String name){
		Logedin = true;
		setCustomerID(cID);
		this.name = name;
		gui.Logedin();
		JOptionPane.showMessageDialog(null, "Välkommen " + name + "!");
	}
	
	public static void main(String[] args) {
		new OnlineShoeShop();
	}

	public int getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}

}
