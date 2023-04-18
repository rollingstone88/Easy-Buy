package com.example.projecteasybuy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class user {
    private int userID;
    private String username;
    private String pass;
   private String email;
    private String firstName;
    private String lastName;
    private int contact_no;
    private String address;
    private int coin_collection;
    private static user INSTANCE;

    public static user getInstance(){
        if(INSTANCE==null){
            INSTANCE= new user();
        }

        return INSTANCE;
    }


    user()
    {
        this.userID = generate_USER_ID();
    }

    user(int userID, String username, String pass)
    {
        this.userID = userID;
        this.username = username;
        this.pass = pass;
    }
    public static int generate_USER_ID()
    {
        int cnt = 0;
        try {
            DBMSConnection d = new DBMSConnection("jdbc:mysql://localhost:3306/mydb", "root","");
            Connection myConnection = d.getConnection();

            String Query = "SELECT count(*) from user";

            Statement stmt = myConnection.createStatement();

            stmt.execute("use mydb");
            ResultSet rs = stmt.executeQuery(Query);

            while(rs.next()) {
                cnt = rs.getInt(1);
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            String errorMsg = e.getMessage();
            System.out.println(errorMsg);

        }
        return cnt + 1;
    }
    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public int getCoin_collection() {
        return coin_collection;
    }

    public void setUserID(int c) {
        this.userID = c;
    }

    public void setUsername(String Name) {
        this.username = Name;
    }

    public void setPass(String str) {
        this.pass = str;
    }
};