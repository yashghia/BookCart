/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library.Controller;

import Library.Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Library.Data.*;
import Library.Utility.*;
import javax.mail.MessagingException;

/**
 *
 * @author yash_
 */
//@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url ="";
        
        try
        {
        User user = new User();
        user.setEmailId(request.getParameter("un"));
        user.setFirstName(request.getParameter("firstname"));
        user.setMiddleName(request.getParameter("middlename"));
        user.setLastName(request.getParameter("lastname"));
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String var = request.getParameter("DOB");
        Date date = sdf.parse(var);
        user.setDOB(date);
        //user.setPassword(request.getParameter("password"));
        String pass = request.getParameter("pw");
        Utility.checkPasswordStrength(pass);
        User existinguser = new User();
        existinguser = UserDB.selectUser(user);
        if(existinguser.getIsAvailable()){
            request.setAttribute("message", "Email Id already exists, try login using the email id or click forgot password");
            url = "/login.jsp";
            getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
        }
        else
        {
            user.setSalt(Utility.getSalt());
            String hashpass= Utility.hashAndSaltPassword(pass, user.getSalt());
            user.setPassword(hashpass);
            UserDB.insert(user);
            request.setAttribute("message", "Registration successfull!! Please login with your credentials");
            sendMail(user.getEmailId(),user.getFirstName(),request);
            url = "/login.jsp";
            getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
        }
        }
        catch(ParseException p)
        {
            
        }
        catch(Exception e)
        {
            request.setAttribute("message", e.getMessage());
            url = "/register.jsp";
            getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
        }
        }
    
    //Method to send mail 
    public void sendMail(String email,String firstName,HttpServletRequest request){
            String to = email;
            String from = "email_list@murach.com";
            String subject = "Welcome to our email list";
            String body = "Dear " + firstName + ",\n\n"
                    + "Thanks for joining Library management website. We'll make sure to send "
                    + "you announcements about new products and promotions.\n"
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
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>       
}