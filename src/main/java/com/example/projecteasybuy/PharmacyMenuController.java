package com.example.projecteasybuy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PharmacyMenuController {
    @FXML
    private BorderPane contentPane;
    @FXML
    public ImageView image;
    @FXML
    private AnchorPane ap;

    public void showHomeView() throws IOException {
        contentPane.setCenter(new PharmacyHomeView().getView());
        ap.setVisible(false);
    }

    public void showCartView() throws IOException {
        contentPane.setCenter(new pharmacyCartView().getView());
        ap.setVisible(false);
    }

    public void showMainHomeView() throws IOException {
        contentPane.setCenter(new HomeView().getView());

    }

    public void dashBoard(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Project.class.getResource("up.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}