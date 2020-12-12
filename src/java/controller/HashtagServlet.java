/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.Hashtag;
import business.User;
import dataaccess.HashtagDB;
import dataaccess.TweetDB;
import dataaccess.TweetHashtagDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "HashtagServlet", urlPatterns = {"/hashtag"})
public class HashtagServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String url = "/hashtag.jsp";
        String errorMessage = "";
        
        if(action == null){
            action = "viewTweets";
        }
        
        if(action.equals("viewTweets")){
            viewTweets(request, response);
        }
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    
    protected void viewTweets(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String hashtagText = request.getParameter("hashtagText");
        Hashtag hashtag = HashtagDB.searchHashtag(hashtagText);
        session.setAttribute("activeHashtag", hashtagText);
        
        List tweets = TweetHashtagDB.viewTweets(hashtag.getHashtagID());
        if(tweets != null){
            session.setAttribute("hashtagTweets", tweets);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
