<%-- 
    Document   : register
    Created on : Nov 18, 2016, 6:05:43 PM
    Author     : yash_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/common/header.jsp" />
<section>
     <h1>Registration form</h1>
         <p><b>${message}</b></p>
<form method="post" action="register">
            <label>Email ID:</label>
            <input type="email" name="un" required/><br>	
            <br>
            <label>Password:</label>
            <input type="password" name="pw" required/><br>
            <br>
            <label>First Name</label>
            <input type="text" name="firstname" required/><br>	
            <br>
            <label>Middle Name</label>
            <input type="text" name="middlename"/><br>	
            <br>
            <label>Last Name</label>
            <input type="text" name="lastname" required/><br>	
            <br>
            <label>Date Of Birth</label>
            <input type="text" name="DOB" id = "datepicker" readonly/> 
            <br><br>
            <label>&nbsp;</label>
            <input type="submit" value="register">
        </form>
    </section>
<jsp:include page="/common/footer.jsp" />