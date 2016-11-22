<%-- 
    Document   : bookcart
    Created on : Nov 21, 2016, 1:17:32 AM
    Author     : yash_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/common/header.jsp" />

<section>
    <h1>Your cart</h1>
<table>
  <tr>
    <th>Quantity</th>
    <th>Book Name</th>
    <th>Price</th>
    <th>Amount</th>
    <th></th>
  </tr>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="books" items="${cart.books}">
  <tr>
    <td>
      <form action="" method="post">
        <input type="hidden" name="bookid" 
               value="<c:out value='${books.book.bookId}'/>">
        <input type=text name="quantity" 
               value="<c:out value='${books.quantity}'/>" id="quantity">
        <input type="submit" value="Update">
      </form>
    </td>
    <td><c:out value='${books.book.bookName}'/></td>
    <td>${books.book.priceCurrencyFormat}</td>
    <td>${books.totalCurrencyFormat}</td>
    <td>
      <form action="" method="post">
        <input type="hidden" name="productCode" 
               value="<c:out value='${books.book.bookId}'/>">
        <input type="hidden" name="quantity" value="0">
        <input type="submit" value="Remove Item">
      </form>
    </td>
  </tr>
</c:forEach>
</table>

<p><b>To change the quantity</b>, enter the new quantity 
      and click on the Update button.</p>
  
<form action="" method="post">
  <input type="hidden" name="action" value="books">
  <input type="submit" value="Add More Books">
</form>

<form action="" method="post">
  <input type="hidden" name="action" value="checkout">
  <input type="submit" value="Checkout">
</form>
</section>
<jsp:include page="/common/footer.jsp" />