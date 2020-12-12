<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/header.jsp" />
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.

 Author     : Sara Kim & Phi Huynh
-->
<html>
    <head>
        <title>Mini-Twitter Sign-up</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
        <script src="includes/main.js"></script>
    </head>
    <body>
        <c:if test="${user != null}">
            <c:redirect url="/home.jsp"></c:redirect>
        </c:if>
        
        <div id="errorDiv"></div>
        <br><h2>Please fill out the information below:</h2>
        <form action="membership" method="post" onsubmit="return validateForm();">
                <div id="errorMessage">${errorMessage}</div>
            <fieldsetS>
                <input type="hidden" name="action" value="signup"> 
                <label>Full Name:</label>
                <input type="text" class="fullName" id="fullName" name="fullname" 
                       value="${user_reg.fullname}"
                       pattern="[^'\x22]+"
                       placeholder="Full Name"
                       title="Full Name cannot contain '"
                       required>
                <br>
                <div id="fullName_error" class="notVisible"></div>
                <br>            

                <label>User Name:</label>
                <input type="text" class="userName" id="userName" name="username"
                       value="${user_reg.username}"
                       pattern="[^'\x22]+"
                       placeholder="User Name"
                       title="Username cannot contain '"
                       required>
                <br>
                <div id="userName_error" class="notVisible"></div>
                <br>

                <label>Email:</label>
                <input type="email" class="email" id="email" name="email"
                       value="${user_reg.email}"
                       pattern="[^'\x22]+"
                       placeholder="Email Address"
                       title="Email cannot contain '"
                       required>
                <br>
                <div id="email_error" class="notVisible"></div>
                <br>

                <label>Password:</label>
                <!-- ************** Check 4 **************
                Specify password requirements -->
                <input type="password" class="passwordS" id="password" name="password"
                       pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{0,}[^'\x22]+" 
                       placeholder ="Password"
                       title="Must contain Number/Lowercase/Uppercase & cannot contain '"
                       required>
                <div id="password_error" class="notVisible"></div>
                <br>

                <label>Confirm password:</label>
                <input type="password" class="confirmPassword" id="confirmPassword" name="confirm_password"
                       placeholder ="Re-Enter Password"
                       required>
                <br>
                <div id="confirmPassword_error" class="notVisible"></div>
                <br>

                <label>Date-of-Birth:</label>
                <input type="date" class="dateOfBirth" id="dateOfBirth" name="birthdate"
                       value="${user_reg.birthdate}"
                       required>
                <br>
                <div id="dateOfBirth_error" class="notVisible">*</div>
                <br>

                <label>Security Question:</label>
                <!-- ************** Check 4 **************  -->
                <select id="question" onchange="showAnswer()" name="securityQuestion" value="${user_reg.questionNo}"required>
                    <option value="default">Select Question</option>
                    <option value="1">Your first pet?</option>
                    <option value="2">Your first car?</option>
                    <option value="3">Your first school?</option>
                </select><br>
                <br>
                <input type="text" class="answer" id="answer" name="answer"
                       pattern="[^'\x22]+"
                       title="Answer cannot contain '"
                       placeholder="Input Answer..."
                       style="display: none;" required>
                <div id="answer_error" class="notVisible"></div>
                <br><input type="submit" class="submit" value="Sign Up">
            </fieldsetS>
        </form>
    </body>
</html>

<c:import url="/footer.jsp" />
