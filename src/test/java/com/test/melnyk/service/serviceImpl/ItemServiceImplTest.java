package com.test.melnyk.service.serviceImpl;

import com.test.melnyk.dao.entitydao.ItemDao;
import com.test.melnyk.model.Product;
import com.test.melnyk.repository.Cart;
import com.test.melnyk.repository.LastFiveOrderInCart;
import com.test.melnyk.service.ItemService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.*;

import java.io.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceImplTest {

    private ItemService itemService;

    @Mock
    ItemDao itemDao;

    @Mock
    Cart cart;

    @Mock
    LastFiveOrderInCart lastFiveOrderInCart;

    @Before
    public void setUp() {
        itemDao = mock(ItemDao.class);
        itemService = new ItemServiceImpl(cart, itemDao, lastFiveOrderInCart);
    }

    @Test
    public void getAllProductsFromListTest() {
        List<Product> testListProducts = new ArrayList<>();
        when(itemDao.findAll()).thenReturn(testListProducts);
        itemService.getAllProducts();
        verify(itemDao).findAll();
    }

    @Test
    public void showCartTest() {
        Map<Product, Integer> testMap = new LinkedHashMap<>();
        when(itemService.showCart()).thenReturn(testMap);
        itemService.showCart();
        verify(cart, times(1)).showCart();
    }

    @Test
    public void showLastFiveProductsInCartTest() {
        Map<Product, Integer> testMap = new LinkedHashMap<>();
        when(itemService.showLastFiveProductsInCart()).thenReturn(testMap);
        itemService.showLastFiveProductsInCart();
        verify(lastFiveOrderInCart, times(1)).showLimitCart();
    }

    @Test
    public void addProductToList() {
        Product product = new Product(1, "test", 1);
        when(itemDao.create(product)).thenReturn(true);
        itemService.addProductToList(product);
        verify(itemDao, times(1)).create(product);
    }

    @Test
    public void addProductToCartTest() {
        Product product = new Product(1, "a", 1);
        itemDao.create(product);
        when(itemDao.select(anyInt())).thenReturn(product);
        doNothing().when(cart).addToCart(product);
        doNothing().when(lastFiveOrderInCart).addToLimitCart(product);
        itemService.addProductToCart(product.getId());
        verify(cart).addToCart(product);
        verify(itemDao, times(2)).select(1);
        verify(lastFiveOrderInCart).addToLimitCart(product);
    }

    @Test
    public void comparingFilesSize() throws IOException {
        byte[] arr = new byte[]{1, 2};
        File file = new File("container.dat");
        File gzFile = new File("container.gz");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(arr);
        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(gzFile));
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(out);
        gzipOutputStream.write(arr);
        gzipOutputStream.finish();
        gzipOutputStream.flush();
        gzipOutputStream.close();
        Assert.assertTrue(file.length() > gzFile.length());
    }

    @Test
    public void saveContainerTest() throws IOException, ClassNotFoundException {
        List<Product> testList = new ArrayList<>();
        Product product = new Product(1, "f", 1);
        testList.add(product);
        when(itemDao.findAll()).thenReturn(testList);
        String path = "test.txt";
        itemService.saveContainer(path);
        List<Product> deserializationList = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object productList = objectInputStream.readObject();
        deserializationList = (List<Product>) productList;
        assertThat(deserializationList, containsInAnyOrder(product));
    }

}