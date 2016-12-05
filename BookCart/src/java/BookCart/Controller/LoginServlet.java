/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library.Controller;

import Library.Models.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Library.Data.*;
import static Library.Data.UserDB.loginValidation;
import Library.Utility.Utility;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yash_
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url ="/login.jsp";
        try{
       String action = request.getParameter("action");
       if(action.equals("logout"))
       {
           
           HttpSession session = request.getSession();
           session.setAttribute("user", new User());
           getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response); 
       }
        }
       catch(Exception ex)
               {
               request.setAttribute("message", ex.getMessage());
                getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
               }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url ="";
        try{
       String action = request.getParameter("action");
       
       // below code is used when a user tries to reset his password
       if(action.equals("Submit"))
       {
        String email = (request.getParameter("un"));
        String password = (request.getParameter("pw"));
        Utility.checkPasswordStrength(password);
        String salt = Utility.getSalt();
        String hashpass= Utility.hashAndSaltPassword(password, salt);
        UserDB.resetPassword(email,hashpass,salt);
        request.setAttribute("message", "Password is reset successfully!!! Please login using new credentials");
            url="/login.jsp";
            getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
        }
       else
           
       //This code is used when user tries to login to the system, it has few validation checks
       {
        User user = new User();
        user.setEmailId(request.getParameter("un"));
        user.setPassword(request.getParameter("pw"));
        user = UserDB.selectUser(user);
        List<Book> books = new ArrayList<Book>();
        List<BookReview> bookreviews = new ArrayList<BookReview>();
        if (user.getIsAvailable())
        {
            if(loginValidation(user.getEmailId(),user.getPassword()))
            {
                HttpSession session = request.getSession(true);	    
                session.setAttribute("user",user); 
                bookreviews = BooksDB.selectBookReview(user.getEmailId());
                books = BooksDB.selectBooks();
                request.setAttribute("books", books);
                request.setAttribute("reviews", bookreviews);
                url="/displaybooks.jsp";
                getServletContext()
                        .getRequestDispatcher(url)
                        .forward(request, response);        
            }
            else
            {
            request.setAttribute("message", "Password is incorrect");
            url="/login.jsp";
            getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
            }
        }
        else {
        request.setAttribute("message", "Your Email ID does not exist in system, please register");
        url="/login.jsp";
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
        } 
       }
        }
        catch (Exception ex) 
        {
            request.setAttribute("message", ex.getMessage());
            url = "/login.jsp";
            getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
        //System.out.println("Log In failed: An Exception has occurred! " + ex);
        }
        }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
