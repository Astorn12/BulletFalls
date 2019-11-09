package com.example.user.bulletfalls.Profile.Collection.HeroCollection.FiltersAndSorters;

import java.util.LinkedList;
import java.util.List;

public abstract class SmartFilter<T> implements CollectionFilter<T> {

    SmartFilter<T> nextSmartFilter;

    public List<T> filter(List<T> list)
    {
        List<T> newList= new LinkedList<>();
        for(T t:list){
            if(ifMatchFilter(t))
                newList.add(t);
        }
        if(this.nextSmartFilter !=null) nextSmartFilter.filter(newList);
        return newList;
    }

    protected abstract boolean ifMatchFilter(T t);

}
