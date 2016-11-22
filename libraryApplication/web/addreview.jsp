<%-- 
    Document   : addreview
    Created on : Nov 20, 2016, 7:31:24 PM
    Author     : yash_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/common/header.jsp" />
<section>
    <h1>Add review for the book</h1>
        <p><b>${message}</b></p>
<form action="books" method="post">
<label class="pad_top">Book Id:</label>
<input type="text" name="bookId" value="${book.bookId}" 
       readonly><br><br>
<label class="pad_top">Book Name:</label>
<input type="text" name="bookName" value="${book.bookName}" 
       readonly><br><br>
<label class="pad_top">Review:</label>
<input type="text" name="review"/>
<br>
<br>
<input type="hidden" value="${user.emailId}" name ="emailId">
<label>&nbsp;</label>
            <input type="submit" value="Submit Review" name="action" >
            <br>
    </form>
</section>

<jsp:include page="/common/footer.jsp" />
