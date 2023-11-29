package com.example.axalittest;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.bytedeco.opencv.opencv_core.Mat;

import java.io.File;

public class OriginalImagePane extends VBox {
    public static Image originalImage;

    public static Mat originalImageMat;


    public OriginalImagePane() {
        super();
        updateOriginalImage();
        buildOriginalImagePane(this);
    }

    public void buildOriginalImagePane(OriginalImagePane originalImagePane) {
        originalImagePane.setPrefHeight(600);
        originalImagePane.setSpacing(20);
        originalImagePane.getChildren().add(new Text("Оригинальное изображение"));
        originalImagePane.getChildren().add(buildOriginalImageView());
    }

    private ImageView buildOriginalImageView() {
        ImageView imageView = new ImageView(originalImage);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    public void updateOriginalImage() {
        File file = new File("AnalysisImage.png");
        try {
            originalImage = new Image(file.toString());
            originalImageMat = Utils.getMatFromPath("AnalysisImage.png");
            System.out.println(originalImageMat);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
