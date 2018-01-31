package Database;

public class OrderInfo {

	private int skoID;
	private int pris;
	private int antal;
	private String m�rke;
	private String f�rg;
	private String storlek;
	
	public OrderInfo(int sid, int pris, int antal, String m�rke, String f�rg, String storlek){
		this.skoID = sid;
		this.pris = pris;
		this.antal = antal;
		this.m�rke = m�rke;
		this.f�rg = f�rg;
		this.storlek = storlek;
	}

	public int getSkoID() {
		return skoID;
	}

	public int getPris() {
		return pris;
	}

	public int getAntal() {
		return antal;
	}

	public String getM�rke() {
		return m�rke;
	}

	public String getF�rg() {
		return f�rg;
	}

	public String getStorlek() {
		return storlek;
	}

	public void setSkoID(int skoID) {
		this.skoID = skoID;
	}

	public void setPris(int pris) {
		this.pris = pris;
	}

	public void setAntal(int antal) {
		this.antal = antal;
	}

	public void setM�rke(String m�rke) {
		this.m�rke = m�rke;
	}

	public void setF�rg(String f�rg) {
		this.f�rg = f�rg;
	}

	public void setStorlek(String storlek) {
		this.storlek = storlek;
	}
	
}
