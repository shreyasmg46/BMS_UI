package com.cruds.ui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.cruds.db.BookDAO;

public class SearchBookPanel extends JPanel {

	private JTextField txtTitle;
	private JLabel lblTitle;
	private JLabel lblCategory = null;
	private JTextField txtAuthor;
	private JLabel lblAuthor;
	private JScrollPane pane;
	private JPanel panel;
	private JTable table;
	private JButton btnSearchTitle;
	private JButton btnSearchAuthor;
	private JButton btnHome;

	Vector<String> column = new Vector<String>();
	BookDAO dao = null;

	public SearchBookPanel(Container parent, CardLayout layout) {

		panel = this;

		txtTitle = new JTextField(8);
		lblTitle = new JLabel("Search Title");

		txtAuthor = new JTextField(8);	
		lblAuthor = new JLabel("Search Author");

		lblCategory = new JLabel();
		lblCategory.setPreferredSize(new Dimension(100, 127));
		lblCategory.setMaximumSize(new Dimension(100, 127));


		column.add("ISBN");
		column.add("Title");
		column.add("Category");
		column.add("NoOfBooks");
		column.add("Authorname");
		column.add("MailID");

		dao = new BookDAO();
		table = new JTable(null, column);
		pane = new JScrollPane(table);

		btnSearchTitle = new JButton("Search");
		btnSearchTitle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String strTitle = txtTitle.getText();

				if(strTitle.trim().equals(""))
				{
					getToolkit().beep();
					JOptionPane.showMessageDialog(panel, "Book details cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				BookDAO dao = new BookDAO();
				table.setModel(new DefaultTableModel(dao.getSearchTitleData(strTitle), column));

			

			}

		});
		btnSearchAuthor = new JButton("Search");
		btnSearchAuthor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String strAuthor = txtAuthor.getText();

				if(strAuthor.trim().equals(""))
				{
					getToolkit().beep();
					JOptionPane.showMessageDialog(panel, "Book details cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				BookDAO dao = new BookDAO();
				table.setModel(new DefaultTableModel(dao.getSearchAuthorData(strAuthor), column));
			}
		});


		btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(parent,"HOME");

			}
		});

		add(lblCategory);
		add(lblTitle);
		add(txtTitle);
		add(btnSearchTitle);
		add(lblAuthor);
		add(txtAuthor);
		add(btnSearchAuthor);
		add(btnHome);
		add(pane);

}

}