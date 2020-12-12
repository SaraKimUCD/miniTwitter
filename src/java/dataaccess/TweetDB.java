/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import business.Tweet;
import business.User;
import controller.TweetUtil;
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
public class TweetDB {
    public static int insert(Tweet tweet) throws IOException 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query
                = "INSERT INTO tweet (userID, twit, time) "
                + "VALUES (?, ?, ?)";
        
        try{
            String twit = tweet.getTwit();
            twit = TweetUtil.highlightMention(twit);
            twit = TweetUtil.highlightHashtag(twit);
            ps = connection.prepareStatement(query);
            ps.setString(1, tweet.getTweetUserID());
            ps.setString(2, twit);
            ps.setString(3, tweet.getTime());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static Tweet getLastestTweet(String userID)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM tweet WHERE userID = ? ORDER BY time DESC;";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, userID);
            rs = ps.executeQuery();
            Tweet tweet = null;
            if(rs.next()){
                tweet = new Tweet();
                tweet.setTweetID(rs.getString("tweetID"));
                tweet.setTweetUserID(rs.getString("userID"));
                tweet.setTwit(rs.getString("twit"));
            }
            return tweet;
        }catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int delete(String tweetID, String userID) throws IOException 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        //Find and verify tweet belong to a user
        String query = "SELECT * FROM tweet WHERE tweetID = ? and userID = ?;";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, tweetID);
            ps.setString(2, userID);
            rs = ps.executeQuery();
            
            //Tweet found -> delete tweet
            if(rs.next()){
                deleteTweet(tweetID, userID);
                deleteMention(tweetID);
                deleteHashTag(tweetID);
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
    
    private static int deleteTweet(String tweetID, String userID) throws IOException 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        //Delete tweet
        String query = query = "DELETE FROM tweet WHERE tweetID = ? and userID = ?;";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, tweetID);
            ps.setString(2, userID);
            return ps.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    private static int deleteMention(String tweetID) throws IOException 
    {
        return(MentionDB.delete(tweetID));
    }
    
    private static int deleteHashTag(String tweetID) throws IOException 
    {
        return(TweetHashtagDB.delete(tweetID));
    }
    
    public static List viewTweets(String userID) throws IOException 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query
                = "SELECT tweetID, tweet_userID, "
                + "MAX(mention_userID) AS mention_userID, "
                + "MAX(follow_userID) AS follow_userID, "
                + "twit, time, username, fullname, profileURL "
                + "FROM view_tweets "
                + "WHERE tweet_userID = ? OR mention_userID = ? OR follow_userID = ?  "
                + "GROUP BY tweetID "
                + "ORDER BY time DESC;";
        
        List tweets = new LinkedList();
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, userID);
            ps.setString(2, userID);
            ps.setString(3, userID);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Tweet tweet = new Tweet();
                tweet.setTweetID(rs.getString("tweetID"));
                tweet.setTweetUserID(rs.getString("tweet_userID"));
                tweet.setTweetMentionID(rs.getString("mention_userID"));
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
    
    
    
    public static int countUserTweets(String userID) throws IOException 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        
        String query
                = "SELECT COUNT(*) AS count FROM tweet WHERE userID = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, userID);
            rs = ps.executeQuery();
            
            if(rs.next()){
                count = Integer.parseInt(rs.getString("count"));
            }
            return count;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
