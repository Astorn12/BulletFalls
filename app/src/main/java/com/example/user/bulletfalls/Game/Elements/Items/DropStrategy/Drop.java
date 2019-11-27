package com.example.user.bulletfalls.Game.Elements.Items.DropStrategy;

import android.content.Context;

import com.example.user.bulletfalls.Storage.Data.CurrencyRepository;
import com.example.user.bulletfalls.Profile.Currency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Drop {
    HashMap<Double,Currency> drops;

    public Drop(HashMap<Double, Currency> drops) {
        this.drops = drops;
    }
    public Drop(Context context, double... params)
    {
        this.drops= new HashMap<>();
                double key;
                Currency tmp=null;
                CurrencyRepository repo= new CurrencyRepository(context);

                boolean flag=true;
                for(double d : params)
                {
                    if(flag)
                    {
                        d+=0.5;
                        int n=(int)d;
                        tmp=repo.getById(n);
                        flag=false;
            }else{
                key=d;
                this.drops.put(d,tmp);
                flag=true;
            }
        }
    }
    public Currency drop()
    {
        Random random= new Random();
        List<Double> keys= new ArrayList<>(drops.keySet());
        double d= keys.get(random.nextInt(keys.size()));
        Currency currency= null;
        if(d>random.nextDouble())
        {
            currency=drops.get(d);
        }
        return currency;
    }
}
