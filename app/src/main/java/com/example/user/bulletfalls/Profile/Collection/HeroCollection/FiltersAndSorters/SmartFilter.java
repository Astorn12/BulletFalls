package com.example.user.bulletfalls.Profile.Collection.HeroCollection;

import java.util.LinkedList;
import java.util.List;

public abstract class Filter<T> {

    Filter<T> nextFilter;

    public List<T> filter(List<T> list)
    {
        List<T> newList= new LinkedList<>();
        for(T t:list){
            if(ifMatchFilter(t)) newList.add(t);
        }
        if(this.nextFilter!=null) nextFilter.filter(newList);
        return newList;
    }

    protected abstract boolean ifMatchFilter(T t);

}
