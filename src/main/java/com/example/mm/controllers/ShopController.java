package com.example.mm.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.mm.services.ProductsService.getAllProductsByPriceAscending;
import static com.example.mm.utils.ControllerUtils.renderView;

public class ShopController {

    public ShopController() throws SQLException {
        System.out.println(getAllProductsByPriceAscending());
    }

    public void onCartButtonView(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        renderView(currentStage, "cart-view.fxml");

    }
}
