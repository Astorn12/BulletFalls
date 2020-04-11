package com.example.user.bulletfalls.Game.Management;

import com.example.user.bulletfalls.Game.Elements.Ability.Specyfication.WaitAbilitySpecyfication;
import com.example.user.bulletfalls.Game.Elements.Enemy.EnemySpecyfication;
import com.example.user.bulletfalls.Missions.Missionable;
import com.example.user.bulletfalls.Missions.Requirements.OveralStatisticChecker;
import com.example.user.bulletfalls.Profile.Currency;
import com.example.user.bulletfalls.Game.Elements.Ability.Specyfication.AbilitySpecyfication;
import com.example.user.bulletfalls.Game.Strategies.Bounty.Bounty;
import com.example.user.bulletfalls.Game.Elements.Ability.Ability;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;

import com.example.user.bulletfalls.Game.Elements.Hero.HeroSpecyfication;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.LinkedList;
import java.util.List;

public class Medium implements Missionable {
    HeroSpecyfication heroSpecyfication;
    List<EnemySpecyfication> deathEnemySpecyficationList;
    List<EnemySpecyfication> pushedEnemySpecyfications;
    long startTime;
    long endTime;
    ArchivContainer<AbilitySpecyfication> usedAbilities;
    ArchivContainer<BulletSpecyfication> heroBullets;
    ArchivContainer<EnemyShot> enemyBullets;
    List<Hitt>  enemyHits;
    //List<MutablePair<Integer,BulletSpecyfication>> takenDamage;
   // ArchivContainer<BulletSpecyfication> takenDamage;

    Bounty bounty;

    List<HeroHited> hitsFromEnemyToHero;
    String result;

    public Medium()
    {
        this.deathEnemySpecyficationList = new LinkedList<>();
        this.pushedEnemySpecyfications = new LinkedList<>();
        this.heroBullets= new ArchivContainer<>();
        this.enemyBullets= new ArchivContainer<>();
        this.enemyHits=new LinkedList<>();
        this.usedAbilities= new ArchivContainer<>();
        //takenDamage= new ArchivContainer<>();
        hitsFromEnemyToHero=new LinkedList<>();
        this.bounty= new Bounty();
        this.hitsFromEnemyToHero=new LinkedList<>();
        result="Win";
    }

    public void startTimer()
    {
        this.startTime= System.currentTimeMillis();
    }
    public void stopTime()
    {
        this.endTime=System.currentTimeMillis();
    }
    public long getDuration()
    {
        if(startTime>0&& endTime>0)
        {
            return endTime-startTime;
        }
        return 0;
    }
    public void heroBorning(HeroSpecyfication heroSpecyfication)
    {
        this.heroSpecyfication = heroSpecyfication;
    }
    public void enemyBorning(EnemySpecyfication enemySpecyficationSpecyfication){this.pushedEnemySpecyfications.add(enemySpecyficationSpecyfication);}
    public void heroShot(BulletSpecyfication bulletSpecyfication, List<Ability> abilities){
        heroBullets.add(bulletSpecyfication);
        for(Ability av: abilities)
        {
            if(av.getAbilitySpecyfication().getClass().equals(WaitAbilitySpecyfication.class))
            {
                WaitAbilitySpecyfication wa=(WaitAbilitySpecyfication)av.getAbilitySpecyfication();
                if(heroBullets.hasEnought(wa.getWaitedBulletSpecyfication(),wa.getAmount()))
                {
                    wa.activate();
                    av.checkActivation();
                }
            }
        }
    }
    public void enemyShot(EnemyShot enemyShot)
    {
       enemyBullets.add(enemyShot);
    }
    public void abilityUse(AbilitySpecyfication abilitySpecyfication)
    {
    usedAbilities.add(abilitySpecyfication);
    }

    public void deathOfEnemy(EnemySpecyfication enemySpecyficationSpecyfication)
    {
        this.deathEnemySpecyficationList.add(enemySpecyficationSpecyfication);
    }

    public void enemyHited(Hitt hit)
    {
        this.enemyHits.add(hit);
    }

    public void takedDamage(MutablePair<Integer,BulletSpecyfication> hit)
    {
       // this.takenDamage.add(hit.getRight(),hit.getLeft());
        this.hitsFromEnemyToHero.add(new HeroHited(System.currentTimeMillis(),hit.getLeft(),hit.getRight()));
    }

    public int getTakenDamage()
    {

        //return this.takenDamage.countAll();
        int sum=0;
        for(HeroHited h: this.hitsFromEnemyToHero)
        {
            sum+=h.damage;
        }
        return sum;
    }

    public int dealedDamage(){
        int sum=0;
        for(Hitt h: this.enemyHits){
            sum+=h.damage;
        }
        return sum;
    }

    
    /**Do zaprogramowania*/
    // lista zdobytych obietów i obiekt item
    //lista herosupporters stworzyć herosupporter


    public Bounty getBounty() {
        return bounty;
    }
    public List<EnemySpecyfication> getKilledEnemys()
    {
        return this.deathEnemySpecyficationList;
    }

    public int getAbilityUseCounter(AbilitySpecyfication abilitySpecyfication)
    {
        return this.usedAbilities.getUseCounter(abilitySpecyfication);
    }
    public int getShootedByHero()
    {
        return this.heroBullets.countAll();
    }

    public int timeJumpHeal(long time)
    {
        int takenDamage=0;
        long now=System.currentTimeMillis();
        for(HeroHited h: this.hitsFromEnemyToHero)
        {
            if(now-time<h.time) {
                takenDamage += h.damage;
            }
        }
        return takenDamage;
    }

    public void registerItem(Currency currency) {
        this.bounty.addItem(currency);
    }

    public String getResult() {
        return result;
    }

    public void addMoney(int gold){
        this.bounty.addMoney(gold);
    }

    public HeroSpecyfication getHeroSpecyfication() {
        return heroSpecyfication;
    }

    @Override
    public int acceptChecking(OveralStatisticChecker checker,int i) {
        return checker.check(this,i);
    }
}
