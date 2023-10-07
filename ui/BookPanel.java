package com.cruds.ui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cruds.db.BookDAO;
import com.cruds.entity.Author;
import com.cruds.entity.Book;
import com.cruds.exception.BMSException;

public class BookPanel extends JPanel {
	
	private JLabel lblISBN;
	private JLabel lblTitle;
	private JLabel lblCategory;
	private JLabel lblNoOfBooks;
	private JLabel lblAuthorname;
	private JLabel lblMailID;
	private JTextField txtISBN;
	private JTextField txtTitle;
	private JTextField txtCategory;
	private JTextField txtNoOfBooks;
	private JTextField txtAuthorname;
	private JTextField txtMailID;
	private JButton btnCreate;
	private JButton btnHome;
	private JPanel panel;
	
	public BookPanel(Container parent, CardLayout layout) {
		
		panel = this;
		
		lblISBN = new JLabel("Book ISBN");
		lblTitle = new JLabel("Book Title");
		lblCategory = new JLabel("Book Category");
		lblNoOfBooks = new JLabel("NoOfBooks");
		lblAuthorname = new JLabel("Book Author");
		lblMailID = new JLabel("Author MailID");
		
		txtISBN = new JTextField(8);
		txtTitle = new JTextField(12);
		txtCategory = new JTextField(7);
		txtNoOfBooks = new JTextField(8);
		txtAuthorname = new JTextField(12);
		txtMailID = new JTextField(10);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String strISBN = txtISBN.getText();
				String strTitle = txtTitle.getText();
				String strCategory = txtCategory.getText();
				String strNoOfBooks = txtNoOfBooks.getText();
				String strAuthorname = txtAuthorname.getText();
				String strMailID = txtMailID.getText();
				
				if(strTitle.trim().equals("")||strISBN.trim().equals(""))
				{
					getToolkit().beep();
					JOptionPane.showMessageDialog(panel, "Book details cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					Author a = new Author(strAuthorname, strMailID);
					Book b = new Book(Integer.parseInt(strISBN), strTitle, strCategory, Integer.parseInt(strNoOfBooks),a);
					BookDAO dao = new BookDAO();
					if(dao.create(b))
					{
						JOptionPane.showMessageDialog(panel,"Book created successfully" , "Success", JOptionPane.INFORMATION_MESSAGE);
						txtISBN.setText("");
						txtTitle.setText("");
						txtCategory.setText("");
						txtNoOfBooks.setText("");
						txtAuthorname.setText("");
						txtMailID.setText("");
						layout.show(parent, "VIEW");
					}
				} catch (NumberFormatException nfe) {
					getToolkit().beep();
					JOptionPane.showMessageDialog(panel,"Invalid ID","Error", JOptionPane.ERROR_MESSAGE);
				}catch (BMSException bms) {
					getToolkit().beep();
					JOptionPane.showMessageDialog(panel,bms.getInfo(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(parent,"HOME");
				
			}
		});
		
		add(lblISBN);
		add(txtISBN);
		add(lblTitle);
		add(txtTitle);
		add(lblCategory);
		add(txtCategory);
		add(lblNoOfBooks);
		add(txtNoOfBooks);
		add(lblAuthorname);
		add(txtAuthorname);
		add(lblMailID);
		add(txtMailID);
		add(btnCreate);
		add(btnHome);		
	}

}
