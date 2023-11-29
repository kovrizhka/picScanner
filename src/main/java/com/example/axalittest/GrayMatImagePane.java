package com.example.axalittest;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.bytedeco.opencv.opencv_core.Mat;
import javafx.scene.control.Button;


import java.awt.image.BufferedImage;
import java.io.IOException;

public class GrayMatImagePane extends VBox {

    private Text grayMatText = new Text("Серая матрица");
    private Mat grayMat;

    private ImageView grayMatView;

    public GrayMatImagePane() {
        super();
        buildGrayMatImagePane();
    }

    private void buildGrayMatImagePane() {
        Button updateButton = new Button("Получить/обновить изображение");
        updateButton.setOnAction(event -> {
            try {
                updateGrayMatView(OriginalImagePane.originalImageMat);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        setPrefHeight(600);
        setSpacing(20);

        // Используем один ImageView для представления серого изображения
        grayMatView = new ImageView();
        getChildren().add(grayMatView);

        getChildren().add(updateButton);
    }

    private void updateGrayMatView(Mat srcMat) throws IOException {

        try {

            // Преобразование в серую матрицу
            Mat newGrayMat = Utils.grayMat(srcMat);

            ////// Конвертация серой матрицы в объект Image

            // сначала byte[] из матрицы
            byte[] matToByteArr = Utils.getByteArrayFromMat(newGrayMat, newGrayMat.channels());

            // затем конвертируем в BufferedImage
            BufferedImage bufferedImage = Utils.convertByteArrayToBufferedImage(matToByteArr,
                    newGrayMat.channels(),
                    newGrayMat.cols(),
                    newGrayMat.rows());

            // и создаем изображение
            Image grayMatImage = Utils.toFXImage(bufferedImage);

            grayMat = newGrayMat;

            // Обновление ImageView на пэйне
            grayMatView.setImage(grayMatImage);

            // Освобождение ресурсов
            newGrayMat.release();
            grayMat.release();
            buildGrayMatImagePane();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
