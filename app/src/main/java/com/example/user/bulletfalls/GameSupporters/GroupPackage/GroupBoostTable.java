package com.example.user.bulletfalls.GameSupporters.GroupPackage;

import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.Database.JsonDatabases.HeroesSet;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GroupBoostTable {
    List<Integer> table;
    private List<Float> constanRanges=Arrays.asList(0.2f,0.35f,0.45f,0.65f,0.80f,1f);
    public GroupBoostTable(List<Integer> table)
    {
        this.table= table;
    }

    public int getBoostCount(GroupName groupName)
    {
        float level=0;
        HeroesSet heroesSet= new HeroesSet();
        level=heroesSet.getGroupPercentage(groupName);


        return table.get(getRange(level));
    }

    public int getLevel(GroupName groupName)
    {
        float level=0;
        HeroesSet heroesSet= new HeroesSet();
        level=heroesSet.getGroupPercentage(groupName);
        return getRange(level);
    }

    private int getRange(float f)
    {
        int n=0;

        for(float x : constanRanges)
        {
            if(f>x)n++;
            else break;
        }
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
