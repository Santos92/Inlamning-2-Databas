package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Main.OnlineShoeShop;

public class Database {

	private Connection conn;
	private PreparedStatement query;
	private ResultSet RS;
	
	private String Host = "jdbc:mysql://localhost:3306/OnlineShoeShop"
			+ "?verifyServerCertificate=false"
            + "&useSSL=false"
            + "&requireSSL=false";

	private String User = "Slazhy";
	private String Pass = "asd123";
	
	private OnlineShoeShop shop;
	
	public Database(OnlineShoeShop shop){
		this.shop = shop;
	}
	
	public void Login(String user, String pass){
		
		try {
			conn = Connect();
			query = conn.prepareStatement("SELECT kunder.Namn, Users.kundID FROM users "
					+ "INNER JOIN kunder ON kunder.id = users.kundID "
					+ "WHERE userN = '" + user + "' AND passW = '" + pass + "';");
			RS = query.executeQuery();
			if(RS.next()){
				shop.setLogedin(RS.getInt("kundID"), RS.getString("namn"));
			} else {
				System.out.println("Du har angivit fel uppgifter");
			}
		} catch (SQLException e) {
			System.out.println("N�got gick fel!. ...\n" + e);
		}finally{
			Disconnect();
		}
	}
	
	public void Register(String Name, String user, String pass, String address, String ort, String land){
		try {
			conn = Connect();
			query = conn.prepareStatement("SELECT * FROM Users WHERE userN = '" + user + "'");
			RS = query.executeQuery();
			
			if(!RS.next()){
				query = conn.prepareStatement("call onlineshoeshop.RegisterUser('" + Name + "', '" + user + "', '" + pass + "', '" + address + "', '" + ort + "', '" + land + "')");
				query.executeUpdate();
				Login(user, pass);
			} else {
				System.out.println("Anv�ndarnamnet upptaget!");
			}
		} catch (SQLException e) {
			System.out.println("Kunde inte registrera anv�ndare\n" + e);
		}finally{
			Disconnect();
		}
	}
	
	public ArrayList<String> getOrders(int id) {
		ArrayList<String> Results = new ArrayList<>();
		try {
		conn = Connect();
		query = conn.prepareStatement("call onlineshoeshop.GetCustomerOrders(" + id + ")");
		RS = query.executeQuery();
		while(RS.next()){
			Results.add(RS.getString("namn"));
			Results.add(RS.getString("TotalaSumman"));
		}
	} catch (SQLException e) {
		System.out.println("N�got gick fel!");
	}finally{
		Disconnect();
	}
		return Results;
	}
	
	public String[] showProductsOnCategories() {
		ArrayList<String> Results = new ArrayList<>();
		try {
		conn = Connect();
		query = conn.prepareStatement("SELECT kategori.namn FROM kategori;");
		RS = query.executeQuery();
		while(RS.next()){
			Results.add(RS.getString("namn"));
		}
		} catch (SQLException e) {
		System.out.println("N�got gick fel!");
	}finally{
		Disconnect();
	}
		String[] s = Results.toArray(new String[0]);
		return s;
	}
	
	public ArrayList<String> showProductList(String cat){

		ArrayList<String> Results = new ArrayList<>();
		try {
			conn = Connect();
			query = conn.prepareStatement("call onlineshoeshop.ListProductsByCategory('" + cat + "');");
			RS = query.executeQuery();
			while(RS.next()){
				Results.add(RS.getString("skoID"));
				Results.add(RS.getString("skoM�rke"));
			}
		} catch (SQLException e) {
		System.out.println("N�got gick fel!");
	}finally{
		Disconnect();
	}
		return Results;
		
	}
	
	public ArrayList<Product> showAllProductsInStore(){

		ArrayList<Product> Results = new ArrayList<>();
		try {
			conn = Connect();
			query = conn.prepareStatement("SELECT skor.id AS id, skor.pris AS pris, skor.LagerAntal AS lager, M�rken.Namn AS namn FROM skor "
					+ "INNER JOIN M�rken ON M�rken.ID = skor.m�rkeid "
					+ "WHERE LagerAntal > 0 ");
			RS = query.executeQuery();
			while(RS.next()){
				int id = RS.getInt("id");
				String m�rke = RS.getString("namn");
				int pris = RS.getInt("pris");
				int Lager = RS.getInt("lager");
				Results.add(new Product(id, m�rke, pris, Lager, getProductColors(id), getProductSize(id)));

			}
		} catch (SQLException e) {
		System.out.println("N�got gick fel! product\n" + e);
	}finally{
		Disconnect();
	}
		return Results;
	}
	
	public void SendOrder(Orders ord){
		try {
			int id = ord.getOrderID();
			conn = Connect();
			query = conn.prepareStatement("UPDATE Best�llning SET Expedierad = 1 WHERE Best�llning.ID = " + id);
			query.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Disconnect();
		}
	}
	
