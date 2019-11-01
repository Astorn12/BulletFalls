package com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamilyAbilityPackage.FamilyHealAbility;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamilyAbilityPackage.FamilyIncreaseLifeAbility;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamilyAbilityPackage.FamilyIncreaseResistanceAbility;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamilyAbilityPackage.FamilyPowerUpBulletAbility;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamilyAbilityPackage.NoFamilyAbility;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.R;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FamiliesContainer {
    List<Family> familyList;//= new LinkedList<>();
    public FamiliesContainer()
    {
        this.familyList = new LinkedList<>();
        initial();
    }



    public List<Action> getActionListByHero(Hero hero){
        List<Action> actions= new LinkedList<>();
        for(Family family :this.familyList)
        {
            if(hero.isFromGroup(family.name))
            {
                actions.add(family.boost());
            }
        }
        return actions;
    }


    public Family getGroup(FamilyName familyName)
    {
        for(Family g: this.familyList)
        {
            if(g.name.equals(familyName)) return g;
        }
        return null;
    }

    private void initial()
    {
        this.familyList.add(MysteryHackGroup());
        this.familyList.add(LumberJack());
        this.familyList.add(TentOfThelepathy());

        this.familyList.add(WendyTeam());
        this.familyList.add(MabelTeam());
        this.familyList.add(Null());
        this.familyList.add(Ramirez());
        this.familyList.add(Scientist());
    }
    private Family MysteryHackGroup()
    {
        return new Family(FamilyName.MysteryShack,new FamilyBoostTable(Arrays.asList(10,20,30,40,50,80)),new FamilyIncreaseLifeAbility(),0,R.drawable.mysteryshackminiature,
                "Mystery Hack");
    }
    private Family LumberJack()
    {
        return new Family(FamilyName.Lumberjack,new FamilyBoostTable(Arrays.asList(10,20,30,40,50,80)),new FamilyIncreaseLifeAbility(),R.drawable.couldrons ,R.drawable.couldrongroupminiature,
                "Lumber Jack");
    }
    private Family TentOfThelepathy()
    {
        return new Family(FamilyName.TentOfThelepathy,new FamilyBoostTable(Arrays.asList(10,20,30,40,50,80)),new FamilyPowerUpBulletAbility());

    }

    private Family WendyTeam()
    {
        return new Family(FamilyName.WendyTeam,new FamilyBoostTable(Arrays.asList(10,20,30,40,50,80)),new FamilyIncreaseResistanceAbility());

    }
    private Family MabelTeam()
    {
        return new Family(FamilyName.MabelTeam,new FamilyBoostTable(Arrays.asList(10,20,30,40,50,80)),new FamilyHealAbility());

    }
    private Family Null()
    {
        return new Family(FamilyName.Null,new FamilyBoostTable(Arrays.asList(0,0,0,0,0,0)),new NoFamilyAbility());
    }

    private Family Ramirez()
    {/*może być kopią umiejętności drugiej rodziny z lepszą/gorszą tabelką*/
        return new Family(FamilyName.Ramirez,new FamilyBoostTable(Arrays.asList(10,20,30,40,50,120)),new FamilyHealAbility());
    }

    private Family Scientist(){
        return new Family(FamilyName.Scientists,new FamilyBoostTable(Arrays.asList(10,20,30,40,50,120)),new FamilyHealAbility());

    }
}
