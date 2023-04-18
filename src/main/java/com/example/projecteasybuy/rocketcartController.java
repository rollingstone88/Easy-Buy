package com.example.projecteasybuy;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class rocketcartController {
    @FXML
    private VBox cartPane;

    private Label totalPriceLabel;

    @FXML
    public void initialize() throws FileNotFoundException {
        //populate the view
        List<CartEntry> entries = RocketShoppingCart.getInstance().rocketgetEntries();
        cartPane.getChildren().clear();

        if (entries.isEmpty()) {
            Label emptyLabel = new Label("Empty Cart!");
            emptyLabel.setStyle("-fx-font-weight: bold;-fx-font-size:20pt;-fx-text-fill: #fc0202");
            cartPane.getChildren().add(emptyLabel);
        } else {
            Label shoppingCartTitle = new Label("Your Cart");
            shoppingCartTitle.setStyle("-fx-font-weight: bold;-fx-font-size:20pt");
            cartPane.getChildren().add(shoppingCartTitle);

            for (CartEntry cartEntry : entries) {
                //System.out.println(cartEntry.getProduct().getProduct_name()+"\n");
                HBox productView = cartEntryView(cartEntry);
                cartPane.getChildren().add(productView);
            }

            Separator separator = new Separator();
            separator.setOrientation(Orientation.HORIZONTAL);
            cartPane.getChildren().add(separator);

            HBox totalView = totalView((float) RocketShoppingCart.getInstance().calculateTotal());
            cartPane.getChildren().add(totalView);


        }

    }

    private HBox totalView(float totalPrice) {
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);

        Label totalLabel = new Label("Total: ");
        totalLabel.setStyle("-fx-font-size:15pt");

        this.totalPriceLabel = new Label(String.valueOf(totalPrice));
        HBox lo2=new HBox();
        totalPriceLabel.setStyle("-fx-font-weight: bold;-fx-font-size:15pt");
        Button ptcButton = new Button("Proceed to checkout");
        ptcButton.setStyle("-fx-background-color: #fc0202;-fx-font-weight: bold;-fx-text-fill: white");
        lo2.getChildren().addAll(totalLabel,this.totalPriceLabel);
        VBox lo3=new VBox();
        lo3.getChildren().addAll(lo2,ptcButton);
        layout.getChildren().addAll(lo3);

        ptcButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                user curr_user = user.getInstance();

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
                    Order order = new Order("ROCKET", id);
                    String Query = "INSERT INTO orders VALUES(";
                    Query = Query +  order.getOrder_ID() + "," + order.getCustomer_ID() + ","
                            + order.getTotal_amount() + ",'" + order.getStore() + "');";
                    System.out.println(id);

                    stmt.execute("use mydb");
                    System.out.println(Query);
                    stmt.executeUpdate(Query);

                    stmt.close();

                    Stage curr_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(Project.class.getResource("confirm.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    curr_stage.setScene(scene);

                } catch (Exception e) {
                    String errorMsg = e.getMessage();
                    System.out.println(errorMsg);
                }
            }
        });        return layout;

    }

    public Products Make_Product(String name) {
        Products pr = new Products();

        try {
            DBMSConnection d = new DBMSConnection("jdbc:mysql://localhost:3306/mydb", "root","");
            Connection myConnection = d.getConnection();

            String Query = "SELECT * FROM ROCKET WHERE NAME = '" + name + "'";

            Statement stmt = myConnection.createStatement();

            stmt.execute("use mydb");
            ResultSet rs = stmt.executeQuery(Query);

            String p_name, IMG_loc;
            int stk, P_ID;
            float p_price;

            while (rs.next()) {
                P_ID = rs.getInt(1);
                p_name = rs.getString(2);
                IMG_loc = rs.getString(5);
                stk = rs.getInt(4);
                p_price = rs.getFloat(3);
                pr.setProduct_ID(P_ID);
                pr.setProduct_name(p_name);
                pr.setPrice(p_price);
                pr.setImageFile(IMG_loc);
                pr.setStock(stk);
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            String errorMsg = e.getMessage();
            System.out.println(errorMsg);

        }
        return pr;
    }

    private HBox cartEntryView(CartEntry cartEntry) throws FileNotFoundException {
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER_LEFT);

        FileInputStream input = new FileInputStream("C:/Users/HP/projectEasyBuy/src/main/resources/com/example/projecteasybuy/HOME/" + cartEntry.getImg_loc());
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        Label productName = new Label(cartEntry.getProduct_name());
        productName.setPrefWidth(100);
        productName.setStyle("-fx-font-size:15pt; -fx-padding:5px");

        Label quantity = new Label(String.valueOf(cartEntry.getQuantity()));
        quantity.setStyle("-fx-padding:5px");

        Button plusButton = new Button("+");
        plusButton.setStyle("-fx-padding:5px;-fx-background-color: #fc0202;-fx-font-weight: bold;-fx-text-fill: white");
        plusButton.setUserData(cartEntry.getProduct_name());
        plusButton.setOnAction(e -> {
            String name = (String) ((Node) e.getSource()).getUserData();
            Products product = Make_Product(name);
            RocketShoppingCart.getInstance().addProduct(product);
            quantity.setText(String.valueOf(RocketShoppingCart.getInstance().getQuantity(name)));
            this.totalPriceLabel.setText(String.valueOf(RocketShoppingCart.getInstance().calculateTotal()));
        });

        Button minusButton = new Button("-");
        minusButton.setStyle("-fx-padding:5px;-fx-background-color: #fc0202;-fx-font-weight: bold;-fx-text-fill: white");
        minusButton.setUserData(cartEntry.getProduct_name());
        minusButton.setOnAction(e -> {
            String name = (String) ((Node) e.getSource()).getUserData();
            RocketShoppingCart.getInstance().removeProduct(name);
            quantity.setText(String.valueOf(RocketShoppingCart.getInstance().getQuantity(name)));
            this.totalPriceLabel.setText(String.valueOf(RocketShoppingCart.getInstance().calculateTotal()));
        });

        Label price = new Label(String.valueOf("Tk. " + cartEntry.getPrice()));
        price.setStyle("-fx-padding:10px");

        layout.getChildren().addAll(imageView, productName, plusButton, quantity, minusButton, price);

        return layout;
    }
}