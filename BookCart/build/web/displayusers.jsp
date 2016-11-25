<%-- 
    Document   : displayusers
    Created on : Nov 19, 2016, 4:02:26 AM
    Author     : yash_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/common/header.jsp" />
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="panel panel-primary">
    <div class="panel-heading">
        <h2>Assign Administrative Privilege</h2>
    </div>

       <table class="table">
  <tr>
    <th>First Name</th>
    <th>Last Name</th>
    <th colspan="3">Email ID</th>
  </tr>

  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:forEach var="person" items="${users}">
  <tr>
    <td><c:out value="${person.firstName}"/></td>
    <td><c:out value="${person.lastName}"/></td>
    <td<c:out value="${person.emailId}"/></td>
    <c:if test="${person.admin == false}">
    <td><a class="btn btn-link" href="books?action=admin&amp;emailid=${person.emailId}&amp;admin=1">Assign Administrative Access</a></td>
    </c:if>
    <c:if test="${person.admin == true}">
        <td><a class="btn btn-link" href="books?action=admin&amp;emailid=${person.emailId}&amp;admin=0">Remove Administrative Access</a></td>
    </c:if>
  </tr>
  </c:forEach>
</table>
  <br><br>
         <p><b>${message}</b></p>
  <form action="books" method="POST" class="form-horizontal">
  <input type="submit" value="Go Back" name="action" class="btn btn-primary">
  </form>   
    </div>
</div>
  <jsp:include page="/common/footer.jsp" />