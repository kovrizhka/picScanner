package com.example.axalittest;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainPane extends VBox {

    public MainPane() {
        super();
        buildMainAnchorPane(this);
    }

    public void buildMainAnchorPane(MainPane mainPane) {



//        VBox mainWindowAnchorPane = new VBox();
        mainPane.setFillWidth(true);
        mainPane.setPrefHeight(600);
        mainPane.setSpacing(20);

        HBox sliderHbox = new HBox(buildSliders());
        sliderHbox.setSpacing(10);

        mainPane.getChildren().add(new OriginalImagePane());
        mainPane.getChildren().add(new GrayMatImagePane());
        mainPane.getChildren().add(new ThresholdMatImagePane());
        mainPane.getChildren().add(new DrawContoursMatImagePane());
        mainPane.getChildren().add(sliderHbox);
    }

    private Node buildSliders() {
//        Slider slider = new Slider();
//        Button button = new Button();
//        TextField textField = new TextField();
        return new Text("Место для создания слайдеров и других переменных");
    }
}
