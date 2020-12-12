package dataaccess;

import business.Notification;
import business.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author duypham
 */
public class NotificationDB {
    
    public static List<Notification> getNotifications(User user){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM view_notification "
                + "WHERE (mention_userID = ? OR followedUserID = ?) AND (date > ?) "
                + "ORDER BY date DESC";
        
        List notifications = new LinkedList();
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUserID());
            ps.setString(2, user.getUserID());
            ps.setString(3, user.getLastVisit());
            rs = ps.executeQuery();
            Notification notification = null;
            while(rs.next()){
                notification = new Notification();
                notification.setTablename(rs.getString("table_name"));
                if((rs.getString("table_name")).equals("mention")){
                    notification.setFullname(rs.getString("fullname"));
                    notification.setUsername(rs.getString("username"));
                    notification.setProfileURL(rs.getString("profileURL"));
                    notification.setTwit(rs.getString("twit"));
                    notification.setDate(rs.getString("date"));
                }else{
                    notification.setFullname(rs.getString("follow_fullname"));
                    notification.setUsername(rs.getString("follow_username"));
                    notification.setProfileURL(rs.getString("follow_profileURL"));
                    notification.setDate(rs.getString("date"));
                }
                notifications.add(notification);
            }
            return notifications;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
}
