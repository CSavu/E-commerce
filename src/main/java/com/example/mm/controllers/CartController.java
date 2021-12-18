package com.example.mm.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.mm.services.UserService.getCartIdForUser;
import static com.example.mm.utils.ControllerUtils.renderView;

public class CartController {

    public CartController() throws SQLException {
        System.out.println(getCartIdForUser());
    }

    public void onOrderButtonClick(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        renderView(currentStage, "checkout-view.fxml");
    }
}
