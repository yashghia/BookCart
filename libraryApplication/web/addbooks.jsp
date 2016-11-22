<%-- 
    Document   : addbooks
    Created on : Nov 19, 2016, 4:02:40 AM
    Author     : yash_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/common/header.jsp" />
  <h1>Add or update book details</h1>
    <form action="books" method="post">
        <!--<input type="hidden" name="action" value="update_user">    --> 
        <c:if test="${IsAdd == false}">
            <label class="pad_top">Book ID:</label>
            <input type="text" name="bookId" value="${book.bookId}" 
               readonly><br>
        </c:if>
        <label class="pad_top">Book Name:</label>
        <input type="text" name="bookName" value="${book.bookName}" 
               required><br>
        <label class="pad_top">Author Name:</label>
        <input type="text" name="authorName" value="${book.bookAuthor}"  
               required><br>  
         <label class="pad_top">Genre:</label>
        <input type="text" name="genre" value="${book.bookGenre}">
        <br>
        <label>&nbsp;</label>
        <c:if test="${IsAdd == false}">
            <input type="submit" value="Update" name="action" class="margin_left">
        </c:if>
        <c:if test="${IsAdd == true}">
        <input type="submit" value="Add" name="action"  class="margin_left">
        </c:if>
    </form>
</body>
<jsp:include page="/common/footer.jsp" />