package com.example.user.bulletfalls.Database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.example.user.bulletfalls.Database.DatabaseAdministrator;
import com.example.user.bulletfalls.ProfileActivity.Currency;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.LinkedList;
import java.util.List;

public class StockDao implements DAO<MutablePair<Currency,Integer>> {
    Context context;
    public StockDao(Context context)
    {
        this.context=context;
    }
    @Override
    public List<MutablePair<Currency, Integer>> getAll() {
        List<MutablePair<Currency,Integer>> list= new LinkedList<>();
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        Cursor cursor=da.getAll(null,"Stock");
        cursor.moveToFirst();

        if(cursor.getCount()!=0) {
            do {
                CurrencyDao cd = new CurrencyDao(context);
                int x = cursor.getInt(2);
                Currency currency = cd.getById(cursor.getInt(2));

                MutablePair<Currency, Integer> pair = new MutablePair<>(currency, cursor.getInt(1));
                list.add(pair);
            } while (cursor.moveToNext());
        }
        return list;
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

        int id= da.getId("Stock","currencyId",currencyId+"");
        da.update("Stock","id",id,content);

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
        CurrencyDao cd= new CurrencyDao(context);
        int id= cd.getId(currencyIntegerMutablePair.left.getName());
        ContentValues content= new ContentValues();
        content.put("amount",currencyIntegerMutablePair.right);
        content.put("currencyId",id);
        DatabaseAdministrator ad= new DatabaseAdministrator(context);
        ad.add(content,"Stock");

    }
}
