package com.example.axalittest;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.bytedeco.opencv.opencv_core.Mat;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.opencv.imgproc.Imgproc.THRESH_TRUNC;

public class ThresholdMatImagePane extends VBox {

    private final Text thresholdMatText = new Text("Пороговая матрица");
    private Image thresholdMatImage;
    private static Mat thresholdMat;
    private ImageView thresholdMatView;

    public ThresholdMatImagePane() {
        super();
        buildThresholdMatImagePane(this);
    }

    private void buildThresholdMatImagePane(ThresholdMatImagePane thresholdMatImagePane) {
        Button updateButton = new Button("Получить/обновить изображение");
        updateButton.setOnAction(event -> {
            try {
                updateThresholdMatView(GrayMatImagePane.getGrayMat());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        thresholdMatImagePane.setPrefHeight(600);
        thresholdMatImagePane.setSpacing(20);

        thresholdMatImagePane.getChildren().add(updateButton);
        thresholdMatImagePane.getChildren().add(thresholdMatText);
        thresholdMatImagePane.getChildren().add(buildThresholdMatView());
    }

    private ImageView buildThresholdMatView() {
        thresholdMatView = new ImageView(thresholdMatImage);
        thresholdMatView.setFitWidth(400);
        thresholdMatView.setPreserveRatio(true);
        return thresholdMatView;
    }


    private void updateThresholdMatView(Mat grayMat) throws IOException {

        try {
            thresholdMat = Utils.thresholdMat(grayMat, 40, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C);

            // сначала byte[] из матрицы
            byte[] matToByteArr = Utils.getByteArrayFromMat(thresholdMat, thresholdMat.channels());

            // затем конвертируем в BufferedImage
            BufferedImage bufferedImage = Utils.convertByteArrayToBufferedImage(
                    matToByteArr,
                    thresholdMat.channels(),
                    thresholdMat.cols(),
                    thresholdMat.rows()
            );

            // и создаем изображение
            thresholdMatImage = Utils.toFXImage(bufferedImage);

            // Обновление ImageView на пэйне
            thresholdMatView.setImage(thresholdMatImage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Mat getThresholdMat() {
        return thresholdMat;
    }
}
