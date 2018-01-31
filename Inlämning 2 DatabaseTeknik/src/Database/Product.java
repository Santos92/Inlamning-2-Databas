package Database;

import java.util.ArrayList;

public class Product {

	private int id;
	private String märke;
	private int pris;
	private int lager;
	private int antal;
	private ArrayList<String> färger;
	private ArrayList<String> storlek;
	private String färg;
	private String stl;
	
	public Product(int id, String märke, int pris, int Lager, ArrayList<String> färg, ArrayList<String> Storlek)
	{
		this.id = id;
		this.märke = märke;
		this.pris = pris;
		this.lager = Lager;
		this.färger = färg;
		this.storlek = Storlek;
	}
	
	public int getId() {
		return id;
	}
	public String getMärke() {
		return märke;
	}
	public int getPris() {
		return pris;
	}
	public int getLager() {
		return lager;
	}
	public ArrayList<String> getFärger() {
		return färger;
	}
	public ArrayList<String> getStorlek() {
		return storlek;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setMärke(String märke) {
		this.märke = märke;
	}
	public void setPris(int pris) {
		this.pris = pris;
	}
	public void setLager(int lager) {
		this.lager = lager;
	}
	public void setFärger(ArrayList<String> färger) {
		this.färger = färger;
	}
	public void setStorlek(ArrayList<String> storlek) {
		this.storlek = storlek;
	}

	public int getAntal() {
		return antal;
	}

	public void setAntal(int antal) {
		this.antal = antal;
	}

	public String getStl() {
		return stl;
	}

	public void setStl(String stl) {
		this.stl = stl;
	}

	public String getFärg() {
		return färg;
	}

	public void setFärg(String färg) {
		this.färg = färg;
	}
	
}
