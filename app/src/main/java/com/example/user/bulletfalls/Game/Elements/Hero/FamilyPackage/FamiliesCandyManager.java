package com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage;

import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Game.Elements.Hero.HeroSpecyfication;
import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;
import com.example.user.bulletfalls.GlobalUsage.Enums.HE;

import java.util.Arrays;
import java.util.List;



public class FamiliesCandyManager {
    private final  List<HeroFamilyCandy> heads=Arrays.asList(
                            new HeroFamilyCandy(HE.STANFORDPINNES,FamilyName.MysteryShack,2),
                            new HeroFamilyCandy(HE.SOOSRAMIREZ,FamilyName.MysteryShack,15)

    );

    public int countHeroCandy(HeroSpecyfication hero, FamilyName familyName)
    {
        for(HeroFamilyCandy candy: heads){
            if(hero.getName().equals(candy.heroName.getValue())&&familyName.equals(candy.familyName)){
                return candy.candys;
            }
        }
        return 1;
    }




    public class HeroFamilyCandy{

        HE heroName;
        FamilyName familyName;
        Integer candys;

        public HeroFamilyCandy(HE heroName, FamilyName familyName, Integer candys) {
            this.heroName = heroName;
            this.familyName = familyName;
            this.candys = candys;
        }
    }
}
