package com.example.user.bulletfalls.GameSupporters.GroupPackage;

import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.GameSupporters.GroupPackage.GroupAbilityPackage.GroupHealAbility;
import com.example.user.bulletfalls.GameSupporters.GroupPackage.GroupAbilityPackage.GroupIncreaseLifeAbility;
import com.example.user.bulletfalls.GameSupporters.GroupPackage.GroupAbilityPackage.GroupIncreaseResistanceAbility;
import com.example.user.bulletfalls.GameSupporters.GroupPackage.GroupAbilityPackage.GroupPowerUpBulletAbility;
import com.example.user.bulletfalls.GameSupporters.GroupPackage.GroupAbilityPackage.NoGroupAbility;
import com.example.user.bulletfalls.Objects.Hero;
import com.example.user.bulletfalls.R;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GroupsContainer {
    List<Group> groupList;//= new LinkedList<>();
    public  GroupsContainer()
    {
        this.groupList= new LinkedList<>();
        initial();
    }

    public void boost(Hero hero)
    {
        for(Group group:this.groupList)
        {
            if(hero.isFromGroup(group.name))
            {
                group.boost(hero);
            }
        }
    }
    public Group getGroup(GroupName groupName)
    {
        for(Group g: this.groupList)
        {
            if(g.name.equals(groupName)) return g;
        }
        return null;
    }

    private void initial()
    {
        this.groupList.add(MysteryHackGroup());
        this.groupList.add(LumberJack());
        this.groupList.add(TentOfThelepathy());
        this.groupList.add(Animal());
        this.groupList.add(WendyTeam());
        this.groupList.add(MabelTeam());
        this.groupList.add(Null());
    }
    private Group MysteryHackGroup()
    {
        return new Group(GroupName.MysteryShack,new GroupBoostTable(Arrays.asList(10,20,30,40,50,80)),new GroupIncreaseLifeAbility(),0,R.drawable.mysteryshackminiature);
    }
    private Group LumberJack()
    {
        return new Group(GroupName.Lumberjack,new GroupBoostTable(Arrays.asList(10,20,30,40,50,80)),new GroupIncreaseLifeAbility(),R.drawable.couldrons ,R.drawable.couldrongroupminiature);
    }
    private Group TentOfThelepathy()
    {
        return new Group(GroupName.TentOfThelepathy,new GroupBoostTable(Arrays.asList(10,20,30,40,50,80)),new GroupPowerUpBulletAbility());

    }
    private Group Animal()
    {
        return new Group(GroupName.Animal,new GroupBoostTable(Arrays.asList(10,20,30,40,50,80)),new GroupIncreaseLifeAbility());

    }
    private Group WendyTeam()
    {
        return new Group(GroupName.WendyTeam,new GroupBoostTable(Arrays.asList(10,20,30,40,50,80)),new GroupIncreaseResistanceAbility());

    }
    private Group MabelTeam()
    {
        return new Group(GroupName.MabelTeam,new GroupBoostTable(Arrays.asList(10,20,30,40,50,80)),new GroupHealAbility());

    }
    private Group Null()
    {
        return new Group(GroupName.Null,new GroupBoostTable(Arrays.asList(0,0,0,0,0,0)),new NoGroupAbility());
    }

}