	public void AddToCart(int Bid, int Sid, int Kid, String f�rg, String stl){
		String bid;
		if (Bid == 0)
			bid = "null";
		else
			bid = Bid + "";
		try {
			conn = Connect();
			query = conn.prepareStatement("call onlineshoeshop.AddToCart(" + Kid + ", " + bid + ", " + Sid + ", " + 1 + ", '" + f�rg + "', '" + stl + "' )");
			query.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Orders> showAllOrders(int Kid){

		ArrayList<Orders> Results = new ArrayList<>();
		try {
			conn = Connect();
			query = conn.prepareStatement
					("SELECT best�llning.id, " +
					 "Best�llning.Datum, " +
					 "Best�llning.Expedierad " +
			         "FROM Best�llning " +
					 "INNER JOIN Best�llningInfo ON Best�llningInfo.Best�llningID = best�llning.id " +
			         "INNER JOIN Kunder " +
					 "WHERE Best�llning.Kundid = " + Kid + " AND Expedierad = 0 GROUP BY id");
			
			RS = query.executeQuery();
			while(RS.next()){
				int KundID = Kid;
				int BID = RS.getInt("id");
				String Datum = RS.getString("Datum");
				boolean exp = RS.getBoolean("Expedierad");
				
				Results.add(new Orders(KundID, BID, Datum, exp, getOrderInfo(BID)));

			}
		} catch (SQLException e) {
		System.out.println("N�got gick fel! product\n" + e);
	}finally{
		Disconnect();
	}
		return Results;
	}
	
	public ArrayList<OrderInfo> getOrderInfo(int id){
		ArrayList<OrderInfo> Results = new ArrayList<>();
		try {
		ResultSet RS;
		PreparedStatement query;
		query = conn.prepareStatement
				("SELECT Best�llningInfo.antal, " +
						 "M�rken.namn, " +
						 "skor.pris, " +
						 "skor.id AS SID, " +
						 "F�rger.f�rg, " +
						 "Storlek.storlek " +
				         "FROM Best�llning " +
						 "INNER JOIN Best�llningInfo ON Best�llningInfo.Best�llningID = best�llning.id " +
					  	 "INNER JOIN Skor ON Best�llningInfo.skoID = skor.id " +
						 "INNER JOIN M�rken ON M�rken.ID = skor.m�rkeID " +
					  	 "INNER JOIN F�rger ON F�rger.SkoID = skor.id " +
						 "INNER JOIN Storlek ON Storlek.skoID = Skor.id " +
						 "WHERE best�llning.id = " + id);
		RS = query.executeQuery();
		while(RS.next()){
			int SID = RS.getInt("SID");
			int antal = RS.getInt("antal");
			String m�rke = RS.getString("namn");
			int pris = RS.getInt("pris");
			String f�rg = RS.getString("f�rg");
			String Storlek = RS.getString("Storlek");
			
			Results.add(new OrderInfo(SID, pris, antal, m�rke, f�rg, Storlek));
			
		}
		} catch (SQLException e) {
		System.out.println("N�got gick fel! orderinfo \n" + e);
	}
		return Results;
	}
	public ArrayList<OrderInfo> showOrderInfo(int id){
		ArrayList<OrderInfo> Results = new ArrayList<>();
		try {
		conn = Connect();
		ResultSet RS;
		PreparedStatement query;
		query = conn.prepareStatement
				("SELECT Best�llningInfo.antal, " +
						 "M�rken.namn, " +
						 "skor.pris, " +
						 "skor.id AS SID, " +
						 "F�rger.f�rg, " +
						 "Storlek.storlek " +
				         "FROM Best�llning " +
						 "INNER JOIN Best�llningInfo ON Best�llningInfo.Best�llningID = best�llning.id " +
					  	 "INNER JOIN Skor ON Best�llningInfo.skoID = skor.id " +
						 "INNER JOIN M�rken ON M�rken.ID = skor.m�rkeID " +
					  	 "INNER JOIN F�rger ON F�rger.SkoID = skor.id " +
						 "INNER JOIN Storlek ON Storlek.skoID = Skor.id " +
						 "WHERE best�llning.id = " + id + " GROUP BY SID");
		RS = query.executeQuery();
		while(RS.next()){
			int SID = RS.getInt("SID");
			int antal = RS.getInt("antal");
			String m�rke = RS.getString("namn");
			int pris = RS.getInt("pris");
			String f�rg = RS.getString("f�rg");
			String Storlek = RS.getString("Storlek");
			
			Results.add(new OrderInfo(SID, pris, antal, m�rke, f�rg, Storlek));
			
		}
		} catch (SQLException e) {
		System.out.println("N�got gick fel! orderinfo \n" + e);
		}finally{
			Disconnect();
		}
		return Results;
	}
	public ArrayList<String> getProductColors(int id){
		ArrayList<String> Results = new ArrayList<>();
		try {
		ResultSet RS;
		PreparedStatement query;
		query = conn.prepareStatement("SELECT F�rger.f�rg FROM F�rger WHERE skoID = "+ id +";");
		RS = query.executeQuery();
		while(RS.next()){
			Results.add(RS.getString("f�rg"));
		}
		} catch (SQLException e) {
		System.out.println("N�got gick fel! f�rger \n" + e);
	}
		return Results;
	}
	public ArrayList<String> getProductSize(int id){
		ArrayList<String> Results = new ArrayList<>();
		try {
		ResultSet RS;
		PreparedStatement query;
		query = conn.prepareStatement("SELECT Storlek.Storlek FROM Storlek WHERE skoID = "+ id +";");
		RS = query.executeQuery();
		while(RS.next()){
			Results.add(RS.getString("Storlek"));
		}
		} catch (SQLException e) {
		System.out.println("N�got gick fel! storlek\n" + e);
	}
		return Results;
	}
	
	public Connection Connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(Host, User, Pass);
		}catch(Exception e){
			System.out.println("n�got gick fel!");
		}
		return conn;
	}
	
	public void Disconnect(){
	    try { if (RS != null) RS.close(); } catch (Exception e) {};
	    try { if (query != null) query.close(); } catch (Exception e) {};
	    try { if (conn != null) conn.close(); } catch (Exception e) {};
	}	
}
