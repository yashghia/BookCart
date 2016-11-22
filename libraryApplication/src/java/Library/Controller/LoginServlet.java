/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library.Controller;

import Library.Models.*;
import java.io.IOException;
import java.io.PrintWriter;
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
	       
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url ="";
        try{
       String action = request.getParameter("action");
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
       {
        User user = new User();
        user.setEmailId(request.getParameter("un"));
        user.setPassword(request.getParameter("pw"));
        user = UserDB.selectUser(user);
        List<Book> books = new ArrayList<Book>();
        if (user.getIsAvailable())
        {
            if(loginValidation(user.getEmailId(),user.getPassword()))
            {
                HttpSession session = request.getSession(true);	    
                session.setAttribute("user",user); 
                books = BooksDB.selectBooks();
                request.setAttribute("books", books);
                url="/displaybooks.jsp";
                //response.encodeRedirectURL(url);
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
