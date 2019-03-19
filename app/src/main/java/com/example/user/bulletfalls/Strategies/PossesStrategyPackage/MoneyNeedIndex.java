package com.example.user.bulletfalls.Strategies.PossesStrategyPackage;

public class MoneyNeedIndex {
    private static MoneyNeedIndex moneyNeedIndex=new MoneyNeedIndex();
    int index;
    boolean active;
    private MoneyNeedIndex()
    {
    index=0;
    active=false;
    }

    public static MoneyNeedIndex getInstance() {
        return moneyNeedIndex;
    }

    public void setIndex(int index)
    {
        this.index=index;
        this.active=true;
    }
    public void off()
    {
        this.active=false;
        //this.index=0;
    }

    public int getIndex() {
        return index;
    }
}
