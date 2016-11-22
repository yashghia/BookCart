/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library.Data;

import Library.Models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Library.Models.Book;


/**
 *
 * @author yash_
 */
public class BooksDB {
    
     public static int addReview(int bookid,String bookname,String emailid,String review ) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection(); 
        PreparedStatement ps = null;
        try{
        String insertQuery =
               "Insert into books_review values(?,?,?,?)";
        ps = connection.prepareStatement(insertQuery);
            ps.setInt(1,bookid);
            ps.setString(2,bookname);
            ps.setString(3,emailid);
            ps.setString(4,review);
            int rs = ps.executeUpdate();
            return rs;
        }
        catch (SQLException e) {
            System.err.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
     }
    
     public static List<Book> selectBooks() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ArrayList<Book> books = new ArrayList<Book>();
        
        ResultSet rs = null;
        
        String searchQuery =
               "select * from books";
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(searchQuery);
            while(rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("bookid"));
                book.setBookName(rs.getString("bookname"));
                book.setBookAuthor(rs.getString("bookauthor"));
                book.setBookGenre(rs.getString("bookgenre"));
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
    }
     
     public static Book selectBook(int id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        Book book = new Book();
        
        ResultSet rs = null;
        
        String searchQuery =
               "select * from books where bookid = ?";
        try {
            ps = connection.prepareStatement(searchQuery);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()) {
                book.setBookId(rs.getInt("bookid"));
                book.setBookName(rs.getString("bookname"));
                book.setBookAuthor(rs.getString("bookauthor"));
                book.setBookGenre(rs.getString("bookgenre"));
            }
            return book;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
    }
     
     public static int insertBook(Book book) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        int rowcount = 0;
        
        String insertQuery =
               "Insert into books(bookname,bookauthor,bookgenre) "
                + "values(?,?,?)";
        try {
            ps = connection.prepareStatement(insertQuery);
            ps.setString(1, book.getBookName());
            ps.setString(2, book.getBookAuthor());
            ps.setString(3, book.getBookGenre());
            rowcount = ps.executeUpdate();
            
            return rowcount;
        } catch (SQLException e) {
            System.err.println(e);
            return rowcount;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
     
     public static int updateBook(Book book) {
        PreparedStatement ps = null;
        ConnectionPool pool = ConnectionPool.getInstance();   
        Connection connection = pool.getConnection();
        int updatecount =0;
        String updateQuery = "UPDATE books SET "               
                + "bookname = ?, "
                + "bookauthor =?, "
                + "bookgenre =? "
                + "WHERE bookid = ?";
        try {
            ps = connection.prepareStatement(updateQuery);
            ps.setString(1, book.getBookName());
            ps.setString(2, book.getBookAuthor());
            ps.setString(3, book.getBookGenre());
            ps.setInt(4, book.getBookId());
            updatecount = ps.executeUpdate();
            return updatecount;
        } catch (SQLException e) {
            System.out.println(e);
            return updatecount;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
     }
     
     public static int deletebook(int bookid){
        PreparedStatement ps = null;
        ConnectionPool pool = ConnectionPool.getInstance();   
        Connection connection = pool.getConnection();
        String query = "DELETE FROM books "
                + "WHERE bookid = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, bookid);

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
     }
}
