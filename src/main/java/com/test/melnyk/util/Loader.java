package com.test.melnyk.util;

import com.test.melnyk.dao.entitydao.ItemDao;
import com.test.melnyk.model.Product;

import java.util.List;

public class Loader {

    private ItemDao itemDao;

    public Loader(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public List<Product> loadDataToList() {
        String path = "container.ser";
        List<Product> list = itemDao.readFile(path);
        return list;
    }
}
