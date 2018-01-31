package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Database.Database;
import Database.OrderInfo;
import Database.Orders;
import Database.Product;
import Main.OnlineShoeShop;

public class Pages {
	
	private Color buttonColor = new Color(217, 133, 59);
	private Color textColor = new Color(236, 236, 234);
	private Font buttonFont = new Font("ARIAL", Font.BOLD, 15);
	
	private JLabel userN = new JLabel("Anv�ndarnamn: ");
	private JLabel passW = new JLabel("L�senord: ");
	private JLabel name = new JLabel("Namn: ");
	private JLabel Address = new JLabel("Address: ");
	private JLabel Ort = new JLabel("Ort: ");
	
	private JTextField AddressF = new JTextField(15);
	private JTextField OrtF = new JTextField(15);
	private JTextField LoginF = new JTextField(15);
	private JTextField PassF = new JTextField(15);
	
	private JButton LoginB = new JButton("Logga in");
	private JButton RegisterB = new JButton("Registrera");
	
	private JTextField NameF = new JTextField(15);
	private JComboBox CountryBox = new JComboBox(getAllCountries());
	private JComboBox GetCategoriesBox;
	
	private JButton L�ggBest�llning = new JButton("Hantera best�llningar");
	
	private JLabel kundIDL = new JLabel("Kund id: ");
	private JTextField kundID = new JTextField("0");
	private JButton KollaBest�llningar = new JButton("Kolla best�llningar");
	
	private JButton VisaProdukter = new JButton("Visa produkter");
	
	private Database database;
	private GUI gui;
	private OnlineShoeShop oss;
		
	public Pages(GUI gui, Database database, OnlineShoeShop oss){
		this.database = database;
		this.gui = gui;
		this.oss = oss;
		GetCategoriesBox = new JComboBox(this.database.showProductsOnCategories());
	}
	
