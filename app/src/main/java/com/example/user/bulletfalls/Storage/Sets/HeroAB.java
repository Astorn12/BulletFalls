package com.example.user.bulletfalls.Storage.Sets;

import com.example.user.bulletfalls.GlobalUsage.Enums.AE;
import com.example.user.bulletfalls.GlobalUsage.Enums.BE;
import com.example.user.bulletfalls.GlobalUsage.Enums.HE;

import java.util.LinkedList;
import java.util.List;

public class HeroAB {
    String heroName;
    List<String> availlableAbilities;
    List<String> availlableBullets;

    public HeroAB()
    {
        this.availlableAbilities= new LinkedList<>();
        this.availlableBullets= new LinkedList<>();
        heroName="";
    }

    public  HeroAB(String heroName)
    {
        this.heroName=heroName;
        this.availlableAbilities= new LinkedList<>();
        this.availlableBullets= new LinkedList<>();
    }

    public  HeroAB(HE he)
    {
        this(he.getValue());
    }
    /*-----------------------------Geters & Setters---------------------------*/
    public List<String> getAvaillableAbilities() {
        return availlableAbilities;
    }

    public void setAvaillableAbilities(List<String> availlableAbilities) {
        this.availlableAbilities = availlableAbilities;
    }

    public void setAvaillableAbilitiesenums(List<AE> availlableAbilities) {
        List<String> strings= new LinkedList<>();
        for(AE ae: availlableAbilities)
        {
            strings.add(ae.getValue());
        }
        setAvaillableAbilities(strings);
    }

    public List<String> getAvaillableBullets() {
        return availlableBullets;
    }

    public void setAvaillableBullets(List<String> availlableBullets) {
        this.availlableBullets = availlableBullets;
    }

    public void setAvaillableBulletsenums(List<BE> availlableBullets) {
        List<String> strings= new LinkedList<>();
        for(BE ae: availlableBullets)
        {
            strings.add(ae.getValue());
        }
        setAvaillableBullets(strings);
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }
}
