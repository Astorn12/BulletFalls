package com.example.user.bulletfalls.GameSupporters.MediumTasks;

import com.example.user.bulletfalls.ProfileActivity.Currency;
import com.example.user.bulletfalls.Strategies.Par;


import java.util.LinkedList;
import java.util.List;

public class ArchivCurrencyContainer  {
    protected List<Par<Currency,Integer>>  list;

    public ArchivCurrencyContainer()
    {
        this.list= new LinkedList<>();
    }

    public ArchivCurrencyContainer(List<Par<Currency,Integer>>  list){
        this.list=list;
    }
    public void add(Currency t)
    {
        add(t,1);
    }

    public void add(Currency t, int n)
    {
        if(include(t)) {
            for(Par<Currency,Integer> ti:list)
            {
                if (t.equals(ti.getLeft()))
                {
                    ti.setRight(ti.getRight()+1);
                    break;
                }
            }
        }else{
            list.add(new Par<Currency, Integer>(t,n));
        }
    }

    private boolean include(Currency t)
    {
        for(Par<Currency,Integer> tl:list)
        {
            if(tl.getLeft().equals(t)){
                return true;
            }
        }

        return false;
    }

    public Par<Currency,Integer>get(int n)
    {
        return this.list.get(n);
    }

    public int size()
    {
        return this.list.size();
    }

    public List<Par<Currency,Integer>> cloneList()
    {
        List<Par<Currency,Integer>> clone= new LinkedList<>();
        for(Par<Currency,Integer> m:list)
        {
            clone.add(new Par<Currency, Integer>(m.getLeft(),m.getRight()));
        }
        return clone;
    }

    public List<Par<Currency, Integer>> getList() {
        return list;
    }
}
