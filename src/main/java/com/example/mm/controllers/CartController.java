package com.example.mm.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.sql.SQLException;

import static com.example.mm.services.ProductsService.getProductsForCurrentUser;
import static com.example.mm.services.UserService.getCurrentCartIdForCurrentUser;
import static com.example.mm.utils.ControllerUtils.renderView;

public class CartController {

    public CartController() throws SQLException {
        System.out.println(getCurrentCartIdForCurrentUser());
        System.out.println(getProductsForCurrentUser());
    }

    public void onOrderButtonClick(ActionEvent actionEvent) {
        Stage currentStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        renderView(currentStage, "checkout-view.fxml");
    }
}
