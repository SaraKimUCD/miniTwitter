/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import business.Mention;
import controller.TweetUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Duy Pham
 */
public class MentionDB {
    
    public static int insert(Mention mention)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query
                = "INSERT INTO mention (tweetID, userID) "
                + "VALUES (?, ?)";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, mention.getTweetID());
            ps.setString(2, mention.getUserID());
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
        
        //Find list of mention associate with the tweet
        String query = "SELECT * FROM mention WHERE tweetID = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, tweetID);
            rs = ps.executeQuery();
            
            //Mention found -> delete this list of mention
            if(rs.next()){
                query = "DELETE FROM mention WHERE tweetID = ?";
                        
                try{
                    ps = connection.prepareStatement(query);
                    ps.setString(1, tweetID);
                    return ps.executeUpdate();
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
}
