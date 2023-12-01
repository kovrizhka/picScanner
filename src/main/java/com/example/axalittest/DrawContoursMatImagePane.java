package com.example.axalittest;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Scalar;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrawContoursMatImagePane extends VBox {
    private final Text drawContoursMatText = new Text("Матрица с отрисованными контурами");
    private Image drawContoursMatImage;
    private ImageView drawContoursMatView;

    public DrawContoursMatImagePane() {
        super();
        buildDrawContoursMatImagePane(this);
    }

    private void buildDrawContoursMatImagePane(DrawContoursMatImagePane drawContoursMatImagePane) {
        Button updateButton = new Button("Получить/обновить изображение");
        updateButton.setOnAction(event -> {
            try {
                updateDrawContoursMatView(ThresholdMatImagePane.getThresholdMat(), GrayMatImagePane.getGrayMat());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        drawContoursMatImagePane.setPrefHeight(600);
        drawContoursMatImagePane.setSpacing(20);

        drawContoursMatImagePane.getChildren().add(updateButton);
        drawContoursMatImagePane.getChildren().add(drawContoursMatText);
        drawContoursMatImagePane.getChildren().add(buildDrawContoursMatView());
    }

    private ImageView buildDrawContoursMatView() {
        drawContoursMatView = new ImageView(drawContoursMatImage);
        drawContoursMatView.setFitWidth(400);
        drawContoursMatView.setPreserveRatio(true);
        return drawContoursMatView;
    }


    private void updateDrawContoursMatView(Mat thresholdMat, Mat grayMat) throws IOException {

        try {
            // находим контуры на thresholdMat
            MatVector thresholdMatContours = Utils.findContoursOnMat(thresholdMat);

            Mat drawContoursMat = Utils.drawContoursOnMat(grayMat, thresholdMatContours, -1, Scalar.BLUE);

            // сначала byte[] из матрицы
            byte[] matToByteArr = Utils.getByteArrayFromMat(drawContoursMat, drawContoursMat.channels());

            // затем конвертируем в BufferedImage
            BufferedImage bufferedImage = Utils.convertByteArrayToBufferedImage(
                    matToByteArr,
                    drawContoursMat.channels(),
                    drawContoursMat.cols(),
                    drawContoursMat.rows()
            );

            // и создаем изображение
            drawContoursMatImage = Utils.toFXImage(bufferedImage);

            // Обновление ImageView на пэйне
            drawContoursMatView.setImage(drawContoursMatImage);

            // Освобождение ресурсов
            drawContoursMat.release();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
