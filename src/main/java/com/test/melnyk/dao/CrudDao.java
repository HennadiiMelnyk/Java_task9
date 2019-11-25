package com.test.melnyk.dao;

import java.util.List;

public interface CrudDao<T> {

    /**
     * select object with specified id
     *
     * @param id
     * @return
     */
    T select(int id);

    /**
     * create object
     *
     * @param t
     * @return
     */
    boolean create(T t);

    /**
     * delete object with specified id
     *
     * @param id
     */
    void delete(int id);

    /**
     * update object
     *
     * @param t
     * @return
     */
    T update(T t, int id);

    /**
     * get List parametrized Object
     *
     * @return
     */
    List<T> findAll();
}
