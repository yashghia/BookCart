/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookCart.Data;

import BookCart.Models.BookReview;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import BookCart.Models.Book;


/**
 *
 * @author yash_
 */
public class BooksDB {
    
    public static List<BookReview> selectBookReview(String emailID) throws Exception{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        List<BookReview> reviews = new ArrayList<BookReview>();
        
        ResultSet rs = null;
        
        String searchQuery =
               "select * from books_review where emailId = ? order by bookid";
        try {
           ps = connection.prepareStatement(searchQuery);
            ps.setString(1,emailID);
            rs = ps.executeQuery();
            while(rs.next()) {
                BookReview review = new BookReview();
                review.setBookId(rs.getInt("bookid"));
                review.setBookName(rs.getString("bookname"));
                review.setReview(rs.getString("review"));
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException e) {
            System.err.println(e);
            throw new Exception(e.getMessage());
        } finally {
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
    }
    
     public static int addReview(int bookid,String bookname,String emailid,String review ) throws Exception {
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
            throw new Exception(e.getMessage());
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
     }
    
     public static List<Book> selectBooks() throws Exception{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ArrayList<Book> books = new ArrayList<Book>();
        
        ResultSet rs = null;
        
        String searchQuery =
               "select * from books order by bookid";
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(searchQuery);
            while(rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("bookid"));
                book.setBookName(rs.getString("bookname"));
                book.setBookAuthor(rs.getString("bookauthor"));
                book.setBookGenre(rs.getString("bookgenre"));
                book.setPrice(rs.getInt("bookprice"));
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            System.err.println(e);
            throw new Exception(e.getMessage());
        } finally {
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
    }
     
     public static Book selectBook(int id) throws Exception{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        Book book = new Book();
        
        ResultSet rs = null;
        
        String searchQuery =
               "select * from books where bookid = ? ";
        try {
            ps = connection.prepareStatement(searchQuery);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()) {
                book.setBookId(rs.getInt("bookid"));
                book.setBookName(rs.getString("bookname"));
                book.setBookAuthor(rs.getString("bookauthor"));
                book.setBookGenre(rs.getString("bookgenre"));
                book.setPrice(rs.getInt("bookprice"));
            }
            return book;
        } catch (SQLException e) {
            System.err.println(e);
            throw new Exception(e.getMessage());
        } finally {
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
    }
     
     public static int insertBook(Book book) throws Exception{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        int rowcount = 0;
        
        String insertQuery =
               "Insert into books(bookname,bookauthor,bookgenre,bookprice) "
                + "values(?,?,?,?)";
        try {
            ps = connection.prepareStatement(insertQuery);
            ps.setString(1, book.getBookName());
            ps.setString(2, book.getBookAuthor());
            ps.setString(3, book.getBookGenre());
            ps.setInt(4, book.getPrice());
            rowcount = ps.executeUpdate();
            
            return rowcount;
        } catch (SQLException e) {
            System.err.println(e);
            throw new Exception(e.getMessage());
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
     
     public static int updateBook(Book book) throws Exception{
        PreparedStatement ps = null;
        ConnectionPool pool = ConnectionPool.getInstance();   
        Connection connection = pool.getConnection();
        int updatecount =0;
        String updateQuery = "UPDATE books SET "               
                + "bookname = ?, "
                + "bookauthor =?, "
                + "bookgenre =?, "
                + "bookprice = ? "
                + "WHERE bookid = ?";
        try {
            ps = connection.prepareStatement(updateQuery);
            ps.setString(1, book.getBookName());
            ps.setString(2, book.getBookAuthor());
            ps.setString(3, book.getBookGenre());
            ps.setInt(4, book.getPrice());
            ps.setInt(5, book.getBookId());
            updatecount = ps.executeUpdate();
            return updatecount;
        } catch (SQLException e) {
            System.out.println(e);
            throw new Exception(e.getMessage());
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
     }
     
     public static int deletebook(int bookid) throws Exception{
        PreparedStatement ps_1 = null;
        PreparedStatement ps_2 = null;
        int deletecount = 0;
        ConnectionPool pool = ConnectionPool.getInstance();   
        Connection connection = pool.getConnection();
        String query = "DELETE FROM books "
                + "WHERE bookid = ?";
        String deleteQuery = "DELETE FROM books_review "
                + "WHERE bookid = ?";
        try {
            ps_1 = connection.prepareStatement(query);
            ps_2 = connection.prepareStatement(deleteQuery);
            ps_1.setInt(1, bookid);
            ps_2.setInt(1, bookid);
            
            ps_1.executeUpdate();
            ps_2.executeUpdate();
            
            return 1;
        } 
        catch (SQLException e) {
            System.out.println(e);
            throw new Exception(e.getMessage());
        } 
        finally {
            DBUtil.closePreparedStatement(ps_1);
            DBUtil.closePreparedStatement(ps_2);
            pool.freeConnection(connection);
        }
     }
}
