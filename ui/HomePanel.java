package com.cruds.ui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class HomePanel extends JPanel {
	
	private JButton btnCreateBook;
	private JButton btnView;
	private JButton btnSearchBook;
	private JButton btnListAllBooks;
	
	public HomePanel(Container parent,CardLayout layout) {

		setLayout(new GridLayout(9,1));
		btnCreateBook = new JButton("Create Book");
		btnCreateBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(parent, "CREATE");				
			}

		});
		
		btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				layout.show(parent, "VIEW");
			}
		});
		
		btnSearchBook = new JButton("Search Book based on");
		btnSearchBook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				layout.show(parent, "SEARCH");

			}
		});
		
		btnListAllBooks = new JButton("List all the Books");
		btnListAllBooks.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				layout.show(parent, "LIST");
			}
		});
		
		add(btnCreateBook);
		add(btnView);
		add(btnSearchBook);
		add(btnListAllBooks);

	}

}
