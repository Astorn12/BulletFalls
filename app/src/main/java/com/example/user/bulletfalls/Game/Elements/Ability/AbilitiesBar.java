package com.example.user.bulletfalls.Game.Elements.Ability;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.Elements.Ability.Specyfication.AbilitySpecyfication;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AbilitiesBar {

    List<AbilitySpecyfication> abilities;
    //GameController controller;
    public AbilitiesBar(AbilitySpecyfication abilitySpecyfication1, AbilitySpecyfication abilitySpecyfication2, AbilitySpecyfication abilitySpecyfication3)
    {
        this.abilities= Arrays.asList(abilitySpecyfication1.clone(), abilitySpecyfication2.clone(), abilitySpecyfication3.clone());
    }
    public AbilitiesBar(List<AbilitySpecyfication> abs)
    {
        abilities= new LinkedList<>();
        for(AbilitySpecyfication a: abs)
        {
            abilities.add(a.clone());
        }

        //tutaj nie wiamowo czy nie będzie problemu z brakiem klonowania
    }
    public AbilitiesBar()
    {

    }
    public List<AbilitySpecyfication> getAbilities()
    {
        return abilities;
    }
    @JsonIgnore
    public AbilitySpecyfication getFirstAbility()
    {
        return abilities.get(0);
    }
    @JsonIgnore
    public AbilitySpecyfication getSecondAbility()
    {
        return abilities.get(1);
    }
    @JsonIgnore
    public AbilitySpecyfication getThirdAbility()
    {
        return abilities.get(2);
    }

    public AbilitySpecyfication getAbility(int i)//wartość i jest wartością z punktu widzenia użytkownika czyli pierwsza abilitka traktowana jest jako numer 1 a nie 0
    {
        return abilities.get(i-1);
    }

    public LinearLayout getAbilitiesBar(Context context){

        LinearLayout linearLayout= new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        for(AbilitySpecyfication as:this.abilities)
        {
            Ability a=new Ability(context,as);
            a.setScaleType(ImageView.ScaleType.CENTER_CROP);
            a.setAdjustViewBounds(true);
            linearLayout.addView(a);
        }
        return linearLayout;
    }



}
