package com.example.user.bulletfalls.Specyfications.Characters;

import com.example.user.bulletfalls.Ability;
import com.example.user.bulletfalls.BarAbilities;
import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.Hero;
import com.example.user.bulletfalls.Strategies.Abilities.ChangeBullet;
import com.example.user.bulletfalls.Strategies.Character.Character.PossesStrategyPackage.PossesStrategy;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("hero")
public class HeroSpecyfication extends CharacterSpecyfication {
    BarAbilities abilities;
    Permission permission;
    int numberOfAbilities;
    PossesStrategy possesStrategy;
    public HeroSpecyfication(Hero hero)
    {

        super(hero);
        this.abilities=hero.getAbilities();
        this.permission=hero.getPermission();
        this.numberOfAbilities=hero.getNumberOfAbilities();
        this.possesStrategy=hero.getPossesStrategy();
    }

    public BarAbilities getAbilities() {
        return abilities;
    }

    public void setAbilities(BarAbilities abilities) {
        this.abilities = abilities;
        for(Ability ability:abilities.getAbilities())
        {
            if(ability.getDoToCharacterStrategy().getClass().equals(ChangeBullet.class))
            {
                //((ChangeBullet)ability.getDoToCharacterStrategy()).
            }
        }
    }

    public  HeroSpecyfication()

    {

    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public int getNumberOfAbilities() {
        return numberOfAbilities;
    }

    public void setNumberOfAbilities(int numberOfAbilities) {
        this.numberOfAbilities = numberOfAbilities;
    }

    public PossesStrategy getPossesStrategy() {
        return possesStrategy;
    }

    public void setPossesStrategy(PossesStrategy possesStrategy) {
        this.possesStrategy = possesStrategy;
    }
}
