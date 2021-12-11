package com.example.ecommerce.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.ecommerce.services.UserStateService.setCurrentUser;
import static com.example.ecommerce.utils.ControllerUtils.renderView;
import static com.example.ecommerce.utils.database.DatabaseQueries.getQuery;
import static com.example.ecommerce.utils.database.DatabaseQueriesNames.GET_ALL_PRODUCTS;
import static com.example.ecommerce.utils.database.DatabaseQueriesNames.GET_ALL_USERS;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    public void onLoginButtonClick(ActionEvent actionEvent) throws IOException {
        boolean result = setCurrentUser(usernameField.getText(), passwordField.getText());
        // render based on the result
        // if false - return negative feedback to user
        Stage currentStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        renderView(currentStage, "shop-view.fxml");
    }

    public void onSignUpButtonClick(ActionEvent actionEvent) {
        System.out.println(getQuery(GET_ALL_PRODUCTS));
    }
}