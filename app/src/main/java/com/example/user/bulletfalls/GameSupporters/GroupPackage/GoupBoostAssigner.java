package com.example.user.bulletfalls.GameSupporters.GroupPackage;

import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.ObjectsOfGame.Hero;
import com.example.user.bulletfalls.Database.JsonDatabases.HeroesSet;

public class GoupBoostAssigner {

    public void assignBoost(Hero hero)
    {
        HeroesSet heroesSet= new HeroesSet();

        for(GroupName groupName : hero.getGroupNames())
        {
            assign(hero, groupName,heroesSet.getGroupPercentage(groupName));
        }
    }


    private void assign(Hero hero, GroupName groupName, float percentage)
    {
        switch(groupName){
            case Lumberjack:

                break;
            case WendyTeam:
                break;
            case TentOfThelepathy:
                break;
            case MysteryShack:
                break;
            case MabelTeam:
                break;
            case Animal:
                break;
        }

    }
}
