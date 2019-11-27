package com.example.user.bulletfalls.Profile.Collection.HeroCollection.FiltersAndSorters;

import java.util.LinkedList;
import java.util.List;

public class CollectionFilterComposite<T> extends LinkedList<CollectionFilter<T>> implements CollectionFilter<T>  {

    @Override
    public List<T> filter(List<T> inputList) {
        List<T> manageList=inputList;
        for(CollectionFilter<T> filter: this){
            manageList=filter.filter(manageList);
        }

        return manageList;
    }
    @Override
    public boolean remove(Object object){

        for(int i=0;i<this.size();i++){
            if(this.get(i).getClass().equals(object.getClass())){
                this.remove(i);
                i--;
            }
        }

        return false;
    }

    public void clearFamilyFilters(){

        for(int i=0;i<this.size();i++){
            if(this.get(i).getClass().equals(FamilyFilter.class)){
                this.remove(i);
                i--;
            }
        }
    }


}
