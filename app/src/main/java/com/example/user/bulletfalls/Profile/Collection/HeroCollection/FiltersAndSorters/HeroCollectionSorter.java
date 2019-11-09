package com.example.user.bulletfalls.Profile.Collection.HeroCollection.FiltersAndSorters;

import com.example.user.bulletfalls.Game.Elements.Hero.HeroSpecyfication;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class HeroCollectionSorter extends CollectionSorter<HeroSpecyfication> {
    public HeroCollectionSorter(Comparator<HeroSpecyfication> comparator) {
        super(comparator);
    }

}
