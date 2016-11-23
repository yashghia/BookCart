<%-- 
    Document   : displayusers
    Created on : Nov 19, 2016, 4:02:26 AM
    Author     : yash_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/common/header.jsp" />
 
<div class="panel panel-primary">
    <div class="panel-heading">
        
<table class="table">
    <thead><h2>Assign Administrative Privilege</h2></thead>
       <p><b>${message}</b></p>
  <tr>
    <th>First Name</th>
    <th>Last Name</th>
    <th colspan="3">Email ID</th>
  </tr>

  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:forEach var="person" items="${users}">
  <tr>
    <td>${person.firstName}</td>
    <td>${person.lastName}</td>
    <td>${person.emailId}</td>
    <c:if test="${person.admin == false}">
    <td><a class="btn btn-link" href="books?action=admin&amp;emailid=${person.emailId}">Assign Administrative Access</a></td>
    </c:if>
  </tr>
  </c:forEach>
</table>
  <br>
  <form action="books" method="POST" class="form-horizontal">
  <input type="submit" value="Go Back" name="action" class="btn btn-primary">
  </form>   
    </div>
</div>
  <jsp:include page="/common/footer.jsp" />