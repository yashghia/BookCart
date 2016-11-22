<%-- 
    Document   : confirmDelete
    Created on : Nov 19, 2016, 7:14:40 PM
    Author     : yash_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/common/header.jsp" />
<h1>Are you sure you want to delete this Book?</h1>
    <p><b>${message}</b></p>
        
        <label>Book ID:</label>${book.bookId}
        <br>
        <label>Book Name:</label>${book.bookName}
        <br>
        <label>Book Author:</label>${book.bookAuthor}
        <br>
        <label>Genre:</label>${book.bookGenre}
        <br>
       <!-- Hint! You need to code a form for the 'Yes' button -->
       <form action="books" method="POST">
       <input type="submit" name="action" value="Yes">
       <input type="hidden" name ="bookId" value="${book.bookId}">
       <input type="submit" name="action" value="No">
       </form>
<jsp:include page="/common/footer.jsp" />