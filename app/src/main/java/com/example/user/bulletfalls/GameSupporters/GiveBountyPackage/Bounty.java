package com.example.user.bulletfalls.GameSupporters.GiveBountyPackage;

import com.example.user.bulletfalls.GameSupporters.MediumTasks.ArchivContainer;
import com.example.user.bulletfalls.ProfileActivity.Currency;

public class Bounty {
    int exp;
    int money;
    ArchivContainer<Currency> itemsList;
    public Bounty()
    {
        this.itemsList=new ArchivContainer<>();
        this.money=0;
        this.exp=0;
    }

    public void addExp(int exp)
    {
        this.exp+=exp;
    }
    public void addMoney(int n)
    {
        this.money+=n;
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

    public int getMoney() {
        return money;
    }

    public ArchivContainer<Currency> getItemsList() {
        return itemsList;
    }
}
