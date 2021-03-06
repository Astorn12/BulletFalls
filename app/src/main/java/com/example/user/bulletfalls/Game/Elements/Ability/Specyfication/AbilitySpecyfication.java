package com.example.user.bulletfalls.Game.Elements.Ability.Specyfication;

import android.os.AsyncTask;
import android.util.Log;

import com.example.user.bulletfalls.Game.Elements.Helper.RarityIndicator;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.GlobalUsage.Enums.AE;
import com.example.user.bulletfalls.GlobalUsage.Enums.Permission;
import com.example.user.bulletfalls.GlobalUsage.Enums.Rarity;
import com.example.user.bulletfalls.Game.Elements.Helper.DisplayedSpecyfication;
import com.example.user.bulletfalls.Game.Management.GameController;
import com.example.user.bulletfalls.GlobalUsage.Interfaces.PossesAble;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.StartAction;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.MoneyPossesStrategy;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.PossesStrategy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Thread.sleep;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value =WaitAbilitySpecyfication.class, name = "waitability"),

})
@JsonTypeName("abilityspecyfication")
public class AbilitySpecyfication extends DisplayedSpecyfication implements  Comparable, PossesAble,RarityIndicator {


    int renewalTime;

    protected boolean active=true;

    Rarity rarity;



    boolean unique;
    PossesStrategy possesStrategy;
    StartAction startAction;


    public AbilitySpecyfication(String name, int imageResource, int renewalTime, StartAction startAction, Rarity rarity, boolean unique, PossesStrategy possesStrategy) {
        super(name,imageResource);

        this.startAction = startAction;



        this.name=name;
        this.rarity=rarity;
        this.unique=unique;
        this.possesStrategy=possesStrategy;
        this.renewalTime=renewalTime;

    }
    public AbilitySpecyfication(AE ae, int imageResources, int renewalTime, StartAction startAction, Rarity rarity, boolean unique, PossesStrategy possesStrategy)
    {
        this(ae.getValue(),imageResources,renewalTime, startAction,rarity,unique,possesStrategy);
    }

    public AbilitySpecyfication()
    {


    }

   // protected void activate()
    /*{
        this.active=true;
    }*/

    public void action(GameController controller)
    {
        addAction(controller.getEyeOnGame());
    }
    public void addAction(EyeOnGame eyeOnGame)
    {

            eyeOnGame.addAction(startAction.prepareAction(eyeOnGame));



    }


    public int getRenewalTime() {
        return renewalTime;
    }

    public void setRenewalTime(int renewalTime) {
        this.renewalTime = renewalTime;
    }

    public StartAction getStartAction() {
        return startAction;
    }

    public void setStartAction(StartAction startAction) {
        this.startAction = startAction;
    }





    @Override
    public int compareTo(Object a) {


        return ((AbilitySpecyfication)a).rarity.ordinal() > this.rarity.ordinal()? -1:1 ;
    }






    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbilitySpecyfication clone()
    {
        //String name,int imageResources,int renewalTime,StartAction startAction,Permission perm
        return new AbilitySpecyfication(this.name,this.imageResource,this.renewalTime,this.startAction,this.rarity,this.unique,new MoneyPossesStrategy("Mystery Coin",30));
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public PossesStrategy getPossesStrategy() {
        return possesStrategy;
    }

    public void setPossesStrategy(PossesStrategy possesStrategy) {
        this.possesStrategy = possesStrategy;
    }

    public boolean isActive() {
        return active;
    }

    protected void setActive(boolean active) {
        this.active = active;
    }
    public void activate()
    {
        this.active=true;
    }
    public void deactivate()
    {
        this.active=false;
    }
    @Override
    public boolean equals(Object o)
    {
        if(! (o instanceof AbilitySpecyfication))
            return false;
        if(this.getName().equals(((AbilitySpecyfication)o).getName()))
        {
            return  true;
        }
        return false;
    }
    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public void decreaseRenewalTime(double d)
    {
        this.renewalTime*=(1.0-d);
    }



}


