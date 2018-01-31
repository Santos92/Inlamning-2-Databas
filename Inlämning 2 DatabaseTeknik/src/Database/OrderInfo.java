package Database;

public class OrderInfo {

	private int skoID;
	private int pris;
	private int antal;
	private String märke;
	private String färg;
	private String storlek;
	
	public OrderInfo(int sid, int pris, int antal, String märke, String färg, String storlek){
		this.skoID = sid;
		this.pris = pris;
		this.antal = antal;
		this.märke = märke;
		this.färg = färg;
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

	public String getMärke() {
		return märke;
	}

	public String getFärg() {
		return färg;
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

	public void setMärke(String märke) {
		this.märke = märke;
	}

	public void setFärg(String färg) {
		this.färg = färg;
	}

	public void setStorlek(String storlek) {
		this.storlek = storlek;
	}
	
}
