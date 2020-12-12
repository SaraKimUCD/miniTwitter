/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import business.Hashtag;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class HashtagDB {
    public static int insert(String hashtag)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query
                = "INSERT INTO hashtag (hashtagText) "
                + "VALUES (?)";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, hashtag);
    
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static Hashtag searchHashtag(String hashtagText)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM hashtag WHERE hashtagText = ?";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, hashtagText);
            rs = ps.executeQuery();
            Hashtag hashtag = null;
            
            if(rs.next()){
                hashtag = new Hashtag();
                hashtag.setHashtagID(rs.getString("hashtagID"));
                hashtag.setHashtagText(rs.getString("hashtagText"));
                hashtag.setHashtagCount(rs.getString("hashtagCount"));
            }
            return hashtag;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int updateHashtagCount(String hashtagID)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        int count = getHashtagCount(hashtagID);
        
        if(count > 0){
            String query = "UPDATE hashtag SET hashtagCount = ? WHERE hashtagID = ?";
        
            try{
                ps = connection.prepareStatement(query);
                ps.setString(1, Integer.toString(count));
                ps.setString(2, hashtagID);
                return ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
                return 0;
            } finally {
                DBUtil.closePreparedStatement(ps);
                pool.freeConnection(connection);
            }
        }else{
            deleteHashTag(hashtagID);
            return 1;
        }
    }
    
    public static List getTopTrends()
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM hashtag ORDER BY hashtagCount DESC LIMIT 0, 10;";
        
        List topTrends = new LinkedList();
        
        try{
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            Hashtag hashtag = null;
            while(rs.next()){
                hashtag = new Hashtag();
                hashtag.setHashtagID(rs.getString("hashtagID"));
                hashtag.setHashtagText(rs.getString("hashtagText"));
                hashtag.setHashtagCount(rs.getString("hashtagCount"));
                topTrends.add(hashtag);
            }
            return topTrends;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    private static int getHashtagCount(String hashtagID)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        
        String query
                = "SELECT COUNT(*) AS count FROM tweetHashtag WHERE hashtagID = ?";
       
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, hashtagID);
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
    
    //Private call only: Delete hashtag if hashtagCount == 0
    private static int deleteHashTag(String hashtagID)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        //Find list of mention associate with the tweet
        String query = "DELETE FROM hashtag WHERE hashtagID = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, hashtagID);
            ps.executeUpdate();
            return 1;
        }catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
