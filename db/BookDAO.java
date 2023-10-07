package com.cruds.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.cruds.entity.Author;
import com.cruds.entity.Book;
import com.cruds.exception.BMSException;

public class BookDAO {
	
	public boolean create(Book b) 
	{
		String sql1 = "insert into book(ISBN, title, category,NoOfBooks) values (?,?,?,?)";
		String sql2 = "insert into author(authorname,mailID,ISBN) values (?,?,?)";

		int row1 =0,row2=0 ;

		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.setInt(1, b.getISBN());
			ps.setString(2, b.getTitle());
			ps.setString(3, b.getCategory());
			ps.setInt(4, b.getNoOfBooks());

			row1 = ps.executeUpdate();

			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setString(1, b.getAuthor().getAuthorname());
			ps2.setString(2, b.getAuthor().getMailId());
			ps2.setInt(3, b.getISBN());

			row2 = ps2.executeUpdate();

		} catch (SQLException e) {
			if(e.getMessage().contains("Duplicate"))
			{                          //primary key violation
				throw new BMSException("Book Id already exists");
			}
			else
			{                          //general msg
				throw new BMSException("DB Error during create,contact admin");
			}
		}
		return (row1 > 0 && row2 > 0 );
	}
	
	public List<Book> getAll()
	{
		String sql =" select b.ISBN, b.title, b.category, b.NoOfBooks , a.authorname , a.mailID from book b ,author a where b.ISBN = a.ISBN ";
		List<Book> list = new ArrayList<Book>();
		Book b = null;

		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs != null && rs.next())
			{
				Author a = new Author(rs.getString(5),rs.getString(6));
				b = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), a);
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Book getBook(int ISBN ) 
	{
		String sql = "select b.ISBN, b.title, b.category, b.NoOfBooks , a.authorname , a.mailID from book b ,author a where b.ISBN = a.ISBN and b.ISBN LIKE ?  ";
		Book b = null;

		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,ISBN);

			ResultSet rs = ps.executeQuery();
			if(rs != null && rs.next())
			{
				Author a = new Author(rs.getString(5), rs.getString(6));
				b = new Book( rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public Book getTitle(String title) 
	{
		String sql = "select b.ISBN, b.title, b.category, b.NoOfBooks , a.authorname , a.mailID from book b ,author a where b.ISBN = a.ISBN and b.title LIKE ? ";
		Book b = null;

		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+title+"%");

			ResultSet rs = ps.executeQuery();
			if(rs != null && rs.next())
			{
				Author a = new Author(rs.getString(5), rs.getString(6));
				b = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public Book getCategory(String category) 
	{
		String sql = "select b.ISBN, b.title, b.category, b.NoOfBooks, a.authorname , a.mailID from book b ,author a where b.ISBN = a.ISBN and b.category LIKE ? ";
		Book b = null;

		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,"%"+category +"%");

			ResultSet rs = ps.executeQuery();
			if(rs != null && rs.next())
			{
				Author a = new Author(rs.getString(5), rs.getString(6));
				b = new Book( rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public Book getAuthorname(String authorname ) 
	{
		String sql = "select b.ISBN, b.title, b.category, b.NoOfBooks, a.authorname , a.mailID  from book b ,author a where b.ISBN = a.ISBN and a.authorname LIKE ? ";
		Book b = null;

		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+authorname+"%");

			ResultSet rs = ps.executeQuery();
			if(rs != null && rs.next())
			{
				Author a = new Author(rs.getString(5), rs.getString(6));
				b = new Book( rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean delete(int ISBN)
	{
		String sql = "Delete from Book where ISBN=?";
		int rows = 0;

		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ISBN);

			rows = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows > 0;
	}
	
	public Vector<Vector<String>> getBookData()
	{
		String sql =" select  b.ISBN, b.title, b.category, b.NoOfBooks , a.authorname , a.mailID from book b ,author a where b.ISBN = a.ISBN ";
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		Vector<String> row = null;

		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs !=null && rs.next())
			{
				row = new Vector<String>();
				row.add(String.valueOf(rs.getInt(1)));  //int to string
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(String.valueOf(rs.getInt(4)));
				row.add(rs.getString(5));
				row.add(rs.getString(6));
				data.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	

		return data;
	}

	public Vector<Vector<String>> getSearchAuthorData(String authorname)  
	{
		String sql ="select b.ISBN, b.title, b.category, b.NoOfBooks , a.authorname , a.mailID from book b ,author a where b.ISBN = a.ISBN and a.authorname LIKE ?  ";
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		Vector<String> row = null;

		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1,"%"+authorname+"%");

			ResultSet rs = ps.executeQuery();

			while(rs !=null && rs.next())
			{
				row = new Vector<String>();
				row.add(String.valueOf(rs.getInt(1)));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(String.valueOf(rs.getInt(4)));
				row.add(rs.getString(5));
				row.add(rs.getString(6));
				data.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	

		return data;
	}
	
	public Vector<Vector<String>> getSearchCategoryData(String category)  
	{
		String sql ="select b.ISBN, b.title, b.category, b.NoOfBooks , a.authorname , a.mailID from book b ,author a where b.ISBN = a.ISBN and b.category LIKE ?  ";
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		Vector<String> row = null;

		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+category+"%");

			ResultSet rs = ps.executeQuery();

			while(rs !=null && rs.next())
			{
				row = new Vector<String>();
				row.add(String.valueOf(rs.getInt(1)));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(String.valueOf(rs.getInt(4)));
				row.add(rs.getString(5));
				row.add(rs.getString(6));
				data.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	

		return data;
	}
	
	public Vector<Vector<String>> getSearchTitleData(String title)  
	{
		String sql ="select b.ISBN, b.title, b.category, b.NoOfBooks , a.authorname , a.mailID from book b ,author a where b.ISBN = a.ISBN and b.title LIKE ?  ";
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		Vector<String> row = null;

		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+title+"%");

			ResultSet rs = ps.executeQuery();

			while(rs !=null && rs.next())
			{
				row = new Vector<String>();
				row.add(String.valueOf(rs.getInt(1)));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(String.valueOf(rs.getInt(4)));
				row.add(rs.getString(5));
				row.add(rs.getString(6));
				data.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	

		return data;
	}
	
	
	
}
