package com.example.axalittest;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.File;

public class OriginalImagePane extends HBox {
    private Image originalImage;

    public OriginalImagePane() {
        super();
        setOriginalImage();
        buildOriginalImagePane(this);
    }

    public void setOriginalImage() {
        File file = new File("AnalysisImage.png");
        try {
            this.originalImage = new Image(file.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void buildOriginalImagePane(OriginalImagePane originalImagePane) {

        originalImagePane.setPrefHeight(600);
        originalImagePane.setSpacing(20);
        originalImagePane.getChildren().add(buildOriginalImageView());

    }

    private ImageView buildOriginalImageView() {

        ImageView imageView = new ImageView(originalImage);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);
        return imageView;
    }


}
