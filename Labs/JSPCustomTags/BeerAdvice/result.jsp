<%-- 
    Document   : result
    Created on : May 7, 2019, 1:45:48 PM
    Author     : vieta
--%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result</title>
    </head>
    <body>
        <h1>Servlet BeerSelect</h1>
        <ul>
            <c:forEach var="style" items="${styles}">
                <li>${style}</li>
            </c:forEach>
        </ul>
    </body>
</html>
