package com.example.axalittest;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ThresholdMatImagePane extends VBox {
    private Text thresholdMatImage;

    public ThresholdMatImagePane() {
        super();
        buildThresholdMatImagePane(this);
    }

    public void buildThresholdMatImagePane(ThresholdMatImagePane thresholdMatImagePane) {

        thresholdMatImagePane.setPrefHeight(600);
        thresholdMatImagePane.setSpacing(20);
        thresholdMatImagePane.getChildren().add(buildThresholdMatView());

    }

    private Text /*ImageView*/ buildThresholdMatView() {
        // todo
        thresholdMatImage = new Text("Пороговая матрица");
        return thresholdMatImage;
    }

    private void updateThresholdMat(Image image) {
        // todo
        thresholdMatImage.setText("NewThresholdMat");
    }

}
