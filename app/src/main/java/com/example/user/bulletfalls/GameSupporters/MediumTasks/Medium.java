package com.example.user.bulletfalls.GameSupporters.MediumTasks;

import com.example.user.bulletfalls.Ability;
import com.example.user.bulletfalls.Bullet;
import com.example.user.bulletfalls.GameSupporters.GiveBountyPackage.Bounty;
import com.example.user.bulletfalls.Specyfications.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Specyfications.Characters.EnemySpecyfication;
import com.example.user.bulletfalls.Specyfications.Characters.HeroSpecyfication;

import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.LinkedList;
import java.util.List;

public class Medium  {
    HeroSpecyfication hero;
    List<EnemySpecyfication> deathEnemyList;
    List<EnemySpecyfication> pushedEnemys;
    long startTime;
    long endTime;

    ArchivContainer<Ability> usedAbilities;
    ArchivContainer<BulletSpecyfication> heroBullets;
    ArchivContainer<EnemyShot> enemyBullets;
    List<Hit>  enemyHits;
    //List<MutablePair<Integer,BulletSpecyfication>> takenDamage;
    ArchivContainer<BulletSpecyfication> takenDamage;
    Bounty bounty;

    public Medium()
    {
        this.deathEnemyList= new LinkedList<>();
        this.pushedEnemys= new LinkedList<>();
        this.heroBullets= new ArchivContainer<>();
        this.enemyBullets= new ArchivContainer<>();
        this.enemyHits=new LinkedList<>();
        this.usedAbilities= new ArchivContainer<>();
        takenDamage= new ArchivContainer<>();
        this.bounty= new Bounty();
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
    public void heroBorning(HeroSpecyfication hero)
    {
        this.hero=hero;
    }
    public void enemyBorning(EnemySpecyfication enemySpecyfication){this.pushedEnemys.add(enemySpecyfication);}
    public void heroShot(BulletSpecyfication bulletSpecyfication){

        heroBullets.add(bulletSpecyfication);
    }
    public void enemyShot(EnemyShot enemyShot)
    {
       enemyBullets.add(enemyShot);
    }
    public void abilityUse(Ability ability)
    {
    usedAbilities.add(ability);
    }

    public void deathOfEnemy(EnemySpecyfication enemySpecyfication)
    {
        this.deathEnemyList.add(enemySpecyfication);
    }

    public void enemyHited(Hit hit)
    {
        this.enemyHits.add(hit);
    }

    public void takedDamage(MutablePair<Integer,BulletSpecyfication> hit)
    {
        this.takenDamage.add(hit.getRight(),hit.getLeft());
    }

    public int getTakenDamage()
    {
        return this.takenDamage.countAll();
    }

    
    /**Do zaprogramowania*/
    // lista zdobytych obietów i obiekt item
    //lista herosupporters stworzyć herosupporter


    public Bounty getBounty() {
        return bounty;
    }
    public List<EnemySpecyfication> getKilledEnemys()
    {
        return this.deathEnemyList;
    }

    public int getAbilityUseCounter(Ability ability)
    {
        return this.usedAbilities.getUseCounter(ability);
    }
    public int getShootedByHero()
    {
        return this.heroBullets.countAll();
    }
}
