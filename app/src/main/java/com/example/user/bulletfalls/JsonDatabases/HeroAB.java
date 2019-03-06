package com.example.user.bulletfalls.JsonDatabases;

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
    /*-----------------------------Geters & Setters---------------------------*/
    public List<String> getAvaillableAbilities() {
        return availlableAbilities;
    }

    public void setAvaillableAbilities(List<String> availlableAbilities) {
        this.availlableAbilities = availlableAbilities;
    }

    public List<String> getAvaillableBullets() {
        return availlableBullets;
    }

    public void setAvaillableBullets(List<String> availlableBullets) {
        this.availlableBullets = availlableBullets;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }


}
