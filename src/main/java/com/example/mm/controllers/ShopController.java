package com.example.mm.controllers;

import com.example.mm.HelloApplication;
import com.example.mm.models.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static com.example.mm.models.Product.setCurrentProductId;
import static com.example.mm.services.ProductsService.getAllProducts;
import static com.example.mm.services.ProductsService.getAllProductsByPriceAscending;
import static com.example.mm.utils.ControllerUtils.renderView;

public class ShopController implements Initializable {
    private ObservableList<Product> products;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, Double> priceColumn;


    public ShopController() {

    }

    public void onCartButtonView(ActionEvent actionEvent) {
        Stage currentStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        renderView(currentStage, "cart-view.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            products = FXCollections.observableArrayList(getAllProducts());
            productTable.setRowFactory(tv -> {
                TableRow<Product> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                        Product rowData = row.getItem();
                        // redirect user to Product view with the row data (product_id)
                        setCurrentProductId(rowData.getId());
                        Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        renderView(currentStage, "product-view.fxml");
                    }
                });
                return row ;
            });
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            productTable.getItems().addAll(products);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
