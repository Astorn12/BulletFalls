package com.example.user.bulletfalls.GameSupporters.GroupPackage;

import android.support.v4.util.Pair;
import android.widget.ImageView;

import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.GameSupporters.GroupPackage.GroupAbilityPackage.GroupAbility;
import com.example.user.bulletfalls.ObjectsOfGame.Hero;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.List;

public class Group {
    GroupName name;
    GroupBoostTable boostTable;
    GroupAbility groupAbility;
    int background;
    int miniature;
    String description;


    String abilityDescription;
    int abilitiMiniature;
    public Group(GroupName gm,GroupBoostTable gt,GroupAbility ga,int background,int miniature)
    {
        this.name=gm;
        this.boostTable=gt;
        this.groupAbility=ga;
        this.background=background;
        this.miniature=miniature;
    }

    public Group(GroupName gm,GroupBoostTable gt,GroupAbility ga)
    {
        this.name=gm;
        this.boostTable=gt;
        this.groupAbility=ga;
        this.background=0;
        this.miniature=0;
        description="";

    }

    public void boost(Hero hero)
    {
        groupAbility.boostHero(boostTable.getBoostCount(name),hero);
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

    public String getAbilityDescription() {
        return abilityDescription;
    }

    public void setAbilityDescription(String abilityDescription) {
        this.abilityDescription = abilityDescription;
    }

    public int getAbilitiMiniature() {
        return abilitiMiniature;
    }

    public void setAbilitiMiniature(int abilitiMiniature) {
        this.abilitiMiniature = abilitiMiniature;
    }
}
