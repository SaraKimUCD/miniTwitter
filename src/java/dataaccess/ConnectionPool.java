package dataaccess;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConnectionPool {

    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;

    private ConnectionPool(){
        try {
            InitialContext ic = new InitialContext();
            if (ic == null) {
                System.out.println("No context!");
            }
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/twitterdb");
            if(dataSource == null){
                System.out.println("Data soure not found");
            }
        } catch (NamingException e) {
            System.out.println(e);
        }
    }

    public static synchronized ConnectionPool getInstance(){
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public void freeConnection(Connection c) {
        try {
            c.close();
            c = null;
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}