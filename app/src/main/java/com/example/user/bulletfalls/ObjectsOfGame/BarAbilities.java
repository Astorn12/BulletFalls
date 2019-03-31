package com.example.user.bulletfalls.ObjectsOfGame;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BarAbilities {

    List<Ability> abilities;
    //GameController controller;
    public BarAbilities(Ability ability1,Ability ability2,Ability ability3)
    {
        this.abilities= Arrays.asList(ability1.clone(),ability2.clone(),ability3.clone());
    }
    public BarAbilities(List<Ability> abs)
    {
        abilities= new LinkedList<>();
        for(Ability a: abs)
        {
            abilities.add(a.clone());
        }

        //tutaj nie wiamowo czy nie będzie problemu z brakiem klonowania
    }
    public BarAbilities()
    {

    }
    public List<Ability> getAbilities()
    {
        return abilities;
    }
    @JsonIgnore
    public Ability getFirstAbility()
    {
        return abilities.get(0);
    }
    @JsonIgnore
    public Ability getSecondAbility()
    {
        return abilities.get(1);
    }
    @JsonIgnore
    public Ability getThirdAbility()
    {
        return abilities.get(2);
    }

    public Ability getAbility(int i)//wartość i jest wartością z punktu widzenia użytkownika czyli pierwsza abilitka traktowana jest jako numer 1 a nie 0
    {
        return abilities.get(i-1);
    }


}
