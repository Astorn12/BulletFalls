package com.example.user.bulletfalls.Sets;

import android.content.Context;

import java.util.List;

public interface ISet<T> {

    void load(Context context);

    void save(Context context);

    List<T> getAll(Context context);

    void clear();
}