	public JPanel startPage(){
		
		JPanel p = new JPanel();
		JPanel p1 = new JPanel();
		JLabel land = new JLabel("Land: ");
		
		p1.setLayout(new GridBagLayout());
		
		p.setLayout(new GridLayout(0,2, 10, 5));
		p.setOpaque(false);
		
		CountryBox.setPreferredSize(new Dimension(10, 15));
		
		p.add(name);
		p.add(NameF);
		
		p.add(userN);
		p.add(LoginF);
		
		p.add(passW);
		p.add(PassF);
		
		p.add(Address);
		p.add(AddressF);
		
		p.add(Ort);
		p.add(OrtF);
	
		
		p.add(land);
		p.add(CountryBox);
		p.add(LoginB);
		p.add(RegisterB);
		
		setTheme(land);
		setTheme(AddressF);
		setTheme(OrtF);
		setTheme(CountryBox);
		setTheme(NameF);
		setTheme(LoginF);
		setTheme(LoginB);
		setTheme(PassF);
		setTheme(RegisterB);
		setTheme(userN);
		setTheme(name);
		setTheme(passW);
		setTheme(Address);
		setTheme(Ort);
		
		p1.add(p, new GridBagConstraints());
		p1.setOpaque(false);
		
		LoginB.addActionListener(e -> database.Login(LoginF.getText(), PassF.getText()));
		RegisterB.addActionListener(e -> database.Register(NameF.getText(), LoginF.getText(), PassF.getText(), AddressF.getText(), OrtF.getText(), (String)CountryBox.getSelectedItem()));
		return p1;
	}
	public JPanel LogedinPage(){
		
		JPanel p = new JPanel();
		p.setOpaque(false);
		p.setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel();
		p1.setOpaque(false);
		p1.setLayout(new GridLayout(3,0, 5,5));
		
		p1.add(kundIDL);
		p1.add(kundID);
		p1.add(KollaBest�llningar);
		
		p1.add(VisaProdukter);
		p1.add(GetCategoriesBox);
		p1.add(new JLabel());
		p1.add(L�ggBest�llning);
		
		setTheme(kundIDL);
		setTheme(kundID);
		setTheme(KollaBest�llningar);
		setTheme(VisaProdukter);
		setTheme(GetCategoriesBox);
		setTheme(L�ggBest�llning);
		
		KollaBest�llningar.addActionListener(e -> gui.SwapPages(checkOrders(Integer.parseInt(kundID.getText()))));
		VisaProdukter.addActionListener(e -> gui.SwapPages(checkProducts(GetCategoriesBox.getSelectedItem().toString())));
		L�ggBest�llning.addActionListener(e -> gui.SwapPages(PlaceOrderPage()));
		
		p.add(p1, BorderLayout.NORTH);
		
		return p;
	}
	public JPanel checkOrders(int id){
		
		JPanel p = new JPanel();
		p.setOpaque(false);
		p.setLayout(new GridBagLayout());
		
		JPanel p1 = new JPanel();
		p1.setOpaque(false);
		p1.setLayout(new GridLayout(0,2, 10,5));
		
		JButton back = new JButton("Tillbaka");
		
		JLabel Knamn = new JLabel("Namn");
		JLabel KSum = new JLabel("Summa");
		
		setTheme(Knamn);
		setTheme(KSum);
		setTheme(back);
		
		p1.add(back);
		p1.add(new JLabel(""));
		
		p1.add(Knamn);
		p1.add(KSum);
		
		ArrayList<String> Orders = database.getOrders(id);
		int i = 0;
		for(String x : Orders){
				
			JLabel a = new JLabel(x);
			if ((++i % 2) == 0)
				a.setText(x + ":-");
			setTheme(a);
			p1.add(a);
		}
		
		back.addActionListener(e -> gui.SwapPages(LogedinPage()));
		
		p.add(p1, new GridBagConstraints());
		return p;	
	}
	public JPanel checkProducts(String cat){
		
		JPanel p = new JPanel();
		p.setOpaque(false);
		p.setLayout(new GridBagLayout());
		
		JPanel p1 = new JPanel();
		p1.setOpaque(false);
		p1.setLayout(new GridLayout(0,2, 10,5));
		
		JButton back = new JButton("Tillbaka");
		
		JLabel Knamn = new JLabel("SkoID");
		JLabel KSum = new JLabel("M�rke");
		
		setTheme(Knamn);
		setTheme(KSum);
		setTheme(back);
		
		p1.add(back);
		p1.add(new JLabel(""));
		
		p1.add(Knamn);
		p1.add(KSum);
		
		ArrayList<String> Orders = database.showProductList(cat);
		int i = 0;
		for(String x : Orders){
				
			JLabel a = new JLabel(x);
			if ((++i % 2) == 0)
				a.setText(x);
			setTheme(a);
			p1.add(a);
		}
		
		back.addActionListener(e -> gui.SwapPages(LogedinPage()));
		
		p.add(p1, new GridBagConstraints());
		return p;	
	}
	public JPanel PlaceOrderPage(){
		
		JPanel p = new JPanel();
		p.setOpaque(false);
		p.setLayout(new GridBagLayout());
		
		JPanel p1 = new JPanel();
		p1.setOpaque(false);
		p1.setLayout(new GridLayout(0,2, 5,5));

		JButton back = new JButton("Tillbaka");
		
		JButton SkickaBest�llning = new JButton("Skicka Best�llning");
		
		JButton L�ggTillProduktTillBest�llning = new JButton("L�gg till produkt till best�llning");
		
		JButton L�ggTillNyBest�llning = new JButton("G�r en ny best�llning");
		
		JComboBox best�llningar = new JComboBox( (database.showAllOrders(oss.getCustomerID())).toArray());
		
		JButton SeBest�llning = new JButton("Granska Din Best�llning");
		
		setTheme(back);
		setTheme(SkickaBest�llning);
		setTheme(L�ggTillProduktTillBest�llning);
		setTheme(best�llningar);
		setTheme(L�ggTillNyBest�llning);
		setTheme(SeBest�llning);
		
		p1.add(back);
		if(best�llningar.getItemCount() > 0){
			p1.add(best�llningar);
			p1.add(L�ggTillProduktTillBest�llning);
			p1.add(SeBest�llning);
			p1.add(SkickaBest�llning);
		}
		p1.add(L�ggTillNyBest�llning);
		
		
		
		back.addActionListener(e -> gui.SwapPages(LogedinPage()));
		SkickaBest�llning.addActionListener(e -> {database.SendOrder((Orders) best�llningar.getSelectedItem());
													gui.SwapPages(PlaceOrderPage());} );
		L�ggTillNyBest�llning.addActionListener(e -> gui.SwapPages(addProductToOrderPage(0)));
		L�ggTillProduktTillBest�llning.addActionListener(e -> gui.SwapPages(addProductToOrderPage(getSelectedBID((Orders) best�llningar.getSelectedItem()))));
		SeBest�llning.addActionListener(e -> gui.SwapPages(visaBest�llning(getSelectedBID((Orders) best�llningar.getSelectedItem()))));
				
		
		p.add(p1, new GridBagConstraints());
		return p;	
	}
	
