package com.example.axalittest;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;


public class MainPane extends GridPane {

    OriginalImagePane originalImagePane;
    GrayMatImagePane grayMatImagePane;
    ThresholdMatImagePane thresholdMatImagePane;
    DrawContoursMatImagePane drawContoursMatImagePane;

    public MainPane() {
        super();
        buildMainPane(this);
    }

    public void buildMainPane(MainPane mainPane) {

        originalImagePane = new OriginalImagePane();
        grayMatImagePane = new GrayMatImagePane();
        thresholdMatImagePane = new ThresholdMatImagePane();
        drawContoursMatImagePane = new DrawContoursMatImagePane();


        // создаем дочерние панели
        mainPane.add(originalImagePane, 0, 0);
        mainPane.add(grayMatImagePane, 1, 0);
        mainPane.add(thresholdMatImagePane, 0, 1);
        mainPane.add(drawContoursMatImagePane, 1, 1);
        mainPane.add(buildSliders(), 2, 0);


        // отрисовка границ
        for(Node pane: mainPane.getChildren()) {
            setBorderForPane((Pane) pane);
        }
    }

    private Node buildSliders() {
        // кнопки и слайдер
        Text textField = new Text("Панель управления");

        Button refreshButton = new Button("Получить/обновить изображение с отрисованными контурами");
        refreshButton.setOnAction(event -> {
            try {
                for(int i = 0; i < 5; i++) {
                    grayMatImagePane.grayMatUpdate();
                    thresholdMatImagePane.thresholdUpdate();
                    drawContoursMatImagePane.drawContoursUpdate();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        // панель для кнопок и слайдера
        GridPane controlPanel = new GridPane();
        controlPanel.add(textField, 0, 0);
        controlPanel.add(refreshButton, 0, 1);


        return controlPanel;
    }

    private void setBorderForPane(Pane pane) {
        pane.setStyle("-fx-border-color: black; -fx-border-width: 1;");
    }

}
