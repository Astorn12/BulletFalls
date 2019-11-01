package com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage;

import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;
import com.example.user.bulletfalls.Storage.Sets.HeroesSet;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FamilyBoostTable {
    List<Integer> table;
    private List<Float> constanRanges=Arrays.asList(0.2f,0.35f,0.45f,0.65f,0.80f,1f);
    public FamilyBoostTable(List<Integer> table)
    {
        this.table= table;
    }

    public int getBoostCount(FamilyName familyName)
    {
        float level=0;

        level=HeroesSet.getInstance().getGroupPercentage(familyName);


        return table.get(getRange(level));
    }

    public int getLevel(FamilyName familyName)
    {
        float level=0;

        level=HeroesSet.getInstance().getGroupPercentage(familyName);
        return getRange(level);
    }

    private int getRange(float f)
    {
        int n=-1;

        for(float x : constanRanges)
        {
            if(f>=x)n++;
            else break;
        }
        if(n==-1) n=0;
        return n;
    }

    public List<MutablePair<Float,Integer>> getLevelTable()
    {
        List<MutablePair<Float,Integer>> table= new LinkedList<>();

        for(int i=0;i<constanRanges.size();i++)
        {
            table.add(new MutablePair(constanRanges.get(i),this.table.get(i)));
        }

        return table;
    }



}
