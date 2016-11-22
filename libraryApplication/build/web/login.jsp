<%-- 
    Document   : Login
    Created on : Nov 16, 2016, 4:51:55 PM
    Author     : yash_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/common/header.jsp" />

<section>
    <h1>Login Form</h1>
    <p><i>${message}</i></p>
        <form action="login" method="post">
            <label>Username:</label>
            <input type="text" name="un" required/><br>	
            <br>
            <label>Password:</label>
            <input type="password" name="pw" required/>
            <br><br>
            <label>&nbsp;</label>
            <input type="submit" value="Login" name="action">
            <br>
            <p>New User?  <a href="register.jsp">Register Here</a></p>
            <p>Forgot Password? <a href="forgotpassword.jsp">Click Here</a></p>    
        </form>
</section>
            
<jsp:include page="/common/footer.jsp" />
