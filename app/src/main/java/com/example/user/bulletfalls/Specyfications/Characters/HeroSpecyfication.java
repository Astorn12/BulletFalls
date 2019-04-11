package com.example.user.bulletfalls.Specyfications.Characters;

import com.example.user.bulletfalls.Objects.Ability;
import com.example.user.bulletfalls.Objects.BarAbilities;
import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.Objects.Hero;
import com.example.user.bulletfalls.Strategies.Abilities.ChangeBullet;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.PossesStrategy;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("herospecyfication")
public class HeroSpecyfication extends CharacterSpecyfication {
    BarAbilities abilities;
    Permission permission;
    int numberOfAbilities;
    PossesStrategy possesStrategy;
    int tier;
    public HeroSpecyfication(Hero hero)
    {

        super(hero);
        this.abilities=hero.getAbilities();
        this.permission=hero.getPermission();
        this.numberOfAbilities=hero.getNumberOfAbilities();
        this.possesStrategy=hero.getPossesStrategy();
        this.tier=hero.getTier();
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

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    @Override
    public boolean equals(Object o)
    {
        if(! (o instanceof HeroSpecyfication))
            return false;
        if(this.getName().equals(((HeroSpecyfication)o).getName()))
        {
            return  true;
        }
        return false;
    }}
