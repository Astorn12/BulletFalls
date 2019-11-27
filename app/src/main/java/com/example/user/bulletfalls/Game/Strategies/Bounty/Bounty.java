package com.example.user.bulletfalls.Game.Strategies.Bounty;

import com.example.user.bulletfalls.Game.Management.ArchivContainer;
import com.example.user.bulletfalls.Profile.Currency;
import com.example.user.bulletfalls.Storage.Data.CurrencyEnum;
import com.example.user.bulletfalls.Storage.Data.CurrencyRepository;

public class Bounty {
    int exp;

    ArchivContainer<Currency> itemsList;
    public Bounty()
    {
        this.itemsList=new ArchivContainer<>();
        this.exp=0;
    }

    public void addExp(int exp)
    {
        this.exp+=exp;
    }
    public void addMoney(int n)
    {
        CurrencyRepository currencyRepository=new CurrencyRepository();
        itemsList.add(currencyRepository.getCurrency(CurrencyEnum.MysteryCoin.toString()),n);
    }


    public void addItems(Currency c, int n)
    {
        this.itemsList.add(c,n);
    }

    public void addItem(Currency c)
    {
        this.itemsList.add(c);
    }

    public int getExp() {
        return exp;
    }


    public int getMoney(){
        for (int i=0;i<this.itemsList.size();i++)
        {
            if(this.itemsList.getList().get(i).getLeft().getName().equals(CurrencyEnum.MysteryCoin.toString()))
            {
                return this.itemsList.getList().get(i).right;
            }
        }return 0;
    }

    public ArchivContainer<Currency> getItemsList() {
        return itemsList;
    }
}
