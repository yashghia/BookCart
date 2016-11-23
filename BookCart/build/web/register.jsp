<%-- 
    Document   : register
    Created on : Nov 18, 2016, 6:05:43 PM
    Author     : yash_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/common/header.jsp" />
<section>
    <p><b>${message}</b></p>
    <div class ="center-block" style="text-align: center" >
        <label><h2>Registration Form</h2></label>
    </div>
    <br><br>
<form method="post" action="register" class="form-horizontals">
            <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <input type="email" class="form-control" name="un" required placeholder="Email ID"/>	
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
            <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="firstname" required placeholder="First Name"/>	
            </div>
            </div>
            <br><br>
            <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="middlename" placeholder="Middle Name"/>	
            </div>
            </div>
            <br><br>
            <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="lastname" required placeholder="Last Name"/>	
            </div>
            </div>
            <br><br>
            <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <input type="text" class="form-control input-append date" id="datepicker" readonly name="DOB" required placeholder="Date Of Birth"/>	
            </div>
            </div>
            <br><br>
            <div class="col-sm-offset-5 col-sm-10">
            <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <input type="submit" class="btn btn-primary" value="register">
            </div>
        </form>
    </section>
<jsp:include page="/common/footer.jsp" />