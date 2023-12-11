package com.example.axalittest;

import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
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
                updateDrawContoursMatView(ThresholdMatImagePane.getThresholdMat(), OriginalImagePane.getOriginalImageMat());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        drawContoursMatImagePane.setPrefHeight(600);
        drawContoursMatImagePane.setSpacing(20);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(buildDrawContoursMatView());

        drawContoursMatImagePane.getChildren().add(updateButton);
        drawContoursMatImagePane.getChildren().add(drawContoursMatText);
        drawContoursMatImagePane.getChildren().add(buildDrawContoursMatView());
    }

    private ScrollPane buildDrawContoursMatView() {
        StackPane stackPane = new StackPane();
        drawContoursMatView = new ImageView(drawContoursMatImage);
        stackPane.getChildren().add(drawContoursMatView);

        // Создание ScrollPane и установка StackPane внутри него
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(stackPane);
        scrollPane.setPannable(true);

        // Масштабирование изображения в ScrollPane
        drawContoursMatView.preserveRatioProperty().set(true);
        drawContoursMatView.fitWidthProperty().bind(scrollPane.widthProperty());

        // Добавление масштабирования с помощью ScrollPane
        addZoomFunctionality(stackPane, scrollPane);

        return scrollPane;
    }


    private void updateDrawContoursMatView(Mat thresholdMat, Mat originalMat) throws IOException {

        try {
            // находим контуры на thresholdMat
            MatVector thresholdMatContours = Utils.findContoursOnMat(thresholdMat);

            Mat drawContoursMat = Utils.drawContoursOnMat(originalMat, thresholdMatContours, -1, Scalar.RED);

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

            // Обновление ImageView в ScrollPane
            drawContoursMatView.setImage(drawContoursMatImage);

            // Освобождение ресурсов
            drawContoursMat.release();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawContoursUpdate() throws IOException {
        updateDrawContoursMatView(ThresholdMatImagePane.getThresholdMat(), OriginalImagePane.getOriginalImageMat());
    }

    private void addZoomFunctionality(Pane content, ScrollPane scrollPane) {
        final double SCALE_DELTA = 1.1;
        final Scale scaleTransform = new Scale(1, 1);
        final StackPane stackPane = (StackPane) content;

        stackPane.getTransforms().add(scaleTransform);

        scrollPane.setOnScroll(scrollEvent -> {
            scrollEvent.consume();

            double scaleFactor = (scrollEvent.getDeltaY() > 0) ? SCALE_DELTA : 1 / SCALE_DELTA;
            scaleTransform.setX(scaleTransform.getX() * scaleFactor);
            scaleTransform.setY(scaleTransform.getY() * scaleFactor);

            // Ограничение масштабирования
            Bounds viewportBounds = scrollPane.getViewportBounds();
            double contentWidth = content.getBoundsInParent().getWidth();
            double contentHeight = content.getBoundsInParent().getHeight();

            double hValue = scrollPane.getHvalue();
            double vValue = scrollPane.getVvalue();

            scrollPane.layout();
            content.layout();

            scrollPane.setHvalue(Math.max(0, Math.min(hValue, (contentWidth - viewportBounds.getWidth()) / contentWidth)));
            scrollPane.setVvalue(Math.max(0, Math.min(vValue, (contentHeight - viewportBounds.getHeight()) / contentHeight)));
        });

        // Добавление перемещения
        final Delta dragDelta = new Delta();
        stackPane.setOnMousePressed(mouseEvent -> {
            dragDelta.x = mouseEvent.getX();
            dragDelta.y = mouseEvent.getY();
        });

        stackPane.setOnMouseDragged(mouseEvent -> {
            double deltaX = mouseEvent.getX() - dragDelta.x;
            double deltaY = mouseEvent.getY() - dragDelta.y;

            stackPane.setTranslateX(stackPane.getTranslateX() + deltaX);
            stackPane.setTranslateY(stackPane.getTranslateY() + deltaY);

            dragDelta.x = mouseEvent.getX();
            dragDelta.y = mouseEvent.getY();
        });
    }

    private static class Delta {
        double x, y;
    }

}
