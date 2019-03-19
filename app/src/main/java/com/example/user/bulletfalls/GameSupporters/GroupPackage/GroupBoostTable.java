package com.example.user.bulletfalls.GameSupporters.GroupPackage;

import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.GameSupporters.GroupPackage.GroupAbilityPackage.GroupAbility;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GroupBoostTable {
    List<Integer> table;
    private List<Float> constanRanges=Arrays.asList(0.2f,0.35f,0.45f,0.65f,0.80f,1f);
    public GroupBoostTable(GroupName groupName)
    {
        table= new LinkedList<>();
    }

    public void setTable(List<Integer> table)
    {
        this.table=table;
    }

    public int getBoostCount()
    {
        return 0;
    }

}
