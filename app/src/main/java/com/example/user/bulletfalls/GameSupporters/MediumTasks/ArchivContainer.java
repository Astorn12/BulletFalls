package com.example.user.bulletfalls.GameSupporters.MediumTasks;

import com.example.user.bulletfalls.Strategies.Par;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import org.apache.commons.lang3.tuple.MutablePair;

import java.io.Serializable;
import java.util.Currency;
import java.util.LinkedList;
import java.util.List;

public class ArchivContainer<T>  {
    protected List<MutablePair<T,Integer>>  list;

    public ArchivContainer()
    {
        this.list= new LinkedList<>();
    }

    public ArchivContainer(List<MutablePair<T,Integer>>  list){
        this.list=list;
    }
    public void add(T t)
    {
       add(t,1);
    }

    public void add(T t, int n)
    {
        if(include(t)) {
            for(MutablePair<T,Integer> ti:list)
            {
                if (t.equals(ti.getLeft()))
                {
                    ti.setRight(ti.getRight()+1);
                    break;
                }
            }
        }else{
            list.add(new MutablePair<T, Integer>(t,n));
        }
    }

    private boolean include(T t)
    {
        for(MutablePair<T,Integer> tl:list)
        {
            if(tl.getLeft().equals(t)){
                return true;
            }
        }

        return false;
    }

    public MutablePair<T,Integer>get(int n)
    {
        return this.list.get(n);
    }


    public int size()
    {
        return this.list.size();
    }

    public List<MutablePair<T,Integer>> cloneList()
    {
        List<MutablePair<T,Integer>> clone= new LinkedList<>();
        for(MutablePair<T,Integer> m:list)
        {
            clone.add(new MutablePair<T, Integer>(m.getLeft(),m.getRight()));
        }
        return clone;
    }

    public List<MutablePair<T, Integer>> getList() {
        return list;
    }

    public int getUseCounter(T t)
    {
        for(MutablePair<T, Integer> mp:this.list)
        {
            if(mp.getLeft().equals(t)) return mp.getRight();
        }
        return 0;
    }

    public int countAll()
    {
        int counter=0;
        for(MutablePair<T,Integer> mp:this.list)
        {
            counter+=mp.getRight();
        }
        return  counter;
    }
}




