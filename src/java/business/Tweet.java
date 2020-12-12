/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;

/**
 *
 * @author duypham
 */
public class Tweet implements Serializable{
    String tweetID;
    String tweetUserID;
    String tweetMentionID;
    String twit;
    String time;
    String username;
    String fullname;
    String profileURL;
    
    public Tweet()
    {
        tweetID = "";
        tweetUserID = "";
        tweetMentionID = "";
        twit = "";
        time = "";
        username = "";
        fullname = "";
    }
    
    public Tweet(String userID, String twit, String time)
    {
        this.tweetUserID = userID;
        this.twit = twit;
        this.time = time;
    }
    
    public Tweet(String tweetID, String tweetUserID, String tweetMentionID, String twit, String time, String username, String fullname)
    {
        this.tweetID = tweetID;
        this.tweetUserID = tweetUserID;
        this.tweetMentionID = tweetMentionID;
        this.twit = twit;
        this.time = time;
        this.username = username;
        this.fullname = fullname;
    }
    
    public void setTweetID(String tweetID)
    {
        this.tweetID = tweetID;
    }
    public String getTweetID()
    {
        return this.tweetID;
    }
    public void setTweetUserID(String tweetUserID)
    {
        this.tweetUserID = tweetUserID;
    }
    public String getTweetUserID()
    {
        return this.tweetUserID;
    }
    public void setTweetMentionID(String tweetMentionID)
    {
        this.tweetMentionID = tweetMentionID;
    }  
    public String getTweetMentionID()
    {
        return this.tweetMentionID;
    }
    public void setTwit(String twit)
    {
        this.twit = twit;
    }
    public String getTwit()
    {
        return this.twit;
    }
    public void setTime(String time)
    {
        this.time = time;
    }
    public String getTime()
    {
        return this.time;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getUsername()
    {
        return this.username;
    }
    public void setFullname(String fullname)
    {
        this.fullname = fullname;
    }
    public String getFullname()
    {
        return this.fullname;
    }
    public void setProfileURL(String profileURL)
    {
        this.profileURL = profileURL;
    }
    
    public String getProfileURL()
    {
        return this.profileURL;
    }
    
    

}
