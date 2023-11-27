package com.example.axalittest;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;

public class MainPane extends VBox {

    private Text grayMat;
    private Text thresholdMat;
    private Text drawContoursMat;

    public MainPane() {
        super();
        buildMainAnchorPane(this);
    }

    public void buildMainAnchorPane(MainPane mainPane) {





//        VBox mainWindowAnchorPane = new VBox();
        mainPane.setFillWidth(true);
        mainPane.setPrefHeight(600);
        mainPane.setSpacing(10);
//        mainPane.getChildren().add(buildImageView());

        HBox hBox = new HBox(buildOriginalImageView(), buildGrayMatView());
        hBox.setSpacing(10);
        HBox hBox1 = new HBox(buildThresholdMat(), buildDrawContoursMat());
        hBox1.setSpacing(10);
        HBox sliderHbox = new HBox(buildSliders());
        sliderHbox.setSpacing(10);

        mainPane.getChildren().add(hBox);
        mainPane.getChildren().add(hBox1);
        mainPane.getChildren().add(sliderHbox);

    }

    private Node buildSliders() {
//        Slider slider = new Slider();
//        Button button = new Button();
//        TextField textField = new TextField();
        return new Text("Место для создания слайдеров и других переменных");
    }

    private  ImageView buildOriginalImageView() {
        File file = new File("AnalysisImage.png");
        Image image;
        try {
            image = new Image(file.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    private Text /*ImageView*/ buildGrayMatView() {
        // todo
        grayMat = new Text("Серая матрица");
        return grayMat;
    }

    private Text /*ImageView*/ buildThresholdMat() {
        // todo
        thresholdMat = new Text("Пороговая матрица");
        return thresholdMat;
    }

    private Text /*ImageView*/ buildDrawContoursMat() {
        // todo
        drawContoursMat = new Text("Матрица с отрисованными контурами");
        return drawContoursMat;
    }

    private void updateGrayMatView(Image image) {
        // todo
        grayMat.setText("NewGrayMat");
    }

    private void updateThresholdMat(Image image) {
        // todo
        thresholdMat.setText("NewThresholdMat");
    }

    private void updateDrawContoursMat(Image image) {
        // todo
        drawContoursMat.setText("NewDrawContoursMat");
    }




}
