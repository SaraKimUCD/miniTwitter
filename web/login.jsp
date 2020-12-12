<%-- 
    Document   : login.jsp
    Created on : Oct 5, 2018 4:44:58 PM
    Author     : Phi Huynh & Sara Kim
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/header.jsp" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
        <link rel="stylesheet" 
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="includes/main.js"></script>        
    </head>
    
    <body>    
        <c:if test="${user != null}">
            <c:redirect url="/home.jsp"></c:redirect>
        </c:if>
        <form action="membership" method="post">
        <fieldsetL>
            <input type="hidden" name="action" value="login"> 
            <div id="errorMessage">${errorMessage}</div>
            <br><i class="fa fa-user"></i><br>
            <input type="text" name="loginID" class="usernameL" placeholder="Email or Username" required/><br>
            <input type="password" name="password" class="passwordL" placeholder="Password" required/><br>
            <div class="remMe">
                <br><input type="checkbox" name="remember" class="rememberMe">Remember Me<br>
                <br><input type="submit" name="login" value="Login" /><br>
            </div>
                <br><a href="<c:url value='/forgotpassword.jsp' />"class="loginForgot">Forgot Password?</a>
                <a href="<c:url value='/signup.jsp' />" class="newSignup">Sign Up</a>
        </fieldsetL>    
        </form>
    </body>
</html>

<c:import url="/footer.jsp" />