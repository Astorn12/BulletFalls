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
import com.example.user.bulletfalls.Game.Elements.Weapon.Weapon;
import com.example.user.bulletfalls.Game.GameBiznesFunctions.SuperPowers.MasterAbility;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Game.Elements.Ability.Specyfication.AbilitySpecyfication;
import com.example.user.bulletfalls.GlobalUsage.Supporters.Dimension;
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
    MasterAbility masterAbility;

    /**COLLECTION STATISTICS*/
    PossesStrategy possesStrategy;
    int tier;


    boolean touchFlag;
    List<Beast> beastsWaitingToSummon;


    List<Weapon> weaponList;

    public Hero(Context context, HeroSpecyfication specyfication){
        super(context,specyfication);


        this.abilities=specyfication.abilities;

        this.masterAbility =specyfication.masterAbility;

        this.possesStrategy=specyfication.possesStrategy;
        this.tier=specyfication.tier;


        this.touchFlag=false;

        this.beastsWaitingToSummon= new LinkedList<>();
        this.weaponList=new LinkedList<>();
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


                        ((Game) this.getContext()).moveHero(this, 0, -speed);
                    } else {
                        ((Game) this.getContext()).setHero(this, 0, 0);
                        //uploadWeapons();
                    }
                } else {
                    if (this.getY() + this.getHeight() + speed < frame.getHeight()) {

                        ((Game) this.getContext()).moveHero(this, 0, speed);
                    } else {
                        ((Game) this.getContext()).setHero(this, (int) this.getX(), frame.getHeight() - this.getHeight());
                        //uploadWeapons();
                    }
                }
                uploadlifeview();

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
                new HeroAS(this.characterPositioning,this.attackDefenceFilter,this.appearAction,this.masterAbility),
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
    public MasterAbility getMasterAbility() {
        return masterAbility;
    }

    public void setMasterAbility(MasterAbility masterAbility) {
        this.masterAbility = masterAbility;
    }

    public int getNumberOfAbilities(){
        return 3;
    }
    public void changeForeground(int resource){
        ((Game)this.getContext()).changeForeground(this,resource);
    }

    public void addWeapon(Weapon weapon){
        this.weaponList.add(weapon);
        int d=Math.max(this.getLayoutParams().height,this.getLayoutParams().width);
        getGame().addWeapon(weapon,new Dimension(d,d));
        weapon.setPosition(getGame(), new Dimension(d,d),this.getMiddle());
    }
    public void removeWeapon(int resource){
        for(Weapon w:this.weaponList){
            if(w.getImage()==resource){
                this.weaponList.remove(w);
                getGame().removeObject(w);
                break;
            }
        }

    }
    private Game getGame(){
        return (Game)this.getContext();
    }

    public List<Weapon> getWeaponList(){
        return this.weaponList;
    }
}
