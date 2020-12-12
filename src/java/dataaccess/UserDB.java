/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;
import business.User;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDB {

    public static int insert(User user) throws IOException 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query
                = "INSERT INTO user (fullname, username, emailAddress, birthdate, password, questionNo, answer) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try{
            String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFullname());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getBirthdate());
            ps.setString(5, password);
            ps.setString(6, user.getQuestionNo());
            ps.setString(7, user.getAnswer());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static List viewUsers(String username) throws IOException 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM user WHERE username NOT IN(?);";
        
        List users = new LinkedList();
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            User user = null;
            while(rs.next()){
                user = new User();
                user.setUserID(rs.getString("userID"));
                user.setFullname(rs.getString("fullname"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("emailAddress"));
                user.setBirthdate(rs.getString("birthdate"));
                user.setPassword(rs.getString("password"));
                user.setQuestionNo(rs.getString("questionNo"));
                user.setAnswer(rs.getString("answer"));
                user.setCoverURL(rs.getString("coverURL"));
                user.setProfileURL(rs.getString("profileURL"));
                user.setLastVisit(rs.getString("lastVisit"));
                
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int updateInfo(User user) throws IOException 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query
                = "UPDATE user "
                + "SET fullname = ?, birthdate = ?,  questionNo = ?, answer = ?"
                + "WHERE username = ?;";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFullname());
            ps.setString(2, user.getBirthdate());
            ps.setString(3, user.getQuestionNo());
            ps.setString(4, user.getAnswer());
            ps.setString(5, user.getUsername());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int changePassword(User user) throws IOException 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query
                = "UPDATE user "
                + "SET password = ?"
                + "WHERE username = ?;";
        
        try{
            String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            ps = connection.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, user.getUsername());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int uploadProfileImage(String userID, String url) throws IOException 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query
                = "UPDATE user "
                + "SET profileURL = ?"
                + "WHERE userID = ?;";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, url);
            ps.setString(2, userID);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int uploadCoverImage(String userID, String url) throws IOException 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query
                = "UPDATE user "
                + "SET coverURL = ?"
                + "WHERE userID = ?;";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, url);
            ps.setString(2, userID);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static User searchEmail(String emailAddress) 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM user WHERE emailAddress = ?";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, emailAddress);
            rs = ps.executeQuery();
            User user = null;
            
            if(rs.next()){
                user = new User();
                user.setUserID(rs.getString("userID"));
                user.setFullname(rs.getString("fullname"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("emailAddress"));
                user.setBirthdate(rs.getString("birthdate"));
                user.setPassword(rs.getString("password"));
                user.setQuestionNo(rs.getString("questionNo"));
                user.setAnswer(rs.getString("answer"));
                user.setCoverURL(rs.getString("coverURL"));
                user.setProfileURL(rs.getString("profileURL"));
                user.setLastVisit(rs.getString("lastVisit"));
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static User searchUsername(String username) 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM user WHERE username = ?";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            User user = null;
            
            if(rs.next()){
                user = new User();
                user.setUserID(rs.getString("userID"));
                user.setFullname(rs.getString("fullname"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("emailAddress"));
                user.setBirthdate(rs.getString("birthdate"));
                user.setPassword(rs.getString("password"));
                user.setQuestionNo(rs.getString("questionNo"));
                user.setAnswer(rs.getString("answer"));
                user.setCoverURL(rs.getString("coverURL"));
                user.setProfileURL(rs.getString("profileURL"));
                user.setLastVisit(rs.getString("lastVisit"));
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int setLoginTime(User user) throws IOException 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query
                = "UPDATE user "
                + "SET lastVisit = ?"
                + "WHERE username = ?;";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getLastVisit());
            ps.setString(2, user.getUsername());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
}
