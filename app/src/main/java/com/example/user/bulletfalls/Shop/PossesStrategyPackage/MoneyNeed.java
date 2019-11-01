package com.example.user.bulletfalls.Shop.PossesStrategyPackage;

import com.example.user.bulletfalls.Game.Management.ArchivCurrencyContainer;
import com.example.user.bulletfalls.Profile.Currency;
import com.example.user.bulletfalls.GlobalUsage.Par;

import java.util.List;

public class MoneyNeed {
    ArchivCurrencyContainer need;

    public MoneyNeed()
    {
        this.need= new ArchivCurrencyContainer();
    }


    public MoneyNeed(List<Par<Currency,Integer>>needs)
    {
        this();
        this.need=new ArchivCurrencyContainer(needs);
    }
    public MoneyNeed(ArchivCurrencyContainer a)
    {
        this.need=a;
    }


    public ArchivCurrencyContainer getNeed() {
        return need;
    }

}
