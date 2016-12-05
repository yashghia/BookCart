/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookCart.Controller;

import BookCart.Models.BookCart;
import BookCart.Models.User;
import BookCart.Models.Book;
import BookCart.Models.CartItem;
import BookCart.Models.BookReview;
import BookCart.Data.BooksDB;
import BookCart.Data.UserDB;
import BookCart.Utility.Utility;
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
        String url="/displaybooks.jsp";
        try
        {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        List<BookReview> bookreviews = new ArrayList<BookReview>();
        //If the request is for adding new book.
        if(action.equals("Add Book"))
        {
            session.setAttribute("IsAdd",true); 
            url="/addbooks.jsp";
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);  
        }
        
        //If the request is to add review for a book.
        else if(action.equals("Add Review"))
        {
            Book book = new Book();
            url="/addreview.jsp";
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            bookreviews = BooksDB.selectBookReview(user.getEmailId());
            for(BookReview review :bookreviews)
            {
                if(review.getBookId()==bookId)
                {
                    url="/displaybooks.jsp";
                    request.setAttribute("books", BooksDB.selectBooks());
                    request.setAttribute("reviews", bookreviews);
                    request.setAttribute("message","You have already added review for this book");
                    getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response); 
                }
            }
            book = BooksDB.selectBook(bookId);
            request.setAttribute("book", book);
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response); 
        }
        
        //If an admin tries to add another user as admin.
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
        
        //If an admin tries to modify details of a particular book.
        else if(action.equals("Update"))
       {
            Book book = new Book();
            book.setBookId(Integer.parseInt(request.getParameter("bookId")));
            book = BooksDB.selectBook(book.getBookId());    
            session.setAttribute("IsAdd",false); 
            request.setAttribute("book", book);
            url="/addbooks.jsp";
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);  
       }
        
        //If an admin tries to delete a particular book from the database.
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
         
         //When an admin updates the user's administrative privileges
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
         
         // When a user selects a book and add to their cart for checkout.
        else if(action.equals("Add to Cart")){
            url = "/bookcart.jsp";
            int bookid = Integer.parseInt(request.getParameter("bookId"));
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
        }
        
        //Catch statement will catch any general acception thrown by above code
        catch (Exception ex) 
        {
        url="/displaybooks.jsp";
        request.setAttribute("message","An Exception has occurred! :"+ex.getMessage());
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response); 
        }
    }

   // The below code will capture all the post requests from JSP pages.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/displaybooks.jsp";
        try{
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
            //bookreviews = BooksDB.selectBookReview(user.getEmailId());
            //request.setAttribute("reviews", bookreviews);
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);  
       }
       else if(action.equals("No"))
       {
           url = "/displaybooks.jsp";
            request.setAttribute("books", BooksDB.selectBooks());
            bookreviews = BooksDB.selectBookReview(user.getEmailId());
            //request.setAttribute("reviews", bookreviews);
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
            request.setAttribute("message","An Exception has occurred! :"+ex.getMessage());
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response); 
        }
    }

    // this is a user defined method which is used to send confirmation mail to user for their successfull purchase.
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
                        + e.getMessage();
                request.setAttribute("message", errorMessage);
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
