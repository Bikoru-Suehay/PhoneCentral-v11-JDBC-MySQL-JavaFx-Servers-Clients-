package com.biksue.phonecentral_jdbc_sockets.model.DAO;

import com.biksue.phonecentral_jdbc_sockets.model.exceptions.DAOException;

import java.util.ArrayList;

public interface DAO<T, K> {
    void insert(T t) throws DAOException;

    void modify(T t) throws DAOException;

    void delete(T t) throws DAOException;

    ArrayList<T> getAll() throws DAOException;

    T get(K id) throws DAOException;
}
