/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

public class Follow {
    String userID;
    String followedUserID;
    String followDate;
    
    public Follow()
    {
        userID = "";
        followedUserID  = "";
        followDate = "";
    }
    
    public void setUserID(String userID)
    {
        this.userID = userID;
    }
    public String getUserID()
    {
        return this.userID;
    }
    
    public void setFollowedUserID(String followedUserID)
    {
        this.followedUserID = followedUserID;
    }
    public String getFollowedUserID()
    {
        return this.followedUserID;
    }
    
    public void setFollowDate(String followDate)
    {
        this.followDate = followDate;
    }
    public String getFollowDate()
    {
        return this.followDate;
    }
}
