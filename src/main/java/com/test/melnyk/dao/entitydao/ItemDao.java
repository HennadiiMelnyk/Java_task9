package com.test.melnyk.dao.entitydao;

import com.test.melnyk.dao.CrudDao;
import com.test.melnyk.model.Product;

import java.util.List;

public interface ItemDao extends CrudDao<Product> {
    List readFile(String path);
}
