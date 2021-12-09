package com.example.ecommerce;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onLoginButtonClick(ActionEvent actionEvent) {
    }

    public void onSignUpButtonClick(ActionEvent actionEvent) {
    }
}