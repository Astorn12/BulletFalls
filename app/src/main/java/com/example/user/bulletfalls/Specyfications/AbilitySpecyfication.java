package com.example.user.bulletfalls.Specyfications;

import android.os.AsyncTask;
import android.util.Log;

import com.example.user.bulletfalls.Enums.AE;
import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.Enums.Rarity;
import com.example.user.bulletfalls.GameManagement.GameController;
import com.example.user.bulletfalls.Interfaces.Observed;
import com.example.user.bulletfalls.Interfaces.Observer;
import com.example.user.bulletfalls.Interfaces.PossesAble;
import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Objects.WaitAbilitySpecyfication;
import com.example.user.bulletfalls.Strategies.Abilities.DoToCharacterStrategy;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.MoneyPossesStrategy;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.PossesStrategy;
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
public class AbilitySpecyfication extends DisplayedSpecyfication implements Observed, Comparable, PossesAble {


    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE =    CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<Runnable>(128);
    //GameController controller;

    int renewalTime;
    boolean ready;
    protected boolean active=true;
    @JsonIgnore
    int renewalUpdateProgress;
    @JsonIgnore
    List<Observer> observers= new LinkedList<>();
    Permission permission;
    Rarity rarity;



    boolean unique;
    PossesStrategy possesStrategy;
    DoToCharacterStrategy doToCharacterStrategy;
    AbilityRestorTask task;

    public AbilitySpecyfication(String name, int imageResource, int renewalTime, DoToCharacterStrategy doToCharacterStrategy, Permission permission, Rarity rarity, boolean unique, PossesStrategy possesStrategy) {
        super(name,imageResource);

        this.doToCharacterStrategy=doToCharacterStrategy;
        this.ready=true;
        this.renewalUpdateProgress=100;
        this.permission=permission;
        this.name=name;
        this.rarity=rarity;
        this.unique=unique;
        this.possesStrategy=possesStrategy;
        this.renewalTime=renewalTime;

    }
    public AbilitySpecyfication(AE ae, int imageResources, int renewalTime, DoToCharacterStrategy doToCharacterStrategy, Permission perm, Rarity rarity, boolean unique, PossesStrategy possesStrategy)
    {
        this(ae.getValue(),imageResources,renewalTime,doToCharacterStrategy,perm,rarity,unique,possesStrategy);
    }

    public AbilitySpecyfication()
    {
        this.ready=true;
        this.renewalUpdateProgress=100;
    }

   // protected void activate()
    /*{
        this.active=true;
    }*/

    public void action(GameController controller)
    {
    doToCharacter(controller.getHero());
    }
    public void doToCharacter(Character character)
    {
        if(isReady()) {
            doToCharacterStrategy.doToCharacter(character);
            if (renewalTime > 0) {
                this.setReady(false);
                AbilityRestorTask art=new AbilityRestorTask();
                this.task=art;
                art.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            }
        }


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


        return ((AbilitySpecyfication)a).rarity.ordinal() > this.rarity.ordinal()? -1:1 ;
    }

    public void cancelThread() {
        if(task!=null)
        this.task.cancel(true);

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

    public AbilitySpecyfication clone()
    {
        //String name,int imageResources,int renewalTime,DoToCharacterStrategy doToCharacterStrategy,Permission perm
        return new AbilitySpecyfication(this.name,this.imageResource,this.renewalTime,this.doToCharacterStrategy,this.permission,this.rarity,this.unique,new MoneyPossesStrategy("Mystery Coin",30));
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
}


