package com.example.axalittest;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MainPane extends HBox {

    public MainPane() {
        super();
        buildMainPane(this);
    }

    public void buildMainPane(MainPane mainPane) {


        OriginalImagePane originalImagePane = new OriginalImagePane();
        GrayMatImagePane grayMatImagePane = new GrayMatImagePane();
        ThresholdMatImagePane thresholdMatImagePane = new ThresholdMatImagePane();
        DrawContoursMatImagePane drawContoursMatImagePane = new DrawContoursMatImagePane();


        mainPane.getChildren().add(originalImagePane);
        mainPane.getChildren().add(grayMatImagePane);
        mainPane.getChildren().add(thresholdMatImagePane);
        mainPane.getChildren().add(drawContoursMatImagePane);
//        mainPane.getChildren().add(buildSliders());



        // отрисовка границ
        for(Node pane: mainPane.getChildren()) {
            setBorderForPane((Pane) pane);
        }
    }

    private Node buildSliders() {
//        Slider slider = new Slider();
//        Button button = new Button();
//        TextField textField = new TextField();
        return new Text("Место для создания слайдеров и других переменных");
    }

    private void setBorderForPane(Pane pane) {
        pane.setStyle("-fx-border-color: black; -fx-border-width: 1;");
    }

}
