module org.example.steganofraphy {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires static lombok;

    opens org.example.steganofraphy to javafx.fxml;
    exports org.example.steganofraphy;
    exports org.example.steganofraphy.model;
    opens org.example.steganofraphy.model to javafx.fxml;
    exports org.example.steganofraphy.controller;
    opens org.example.steganofraphy.controller to javafx.fxml;
    exports org.example.steganofraphy.view;
    opens org.example.steganofraphy.view to javafx.fxml;
}