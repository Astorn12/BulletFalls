package com.example.user.bulletfalls.GameSupporters.GroupPackage;

import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.GameSupporters.GroupPackage.GroupAbilityPackage.GroupAbility;
import com.example.user.bulletfalls.Objects.Hero;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.List;

public class Group {
    GroupName name;
    GroupBoostTable boostTable;
    GroupAbility groupAbility;
    int background;
    int miniature;
    String description;
    int abilitiMiniature;
    public Group(GroupName gm,GroupBoostTable gt,GroupAbility ga,int background,int miniature,String description)
    {
        this.name=gm;
        this.boostTable=gt;
        this.groupAbility=ga;
        this.background=background;
        this.miniature=miniature;
        this.description=description;

    }

    public Group(GroupName gm,GroupBoostTable gt,GroupAbility ga)
    {
        this(gm,gt,ga,0,0,"");

    }

    public void boost(Hero hero)
    {
        groupAbility.boostHero(boostTable.getBoostCount(name), hero);
    }


    public int getBackground() {
        return background;
    }

    public int getMiniature() {
        return miniature;
    }

    public String getDescription() {
        return description;
    }

    public GroupName getGroupName() {
        return name;
    }

    public String getName()
    {
        return this.getGroupName().toString();
    }

    public List<MutablePair<Float,Integer>> getLevelTable()
    {
        return this.boostTable.getLevelTable();
    }

    public int getActualLevel()
    {
        return this.boostTable.getLevel(this.name);
    }

    public int getAbilitiMiniature() {
        return abilitiMiniature;
    }

    public void setAbilitiMiniature(int abilitiMiniature) {
        this.abilitiMiniature = abilitiMiniature;
    }

    public GroupAbility getGroupAbility() {
        return groupAbility;
    }
}
