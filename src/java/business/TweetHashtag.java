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
public class TweetHashtag {
    String tweetID;
    String hashtagID;
    
    public TweetHashtag()
    {
        tweetID = "";
        hashtagID = "";
    }
    
    public TweetHashtag(String tweetID, String hashtagID)
    {
        this.tweetID = tweetID;
        this.hashtagID = hashtagID;
    }
    
    public void setTweetID(String tweetID)
    {
        this.tweetID = tweetID;
    }
    
    public String getTweetID()
    {
        return this.tweetID;
    }
    
    public void setHashtagID(String hashtagID)
    {
        this.hashtagID = hashtagID;
    }
    
    public String getHashtagID()
    {
        return this.hashtagID;
    }
}
