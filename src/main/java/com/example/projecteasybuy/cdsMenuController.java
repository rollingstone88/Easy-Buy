package com.example.projecteasybuy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class cdsMenuController<JFXHamburger, JFXDrawer> {
    @FXML
    private BorderPane contentPane;
    @FXML
    public ImageView image;
    @FXML
    private AnchorPane ap;


    public void showHomeView() throws IOException {
        contentPane.setCenter(new CDSHomeView().getView());
        ap.setVisible(false);
    }

    public void showCartView() throws IOException {
        contentPane.setCenter(new cdsCartView().getView());
        ap.setVisible(false);
    }

    public void showMainHomeView() throws IOException {
        contentPane.setCenter(new HomeView().getView());

    }

    public void showDashView() throws IOException {
        contentPane.setCenter(new HomeView().getView());

    }

    public void dashBoard(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Project.class.getResource("up.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }



}