package com.example.projecteasybuy;

// import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import com.jfoenix.controls.JFXDrawer;
//import com.jfoenix.controls.JFXHamburger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class rocketMenuController {
    @FXML
    private BorderPane contentPane;
    @FXML
    public ImageView image;
  /*  @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;*/
  @FXML
  private AnchorPane ap;


    public void showHomeView() throws IOException {
        contentPane.setCenter(new RocketHomeView().getView());
        ap.setVisible(false);
    }

    public void showCartView() throws IOException {
        contentPane.setCenter(new Rocketcartview().getView());
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

    public void initialize(URL url, ResourceBundle rb) {
       /* HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isOpened()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });*/

    }
}
