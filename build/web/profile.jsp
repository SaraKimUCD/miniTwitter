<%-- 
    Document   : profile
    Created on : Oct 25, 2018, 4:26:20 PM
    Author     : sarakim & phihuynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/header.jsp" /> 

<!DOCTYPE html>
<html>
<body>
    <c:if test="${user == null}">
        <c:redirect url="/login.jsp"></c:redirect>
    </c:if>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
        <title>Update Profile</title>
        <script src="includes/main.js"></script>
    </head>
    <main>           
        <profileMiddle>
            <br><h2>Update your profile information below:</h2>
            <form action="membership" method="post" onsubmit="return validateForm();"> 
                <div id="errorMessage">${errorMessage}</div>
                <fieldsetS>          
                    <label>Email (cannot be changed)</label>
                    <input type="email" class="email" id="email"
                           readonly required value="<c:out value='${user.email}'/>" ><br>
                    
                    <label>Username (cannot be changed)</label>
                    <input type="text" class="email" id="email"
                           readonly required value="<c:out value='${user.username}'/>" ><br>

                    <input type="hidden" name="action" value="updateProfile">
                    <label>Full Name:</label>
                    <input type="text" class="fullName" id="fullName" name="fullname" 
                           value="<c:out value='${user.fullname}'/>"
                           pattern="[^'\x22]+"
                           placeholder="Full Name"
                           title="Full Name cannot contain '"
                           required>
                    <br>
                    <div id="fullName_error" class="notVisible"></div>
                    <br>          

                    <label>Date of Birth:</label>
                    <input type="date" class="dateOfBirth" id="dateOfBirth" name="birthdate"
                           value="<c:out value='${user.birthdate}'/>"
                           required>
                    <br>
                    <div id="dateOfBirth_error" class="notVisible">*</div>
                    <br>

                    <label>Security Question:</label>
                    <select id="question" onchange="showAnswer()" name="securityQuestion">
                        <option value="">Select Question</option>
                        <option value="1">Your first pet?</option>
                        <option value="2">Your first car?</option>
                        <option value="3">Your first school?</option>
                    </select><br>
                    <br>
                    <label>Answer:</label>
                    <input type="text" class="answer" id="answer" name="answer"
                           pattern="[^'\x22]+"
                           title="Answer cannot contain '"
                           style="display: none;">
                    <div id="answer_error" class="notVisible"></div>
                    <br><input type="submit" class="submit" value="Update Profile">
                </fieldsetS>
            </form>  
                           
            <br><h2>Update password</h2>
            <form action="membership" method="post" onsubmit="return validateForm();"> 
                <div id="errorMessage">${errorMessage}</div>
                <fieldsetS>    
                    <input type="hidden" name="action" value="changePassword">
                    <label>Old Password:</label>
                    <input type="password" class="passwordS" id="password" name="old_password"
                           placeholder="********"
                           pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{0,}[^'\x22]+" 
                           placeholder ="Password"
                           title="Must contain Number/Lowercase/Uppercase & cannot contain '"
                           required>
                    <div id="password_error" class="notVisible"></div>
                    <br>
                    
                    <label>New Password:</label>
                    <input type="password" class="passwordS" id="password" name="new_password"
                           placeholder="********"
                           pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{0,}[^'\x22]+" 
                           placeholder ="Password"
                           title="Must contain Number/Lowercase/Uppercase & cannot contain '"
                           required>
                    <div id="password_error" class="notVisible"></div>
                    <br>
                    
                    <label>Confirm Password:</label>
                    <input type="password" class="passwordS" id="password" name="confirm_password"
                           placeholder="********"
                           pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{0,}[^'\x22]+" 
                           placeholder ="Password"
                           title="Must contain Number/Lowercase/Uppercase & cannot contain '"
                           required>
                    <div id="password_error" class="notVisible"></div>
                    <br>
                    <br><input type="submit" class="submit" value="Change Password">
                </fieldsetS>
            </form>        
        </profileMiddle>
    </main>	
    </body>
</body>
</html>
<c:import url="/footer.jsp" />
