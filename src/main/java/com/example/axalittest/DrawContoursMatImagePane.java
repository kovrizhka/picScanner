package com.example.axalittest;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DrawContoursMatImagePane extends VBox {
    private Text drawContoursMatImage;

    public DrawContoursMatImagePane() {
        super();
        buildDrawContoursMatImagePane(this);
    }

    public void buildDrawContoursMatImagePane(DrawContoursMatImagePane drawContoursMatImagePane) {

        drawContoursMatImagePane.setPrefHeight(600);
        drawContoursMatImagePane.setSpacing(20);
        drawContoursMatImagePane.getChildren().add(buildDrawContoursMatView());

    }

    private Text /*ImageView*/ buildDrawContoursMatView() {
        // todo
        drawContoursMatImage = new Text("Матрица с отрисованными контурами");
        return drawContoursMatImage;
    }

    private void updateDrawContoursMatView(Image image) {
        // todo
        drawContoursMatImage.setText("NewDrawContoursMat");
    }

}
