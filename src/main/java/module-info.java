module com.example.axalittest {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.web;
    requires javafx.swing;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.bytedeco.opencv;
    requires java.desktop;

    opens com.example.axalittest to javafx.fxml;
    exports com.example.axalittest;
}