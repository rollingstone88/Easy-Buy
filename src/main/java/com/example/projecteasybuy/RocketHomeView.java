package com.example.projecteasybuy;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class RocketHomeView {
    private Parent view;

    public RocketHomeView() throws IOException {
        URL url = new File("C:/Users/HP/projectEasyBuy/src/main/resources/com/example/projecteasybuy/rocket.fxml").toURI().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(url);
        this.view = root;
    }

    public Parent getView(){
        return this.view;
    }
}
