package com.example.projecteasybuy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBMSConnection {
    String url;
    String username;
    String password;

    public DBMSConnection(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws InstantiationException, IllegalAccessException, SQLException {
        Connection con = null;
        con = DriverManager.getConnection(this.url,this.username,this.password);
        return con;
    }

    public void closeConnection(Connection con, Statement stmt) throws SQLException {
        stmt.close();
        con.close();
    }
}
