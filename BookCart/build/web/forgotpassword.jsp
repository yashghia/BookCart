<%-- 
    Document   : forgotpassword
    Created on : Nov 22, 2016, 12:28:36 AM
    Author     : yash_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/common/header.jsp" />
     <p><b>${message}</b></p>
     <br>
    <div class ="center-block" style="text-align: center" >
        <label><h2>Enter your Email Id and New Password</h2></label>
    </div>
<form method="post" action="login" class="form-horizontals">
     <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <input type="email" class="form-control" name="un" required placeholder="Email ID"/>	
            </div>
            </div>
            <br><br>
            <br>
            <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <input type="password" class="form-control" name="pw" required placeholder="New Password"/>	
            </div>
            </div>
            <br><br><br>
            <div class="col-sm-offset-5 col-sm-10">
            <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <input type="submit" class="btn btn-primary" value="Submit" name="action">
            </div>
        </form>
<jsp:include page="/common/footer.jsp" />