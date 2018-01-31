package GUI;


import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import Database.Database;
import Main.OnlineShoeShop;

public class GUI extends JPanel {
	
	private static final long serialVersionUID = 1953800407196679686L;
	private static final int WIDTH = 600, HEIGHT = 500;
	private static final String TITLE = "Shoe shop online";
	
	private Pages pages;
	private Database database;
	private OnlineShoeShop oss;
	
	private JPanel mainP = new JPanel();
	
	public GUI(Database database, OnlineShoeShop oss){
		
		this.oss = oss;
		this.database = database;
		pages = new Pages(this, this.database, oss);
		mainP = this.pages.startPage();
		
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(116, 175, 173));
		this.add(mainP, BorderLayout.CENTER);
				
		new Window(TITLE, WIDTH, HEIGHT, this);
	}
	
	public void SwapPages(JPanel panel)
	{		
		remove(mainP);
		mainP = panel;
		add(mainP);
		validate();
		repaint();
	}
	
	public GUI getGui(){
		return this;
	}

	public void Logedin() {
		SwapPages(pages.LogedinPage());
	}
	
}

