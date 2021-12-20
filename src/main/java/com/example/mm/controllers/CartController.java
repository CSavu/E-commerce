package com.example.mm.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
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
    public void goBackToShopCart(ActionEvent actionEvent) throws IOException {
        Stage currentStage2 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        renderView(currentStage2, "shop-view.fxml");
    }
    public void goToLoginCart(ActionEvent actionEvent) throws IOException   {
        Stage currentStage3=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        renderView(currentStage3,"login-view.fxml");
    }
}
