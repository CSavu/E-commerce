package com.example.mm.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.mm.services.ProductsService.getProductsForCurrentUser;
import static com.example.mm.services.UserService.getCurrentCartIdForCurrentUser;
import static com.example.mm.services.UserStateService.logoutUser;
import static com.example.mm.utils.ControllerUtils.renderView;

public class CartController implements Initializable {

    @FXML
    private Label cartNegativeFeedback;

    public CartController(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cartNegativeFeedback.setVisible(false);
        try {
            System.out.println(getCurrentCartIdForCurrentUser());
            System.out.println(getProductsForCurrentUser());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void onOrderButtonClick(ActionEvent actionEvent) throws SQLException {
        if (!getProductsForCurrentUser().isEmpty()){
            System.out.println(getProductsForCurrentUser().get(0).getId());
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            renderView(currentStage, "checkout-view.fxml");
        } else {
            cartNegativeFeedback.setVisible(true);
        }
    }

    public void goBackToShopCart(ActionEvent actionEvent) throws IOException {
        Stage currentStage2 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        renderView(currentStage2, "shop-view.fxml");
    }

    public void goToLoginCart(ActionEvent actionEvent) throws IOException {
        boolean logoutResult = logoutUser();
        if (logoutResult){
            Stage currentStage3 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            renderView(currentStage3, "login-view.fxml");
        }
    }
}
