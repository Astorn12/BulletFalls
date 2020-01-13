package com.example.user.bulletfalls.Storage.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.user.bulletfalls.Storage.DatabaseAdministrator;
import com.example.user.bulletfalls.Profile.Currency;

import java.util.LinkedList;
import java.util.List;

public class CurrencyRepository extends Repository<Currency> {

    public CurrencyRepository(Context context)
    {
        super(context);
    }




    @Override
    public Currency getById(int id) {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        Cursor cursor= da.getById(id,getTableName());
        cursor.moveToFirst();
        return new Currency(cursor.getString(1),cursor.getInt(2));
    }

    @Override
    public void update(Currency currency) {
        ContentValues content=new ContentValues();
        content.put("name",currency.getName());
        content.put("resource",currency.getResource());
    }

    @Override
    public void remove(Currency currency) {
            DatabaseAdministrator db= new DatabaseAdministrator(context);
            db.remove(getTableName(),"name",currency.getName());
    }

    @Override
    public void add(Currency currency) {
        DatabaseAdministrator db= new DatabaseAdministrator(context);
        ContentValues content= new ContentValues();
        content.put("name",currency.getName());
        content.put("resource",currency.getResource());
        db.add(content,getTableName());
    }

    @Override
    public Currency createFromCursor(Cursor cursor) {
        return new Currency(cursor.getString(1),cursor.getInt(2));
    }


    public Currency getCurrency(String name)
    {
        DatabaseAdministrator databaseAdministrator= new DatabaseAdministrator(context);
        Cursor c=databaseAdministrator.getCursor(getTableName(),"name",name);
        c.moveToFirst();
        return new Currency(c.getString(1),c.getInt(2));
    }

    public int getId(String name)
    {
        DatabaseAdministrator databaseAdministrator= new DatabaseAdministrator(context);
        Cursor c=databaseAdministrator.getCursor(getTableName(),"name",name);
        c.moveToFirst();
        return c.getInt(0);
    }

    @Override
    public String getTableName() {
        return "Currencies";
    }

}





