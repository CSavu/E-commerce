package com.example.mm.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

import static com.example.mm.services.UserService.buildUser;
import static com.example.mm.services.UserStateService.setCurrentUser;
import static com.example.mm.utils.ControllerUtils.renderView;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    public void onLoginButtonClick(ActionEvent actionEvent) throws SQLException {
        boolean loginResult = setCurrentUser(usernameField.getText(), passwordField.getText());
        if (loginResult == true){
            Stage currentStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            renderView(currentStage, "shop-view.fxml");
        } else {
            // return negative feedback to user
            System.out.println("Wrong username or password");
        }
    }

    public void onSignUpButtonClick(ActionEvent actionEvent) throws SQLException {
        boolean buildResult = buildUser(usernameField.getText(), passwordField.getText());
        if (buildResult){
            boolean loginResult = setCurrentUser(usernameField.getText(), passwordField.getText());
            if (loginResult == true) {
                Stage currentStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                renderView(currentStage, "shop-view.fxml");
            } else {
                // return negative feedback to user
            }
        }
    }
}