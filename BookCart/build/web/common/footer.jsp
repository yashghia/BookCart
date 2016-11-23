<%-- 
    Document   : Footer
    Created on : Nov 16, 2016, 2:34:13 PM
    Author     : yash_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.GregorianCalendar, java.util.Calendar" %>
<!DOCTYPE html>

<section class="footer">
<%  
    GregorianCalendar currentDate = new GregorianCalendar();
    int currentYear = currentDate.get(Calendar.YEAR);
%>
<p>&copy; Copyright <%= currentYear %> UNCC ITIS department</p>
</section>
</body>
</html>
