<%-- 
    Document   : Header
    Created on : Nov 16, 2016, 2:31:43 PM
    Author     : yash_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Book Cart Application</title>
        <link rel="stylesheet" href="styles/main.css"/>
        <link rel="stylesheet" href="styles/bootstrap.min.css"/>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function () {
            $("#datepicker").datepicker();
        });
    </script>

</head>
<body>
    <div id="header">
        <nav id ="header_menu">
            <ul class="right">
                <li><c:choose> 
                        <c:when test="${user.firstName == null}">
                            <a>.</a>
                        </c:when> 

                    </c:choose>
                </li>
                <li><c:choose> 
                        <c:when test="${not empty user}">
                        <li><a class="btn btn-default">Welcome ${user.firstName}</a></li>
                        <li><a class="btn btn-default" href="/BookCart/login?action=logout">Logout</a></li>
                        </c:when>
                    </c:choose>
                </li>
            </ul>
        </nav>
    </div>
