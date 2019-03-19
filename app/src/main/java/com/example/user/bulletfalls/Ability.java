package com.example.user.bulletfalls;

import android.os.AsyncTask;
import android.util.Log;

import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.Enums.Rarity;
import com.example.user.bulletfalls.Interfaces.Observed;
import com.example.user.bulletfalls.Interfaces.Observer;
import com.example.user.bulletfalls.Interfaces.PossesAble;
import com.example.user.bulletfalls.Strategies.Abilities.DoToCharacterStrategy;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.MoneyPossesStrategy;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.PossesStrategy;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Thread.sleep;

public class Ability implements Observed, Comparable, PossesAble {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<Runnable>(128);
    //GameController controller;
    int imageResources;
    int renewalTime;
    boolean ready;
    @JsonIgnore
    int renewalUpdateProgress;
    @JsonIgnore
    List<Observer> observers= new LinkedList<>();

    Permission permission;
    String name;
    Rarity rarity;
    boolean unique;
    PossesStrategy possesStrategy;

    DoToCharacterStrategy doToCharacterStrategy;
    public Ability(String name,int imageResources,int renewalTime,DoToCharacterStrategy doToCharacterStrategy,Permission perm,Rarity rarity,boolean unique,PossesStrategy possesStrategy) {
        this(name,imageResources,doToCharacterStrategy,perm,rarity,unique,possesStrategy);
        this.renewalTime=renewalTime;


    }
    public Ability(String name,int imageResources,DoToCharacterStrategy doToCharacterStrategy,Permission permission,Rarity rarity,boolean unique,PossesStrategy possesStrategy) {

        this.imageResources=imageResources;
        this.doToCharacterStrategy=doToCharacterStrategy;
        this.ready=true;
        this.renewalUpdateProgress=100;
        this.permission=permission;
        this.name=name;
        this.rarity=rarity;
        this.unique=unique;
        this.possesStrategy=possesStrategy;
    }
    public Ability()
    {
        this.ready=true;
        this.renewalUpdateProgress=100;
    }
    public void action(GameController controller)
    {
    doToCharacter(controller.hero);
    }
    public void doToCharacter(Character character)
    {
        if(isReady()) {
            doToCharacterStrategy.doToCharacter(character);
            if (renewalTime > 0) {
                this.setReady(false);
                AbilityRestorTask art=new AbilityRestorTask();
                art.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                /*if(Observer.class.isAssignableFrom(this.getDoToCharacterStrategy().getClass()))
                {
                    character.addObserver((NumberChangeBullet)this.getDoToCharacterStrategy());
                }*/

            }
        }


    }
    public int getImageResources() {
        return imageResources;
    }

    public void setImageResources(int imageResources) {
        this.imageResources = imageResources;
    }

    public int getRenewalTime() {
        return renewalTime;
    }

    public void setRenewalTime(int renewalTime) {
        this.renewalTime = renewalTime;
    }

    public DoToCharacterStrategy getDoToCharacterStrategy() {
        return doToCharacterStrategy;
    }

    public void setDoToCharacterStrategy(DoToCharacterStrategy doToCharacterStrategy) {
        this.doToCharacterStrategy = doToCharacterStrategy;
    }
    public boolean isReady() {
        return ready;
    }
    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public int getRenewalUpdateProgress() {
        return renewalUpdateProgress;
    }

    public void setRenewalUpdateProgress(int renewalUpdateProgress) {
        if(renewalUpdateProgress>=0&&renewalUpdateProgress<=100) {
            this.renewalUpdateProgress = renewalUpdateProgress;
        }
        }

    public void updateRenewalProgress(int alfa)
    {
     setRenewalUpdateProgress(alfa);
     execute();
       // executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if(observers.contains(observer))
        {
            observers.remove(observer);
        }
    }

    @Override
    public void execute() {
        for(Observer observer:observers)
        {
            observer.update(this.renewalUpdateProgress);
        }
    }

    @Override
    public int compareTo(Object a) {


        return ((Ability)a).rarity.ordinal() > this.rarity.ordinal()? -1:1 ;
    }

    public class AbilityRestorTask extends AsyncTask<Integer,Integer,Boolean> {
        @Override
        protected Boolean doInBackground(Integer... integers) {
            //for(int i=0;i<100;i++)
           // {
                try {
                    for(int i=0;i<100;i++) {
                        sleep(renewalTime/100);
                        publishProgress(i);
                        Log.d("AsyncTask",i+"");

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
           // }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... progress) {

            updateRenewalProgress(progress[0]);

        }
        @Override
        protected void onPostExecute(Boolean result) {
           setReady(true);
        }


    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ability clone()
    {
        //String name,int imageResources,int renewalTime,DoToCharacterStrategy doToCharacterStrategy,Permission perm
        return new Ability(this.name,this.imageResources,this.renewalTime,this.doToCharacterStrategy,this.permission,this.rarity,this.unique,new MoneyPossesStrategy("Mystery Coin",30));
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
}


