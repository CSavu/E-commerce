package com.example.mm.controllers;

import java.sql.SQLException;

import static com.example.mm.models.Product.getCurrentId;
import static com.example.mm.services.ProductsService.addProductToCart;

public class ProductController {
    public ProductController() throws SQLException {
        addProductToCart(getCurrentId());
    }
}
