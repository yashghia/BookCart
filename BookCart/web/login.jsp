<%-- 
    Document   : Login
    Created on : Nov 16, 2016, 4:51:55 PM
    Author     : yash_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/common/header.jsp" />
<div class ="my-container">
    <img src="/BookCart/images/books.jpg">

<br>
    <div class ="center-block" style="text-align: center" >
        <label><h2>Login Here</h2></label>
    </div>

    <br><br>
        <form class="form-horizontals" action="login" method="post">
            <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="un" required placeholder="Email ID"/>	
            </div>
            </div>
            <br><br><br>
            <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <input type="password" class="form-control" name="pw" required placeholder="Password"/>
            </div>
            </div>
            <br><br>
            <div class="col-sm-offset-5 col-sm-10">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" class="btn btn-primary" value="Login" name="action">
            <br>
            <br>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <p>New User?  <a class="btn btn-link" href="register.jsp">Register Here</a></p>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <p>Forgot Password? <a class="btn btn-link" href="forgotpassword.jsp">Click Here</a></p>  
            <br>
            <p><b>${message}</b></p>
            <br><br><br><br><br><br><br><br><br><br>
            </div>
        </form>
    
    </div>
<jsp:include page="/common/footer.jsp" />
