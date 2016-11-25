<%-- 
    Document   : addreview
    Created on : Nov 20, 2016, 7:31:24 PM
    Author     : yash_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/common/header.jsp" />
        <p><b>${message}</b></p>
<br>
<div class ="center-block" style="text-align: center" >
    <label><h2>Add Review for the Book</h2></label>
</div>
<form action="books" method="post" class="form-horizontals">
    <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="bookId" value="${book.bookId}" readonly placeholder="Book ID"/>	
            </div>
            </div>
            <br><br><br>
    <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="bookName" value="${book.bookName}" readonly placeholder="Book Name"/>	
            </div>
            </div>
            <br><br>
    <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="review" placeholder="Review"/>	
            </div>
            </div>
            <br><br>
<input type="hidden" value="${user.emailId}" name ="emailId">
<div class="col-sm-offset-5 col-sm-10">
<label>&nbsp;</label>
            <input type="submit" value="Submit Review" name="action" class="btn btn-primary">
            <br>
</div>
    </form>
<jsp:include page="/common/footer.jsp" />
