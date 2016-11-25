<%-- 
    Document   : Header
    Created on : Nov 16, 2016, 2:31:43 PM
    Author     : yash_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <c:if test="${user.firstName}">
                <ul class="right">
                    <c:out value="Welcome, ${user.firstName}"
                </ul>
                <ul class="right">
                    <li><a class="btn btn-default" href="/BookCart/login?action=logout">Logout</a></li>
                </ul>
            </c:if>
        </nav>
    </div>
