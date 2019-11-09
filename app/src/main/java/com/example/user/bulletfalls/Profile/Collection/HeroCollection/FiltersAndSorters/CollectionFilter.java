package com.example.user.bulletfalls.Profile.Collection.HeroCollection.FiltersAndSorters;

import java.util.List;

public interface CollectionFilter<T>  {

    List<T> filter(List<T> inputList);

}
