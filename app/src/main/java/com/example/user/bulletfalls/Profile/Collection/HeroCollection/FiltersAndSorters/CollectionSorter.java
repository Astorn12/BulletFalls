package com.example.user.bulletfalls.Profile.Collection.HeroCollection.FiltersAndSorters;
import android.os.Build;
import com.example.user.bulletfalls.Game.Elements.Hero.HeroSpecyfication;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import androidx.annotation.RequiresApi;

public class CollectionSorter<T> implements CollectionFilter<T> {

    Comparator<T> comparator;

    public CollectionSorter(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<T> filter(List<T> inputList) {

        Collections.sort(inputList, comparator);
        return inputList;

    }
}
