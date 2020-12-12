/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author duypham
 */
public class Notification {
    String tablename;
    String twit;
    String username;
    String fullname;
    String profileURL;
    String date;
    
    public Notification()
    {
        tablename = "";
        twit = "";
        username = "";
        fullname = "";
        profileURL = "";
    }
    
    public void setTablename(String tablename)
    {
        this.tablename = tablename;
    }
    public String getTablename()
    {
        return this.tablename;
    }
    public void setTwit(String twit)
    {
        this.twit = twit;
    }
    public String getTwit()
    {
        return this.twit;
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
    public void setDate(String date)
    {
        this.date = date;
    }
    public String getDate()
    {
        return this.date;
    }
}
