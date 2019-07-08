package com.example.user.bulletfalls.GameSupporters.MediumTasks;

import com.example.user.bulletfalls.Objects.WaitAbilitySpecyfication;
import com.example.user.bulletfalls.Specyfications.AbilitySpecyfication;
import com.example.user.bulletfalls.GameSupporters.GiveBountyPackage.Bounty;
import com.example.user.bulletfalls.Objects.Ability;
import com.example.user.bulletfalls.Specyfications.Dynamic.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.EnemySpecyfication;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.HeroSpecyfication;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.LinkedList;
import java.util.List;

public class Medium  {
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
}
