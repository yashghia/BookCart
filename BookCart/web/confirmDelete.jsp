<%-- 
    Document   : confirmDelete
    Created on : Nov 19, 2016, 7:14:40 PM
    Author     : yash_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/common/header.jsp" />
      <p><b>${message}</b></p>
<br>
<div class ="center-block" style="text-align: center" >
    <label><h2>Are you sure you want to delete this Book?</h2></label>
</div>   
<br>
<br>
 <div class="col-sm-offset-4 col-sm-10">
<div class="form-group">
            <label class="col-sm-4 control-label">Book ID:</label><c:out value="${book.bookId}"/>
            <br><br>
</div>
            <div class="form-group">
        <label class="col-sm-4 control-label">Book Name:</label><c:out value="${book.bookName}"/>
        <br><br>
            </div>
        <label class="col-sm-4 control-label">Book Author:</label><c:out value="${book.bookAuthor}"/>
        <br><br>
        <div class="form-group">
        <label class="col-sm-4 control-label">Genre:</label><c:out value="${book.bookGenre}"/>
        <br><br>
        </div>
       <!-- Hint! You need to code a form for the 'Yes' button -->
       <form action="books" method="POST" class="form-horizontals">
       <input type="submit" name="action" value="Yes" class="btn btn-primary">
       <input type="hidden" name ="bookId" value="${book.bookId}">
       <input type="submit" name="action" value="No" class="btn btn-primary">
       </form>
 </div>

<jsp:include page="/common/footer.jsp" />