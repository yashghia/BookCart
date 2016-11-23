<%-- 
    Document   : forgotpassword
    Created on : Nov 22, 2016, 12:28:36 AM
    Author     : yash_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/common/header.jsp" />
<section>
     <h1>Enter your Email Id and new password</h1>
         <p><b>${message}</b></p>
<form method="post" action="login">
            <label>Email ID:</label>
            <input type="email" name="un" required/><br>	
            <br>
            <label>New Password:</label>
            <input type="password" name="pw" required/><br>
            <br><br>
            <label>&nbsp;</label>
            <input type="submit" value="Submit" name="action">
        </form>
    </section>
<jsp:include page="/common/footer.jsp" />