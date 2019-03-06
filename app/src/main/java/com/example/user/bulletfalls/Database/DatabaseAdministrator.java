package com.example.user.bulletfalls.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdministrator extends SQLiteOpenHelper {

    public DatabaseAdministrator(Context context) {
        super(context, "profileDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating of table currencies
        db.execSQL("create table Currencies(" +
                "currencyId integer primary key autoincrement," +
                "name text," +
                "resource integer);");
        //creating of  table stock
        db.execSQL("create table Stock(" +
                "id integer primary key autoincrement," +
                "amount integer," +
                "currencyId integer," +
                "foreign key (currencyId) references Currencies(currencyId) );");
        //creating of table profile
        db.execSQL("create table Profile(" +
                "name text," +
                "resource integer," +
                "level integer," +
                "stockId integer, " +
                "foreign key (stockId) references Stock(stockId));");
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
