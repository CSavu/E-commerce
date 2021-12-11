module com.example.ecommerce {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires spring.jdbc;
    requires bcrypt;

    opens com.example.ecommerce to javafx.fxml;
    exports com.example.ecommerce;
    exports com.example.ecommerce.controllers;
    opens com.example.ecommerce.controllers to javafx.fxml;
    exports com.example.ecommerce.utils;
    opens com.example.ecommerce.utils to javafx.fxml;
    exports com.example.ecommerce.utils.database;
    opens com.example.ecommerce.utils.database to javafx.fxml;
}