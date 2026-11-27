package com.ejercicio3.dao;

import java.util.List;

public interface IDao<T, V> {
    boolean createRecord(T model);
    T readRecord(V id);
    List<T> readAllRecords();
    boolean updateRecord(T model);
    boolean deleteRecord(V id);
}