	private JPanel visaBest�llning(int selectedBID) {
		ArrayList<OrderInfo> orderInfo = database.showOrderInfo(selectedBID);
		
		int totSumma = 0;
		for (OrderInfo x : orderInfo){
			totSumma += x.getPris() * x.getAntal();
		}
		
		JPanel p1 = new JPanel();
		p1.setOpaque(false);
		p1.setLayout(new BorderLayout());
		
		JPanel pN = new JPanel();
		pN.setOpaque(false);
		pN.setLayout(new GridLayout(0,5, 5,5));
		
		JPanel pS = new JPanel();
		pS.setOpaque(false);
		pS.setLayout(new GridLayout(0,1));
		
		JPanel pC = new JPanel();
		pC.setOpaque(false);
		pC.setLayout(new GridLayout(0,5, 5,5));
		
		JLabel totSum = new JLabel("Totala summan: " + totSumma + ":-");
		totSum.setHorizontalAlignment(JLabel.CENTER);
		JButton back = new JButton("Tillbaka");
		
		JLabel m�rke = new JLabel("M�rke");
		JLabel pris = new JLabel("Pris");
		JLabel f�rg = new JLabel("F�rg");
		JLabel Storlek = new JLabel("Storlek");
		JLabel Antal = new JLabel("Antal");
		
		m�rke.setHorizontalAlignment(JLabel.CENTER);
		pris.setHorizontalAlignment(JLabel.CENTER);
		f�rg.setHorizontalAlignment(JLabel.CENTER);
		Storlek.setHorizontalAlignment(JLabel.CENTER);
		Antal.setHorizontalAlignment(JLabel.CENTER);
		
		setTheme(m�rke);
		setTheme(pris);
		setTheme(f�rg);
		setTheme(Storlek);
		setTheme(Antal);
		setTheme(totSum);
		setTheme(back);
		
		pN.add(back);

		pC.add(m�rke);
		pC.add(pris);
		pC.add(f�rg);
		pC.add(Storlek);
		pC.add(Antal);
		
		for(OrderInfo x : orderInfo){
			
			JLabel M = new JLabel(x.getM�rke());
			JLabel P = new JLabel(x.getPris() + "");
			JLabel F = new JLabel(x.getF�rg());
			JLabel S = new JLabel(x.getStorlek());
			JLabel A = new JLabel(x.getAntal() + "");
			
			M.setHorizontalAlignment(JLabel.CENTER);
			F.setHorizontalAlignment(JLabel.CENTER);
			P.setHorizontalAlignment(JLabel.CENTER);
			S.setHorizontalAlignment(JLabel.CENTER);
			A.setHorizontalAlignment(JLabel.CENTER);
			
			setTheme(M);
			setTheme(P);
			setTheme(F);
			setTheme(S);
			setTheme(A);
			
			pC.add(M);
			pC.add(P);
			pC.add(F);
			pC.add(S);
			pC.add(A);
		}
		
		pS.add(totSum);
		
		back.addActionListener(e -> gui.SwapPages(PlaceOrderPage()));
		
		p1.add(pN, BorderLayout.NORTH);
		p1.add(pC, BorderLayout.CENTER);
		p1.add(pS, BorderLayout.SOUTH);
		
		return p1;
	}

