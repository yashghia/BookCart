<%-- 
    Document   : bookcart
    Created on : Nov 21, 2016, 1:17:32 AM
    Author     : yash_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/common/header.jsp" />
    
    <div class="panel panel-primary">
    <div class="panel-heading">
    <h2>Your Cart Items</h2>
    </div>
    <table class="table">
  <tr>
    <th>Quantity</th>
    <th>Book Name</th>
    <th>Price</th>
    <th>Amount</th>
    <th></th>
  </tr>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="books" items="${cart.items}">
  <tr>
    <td>
      <form action="" method="post" class="form-horizontal">
        <input type="hidden" name="bookid" 
               value="<c:out value='${books.book.bookId}'/>">
        <input type=text name="quantity" 
               value="<c:out value='${books.quantity}'/>" id="quantity">
        <input type="submit" value="Update" class="btn btn-primary">
      </form>
    </td>
    <td><c:out value='${books.book.bookName}'/></td>
    <td>${books.book.priceCurrencyFormat}</td>
    <td>${books.totalCurrencyFormat}</td>
    <td>
      <form action="books" method="post" class="form-horizontal">
        <input type="hidden" name="bookCode" 
               value="<c:out value='${books.book.bookId}'/>">
        <input type="hidden" name="quantity" value="0">
        <input type="submit" value="Remove Item" name="action" class="btn btn-primary">
      </form>
    </td>
  </tr>
</c:forEach>
</table>
<p><b>${message}</b></p>
<br><br>
<p><b>To change the quantity</b>, enter the new quantity 
      and click on the Update button.</p>
<br>
<form action="books" method="post" class="form-horizontal">
  <input type="hidden" name="action" value="Add More Books">
  <input type="submit" value="Add More Books" class="btn btn-primary">
</form>
<br><br>
<form action="books" method="post" class="form-horizontal">
  <input type="hidden" name="action" value="checkout">
  <input type="submit" value="Checkout" name="action"  class="btn btn-primary">
</form>
    </div>
<jsp:include page="/common/footer.jsp" />