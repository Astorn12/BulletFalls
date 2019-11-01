package com.example.user.bulletfalls.Storage.Data;

import java.util.List;

public interface Repository<T> {
    public List<T> getAll();
    public T getById(int n);
    public void update(T t);
    public void remove(T t);
    public void add(T t);

}
