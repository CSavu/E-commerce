package com.example.mm.controllers;

import com.example.mm.models.Product;
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

import static com.example.mm.models.Product.getCurrentProductId;
import static com.example.mm.services.ProductsService.addProductToCart;
import static com.example.mm.services.ProductsService.getProductById;
import static com.example.mm.utils.ControllerUtils.renderView;

public class ProductController implements Initializable{
    @FXML
    private Label productTitle;

    @FXML
    private Label productPrice;

    @FXML
    private Label feedbackLabel;

    public ProductController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initialized product");
        feedbackLabel.setVisible(false);
        try{
            Product currentProduct = getProductById(getCurrentProductId());
            System.out.println("this is " + currentProduct.getPrice());
            productPrice.setText(currentProduct.getPrice().toString());
            productTitle.setText(currentProduct.getName());
            System.out.println(currentProduct.getName());
        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void onAddToCart(ActionEvent actionEvent) throws SQLException {
        boolean addToCartResult = addProductToCart(getCurrentProductId());
        if (addToCartResult){
            System.out.println("added");
            feedbackLabel.setVisible(true);
        }
    }
    public void goBackToShop(ActionEvent actionEvent) throws IOException {
        Stage currentStage1 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        renderView(currentStage1, "shop-view.fxml");
    }
    public void goToCartProduct(ActionEvent actionEvent) throws IOException{
        Stage currentStage2=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        renderView(currentStage2,"cart-view.fxml");
    }

}
