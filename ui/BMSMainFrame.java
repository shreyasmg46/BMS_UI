package com.cruds.ui;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class BMSMainFrame extends JFrame {
	
	Container cpane;
	CardLayout layout = new CardLayout();
	
	public BMSMainFrame() {

		cpane = getContentPane();
		cpane.setLayout(layout);
		setTitle("Book Management System");
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		cpane.add("HOME",new HomePanel(cpane, layout));
		cpane.add("CREATE",new BookPanel(cpane, layout));
		cpane.add("VIEW",new ViewPanel(cpane, layout));
		cpane.add("SEARCH", new SearchBookPanel(cpane,layout));
		cpane.add("LIST",new ListAllBooksPanel(cpane, layout));
		
		setVisible(true);
	}
	
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new BMSMainFrame();

			}
		});

	}
}
