package com.test.melnyk.service;

import com.test.melnyk.model.Product;

import java.util.List;
import java.util.Map;

public interface ItemService {

    List<Product> getAllProducts();

    boolean addProductToCart(int productId);

    void removeProductFromProductList(int productId);

    Map<Product, Integer> showCart();

    Map<Product, Integer> showLastFiveProductsInCart();

    boolean addProductToList(Product product);

    boolean saveContainer(String pathname);

    int getSize();

    Product getProductDetails(int id);
}
