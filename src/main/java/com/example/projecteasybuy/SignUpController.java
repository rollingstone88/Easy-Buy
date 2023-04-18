package com.example.projecteasybuy;

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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignUpController {
    @FXML
    private TextField addressTextField;

    @FXML
    private TextField contactTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailTestField;

    @FXML
    private TextField userTestField;

    @FXML
    private PasswordField passTestField;

    @FXML
    private PasswordField confirmPassTestField;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField confirmtext;


    public void clearAll() {
        emailTestField.setText("");
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        contactTextField.setText("");
        addressTextField.setText("");
    }

    private int random_int;
    public user User;

    @FXML
    void gotolog(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Project.class.getResource("loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    void onSignUpButtonClick(ActionEvent event) throws SQLException, InstantiationException, IllegalAccessException, IOException {

        errorLabel.setText("");
        int min = 50;
        int max = 100;

        //Generate random int value from 50 to 100
        this.random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
        Mail myEmail = new Mail();
        myEmail.to = emailTestField.getText();
        myEmail.sendMail(random_int);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Project.class.getResource("confirmSignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        try {
            DBMSConnection d = new DBMSConnection("jdbc:mysql://localhost:3306/mydb", "root","");
            Connection myConnection = d.getConnection();

            String dt= "delete from authentication_numbers";
            String query = "insert into authentication_numbers values (" + random_int + ")";
            if (emailTestField.getText() == "" ||
                    firstNameTextField.getText() == "" ||
                    lastNameTextField.getText() == "" ||
                    contactTextField.getText() == "" ||
                    addressTextField.getText() == "" ||
                    userTestField.getText() == "" ||
                    passTestField.getText() == ""
            ) {
                throw new Exception("Empty field");
            }

            if (!passTestField.getText().equals(confirmPassTestField.getText())) {
                throw new Exception("Passwords do not match");
            }

            Statement stat = myConnection.createStatement();
            stat.execute("use mydb;");
            stat.execute(dt);
            stat.execute(query);
            System.out.println(query);
            String statementSyntax = "INSERT INTO user VALUES(";
            statementSyntax = statementSyntax + user.generate_USER_ID() + ",'" + firstNameTextField.getText() + "','" + lastNameTextField.getText() + "','"
                    + emailTestField.getText() + "','" + addressTextField.getText() + "'," + Integer.parseInt(contactTextField.getText()) + ",'" + userTestField.getText() + "','" + passTestField.getText() + "'," + 0 + ");";

            stat.execute("use mydb;");
            stat.execute(statementSyntax);
            System.out.println(statementSyntax);

            errorLabel.setText("Successfully added");

        } catch (Exception e) {
            String errorMsg = e.getMessage();
            System.out.println(errorMsg);
            errorLabel.setText(errorMsg);

        }
    }

    @FXML
    void onConfirmClick(ActionEvent event) throws IOException {

        int i = Integer.parseInt(confirmtext.getText());

        try {
            DBMSConnection d = new DBMSConnection("jdbc:mysql://localhost:3306/mydb", "root","");
            Connection myConnection = d.getConnection();

            String query = "select * from authentication_numbers";
            Statement stmt = myConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                random_int = rs.getInt(1);
            }
            stmt.close();
            System.out.println(i);
            System.out.println(this.random_int);
            if (i != random_int) {
                Statement st = myConnection.createStatement();
                String query2 = "DELETE FROM user WHERE user_id = " + user.generate_USER_ID();
                st.execute(query2);
                throw new Exception("Code doesn't match");

            }
            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Project.class.getResource("loginPage.fxml"));
            Scene scene1 = new Scene(fxmlLoader.load());
            stage1.setScene(scene1);

        } catch (Exception e) {
            String errorMsg = e.getMessage();
            System.out.println(errorMsg);
            //errorLabel.setText(errorMsg);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Project.class.getResource("SignUp.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

        }

    }
}
