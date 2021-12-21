package com.example.mm.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.mm.services.UserService.*;
import static com.example.mm.utils.ControllerUtils.renderView;

public class CheckoutController {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneNumberField;

    public void onPayButtonClick(ActionEvent actionEvent) throws SQLException {
        boolean newInvoiceResult = buildInvoiceForCurrentUser(firstName.getText(), lastName.getText(), addressField.getText(), phoneNumberField.getText());
        if (newInvoiceResult) {
            boolean newCartResult = buildNewCartForCurrentUser();
            if (newCartResult) {
                System.out.println(getCurrentCartIdForCurrentUser());
                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                renderView(currentStage, "feedback-view.fxml");
            }
        }
    }
    public void goBackToCartCheckout(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        renderView(currentStage, "cart-view.fxml");
    }
    public void goBackToShopCheckout(ActionEvent actionEvent) throws IOException {
        Stage currentStage3 =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        renderView(currentStage3, "shop-view.fxml");
    }
}