	public int getSelectedBID(Orders obj)
	{
		int id = obj.getOrderID();
		return id;
	}
	
	public JPanel addProductToOrderPage(int bid){
		
		JPanel p = new JPanel();
		p.setOpaque(false);
		p.setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel();
		p1.setOpaque(false);
		p1.setLayout(new GridLayout(2,1, 5,5));
		
		JPanel p2 = new JPanel();
		p2.setOpaque(false);
		p2.setLayout(new GridLayout(0,3, 10,10));
		
		JButton back = new JButton("Tillbaka");
		setTheme(back);
		p1.add(back);		
		
		JLabel info = new JLabel("Klicka p� produkten f�r att L�gga till i best�llningen! Gl�m inte att v�lja f�rg och storlek!");
		info.setHorizontalAlignment(JLabel.CENTER);
		setTheme(info);
		p1.add(info);
		
		ArrayList<Product> products = database.showAllProductsInStore();
		for(Product x : products){

			JPanel pp = new JPanel();
			pp.setOpaque(false);
			pp.setLayout(new BorderLayout());
			
			JPanel pp1 = new JPanel();
			pp1.setOpaque(false);
			pp1.setLayout(new GridLayout(2,2, 2,2));
			
			JButton B = new JButton("<html>" + x.getM�rke() + "<br>Pris: " + x.getPris() + "<br>Antal: " + x.getLager() + "</html>");
			setTheme(B);
			
			JLabel f = new JLabel("F�rger:");
			JLabel s = new JLabel("Storlek:");
			JComboBox f�rger = new JComboBox(x.getF�rger().toArray());
			JComboBox Storlek = new JComboBox(x.getStorlek().toArray());
			setTheme(f);
			setTheme(s);
			setTheme(f�rger);
			setTheme(Storlek);
			
			pp1.add(f);
			pp1.add(f�rger);
			pp1.add(s);
			pp1.add(Storlek);
			pp.add(B, BorderLayout.CENTER);
			pp.add(pp1, BorderLayout.SOUTH);
			
			p2.add(pp);
			B.addActionListener(e -> { database.AddToCart(bid, x.getId(), oss.getCustomerID(), f�rger.getSelectedItem().toString(), Storlek.getSelectedItem().toString()); 
			gui.SwapPages(PlaceOrderPage()); });
			
		}
		back.addActionListener(e -> gui.SwapPages(PlaceOrderPage()));
		
		p.add(p1, BorderLayout.NORTH);
		p.add(p2, BorderLayout.CENTER);
		return p;	
	}
	
	
	
	public void setTheme(JButton button){
		button.setBackground(buttonColor);
		button.setForeground(textColor);
		button.setFont(buttonFont);
		button.setFocusable(false);
	}
	public void setTheme(JTextField field){
		field.setBackground(buttonColor);
		field.setForeground(textColor);
		field.setFont(buttonFont);
	}
	public void setTheme(JComboBox Box){
		Box.setBackground(buttonColor);
		Box.setForeground(textColor);
		Box.setFont(buttonFont);
	}
	public void setTheme(JLabel lab){
		lab.setForeground(textColor);
		lab.setFont(buttonFont);
	}
	
	public String[] getAllCountries() {
		String[] countries = new String[Locale.getISOCountries().length];
		String[] countryCodes = Locale.getISOCountries();
		for (int i = 0; i < countryCodes.length; i++) {
			Locale obj = new Locale("", countryCodes[i]);
		    countries[i] = obj.getDisplayCountry();
		}
		return countries;
	}
}
