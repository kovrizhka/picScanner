package com.example.axalittest;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class MainPane extends Pane{

    public MainPane() {
        super();
        buildMainPane(this);
    }

    public void buildMainPane(MainPane mainPane) {

        setPanelSize(mainPane, 1280, 1024);

        OriginalImagePane originalImagePane = new OriginalImagePane();
        GrayMatImagePane grayMatImagePane = new GrayMatImagePane();
        DrawContoursMatImagePane drawContoursMatImagePane = new DrawContoursMatImagePane();
        ThresholdMatImagePane thresholdMatImagePane = new ThresholdMatImagePane();

        setPanelSize(originalImagePane, 500, 500);
        setPanelSize(grayMatImagePane, 500, 500);
        setPanelSize(drawContoursMatImagePane, 500, 500);
        setPanelSize(thresholdMatImagePane, 500, 500);

        StackPane.setAlignment(grayMatImagePane, javafx.geometry.Pos.CENTER);

        mainPane.getChildren().addAll(
                originalImagePane,
                grayMatImagePane,
                drawContoursMatImagePane,
                thresholdMatImagePane
        );



//        VBox mainWindowAnchorPane = new VBox();
//        mainPane.setFillWidth(true);
//        mainPane.setPrefHeight(600);
//        mainPane.setSpacing(20);
//
//        HBox sliderHbox = new HBox(buildSliders());
//        sliderHbox.setSpacing(10);
//
//        mainPane.getChildren().add(new OriginalImagePane());
//        mainPane.getChildren().add(new GrayMatImagePane());
//        mainPane.getChildren().add(new ThresholdMatImagePane());
//        mainPane.getChildren().add(new DrawContoursMatImagePane());
//        mainPane.getChildren().add(sliderHbox);
    }

    private Node buildSliders() {
//        Slider slider = new Slider();
//        Button button = new Button();
//        TextField textField = new TextField();
        return new Text("Место для создания слайдеров и других переменных");
    }

    private static void setPanelSize(Pane panel, double width, double height) {
        panel.setMinSize(width, height);
        panel.setMaxSize(width, height);
        panel.setPrefSize(width, height);
    }

}
