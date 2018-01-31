package Database;

import java.util.ArrayList;

public class Product {

	private int id;
	private String m�rke;
	private int pris;
	private int lager;
	private int antal;
	private ArrayList<String> f�rger;
	private ArrayList<String> storlek;
	private String f�rg;
	private String stl;
	
	public Product(int id, String m�rke, int pris, int Lager, ArrayList<String> f�rg, ArrayList<String> Storlek)
	{
		this.id = id;
		this.m�rke = m�rke;
		this.pris = pris;
		this.lager = Lager;
		this.f�rger = f�rg;
		this.storlek = Storlek;
	}
	
	public int getId() {
		return id;
	}
	public String getM�rke() {
		return m�rke;
	}
	public int getPris() {
		return pris;
	}
	public int getLager() {
		return lager;
	}
	public ArrayList<String> getF�rger() {
		return f�rger;
	}
	public ArrayList<String> getStorlek() {
		return storlek;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setM�rke(String m�rke) {
		this.m�rke = m�rke;
	}
	public void setPris(int pris) {
		this.pris = pris;
	}
	public void setLager(int lager) {
		this.lager = lager;
	}
	public void setF�rger(ArrayList<String> f�rger) {
		this.f�rger = f�rger;
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

	public String getF�rg() {
		return f�rg;
	}

	public void setF�rg(String f�rg) {
		this.f�rg = f�rg;
	}
	
}
