package com.example.mm.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.mm.services.UserService.*;
import static com.example.mm.utils.ControllerUtils.renderView;

public class CheckoutController {

    public void onPayButtonClick(ActionEvent actionEvent) throws SQLException {
        boolean newInvoiceResult = buildInvoiceForUser("Dummy", "Dummy", "Dummy", "Dummy");
        if (newInvoiceResult) {
            boolean newCartResult = buildNewCartForUser();
            if (newCartResult) {
                System.out.println(getCurrentCartIdForUser());
                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                renderView(currentStage, "feedback-view.fxml");
            }
        }
    }
}
