package com.test.melnyk.service.serviceImpl;

import com.test.melnyk.dao.entitydao.ItemDao;
import com.test.melnyk.exception.SaveContainerException;
import com.test.melnyk.model.Product;
import com.test.melnyk.repository.Cart;
import com.test.melnyk.repository.LastFiveOrderInCart;
import com.test.melnyk.service.ItemService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

public class ItemServiceImpl implements ItemService {

    private Cart cart;
    private ItemDao itemDao;
    private LastFiveOrderInCart lastFiveOrderInCart;


    public ItemServiceImpl(Cart cart, ItemDao itemDao, LastFiveOrderInCart lastFiveOrderInCart) {
        this.cart = cart;
        this.itemDao = itemDao;
        this.lastFiveOrderInCart = lastFiveOrderInCart;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = itemDao.findAll();
        return productList;
    }

    @Override
    public boolean addProductToCart(int productId) {
        cart.addToCart(itemDao.select(productId));
        lastFiveOrderInCart.addToLimitCart(itemDao.select(productId));
        return true;
    }

    @Override
    public void removeProductFromProductList(int productId) {
        itemDao.delete(productId);
    }

    @Override
    public Map<Product, Integer> showCart() {
        Map<Product, Integer> cartList = cart.showCart();
        return cartList;
    }

    @Override
    public Map<Product, Integer> showLastFiveProductsInCart() {
        Map<Product, Integer> limitCart = lastFiveOrderInCart.showLimitCart();
        return limitCart;
    }

    @Override
    public boolean addProductToList(Product product) {
        return itemDao.create(product);
    }

    @Override
    public boolean saveContainer(String pathName) {
        File file = new File(pathName);
        List<Product> list = itemDao.findAll();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            throw new SaveContainerException("fail to write data into file");
        }
        return true;
    }

    @Override
    public int getSize() {
        return itemDao.findAll().size();
    }

    @Override
    public Product getProductDetails(int id) {
        return itemDao.select(id);
    }
}
