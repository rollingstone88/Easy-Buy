package com.example.projecteasybuy;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RocketController {

    @FXML
    private GridPane ProductsGridPane;

    @FXML
    public void initialize() throws FileNotFoundException {

        ProductsGridPane.getChildren().clear();
        Label RocketLabel = new Label("IUT ROCKET");
        RocketLabel.setStyle("-fx-font-weight: bold;-fx-font-size:25pt;-fx-text-fill: #fc0202");
        ProductsGridPane.add(RocketLabel, 1, 0);

        try {
            DBMSConnection d = new DBMSConnection("jdbc:mysql://localhost:3306/mydb", "root","");
            Connection myConnection = d.getConnection();

            String Query = "SELECT * FROM ROCKET";

            Statement stmt = myConnection.createStatement();

            stmt.execute("use mydb");
            ResultSet rs = stmt.executeQuery(Query);

            String p_name, IMG_loc;
            int stk, P_ID;
            float p_price;

            int hor = 1;
            int ver = 1;
            while (rs.next()) {
                P_ID = rs.getInt(1);
                p_name = rs.getString(2);
                IMG_loc = rs.getString(5);
                stk = rs.getInt(4);
                p_price = rs.getFloat(3);
                Products pr = new Products(P_ID, p_name, IMG_loc, stk, p_price);

                VBox productView1 = productView(pr);
                ProductsGridPane.add(productView1, hor, ver);
                productView1.setPadding(new Insets(10, 10, 10, 10));
                hor +=3;
                if(hor>22) {hor = 1; ver = ver+1;}
            }

            rs.close();
            stmt.close();


        } catch (Exception e) {
            String errorMsg = e.getMessage();
            System.out.println(errorMsg);

        }

    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cart");
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Item added to cart!");
        alert.showAndWait();
    }
    private VBox productView(Products product)throws FileNotFoundException {
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);

        FileInputStream input = new FileInputStream("C:/Users/HP/projectEasyBuy/src/main/resources/com/example/projecteasybuy/HOME/" + product.getImageFile());
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(170);
        // imageView.setPadding(new Insets(10, 10, 10, 10));
        Label productName = new Label(product.getProduct_name());
        Label price = new Label("Tk " + product.getPrice());

        Button addButton = new Button("Add to Cart");
        addButton.setUserData(product.getProduct_name());          //to identify the product
        //addButton.setStyle("-fx-font-color: white");
        addButton.setStyle("-fx-background-color: #fc0202;-fx-font-weight: bold;-fx-text-fill: white");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //add product to Shopping Cart
                Node sourceComponent = (Node) actionEvent.getSource();
                //String productName = (String) sourceComponent.getUserData();
                RocketShoppingCart shoppingCart = RocketShoppingCart.getInstance();
                shoppingCart.addProduct(product);
                showAlert();
            }
        });
        layout.getChildren().addAll(imageView, productName, price, addButton);

        return layout;
    }

}
