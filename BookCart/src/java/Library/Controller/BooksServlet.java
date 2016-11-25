/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library.Controller;

import Library.Data.*;
import Library.Models.*;
import Library.Utility.Utility;
import java.io.IOException;
import java.util.*;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yash_
 */
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
            int admin = Integer.parseInt(request.getParameter("admin"));
            UserDB.provideAdminPrivilege(emailid,admin);
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
       HttpSession session = request.getSession(true);
       User user = (User)session.getAttribute("user");
       List<BookReview> bookreviews = new ArrayList<BookReview>();
       if(action.equals("Add"))
        {
            Book book = new Book();
            book.setBookName(request.getParameter("bookName"));
            book.setBookAuthor(request.getParameter("authorName"));
            book.setBookGenre(request.getParameter("genre"));
            book.setPrice(Integer.parseInt(request.getParameter("price")));
            BooksDB.insertBook(book);
            request.setAttribute("books", BooksDB.selectBooks());
            bookreviews = BooksDB.selectBookReview(user.getEmailId());
            request.setAttribute("reviews", bookreviews);
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
            book.setPrice(Integer.parseInt(request.getParameter("price")));
            BooksDB.updateBook(book);
            request.setAttribute("books", BooksDB.selectBooks());
            bookreviews = BooksDB.selectBookReview(user.getEmailId());
            request.setAttribute("reviews", bookreviews);
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);  
        }
       else if(action.equals("Yes")){
           url = "/displaybooks.jsp";
            int bookid = Integer.parseInt(request.getParameter("bookId"));
            BooksDB.deletebook(bookid);
            request.setAttribute("books", BooksDB.selectBooks());
            bookreviews = BooksDB.selectBookReview(user.getEmailId());
            request.setAttribute("reviews", bookreviews);
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);  
       }
       else if(action.equals("No"))
       {
           url = "/displaybooks.jsp";
            request.setAttribute("books", BooksDB.selectBooks());
            bookreviews = BooksDB.selectBookReview(user.getEmailId());
            request.setAttribute("reviews", bookreviews);
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);  
       }
       else if(action.equals("Go Back")||action.equals("Add More Books"))
        {
            request.setAttribute("books", BooksDB.selectBooks());
            bookreviews = BooksDB.selectBookReview(user.getEmailId());
            request.setAttribute("reviews", bookreviews);
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
            bookreviews = BooksDB.selectBookReview(user.getEmailId());
            request.setAttribute("reviews", bookreviews);
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);  
        }
       else if(action.equals("Add to Cart")){
            url = "/bookcart.jsp";
            int bookid = Integer.parseInt(request.getParameter("bookId"));
            //int deletebookid = Integer.parseInt(request.getParameter("deletebook"));
            String quantityString = request.getParameter("quantity");
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
          else if(action.equals("Remove Item")){
            url = "/bookcart.jsp";
            int bookid = Integer.parseInt(request.getParameter("bookCode"));
            //int deletebookid = Integer.parseInt(request.getParameter("deletebook"));
            String quantityString = request.getParameter("quantity");

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
       else if(action.equals("checkout")){
           request.setAttribute("message","You have successfully checked out books and confirmation email is sent!!!");
           sendOrderConfirmationMail(user.getEmailId(),user.getFirstName(),request);
           session.setAttribute("cart", new BookCart());
           url = "/bookcart.jsp";
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

    
    public void sendOrderConfirmationMail(String email,String firstName,HttpServletRequest request){
            String to = email;
            String from = "NbadProject@UNCC.com";
            String subject = "Book Order Confirmation";
            String body = "Dear " + firstName + ",\n\n"
                    + "Thanks for ordering books on BookCart website. We'll make sure to deliver "
                    + "you the books soon.\n"
                    + "Have a great day and thanks again!\n\n";
            boolean isBodyHTML = false;

            try {
                //MailUtilLocal.sendMail(to, from, subject, body, isBodyHTML);
                Utility.sendMail(to, from, subject, body, isBodyHTML);
            } catch (MessagingException e) {
                String errorMessage
                        = "ERROR: Unable to send email. "
                        + "Check Tomcat logs for details.<br>"
                        + "NOTE: You may need to configure your system "
                        + "as described in chapter 14.<br>"
                        + "ERROR MESSAGE: " + e.getMessage();
                request.setAttribute("errorMessage", errorMessage);
                this.log(
                        "Unable to send email. \n"
                        + "Here is the email you tried to send: \n"
                        + "=====================================\n"
                        + "TO: " + email + "\n"
                        + "FROM: " + from + "\n"
                        + "SUBJECT: " + subject + "\n"
                        + "\n"
                        + body + "\n\n");
            }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
