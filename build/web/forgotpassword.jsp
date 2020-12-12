<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/header.jsp" />

<c:if test="${user != null}">
    <c:redirect url="/home.jsp"></c:redirect>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
        <title>Forgot Password</title>
    </head>
    <fpBody>
    <h2>Please enter the following information to recover your lost password:</h2>
        <div class="formBox">
            <form action="membership" method="post">
            <div id="errorMessage">${errorMessage}</div>
                <input type="hidden" name="action" value="forgotPassword">
                <input type="email" id="email" name="email" placeholder="Email" required><br>
                    <select id="securityQuestion" name="securityQuestion" onchange="showAnswer2();">
                        <option value="default">Select Security Question</option>
                        <option value="1">Your first pet?</option>
                        <option value="2">Your first car?</option>
                        <option value="3">Your first school?</option>
                    </select>
                    <input type="text" id="answer" name="answer" placeholder="Answer" required><br>
                    <input type="submit" value="Submit" class="fpSubmit"><br>
            </form>
                <br><a href="/MyTwitter"><button type="button" class="cancelbtn">Return</button></a>
        </div>
    </fpBody>
</html>

<c:import url="/footer.jsp" />