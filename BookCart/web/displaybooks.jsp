<%-- 
    Document   : librarybooks
    Created on : Nov 19, 2016, 3:18:55 AM
    Author     : yash_
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/common/header.jsp" />
<div class="panel panel-primary">
    <div class="panel-heading">
        <h2>Books Available</h2>
    </div>
    <table class="table">
        <thead>
            <tr>
                <th>Book ID</th>
                <th>Book Name</th>
                <th>Book Author</th>
                <th>Book Genre</th>
                <th colspan="3">Book Price</th>
            </tr>
        </thead>
        <tbody>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td><c:out value="${book.bookId}"/></td>
                    <td><c:out value="${book.bookName}"/></td>
                    <td><c:out value="${book.bookAuthor}"/></td>
                    <td><c:out value="${book.bookGenre}"/></td>
                    <td><c:out value="${book.priceCurrencyFormat}"/></td>
                    <c:if test="${user.admin == true}">
                        <td><a class="btn btn-link" href="books?action=Update&amp;bookId=${book.bookId}">Update</a></td>
                        <td><a class="btn btn-link" href="books?action=Delete&amp;bookId=${book.bookId}">Delete</a></td>
                    </c:if>
                    <c:if test="${user.admin == false}">
                        <td><a class="btn btn-link" href="books?action=Add to Cart&amp;bookId=${book.bookId}">Add to Cart</a></td>
                        <td><a class="btn btn-link" href="books?action=Add Review&amp;bookId=${book.bookId}">Add Review</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:if test="${user.admin == false}">        
        <div class="panel-heading">
            <h2>Book Reviews added by you</h2>
        </div>
        <table class="table">
            <th>Book ID</th>
            <th>Book Name</th>
            <th>Book Review</th>
                <c:forEach var="review" items="${reviews}">
                <tr>
                    <td><c:out value="${review.bookId}"/></td>
                    <td><c:out value="${review.bookName}"/></td>
                    <td><c:out value="${review.review}"/></td>
                </tr>
            </c:forEach>
        </table>
        <br><br>
    </c:if>
    <p><b>${message}</b></p>
    <c:if test="${user.admin == true}">
        <br>
        <form action="books" method="get" class="form-horizontal">
            <input type="submit" value="Add Book" name="action" class="btn btn-primary"><br>
        </form>
        <br>
        <form action="books" method="get" class="form-horizontal">
            <input type="submit" value="Assign Admin Role" name="action" class="btn btn-primary"><br>
        </form>
    </c:if>
</div>
<jsp:include page="/common/footer.jsp" />
