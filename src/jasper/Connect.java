/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jasper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Frost
 */
public class Connect {
    public static final int MYSQL = 0;
    public static final int MSSQLSERVER = 1;
    public static final int ORACLE = 2;

    private Connection conn;
    private String host;
    private String db;
    private String username;
    private String password;


    private static Connect connect;

    public Connect(int type, String host, int port, String db, String username, String password) {
        String driver = "";
        String url = "";
        switch (type) {
        case MYSQL:
            driver = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://" + host + ":"+port+"/" + db;
            break;
        case MSSQLSERVER:
            driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            //url = "jdbc:sqlserver://"+host+":"+port+";db="+db;
            url = "jdbc:sqlserver://"+host+":"+port+";db="+db+";SelectMethod=direct";
            //Log.log(url);
            break;
        case ORACLE:
            driver = "oracle.jdbc.driver.OracleDriver";
            url = "jdbc:oracle:thin:@//"+host+":"+port+"/"+db;
            break;
        }
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.host = host;
        this.db = db;
        this.username = username;
        this.password = password;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        connect = this;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection connection() {
        try {
            return conn;
        } finally {

        }
    }
    
    public Statement query(String sQuery) throws SQLException {
        Statement s = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        s.executeQuery(sQuery);
        return s;
    }
    
    public Statement update(String sQuery) throws SQLException {
        Statement s = conn.createStatement();
        s.executeUpdate(sQuery);
        return s;
    }
    
    public void close(Statement s) {
        try {
            s.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage() + "\n" +
                    e.getErrorCode());
        }
    }
    
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage() + "\n" + e.getErrorCode());
        }
    }

    public static Connect getConnect() {
        return connect;
    }

    public static void setConnect(Connect connect) {
        Connect.connect = connect;
    }
}
