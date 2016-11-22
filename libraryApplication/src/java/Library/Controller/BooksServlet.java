/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library.Controller;

import Library.Data.*;
import Library.Models.*;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yash_
 */
@WebServlet(name = "BooksServlet", urlPatterns = {"/books"})
public class BooksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
        String url="";
        String action = request.getParameter("action");
        
        
        if(action.equals("Add Book"))
        {
            HttpSession session = request.getSession(true);	    
            session.setAttribute("IsAdd",true); 
            url="/addbooks.jsp";
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);  
        }
        else if(action.equals("Add Review"))
        {
            Book book = new Book();
            url="/addreview.jsp";
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            book = BooksDB.selectBook(bookId);
            request.setAttribute("book", book);
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response); 
        }
        else if(action.equals("Assign Admin Role"))
        {
            List<User> users = new ArrayList<User>();
            url="/displayusers.jsp";
            users = UserDB.selectUsers();
            request.setAttribute("users", users);
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response); 
        }
        else if(action.equals("Update"))
       {
            Book book = new Book();
            book.setBookId(Integer.parseInt(request.getParameter("bookId")));
            book = BooksDB.selectBook(book.getBookId());
            HttpSession session = request.getSession(true);	    
            session.setAttribute("IsAdd",false); 
            request.setAttribute("book", book);
            url="/addbooks.jsp";
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);  
       }
         else if(action.equals("Delete"))
       {
            Book book = new Book();
            int id = Integer.parseInt(request.getParameter("bookId"));
            book = BooksDB.selectBook(id);
            request.setAttribute("book", book);
            url="/confirmDelete.jsp";
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);  

       }
         else if(action.equals("admin")){
            String emailid = request.getParameter("emailid");
            UserDB.provideAdminPrivilege(emailid);
            List<User> users = new ArrayList<User>();
            url="/displayusers.jsp";
            users = UserDB.selectUsers();
            request.setAttribute("users", users);
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response); 
         }
        else if(action.equals("Add to Cart")){
            url = "/bookcart.jsp";
            int bookid = Integer.parseInt(request.getParameter("bookId"));
            String quantityString = request.getParameter("quantity");

            HttpSession session = request.getSession();
            BookCart cart = (BookCart) session.getAttribute("cart");
            if (cart == null) {
                cart = new BookCart();
            }
             //if the user enters a negative or invalid quantity,
            //the quantity is automatically reset to 1.
            int quantity;
            try {
                quantity = Integer.parseInt(quantityString);
                if (quantity < 0) {
                    quantity = 1;
                }
            } catch (NumberFormatException nfe) {
                quantity = 1;
            }

            Book book = BooksDB.selectBook(bookid);
            CartItem cartItem = new CartItem();
            cartItem.setBook(book);
            cartItem.setQuantity(quantity);
            if (quantity > 0) {
                cart.addItem(cartItem);
            } else if (quantity == 0) {
                cart.removeItem(cartItem);
            }
            session.setAttribute("cart", cart);
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response); 
       }
        }
        catch (Exception ex) 
        {
        System.out.println("Log In failed: An Exception has occurred! " + ex);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
       String url = "/displaybooks.jsp";
       String action = request.getParameter("action");
       if(action.equals("Add"))
        {
            Book book = new Book();
            book.setBookName(request.getParameter("bookName"));
            book.setBookAuthor(request.getParameter("authorName"));
            book.setBookGenre(request.getParameter("genre"));
            BooksDB.insertBook(book);
            request.setAttribute("books", BooksDB.selectBooks());
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);  
        }
       else if(action.equals("Update"))
        {
            Book book = new Book();
            book.setBookId(Integer.parseInt(request.getParameter("bookId")));
            book.setBookName(request.getParameter("bookName"));
            book.setBookAuthor(request.getParameter("authorName"));
            book.setBookGenre(request.getParameter("genre"));
            BooksDB.updateBook(book);
            request.setAttribute("books", BooksDB.selectBooks());
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);  
        }
       else if(action.equals("Yes")){
            int bookid = Integer.parseInt(request.getParameter("bookId"));
            BooksDB.deletebook(bookid);
            request.setAttribute("books", BooksDB.selectBooks());
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);  
       }
       else if(action.equals("No"))
       {
           int bookid = Integer.parseInt(request.getParameter("bookId"));
            request.setAttribute("books", BooksDB.selectBooks());
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);  
       }
       else if(action.equals("Go Back")||action.equals("Add More Books"))
        {
            request.setAttribute("books", BooksDB.selectBooks());
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);  
        }
       else if(action.equals("Submit Review"))
        {
            int bookid = Integer.parseInt(request.getParameter("bookId"));
            String bookname = request.getParameter("bookName");
            String emailId = request.getParameter("emailId");
            String review = request.getParameter("review");
            BooksDB.addReview(bookid,bookname,emailId,review);
            request.setAttribute("message", "Review added successfully");
            request.setAttribute("books", BooksDB.selectBooks());
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);  
        }
       else if(action.equals("Add to Cart")){
            url = "/bookcart.jsp";
            int bookid = Integer.parseInt(request.getParameter("bookId"));
            String quantityString = request.getParameter("quantity");

            HttpSession session = request.getSession();
            BookCart cart = (BookCart) session.getAttribute("cart");
            if (cart == null) {
                cart = new BookCart();
            }
             //if the user enters a negative or invalid quantity,
            //the quantity is automatically reset to 1.
            int quantity;
            try {
                quantity = Integer.parseInt(quantityString);
                if (quantity < 0) {
                    quantity = 1;
                }
            } catch (NumberFormatException nfe) {
                quantity = 1;
            }

            Book book = BooksDB.selectBook(bookid);
            CartItem cartItem = new CartItem();
            cartItem.setBook(book);
            cartItem.setQuantity(quantity);
            if (quantity > 0) {
                cart.addItem(cartItem);
            } else if (quantity == 0) {
                cart.removeItem(cartItem);
            }
            session.setAttribute("cart", cart);
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response); 
       }
        }
        catch (Exception ex) 
        {
        System.out.println("Log In failed: An Exception has occurred! " + ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
