package com.example.axalittest;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.bytedeco.opencv.opencv_core.Mat;
import javafx.scene.control.Button;


import java.io.IOException;

public class GrayMatImagePane extends VBox {

    private ImageView grayMatView;

    public GrayMatImagePane() {
        super();
        buildGrayMatImagePane();
    }

    private void buildGrayMatImagePane() {
        Button updateButton = new Button("Получить/обновить изображение");
        updateButton.setOnAction(event -> {
            try {
                updateGrayMatView(OriginalImagePane.originalImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        setPrefHeight(600);
        setSpacing(20);
        getChildren().add(new Text("Серое изображение"));

        // Используем один ImageView для представления серого изображения
        grayMatView = new ImageView();
        getChildren().add(grayMatView);

        getChildren().add(updateButton);
    }

    private void updateGrayMatView(Image image) throws IOException {
        byte[] imageBytes = ImageConverter.getImageBytes(image);

        try {
            Mat originalMat = Utils.getMatFromByteArray(imageBytes, (int) image.getWidth(), (int) image.getHeight());

            // Преобразование в серую матрицу
            Mat grayMat = Utils.grayMat(originalMat);

            // Конвертация серой матрицы в объект Image
            Image grayMatImage = Utils.toFXImage(Utils.convertByteArrayToBufferedImage(
                    Utils.getByteArrayFromMat(grayMat), grayMat.channels(), grayMat.cols(), grayMat.rows()));

            // Обновление ImageView на пэйне
            grayMatView.setImage(grayMatImage);

            // Освобождение ресурсов
            originalMat.release();
            grayMat.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
