package com.example.ecommerce.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.ecommerce.utils.ControllerUtils.renderView;

public class ShopController {

    public void renderCartView(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        renderView(currentStage, "cart-view.fxml");

    }
}
