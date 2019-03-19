package com.example.user.bulletfalls.Strategies.PossesStrategyPackage;

import com.example.user.bulletfalls.GameSupporters.MediumTasks.ArchivCurrencyContainer;
import com.example.user.bulletfalls.ProfileActivity.Currency;
import com.example.user.bulletfalls.Strategies.Par;

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
