/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import business.Hashtag;
import business.Tweet;
import business.TweetHashtag;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author duypham
 */
public class TweetHashtagDB {
    public static int insert(TweetHashtag tweetHashtag)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query
                = "INSERT INTO tweetHashtag (tweetID, hashtagID) "
                + "VALUES (?, ?)";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, tweetHashtag.getTweetID());
            ps.setString(2, tweetHashtag.getHashtagID());
    
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int delete(String tweetID)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        //Find list of hashtag associate with the tweet
        String query = "SELECT * FROM tweetHashtag WHERE tweetID = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, tweetID);
            rs = ps.executeQuery();
            
            //hashtag found -> save all the hashtagID in this tweet to the list
            //This is uses to update the hashtagCount after tweet is deleted.
            LinkedList<String> hashtags = new LinkedList();
            boolean hashtagFound = false;
            while(rs.next()){
                hashtagFound = true;
                hashtags.add(rs.getString("hashtagID"));
            }
            if(hashtagFound){
                query = "DELETE FROM tweetHashtag WHERE tweetID = ?";
                        
                try{
                    ps = connection.prepareStatement(query);
                    ps.setString(1, tweetID);
                    ps.executeUpdate();
                    
                    //update hashtagCount
                    hashtags.forEach((id) -> {
                        HashtagDB.updateHashtagCount(id);
                    });
                    return 1;
                }catch (SQLException e) {
                    System.out.println(e);
                    return 0;
                }
            }
            return 1;
        }catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static List viewTweets(String hashtagID) throws IOException 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query
                = "SELECT * "
                + "FROM view_hashtag_tweet "
                + "WHERE hashtagID = ? "
                + "ORDER BY time DESC;";
        
        List tweets = new LinkedList();
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, hashtagID);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Tweet tweet = new Tweet();
                tweet.setTweetID(rs.getString("tweetID"));
                tweet.setTweetUserID(rs.getString("UserID"));
                tweet.setTwit(rs.getString("twit"));
                tweet.setTime(rs.getString("time"));
                tweet.setUsername(rs.getString("username"));
                tweet.setFullname(rs.getString("fullname"));
                tweet.setProfileURL(rs.getString("profileURL"));
                
                tweets.add(tweet);
            }
            return tweets;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
