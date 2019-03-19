package com.example.user.bulletfalls.GameSupporters.GroupPackage;

import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.GameSupporters.GroupPackage.GroupAbilityPackage.GroupAbility;
import com.example.user.bulletfalls.Hero;

public class Group {
    GroupName name;
    GroupBoostTable boostTable;
    GroupAbility groupAbility;
    public Group(GroupName gm,GroupBoostTable gt,GroupAbility ga)
    {
        this.name=gm;
        this.boostTable=gt;
        this.groupAbility=ga;
    }


    public void groupBoost(Hero hero)
    {
        groupAbility.boostHero(boostTable.getBoostCount(),hero);
    }

}
