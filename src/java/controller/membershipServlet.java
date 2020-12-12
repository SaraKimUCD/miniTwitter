/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.Notification;
import business.User;
import dataaccess.BCrypt;
import dataaccess.NotificationDB;
import dataaccess.UserDB;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "membershipServlet", urlPatterns = {"/membership"})
public class membershipServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "checkuser";  // default action
        }

        // perform action and set URL to appropriate page
        String url = "/login.jsp";
        if (action.equals("checkuser")) {
            if(checkUser(request, response)){
                url = "/tweet";
            }else{
                url = "/login.jsp";
            }
        }
        else if(action.equals("logout")){
            userLogout(request, response);
            url = "/login.jsp";
        }
        
        // forward to the view
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String url = "/login.jsp";
        String errorMessage = "";
        String notification = "";
        
        if(action.equals("login"))
        {
            url = "/login.jsp";
            int errorCode = userLogin(request, response);
            if(errorCode != 0){
                errorMessage = "Username/Email or password does not match with our record. Please try again.";
            }else{
                errorMessage = "";
                url = "tweet";
            }
        }
        else if(action.equals("signup")){
            url = "/signup.jsp";
            int errorCode = userAdd(request, response);

        }
        else if(action.equals("updateProfile")){
            url = "/profile.jsp";
            boolean isSuccess = userInfoUpdate(request, response);
            if(isSuccess){
                notification = "Update Successful";
            }
            else{
                notification = "Update Fail";
            }
        }
        else if(action.equals("changePassword")){
            url = "/profile.jsp";
            int errorCode = userChangePassword(request, response);
            switch(errorCode){
                case 0:
                    notification = "You have changed password successfully";
                    break;
                case 601:
                    notification = "Old password mismatch";
                    break;
                case 602:
                    notification = "Confirm password mismatch";
                    break;
                case 603:
                    notification = "Request cannot be complete. Please try again later.";
                    break;
                case 604:
                    notification = "Fields empty. Please fill all the fields";
                    break;
                default:
                    notification = "";
                    
            }
        }
        else if(action.equals("forgotPassword")){
            url = "/forgotpassword.jsp";
            try {
                int i = forgotPassword(request, response);
            } catch (MessagingException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        request.setAttribute("errorMessage", errorMessage);
        request.setAttribute("notification", notification);
        if(url.equals("tweet")){
            response.sendRedirect(url);
        }else{
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
        
    }
    
    
    
    /*
    method userLogin:
    param: HttpServletRequest request, HttpServletResponse response
    return: errorCode
        - errorCode 0: login success
        - errorCode 101: wrong Password
        - errorCode 102: email not found
        - errorCode 103: username not found
    
    */
    private int userLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String loginID = request.getParameter("loginID");
        String password = request.getParameter("password");
        String[] remember = request.getParameterValues("remember");
        HttpSession session = request.getSession();
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
        String time= (String) dtf.format(now);
        
        if(loginID.indexOf('@') != -1){ //login ID is an email address
            User user = UserDB.searchEmail(loginID);
            if(user != null){ //user exist
                if (BCrypt.checkpw(password, user.getPassword())){
                    List<Notification> notifications = NotificationDB.getNotifications(user);
                    session.setAttribute("notifications", notifications);
                    
                    user.setLastVisit(time);
                    UserDB.setLoginTime(user);
                    session.setAttribute("user", user);
                    
                    if(remember != null){
                        setCookie(request, response, user);
                    } 
                    return 0; //login success
                }
                else{
                    return 101; // wrong password
                }
            }else{ //user not found
                return 102; // email not found
            }
        }else{ // login ID is an username
            User user = UserDB.searchUsername(loginID);
            if(user != null){ //user exist
                if (BCrypt.checkpw(password, user.getPassword())){
                    List<Notification> notifications = NotificationDB.getNotifications(user);
                    session.setAttribute("notifications", notifications);
                    
                    user.setLastVisit(time);
                    UserDB.setLoginTime(user);
                    session.setAttribute("user", user);
                    
                    if(remember != null){
                        setCookie(request, response, user);
                    } 
                    return 0; //login success
                }
                else{
                    return 101; // wrong password
                }
            }else{ //user not found
                return 103; // username not found
            }
        }     
    }
    
    private int forgotPassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, MessagingException {
              String email = request.getParameter("email");
        String questionNo = request.getParameter("securityQuestion");
        String answer = request.getParameter("answer");
        User user = UserDB.searchEmail(email);

        char[] tempPassword = new char[8];
        if(user != null){ //user exist
            if(questionNo.equals(user.getQuestionNo()) && answer.equals(user.getAnswer())){
                String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
                SecureRandom rnd = new SecureRandom();
                for(int i = 0; i < 8; i++)
                {
                    tempPassword[i] = characters.charAt(rnd.nextInt(characters.length()));
                }
                String newPassword = new String(tempPassword);

                user.setPassword(newPassword);
                UserDB.changePassword(user);
                String to = email;
                String from = "webappPHSK@gmail.com";
        
                String subject = "Password Reset from MiniTwitter";
                String body = "Hello " + user.getFullname() + "!\n" +
                    "This is your temporary password. Please login and change it as soon as possible!\n"
                    + "Password: " + newPassword + "\n Thank you.\n";
                MailUtilGmail.sendMail(to, from, subject, body);
                return 0;
            }
        }
        return 0;
    }
    
    private int userAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String birthdate = request.getParameter("birthdate");
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm_password");
        String questionNo = request.getParameter("securityQuestion");
        String answer = request.getParameter("answer");
        
        User user= new User(fullname, username, email, password, birthdate, questionNo, answer);
        session.setAttribute("user_reg", user);
        
        if(fullname == null || username == null || email == null || birthdate == null || password == null || confirm_password == null || answer == null
                || fullname.isEmpty() || username.isEmpty() || email.isEmpty() || birthdate.isEmpty() || password.isEmpty() || confirm_password.isEmpty() || answer.isEmpty()){
            return 201; // one of the field is empty
        }else if(!password.equals(confirm_password)){
            return 202; // confirm password mismatch
        }else{
            if(UserDB.searchUsername(username) != null){
                return 203; //username already exist
            }else if(UserDB.searchEmail(email) != null){
                return 204; // email already exist
            }else{
                int isInserted = UserDB.insert(user);
                if(isInserted == 0){
                    return 205; // SQL query error
                }else{
                    
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String time= (String) dtf.format(now);
                    
                    user = UserDB.searchUsername(username);
                    user.setLastVisit(time);
                    UserDB.setLoginTime(user);
                    session.setAttribute("user", user);
                    session.removeAttribute("user_reg");
                    return 0; // signup success
                }
            }
        }
    }
    
    private boolean userInfoUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        request.setAttribute("formActive", "updateProfile");
        String fullname = request.getParameter("fullname");
        String birthdate = request.getParameter("birthdate");
        String questionNo = request.getParameter("securityQuestion");
        String answer = request.getParameter("answer");
        
        User user = (User) session.getAttribute("user");

        if(!fullname.equals("")){
            user.setFullname(fullname);
        }
        if(!birthdate.equals("")){
            user.setBirthdate(birthdate);
        }
        if(!questionNo.equals("0")){
            user.setQuestionNo(questionNo);
        }
        if(!answer.equals("")){
            user.setAnswer(answer);
        }
        
        int isUpdated = UserDB.updateInfo(user);
        if(isUpdated == 1){
            return true;
        }else{
            return false;
        }
    }
    
    private int userChangePassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        request.setAttribute("formActive", "changePassword");
        String old_password = request.getParameter("old_password");
        String new_password = request.getParameter("new_password");
        String confirm_password = request.getParameter("confirm_password");
        User user = (User) session.getAttribute("user");

        if(old_password.equals("") || new_password.equals("") || confirm_password.equals("")){
            return 604;
        }
        else{
            if (BCrypt.checkpw(old_password, user.getPassword())){
                if(new_password.equals(confirm_password)){
                    user.setPassword(new_password);
                    int isUpdated = UserDB.changePassword(user);
                    if(isUpdated == 1){
                        return 0;
                    }else{
                        return 603; // query error
                    }
                }
                else{
                    return 602; //confirm password mismatch
                }
            }
            else{
                return 601; //old password mismatch
            }
        }
    }
    private boolean checkUser(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        User user = (User) request.getAttribute("user");

        // if User object doesn't exist, check loginID cookie
        if (user == null) {
            Cookie[] cookies = request.getCookies();
            String loginID = CookieUtil.getCookieValue(cookies, "miniTwitterLoginID");

            // if cookie doesn't exist
            if (loginID == null || loginID.equals("")) {
                return false;
            } 
            // if cookie exists, create User object
            else {
                user = UserDB.searchUsername(loginID);
                session.setAttribute("user", user);
                return true;
            }
        } 
        // if User object exists, go to Downloads page
        else {
            return true;
        }
    }

    private void setCookie(HttpServletRequest request, HttpServletResponse response, User user) {

        // add a cookie that stores the user's loginID to browser
        Cookie loginIDCookie = new Cookie("miniTwitterLoginID", user.getUsername());
        loginIDCookie.setMaxAge(60 * 60 * 24); // set age to 1 day
        loginIDCookie.setPath("/");                 // allow entire app to access it
        response.addCookie(loginIDCookie);
   }
    
    private void deleteCookies(HttpServletRequest request,
            HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("miniTwitterLoginID")) 
            {
                cookie.setMaxAge(0); //delete the cookie
                cookie.setPath("/"); //allow the download application to access it
                response.addCookie(cookie);
            }
        }
    }
    
    private void userLogout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        deleteCookies(request, response);
        session.removeAttribute("user");
    }
}
