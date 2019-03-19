package com.example.user.bulletfalls.Database.DAO;

import java.util.List;

public interface DAO<T> {
    public List<T> getAll();
    public T getById(int n);
    public void update(T t);
    public void remove(T t);
    public void add(T t);

}
