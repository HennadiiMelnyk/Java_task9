package com.test.melnyk.dao.impl;

import com.test.melnyk.dao.entitydao.ItemDao;
import com.test.melnyk.dao.entitydao.impl.ItemDaoImpl;
import com.test.melnyk.model.Product;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;


public class ItemDaoImplTest {
    List<Product> productList;
    private ItemDao itemDao;
    private Product product;

    @Before
    public void setUp() {
        productList = new ArrayList<>();
        product = new Product(1, "a", 1);
        productList.add(product);
        productList.add(null);
        itemDao = new ItemDaoImpl(productList);
    }

    @Test
    public void shouldReturnSpecificProductById() {
        Assert.assertEquals(itemDao.select(0), product);
    }

    @Test
    public void shouldCreateProduct() {
        Product testProduct = new Product(2, "b", 11);
        itemDao.create(new Product(2, "b", 11));
        Assert.assertEquals(testProduct, productList.get(2));
    }

    @Test
    public void shouldDeleteProduct() {
        itemDao.delete(1);
        int listSize = 1;
        Assert.assertEquals(listSize, productList.size());
    }

    @Test
    public void shouldUpdateProduct() {
        Product testProduct = new Product(666, "cleaner", 55555);
        itemDao.update(testProduct, 0);
        Assert.assertEquals(testProduct, productList.get(0));
    }

    @Test
    public void shouldReturnListOfProduct() {
        itemDao.findAll();
        MatcherAssert.assertThat(itemDao.findAll(), containsInAnyOrder(product, null));
    }

    @Test
    public void readFile() {
        int expectedSize = 9;
        productList = itemDao.readFile("container.ser");
        Assert.assertEquals(expectedSize, productList.size());
    }

}