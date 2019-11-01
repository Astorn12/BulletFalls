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
    Rarity rarity;



    boolean unique;
    PossesStrategy possesStrategy;
    StartAction startAction;
    AbilityRestorTask task;

    public AbilitySpecyfication(String name, int imageResource, int renewalTime, StartAction startAction, Rarity rarity, boolean unique, PossesStrategy possesStrategy) {
        super(name,imageResource);

        this.startAction = startAction;
        this.ready=true;
        this.renewalUpdateProgress=100;

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
        this.ready=true;
        this.renewalUpdateProgress=100;
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
        if(isReady()) {
            eyeOnGame.addAction(startAction.prepareAction(eyeOnGame));
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

    public StartAction getStartAction() {
        return startAction;
    }

    public void setStartAction(StartAction startAction) {
        this.startAction = startAction;
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
       // executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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


