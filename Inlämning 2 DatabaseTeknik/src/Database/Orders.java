package Database;

import java.util.ArrayList;

public class Orders {

	private int KID;
	private int OrderID;
	private String Date;
	private boolean Expedierad;
	private ArrayList<OrderInfo> orderInfo;
	
	public Orders(int KundID, int OrderID, String Date, Boolean Exp, ArrayList<OrderInfo> orders)
	{
		this.KID = KundID;
		this.OrderID = OrderID;
		this.Date = Date;
		this.Expedierad = Exp;
		orderInfo = orders;
	}
	
	public String toString(){
		return Date;
	}

	public int getKID() {
		return KID;
	}

	public int getOrderID() {
		return OrderID;
	}

	public String getDate() {
		return Date;
	}

	public boolean isExpedierad() {
		return Expedierad;
	}

	public ArrayList<OrderInfo> getOrderInfo() {
		return orderInfo;
	}

	public void setKID(int kID) {
		KID = kID;
	}

	public void setOrderID(int orderID) {
		OrderID = orderID;
	}

	public void setDate(String date) {
		Date = date;
	}

	public void setExpedierad(boolean expedierad) {
		Expedierad = expedierad;
	}

	public void setOrderInfo(ArrayList<OrderInfo> orderInfo) {
		this.orderInfo = orderInfo;
	}
	
}
