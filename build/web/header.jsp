<%-- 
    Document   : header.jsp
    Created on : Oct 5, 2018 4:37:19 PM
    Author     : Phi Huynh & Sara Kim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
        <script src="includes/main.js" type="text/javascript"></script>
        <title>Mini Twitter</title>
    </head>
        
    <body>
        <div class="header">
            <h1>Mini-Twitter</h1>
        </div>
        <nav>
            <nav-left><a href="<c:url value='/tweet' />">Home</a></nav-left>
            <nav-left id="login" class="isVisible"><a href="<c:url value='/login.jsp' />">Login</a></nav-left>
            <nav-left id="signup" class="isVisible"><a href="<c:url value='/signup.jsp' />">Sign Up</a></nav-left>
            <nav-left id="notification" class="notVisible"><a href="<c:url value='/notification.jsp' />">Notification</a></nav-left>
            <nav-left id="profile" class="notVisible"><a href="<c:url value='/profile.jsp' />">Profile</a></nav-left>
            <nav-right id="signout" class="notVisible"><a href="/MyTwitter/membership?action=logout">Sign Out</a></nav-right>
        </nav>
    </body>
    <c:if test="${user != null}">
        <script type="text/javascript">
            showSignIn();
        </script>
    </c:if>
    
</html>
