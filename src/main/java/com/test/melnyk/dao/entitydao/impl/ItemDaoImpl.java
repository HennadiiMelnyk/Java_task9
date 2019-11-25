package com.test.melnyk.dao.entitydao.impl;

import com.test.melnyk.dao.entitydao.ItemDao;
import com.test.melnyk.model.Product;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    private List<Product> productList;


    public List<Product> getProductList() {
        return productList;
    }

    public ItemDaoImpl() {
        productList = new ArrayList<>();
    }

    public ItemDaoImpl(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public Product select(int id) {
        return productList.get(id);
    }

    @Override
    public boolean create(Product product) {
        return productList.add(product);
    }

    @Override
    public void delete(int id) {
        productList.remove(id);
    }

    @Override
    public Product update(Product product, int id) {
        return productList.set(id, product);
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public List readFile(String path) {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object productList = objectInputStream.readObject();
            this.productList = (List<Product>) productList;
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("Cannot read file");
        }
        return productList;
    }
}
