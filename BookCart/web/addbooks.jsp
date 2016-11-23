<%-- 
    Document   : addbooks
    Created on : Nov 19, 2016, 4:02:40 AM
    Author     : yash_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/common/header.jsp" />
<p><b>${message}</b></p>
<br>
<div class ="center-block" style="text-align: center" >
    <label><h2>Add or Update Book Details</h2></label>
</div>
      
    <form action="books" method="post"  class="form-horizontals">
        <!--<input type="hidden" name="action" value="update_user">    --> 
        <c:if test="${IsAdd == false}">
            <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="bookId" value="${book.bookId}" readonly placeholder="Book ID"/>	
            </div>
            </div>
        </c:if>
        <br><br><br>
           <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="bookName" value="${book.bookName}" required placeholder="Book Name"/>	
            </div>
            </div>
           <br><br>
           <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="authorName" value="${book.bookAuthor}" required placeholder="Author Name"/>	
            </div>
            </div>
           <br><br>
           <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="genre" value="${book.bookGenre}" required placeholder="Book Genre"/>	
            </div>
            </div>
           <br><br>
           <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="price" value="${book.price}" required placeholder="Price"/>	
            </div>
            </div>
           <br><br>
           <div class="col-sm-offset-5 col-sm-10">
            <label>&nbsp;</label>
            <c:if test="${IsAdd == false}">
                <input type="submit" value="Update" name="action" class="btn btn-primary">
            </c:if>
            <c:if test="${IsAdd == true}">
            <input type="submit" value="Add" name="action"  class="btn btn-primary">
            </c:if>
           </div>
    </form>
</body>
<jsp:include page="/common/footer.jsp" />