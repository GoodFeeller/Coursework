package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import viewControl.LoadScreen;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        LoadScreen loadScreen=new LoadScreen();
        stage=loadScreen.getStage();
        stage.show();
    }
}
