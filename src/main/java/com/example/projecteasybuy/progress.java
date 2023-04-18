package com.example.projecteasybuy;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class progress implements Initializable {

    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private ProgressIndicator progressIndicator1;
    @FXML
    private ProgressIndicator progressIndicator2;
    @FXML
    private Text loadingText;

    LoadingScreen loadingScreen;
    LoadingScreen loadingScreen1;
    LoadingScreen loadingScreen2;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadingScreen = new LoadingScreen(progressIndicator, loadingText);
        loadingScreen1 = new LoadingScreen(progressIndicator1, loadingText);
        loadingScreen2 = new LoadingScreen(progressIndicator2, loadingText);
    }

    @FXML
    void ok(ActionEvent event) {
        Thread thread = new Thread(loadingScreen);
        Thread thread1 = new Thread(loadingScreen1);
        Thread thread2 = new Thread(loadingScreen2);
        thread.setDaemon(true);
        thread.start();
        try{
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
     //   thread1.sleep(500);
        thread1.setDaemon(true);
        thread1.start();
        try{
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        thread2.setDaemon(true);
        thread2.start();
    }

   /* @FXML
    void restart(ActionEvent event) {
        progressIndicator.setProgress(0);
        loadingText.setText("Loading...");
    }*/


    //Loading screen runnable class
    public class LoadingScreen implements Runnable {

        ProgressIndicator progressIndicator;
        ProgressIndicator progressIndicator1;
        ProgressIndicator progressIndicator2;
        Text loadingText;

       public LoadingScreen(ProgressIndicator progressIndicator, Text loadingText) {
            this.progressIndicator = progressIndicator;
            this.progressIndicator1=progressIndicator;
           this.progressIndicator2=progressIndicator;

           // this.progressIndicator1 = progressIndicator;
            this.loadingText = loadingText;
        }


        @Override
        public void run() {
            while(progressIndicator.getProgress() <= 1) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        progressIndicator.setProgress(progressIndicator.getProgress() + 0.1);
                    }
                });
                synchronized (this){
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }
            }
            //loadingText.setText("Items Prepared");
        }
        }
    @FXML
    public void switchToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Project.class.getResource("homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}




