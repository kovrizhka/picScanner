package com.example.axalittest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        stage.initStyle(StageStyle.DECORATED);
        stage.setWidth(1280);
        stage.setHeight(1024);

        Scene scene = new Scene(new MainPane());

        stage.setTitle("AxalitTest");
        stage.setScene(scene);
        stage.show();
    }
}