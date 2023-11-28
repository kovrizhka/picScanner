package com.example.axalittest;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GrayMatImagePane extends VBox {
    private Text grayMatImage;


    public GrayMatImagePane() {
        super();
        buildGrayMatImagePane(this);
    }

    public void buildGrayMatImagePane(GrayMatImagePane grayMatPane) {

        grayMatPane.setPrefHeight(600);
        grayMatPane.setSpacing(20);
        grayMatPane.getChildren().add(buildGrayMatView());

    }

        private Text /*ImageView*/ buildGrayMatView() {
        // todo
        grayMatImage = new Text("Серая матрица");
        return grayMatImage;
    }

    private void updateGrayMatView(Image image) {
        // todo
        grayMatImage.setText("NewGrayMat");
    }

}
