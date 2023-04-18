package com.example.projecteasybuy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class loginController {

    public user curr_user;

    @FXML
    private Label label;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passTextField;

    @FXML
    void onLoginClick(ActionEvent event) throws IOException {

        String ut = usernameTextField.getText();
        String pt = passTextField.getText();
        String Query = "SELECT count(*) FROM user WHERE username = '" + ut + "' AND password = '" + pt + "'";
        String Query1 = "SELECT user_id FROM user WHERE username = '" + ut + "' AND password = '" + pt + "'";

        try {
            DBMSConnection d = new DBMSConnection("jdbc:mysql://localhost:3306/mydb", "root","");
            Connection conn = d.getConnection();

            if (usernameTextField.getText() == "" || passTextField.getText() == "")
            {
                throw new Exception("Empty field");
            }

            Statement stmt  = conn.createStatement();

            stmt.execute("use mydb");
            ResultSet rs = stmt.executeQuery(Query);
            int i = 0;
            while(rs.next())
            i = rs.getInt(1);
            if(i == 1){
            label.setText("login successful");
                Stage curr_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(Project.class.getResource("homepage.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                curr_stage.setScene(scene);
            }
            else {label.setText("INCORRECT USERNAME OR PASSWORD");
                throw new Exception("INCORRECT USERNAME OR PASSWORD");}

            rs = stmt.executeQuery(Query1);
            int Curr_ID = 0;

            while(rs.next())
            {
                Curr_ID = rs.getInt(1);
            }

            curr_user = new user(Curr_ID,ut,pt);
            System.out.println(curr_user.getUserID());

            String drop = "delete from curruser";
            stmt.executeUpdate(drop);

            String Query12 = "INSERT INTO curruser VALUES(";
            Query12 = Query12 + curr_user.getUserID() + ",'" + curr_user.getUsername() + "');";

            System.out.println(Query12);
            stmt.executeUpdate(Query12);

            rs.close();
            stmt.close();

        } catch (Exception e) {
            String errorMsg = e.getMessage();
            System.out.println(errorMsg);
            label.setText(errorMsg);

        }
    }

    @FXML
    void onHyperlinkClick(ActionEvent event) throws IOException {
        Stage curr_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Project.class.getResource("SignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        curr_stage.setScene(scene);
    }
    @FXML
    void onShopNowClick(ActionEvent event) throws IOException{
        Stage curr_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Project.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        curr_stage.setScene(scene);
    }
}
