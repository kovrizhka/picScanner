package com.example.axalittest;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class GrayMatPane extends HBox {
    private Text grayMat;


    public GrayMatPane() {
        super();
        buildGrayMatPane(this);
    }

    public void buildGrayMatPane(GrayMatPane grayMatPane) {

        grayMatPane.setPrefHeight(600);
        grayMatPane.setSpacing(20);
        grayMatPane.getChildren().add(buildGrayMatView());

    }


        private Text /*ImageView*/ buildGrayMatView() {
        // todo
        grayMat = new Text("Серая матрица");
        return grayMat;
    }

}
