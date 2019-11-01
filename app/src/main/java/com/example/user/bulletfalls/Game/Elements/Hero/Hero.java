package com.example.user.bulletfalls.Game.Elements.Hero;

import android.content.Context;
import android.graphics.Point;
import android.widget.FrameLayout;

import com.example.user.bulletfalls.Game.Elements.Helper.Sizers.CharacterSizer;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Active.HeroAS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Collection.HeroCS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive.HeroPS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.View.CharacterVS;
import com.example.user.bulletfalls.Game.Elements.Ability.AbilitiesBar;
import com.example.user.bulletfalls.Game.Elements.Beast.Beast;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.example.user.bulletfalls.Game.Elements.Helper.Summoner;
import com.example.user.bulletfalls.Game.GameBiznesFunctions.Classes.IClass;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.GlobalUsage.Interfaces.PossesAble;
import com.example.user.bulletfalls.Game.Elements.Ability.Specyfication.AbilitySpecyfication;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.PossesStrategy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.LinkedList;
import java.util.List;

@JsonTypeName("hero")
public class Hero extends Character implements Summoner {


    /**VIEW STATISTICS*/


    /**PASSIVE STATISTICS*/
    AbilitiesBar abilities;

    /**ACTIVE STATISTICS*/
    IClass iClass;

    /**COLLECTION STATISTICS*/
    PossesStrategy possesStrategy;
    int tier;


    boolean touchFlag;
    List<Beast> beastsWaitingToSummon;



    public Hero(Context context, HeroSpecyfication specyfication){
        super(context,specyfication);


        this.abilities=specyfication.abilities;

        this.iClass=specyfication.iClass;

        this.possesStrategy=specyfication.possesStrategy;
        this.tier=specyfication.tier;


        this.touchFlag=false;

        this.beastsWaitingToSummon= new LinkedList<>();
    }



    public AbilitiesBar getAbilities() {
        return abilities;
    }
    public void setAbilities(AbilitiesBar abilities) {
        this.abilities = abilities;
    }



    @Override
        public void move(EyeOnGame eyeOnGame)
        {

                //tu powinniśmy jeszcze sprawdzać gdzie będzie postać po następnym przeskoku aby zapobie wychodzeniu postaci za ekran
                if (this.touchFlag) {
                    if (this.getY() - speed > frame.getY()) {

                        // this.setY(getY() - 20);
                        ((Game) this.getContext()).moveViewElement(this, 0, -speed);
                    } else {
                        ((Game) this.getContext()).setViewElement(this, 0, 0);
                    }
                } else {
                    if (this.getY() + this.getHeight() + speed < frame.getHeight()) {

                        //this.setY(getY() + 20);
                        ((Game) this.getContext()).moveViewElement(this, 0, speed);
                    } else {
                        ((Game) this.getContext()).setViewElement(this, (int) this.getX(), frame.getHeight() - this.getHeight());
                    }
                }
                uploatLifeView();

        }

    @Override
    public void setStartingPoint() {

    }

    @Override
    public Object clone() {
        return null;
    }

    public void comeDown()
        {
            this.touchFlag=false;
        }
        public void flyUp()
        {
            this.touchFlag=true;
        }
    public Point getStartingPointForBullet()//#
    {
        return new Point((int)(getX()+getWidth()),(int)(getY()+getHeight()/2));
    }

    @Override
    public void died() {

    }

    public boolean isTouchFlag() {
        return touchFlag;
    }
    public void setTouchFlag(boolean touchFlag) {
        this.touchFlag = touchFlag;
    }

    @JsonIgnore
    public AbilitySpecyfication getFirstAbility()
    {
        return this.abilities.getFirstAbility();
    }
    @JsonIgnore
    public AbilitySpecyfication getSecondAbility()
    {
        return this.abilities.getSecondAbility();
    }
    @JsonIgnore
    public AbilitySpecyfication getThirdAbility()
    {
        return this.abilities.getThirdAbility();
    }


//metody specjalne, obchodzące zwyczajne zachowanie systemu


    public Hero changeContext(Context context)
    {
        Hero hero = new Hero(context,this.getSpecyfication());
        hero.setIrrealWidth(this.getIrrealWidth());
        hero.setIrrealHeight(this.getIrrealHeight());
        return hero;
    }

    public HeroSpecyfication getSpecyfication(){
        HeroSpecyfication heroSpecyfication= new HeroSpecyfication(this.name,
                new CharacterVS(this.image,CharacterSizer.getDipperCounter(this.height),this.description),
                new HeroPS(this.speed,this.life,this.shootingSpeed,this.bulletSpecyfication),
                new HeroAS(this.characterPositioning,this.attackDefenceFilter,this.appearAction,this.iClass),
                new HeroCS(this.indyvidualHeroMarker,this.familyNames,this.kind,this.possesStrategy,this.tier));
        heroSpecyfication.setAbilities(this.abilities);
        return heroSpecyfication;
    }



    public PossesStrategy getPossesStrategy() {
        return possesStrategy;
    }

    public void setPossesStrategy(PossesStrategy possesStrategy) {
        this.possesStrategy = possesStrategy;
    }

    public boolean isPermitted()
    {
        return true;
    }

    public void boostLife(int extrLife)
    {
        if(extrLife>0)
        this.life+=extrLife;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }


    @Override
    public void summon(Beast beast) {

        this.beastsWaitingToSummon.add(beast);
    }

    public void Summon(FrameLayout frame,List<Beast> beasts)
    {
        for(int i=0;i<this.beastsWaitingToSummon.size();i++)
        {
            Beast beast= this.beastsWaitingToSummon.get(i);
            beast.setFrame(frame);
            beast.born();
            beasts.add(beast);
        }
        this.beastsWaitingToSummon.clear();
    }

    public List<Beast> getBeastsWaitingToSummon() {
        return beastsWaitingToSummon;
    }

    public void setBeastsWaitingToSummon(List<Beast> beastsWaitingToSummon) {
        this.beastsWaitingToSummon = beastsWaitingToSummon;
    }


    /***GETTERS &SETTERS/*/
    public IClass getiClass() {
        return iClass;
    }

    public void setiClass(IClass iClass) {
        this.iClass = iClass;
    }

    public int getNumberOfAbilities(){
        return 3;
    }
}
