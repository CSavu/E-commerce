package com.example.mm.controllers;

import com.example.mm.models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.mm.services.ProductsService.*;
import static com.example.mm.services.UserService.getCurrentCartIdForCurrentUser;
import static com.example.mm.services.UserStateService.logoutUser;
import static com.example.mm.utils.ControllerUtils.renderView;

public class CartController implements Initializable {

    private ObservableList<Product> products;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, Double> priceColumn;

   // @FXML
   // private TableColumn<Product, Double> quantityColumn;
   @FXML
   private TableColumn<Product, Double> quantityColumn;


    @FXML
    private Label cartNegativeFeedback;

    @FXML
    private Label totalLabel;

    public CartController() {

    }

    @FXML
    private void onUpdateButtonClick() throws SQLException {
        //productTable.getItems().removeAll(products);
        //products = FXCollections.observableArrayList(getProductsForCurrentUser());
       // quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
         //productTable.getItems().addAll(products);
        for (Product p : productTable.getItems()){
            boolean changeResult = changeProductQuantityInCart(p.getQuantity(), p.getId());
            if (changeResult) System.out.println("success");
            else System.out.println("failed to update");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cartNegativeFeedback.setVisible(false);
        try {
            List<Product> productList = getProductsForCurrentUser();
            productTable.setEditable(true);
            quantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            products = FXCollections.observableArrayList(getAllProducts());
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
           // quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            System.out.println(quantityColumn.getCellValueFactory());
            Double sum = 0.0;
            if (!productList.isEmpty()) {
                for (Product partialProduct : productList) {

                    Product completeProduct = getProductById(partialProduct.getId());
                    completeProduct.setQuantity(partialProduct.getQuantity());
                    System.out.println(completeProduct.getPrice() + " " + completeProduct.getQuantity() + " " + completeProduct.getName());
                    productTable.getItems().add(completeProduct);
                    sum += completeProduct.getPrice() * completeProduct.getQuantity();
                }
                totalLabel.setText(sum.toString());
                System.out.println(getCurrentCartIdForCurrentUser());
                System.out.println(getProductsForCurrentUser());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onOrderButtonClick(ActionEvent actionEvent) throws SQLException {
        if (!getProductsForCurrentUser().isEmpty()) {
            System.out.println(getProductsForCurrentUser().get(0).getId());
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            renderView(currentStage, "checkout-view.fxml");
        } else {
            cartNegativeFeedback.setVisible(true);
        }
    }

    public void goBackToShopCart(ActionEvent actionEvent) {
        Stage currentStage2 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        renderView(currentStage2, "shop-view.fxml");
    }

    public void goToLoginCart(ActionEvent actionEvent) {
        boolean logoutResult = logoutUser();
        if (logoutResult) {
            Stage currentStage3 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            renderView(currentStage3, "login-view.fxml");
        }
    }

    public void changeQuantity(TableColumn.CellEditEvent<Product, Double> productDoubleCellEditEvent) throws SQLException {
        //productTable.getItems().removeAll(products);
        products = FXCollections.observableArrayList(getProductsForCurrentUser());
       // productTable.getItems().addAll(products);
        Product product=productTable.getSelectionModel().getSelectedItem();
        product.setQuantity((int) Math.floor(productDoubleCellEditEvent.getNewValue()));


    }
}
