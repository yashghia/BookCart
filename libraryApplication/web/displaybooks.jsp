<%-- 
    Document   : librarybooks
    Created on : Nov 19, 2016, 3:18:55 AM
    Author     : yash_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/common/header.jsp" />

<section>
    <p>${message}</p>
<table>

  <tr>
      <th>Book ID</th>
      <th>Book Name</th>
      <th>Book Author</th>
      <th colspan="3">Book Genre</th>
  </tr>

  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:forEach var="book" items="${books}">
  <tr>
    <td>${book.bookId}</td>
    <td>${book.bookName}</td>
    <td>${book.bookAuthor}</td>
    <td>${book.bookGenre}</td>
    <c:if test="${user.admin == true}">
    <td><a href="books?action=Update&amp;bookId=${book.bookId}">Update</a></td>
    <td><a href="books?action=Delete&amp;bookId=${book.bookId}">Delete</a></td>
    </c:if>
    <c:if test="${user.admin == false}">
    <td><a href="books?action=Add to Cart&amp;bookId=${book.bookId}">Add to Cart</a></td>
    <td><a href="books?action=Add Review&amp;bookId=${book.bookId}">Add Review</a></td>
    </c:if>
  </tr>
  </c:forEach>
</table>
  <c:if test="${user.admin == true}">
    <br>
    <form action="books" method="get">
      <input type="submit" value="Add Book" name="action"><br>
    </form>
    <br>
    <form action="books" method="get">
        <input type="submit" value="Assign Admin Role" name="action"><br>
    </form>
  </c:if>
</section>   
<jsp:include page="/common/footer.jsp" />
