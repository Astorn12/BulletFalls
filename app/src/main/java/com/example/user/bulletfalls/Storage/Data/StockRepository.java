package com.example.user.bulletfalls.Storage.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.user.bulletfalls.Storage.DatabaseAdministrator;
import com.example.user.bulletfalls.Profile.Currency;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.LinkedList;
import java.util.List;

public class StockRepository extends Repository<MutablePair<Currency, Integer>> {

    public StockRepository(Context context)
    {
        super(context);
    }


    @Override
    public MutablePair<Currency, Integer> getById(int n) {
        return null;
    }

    @Override
    public void update(MutablePair<Currency, Integer> pair) {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        ContentValues content= new ContentValues();
        content.put("amount",pair.getRight());

        int currencyId= da.getId("Currencies","name",pair.getLeft().getName());

        int id= da.getId(getTableName(),"currencyId",currencyId+"");
        da.update(getTableName(),"id",id,content);

    }

    public void update(List<MutablePair<Currency, Integer> > currencyIntegerMutablePairList) {
        for(MutablePair<Currency, Integer> pair: currencyIntegerMutablePairList)
        {
            update(pair);
        }
    }

    @Override
    public void remove(MutablePair<Currency, Integer> currencyIntegerMutablePair) {

    }

    @Override
    public void add(MutablePair<Currency, Integer> currencyIntegerMutablePair) {
        CurrencyRepository cd= new CurrencyRepository(context);
        int id= cd.getId(currencyIntegerMutablePair.left.getName());
        ContentValues content= new ContentValues();
        content.put("amount",currencyIntegerMutablePair.right);
        content.put("currencyId",id);
        DatabaseAdministrator ad= new DatabaseAdministrator(context);
        ad.add(content,getTableName());

    }

    @Override
    public MutablePair<Currency, Integer> createFromCursor(Cursor cursor) {
        CurrencyRepository cd = new CurrencyRepository(context);
        Currency currency = cd.getById(cursor.getInt(2));
        return new MutablePair<>(currency, cursor.getInt(1));
    }

    @Override
    public String getTableName() {
        return "Stock";
    }
}
