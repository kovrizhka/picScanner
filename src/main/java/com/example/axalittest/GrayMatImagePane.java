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

    private final Text grayMatText = new Text("Серая матрица");
    private Image grayMatImage;
    private static Mat grayMat;
    private ImageView grayMatView;

    public GrayMatImagePane() {
        super();
        buildGrayMatImagePane(this);
    }

    private void buildGrayMatImagePane(GrayMatImagePane grayMatImagePane) {
        Button updateButton = new Button("Получить/обновить изображение");
        updateButton.setOnAction(event -> {
            try {
                updateGrayMatView(OriginalImagePane.getOriginalImageMat());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        grayMatImagePane.setPrefHeight(600);
        grayMatImagePane.setSpacing(20);

        grayMatImagePane.getChildren().add(updateButton);
        grayMatImagePane.getChildren().add(grayMatText);
        grayMatImagePane.getChildren().add(buildGrayMatView());
    }

    private ImageView buildGrayMatView() {
        grayMatView = new ImageView(grayMatImage);
        grayMatView.setFitWidth(400);
        grayMatView.setPreserveRatio(true);
        return grayMatView;
    }


    private void updateGrayMatView(Mat srcMat) throws IOException {

        try {
            // Преобразование в серую матрицу
            grayMat = Utils.grayMat(srcMat);

            // сначала byte[] из матрицы
            byte[] matToByteArr = Utils.getByteArrayFromMat(grayMat, grayMat.channels());

            // затем конвертируем в BufferedImage
            BufferedImage bufferedImage = Utils.convertByteArrayToBufferedImage(
                    matToByteArr,
                    grayMat.channels(),
                    grayMat.cols(),
                    grayMat.rows()
            );

            // и создаем изображение
            grayMatImage = Utils.toFXImage(bufferedImage);

            // Обновление ImageView на пэйне
            grayMatView.setImage(grayMatImage);

//            // Освобождение ресурсов
//            grayMat.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Mat getGrayMat() {
        return grayMat;
    }
}
