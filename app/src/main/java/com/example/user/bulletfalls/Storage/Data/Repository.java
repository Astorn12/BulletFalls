package com.example.user.bulletfalls.Storage.Data;

import android.content.Context;
import android.database.Cursor;

import com.example.user.bulletfalls.Profile.Level;
import com.example.user.bulletfalls.Storage.DatabaseAdministrator;

import java.util.LinkedList;
import java.util.List;

public abstract class Repository<T> {
    protected Context context;
    public Repository(Context context){
        this.context=context;

    }
    public  List<T> getAll(){
        List<T> tets= new LinkedList<>();
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        Cursor cursor = da.getAll(null,getTableName());
        cursor.moveToFirst();
        if(cursor.getCount()>0)
        {
            do {

                tets.add(createFromCursor(cursor));

            }while(cursor.moveToNext());
        }
        return tets;
    }
    public abstract T getById(int n);
    public abstract void update(T t);
    public abstract void remove(T t);
    public abstract void add(T t);
    public  abstract T createFromCursor(Cursor cursor);

    public abstract String getTableName();

}
