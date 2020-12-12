/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.Follow;
import business.User;
import dataaccess.FollowDB;
import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author duypham
 */
@WebServlet(name = "FollowServlet", urlPatterns = {"/follow"})
public class FollowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String url = "tweet";
        String errorMessage = "";
        
        if(action == null){
            action = "goHome";
        }
        
        if(action.equals("goHome")){
            url = "/home.jsp";
        }else if(action.equals("follow")){
            followUser(request, response);
        }else if(action.equals("unfollow")){
            unfollowUser(request, response);
        }
        
        request.setAttribute("errorMessage", errorMessage);
        if(url.equals("tweet")){
            response.sendRedirect(url);
        }else{
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
        
    }
    
    protected void followUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        
        User thisUser = (User) session.getAttribute("user");
        User followedUser = UserDB.searchUsername(username);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
        String date= (String) dtf.format(now);
        
        Follow follow = new Follow();
        follow.setUserID(thisUser.getUserID());
        follow.setFollowedUserID(followedUser.getUserID());
        follow.setFollowDate(date);
        FollowDB.insert(follow);
    }
    
    protected void unfollowUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = request.getParameter("username");

        User thisUser = (User) session.getAttribute("user");
        User followedUser = UserDB.searchUsername(username);
        
        Follow follow = new Follow();
        follow.setUserID(thisUser.getUserID());
        follow.setFollowedUserID(followedUser.getUserID());
        FollowDB.delete(follow);   
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
