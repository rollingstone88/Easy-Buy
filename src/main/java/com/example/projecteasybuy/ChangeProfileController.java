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
import java.sql.Statement;


public class ChangeProfileController {
    @FXML
    private TextField cn;

    @FXML
    private TextField fn;

    @FXML
    private TextField ln;

    @FXML
    private TextField us;

    @FXML
    private PasswordField cp;

    @FXML
    private PasswordField np;

    @FXML
    private PasswordField pp;

    @FXML
    private Label label;

    @FXML
    private Label gc;

    @FXML
    private Label a;

    @FXML
    private Label b;

    @FXML
    private Label c;

    @FXML
    private Label d;

    @FXML
    private Label e;

    @FXML
    private Label f;

    @FXML
    void onclickChangeProfile(ActionEvent event) throws IOException {
        Stage curr_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Project.class.getResource("up_changeprofile.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        curr_stage.setScene(scene);

    }

    @FXML
    void onclickChangePass(ActionEvent event) throws IOException {
        Stage curr_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Project.class.getResource("up_password.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        curr_stage.setScene(scene);

    }

    @FXML
    void coincollect(ActionEvent event) throws IOException {
        Stage curr_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Project.class.getResource("goldcoins.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        curr_stage.setScene(scene);

        int total_coins = 0;
        try {
            DBMSConnection d = new DBMSConnection("jdbc:mysql://localhost:3306/mydb", "root","");
            Connection myConnection = d.getConnection();
            int id = 0;
            String Query123 = "select user_id from curruser";

            Statement stmt = myConnection.createStatement();

            stmt.execute("use mydb");
            ResultSet rs = stmt.executeQuery(Query123);
            while (rs.next()) {
                id = rs.getInt(1);
            }
            int prev_coins = 0;
            String ager_coin = "select coincollection from user where user_id = " + id;
            rs = stmt.executeQuery(ager_coin);
            while (rs.next()) {
            prev_coins = rs.getInt(1);
            }

            String last_order = "select total from orders where customer_id = " + id;
            rs = stmt.executeQuery(last_order);
            int order_total = 0;
            while(rs.next()){
            order_total = rs.getInt(1);}
            total_coins = (prev_coins + (int)order_total/500);
            System.out.println(prev_coins);
            System.out.println(order_total);
            System.out.println(id);
            rs.close();
            stmt.close();
            System.out.println(total_coins);

            String coin = Integer.toString(total_coins);
            System.out.println(coin);
            gc.setText(coin);
        }catch (Exception e) {
            String errorMsg = e.getMessage();
            System.out.println(errorMsg);

        }

    }

    @FXML
    void onClickOrderDetails(ActionEvent event) throws IOException {
        Stage curr_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Project.class.getResource("order.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        curr_stage.setScene(scene);
        try {
            DBMSConnection d = new DBMSConnection("jdbc:mysql://localhost:3306/mydb", "root","");
            Connection myConnection = d.getConnection();
            int id = 0;
            String Query123 = "select user_id from curruser";

            Statement stmt = myConnection.createStatement();

            stmt.execute("use mydb");
            ResultSet rs = stmt.executeQuery(Query123);
            while (rs.next()) {
                id = rs.getInt(1);
            }
            String Query22 = "select order_id, total, store from order";

            rs = stmt.executeQuery(Query22);
            int id1 = 0, id2 = 0, total1 = 0,total2 = 0;
            String store1 = "", store2 = "";
            while (rs.next()) {
                id1 = rs.getInt(1);
                total1 = rs.getInt(2);
                store1 = rs.getString(3);
        }
            a.setText(Integer.toString(id1));
            b.setText(Integer.toString(total1));
            c.setText(store1);
        }catch (Exception e) {
                String errorMsg = e.getMessage();
                System.out.println(errorMsg);

            }
    }

    @FXML
    public void switchToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Project.class.getResource("homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    void saveChanges(ActionEvent event) {

        String first = fn.getText();
        String last = ln.getText();
        int contact = Integer.parseInt(cn.getText());
        String username = us.getText();
        try {
            DBMSConnection d = new DBMSConnection("jdbc:mysql://localhost:3306/mydb", "root","");
            Connection myConnection = d.getConnection();
            int id = 0;
            String Query123 = "select user_id from curruser";

            Statement stmt = myConnection.createStatement();

            stmt.execute("use mydb");
            ResultSet rs = stmt.executeQuery(Query123);
            while (rs.next()) {
                id = rs.getInt(1);
            }
            String Query = "UPDATE user \n" +
                    "SET \n" +
                    "    firstname = '" + first +
                    "',   lastname = '" + last +
                    "',    contact = " + contact +
                    ",   username = '" + username +
                    "' WHERE user_id = " + id;
            stmt.executeUpdate(Query);

            fn.setText("");
            ln.setText("");
            cn.setText("");
            us.setText("");
            label.setText("SAVED!");

            //rs.close();
            stmt.close();

        } catch (Exception e) {
            String errorMsg = e.getMessage();
            System.out.println(errorMsg);

        }

    }

    @FXML
    void savePass(ActionEvent event) {
        try {
            DBMSConnection d = new DBMSConnection("jdbc:mysql://localhost:3306/mydb", "root", "");
            Connection myConnection = d.getConnection();
            int id = 0;
            String Query123 = "select user_id from curruser";

            Statement stmt = myConnection.createStatement();

            stmt.execute("use mydb");
            ResultSet rs = stmt.executeQuery(Query123);
            while (rs.next()) {
                id = rs.getInt(1);
            }
            String pass = pp.getText();
            String match = "select count(*) from user where user_id = " + id + " and password = '" + pass + "';";

            int flag = 0;

            rs = stmt.executeQuery(match);
            while (rs.next()) {
                flag = rs.getInt(1);
            }
            String newpass = np.getText();
            if (flag == 1) {
                System.out.println("hello");
                String Query14 = "UPDATE user \n" +
                        "SET \n" +
                        "    password = '" + newpass +
                        "' WHERE user_id = " + id;
                stmt.executeUpdate(Query14);
            }

        } catch (Exception e) {
            String errorMsg = e.getMessage();
            System.out.println(errorMsg);

        }
    }
}
