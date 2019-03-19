package com.example.user.bulletfalls.ProfileActivity;

import android.content.Context;

import com.example.user.bulletfalls.Database.DAO.CurrencyDao;
import com.example.user.bulletfalls.Database.DAO.CurrencyEnum;
import com.fasterxml.jackson.annotation.JsonTypeName;


public class Currency {
    String name;
    int resource;

    public Currency()
    {

    }

    public Currency(CurrencyEnum currencyEnum, int resource) {
        this(currencyEnum);
        this.resource = resource;
    }
    public Currency(CurrencyEnum currencyEnum)
    {
        this.name=currencyEnum.toString();
    }

    public Currency (CurrencyEnum currencyEnum,Context context)
    {
        this(currencyEnum);
        CurrencyDao currencyDao= new CurrencyDao(context);
        this.resource=currencyDao.getCurrency(this.name).getResource();

    }
    public Currency(String name, int resource) {
        this(name);
        this.resource = resource;
    }
    public Currency(String name)
    {
        this.name=name;
    }

    public Currency (String name,Context context)
    {
        this(name);
        CurrencyDao currencyDao= new CurrencyDao(context);
        this.resource=currencyDao.getCurrency(this.name).getResource();

    }

    public void loadImageRessource(Context context)
    {
        CurrencyDao currencyDao= new CurrencyDao(context);
        this.resource=currencyDao.getCurrency(this.name).getResource();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

}
