package GUI;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame{

	private GUI gui;
	
	public Window(String title, int width, int height, GUI gui){
		super(title);
		
		Dimension dim = new Dimension(width, height);
		
		gui.setPreferredSize(dim);
		gui.setMaximumSize(dim);
		gui.setMinimumSize(dim);
		add(gui);
		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
