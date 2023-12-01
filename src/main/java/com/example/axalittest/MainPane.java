package com.example.axalittest;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainPane extends HBox {

    public MainPane() {
        super();
        buildMainPane(this);
    }

    public void buildMainPane(MainPane mainPane) {

        // создаем дочерние панели
        mainPane.getChildren().add(new OriginalImagePane());
        mainPane.getChildren().add(new GrayMatImagePane());
        mainPane.getChildren().add(new ThresholdMatImagePane());
        mainPane.getChildren().add(new DrawContoursMatImagePane());
        mainPane.getChildren().add(buildSliders());

        // отрисовка границ
        for(Node pane: mainPane.getChildren()) {
            setBorderForPane((Pane) pane);
        }
    }

    private Node buildSliders() {
        TextField textField = new TextField("Панель управления");
        Slider slider = new Slider();
        Button button = new Button("Увеличить четкость контура");

        VBox controlPanel = new VBox();

        controlPanel.getChildren().add(textField);
        controlPanel.getChildren().add(slider);
        controlPanel.getChildren().add(button);

        return controlPanel;
    }

    private void setBorderForPane(Pane pane) {
        pane.setStyle("-fx-border-color: black; -fx-border-width: 1;");
    }

}
