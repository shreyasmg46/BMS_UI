package com.cruds.ui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.cruds.db.BookDAO;

public class ListAllBooksPanel extends JPanel {

	private JTable table;
	private JScrollPane pane;
	private JButton btnHome;

	Vector<String> column = new Vector<String>();
	BookDAO dao = null;

	public ListAllBooksPanel(Container parent, CardLayout layout) {
		column.add("ISBN");
		column.add("Title");
		column.add("Category");
		column.add("NoOfBooks");
		column.add("Author");
		column.add("MailID");

		dao = new BookDAO();

		table = new JTable(new DefaultTableModel(dao.getBookData(), column));
		pane = new JScrollPane(table);

		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentShown(ComponentEvent e) {

				table.setModel(new DefaultTableModel(dao.getBookData(), column));
			}
		});

		btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(parent,"HOME");
			}
		});
		
		add(btnHome);
		add(pane);
	}
	
}
