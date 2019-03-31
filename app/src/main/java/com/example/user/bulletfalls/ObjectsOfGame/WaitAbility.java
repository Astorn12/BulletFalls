package com.example.user.bulletfalls.ObjectsOfGame;
import com.example.user.bulletfalls.Enums.AE;
import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.Enums.Rarity;
import com.example.user.bulletfalls.Specyfications.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Strategies.Abilities.DoToCharacterStrategy;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.MoneyPossesStrategy;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.PossesStrategy;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("waitability")
public class WaitAbility extends Ability {

    BulletSpecyfication waitedBullet;
    int amount;

    public WaitAbility(String name, int imageResources,int renewalTime, DoToCharacterStrategy doToCharacterStrategy, Permission permission, Rarity rarity, boolean unique, PossesStrategy possesStrategy, BulletSpecyfication waitingBullet, int amount)
    {
        super( name, imageResources, renewalTime, doToCharacterStrategy, permission, rarity, unique, possesStrategy);
        this.waitedBullet=waitingBullet;
        this.amount=amount;
        this.active=false;
    }
    public WaitAbility(AE ae, int imageResources, int renewalTime, DoToCharacterStrategy doToCharacterStrategy, Permission permission, Rarity rarity, boolean unique, PossesStrategy possesStrategy, BulletSpecyfication waitingBullet, int amount)
    {
        super( ae.getValue(), imageResources, renewalTime, doToCharacterStrategy, permission, rarity, unique, possesStrategy);
        this.waitedBullet=waitingBullet;
        this.amount=amount;
        this.active=false;
    }
    public WaitAbility()
    {}

    public BulletSpecyfication getWaitedBullet() {
        return waitedBullet;
    }

    public void setWaitedBullet(BulletSpecyfication waitedBullet) {
        this.waitedBullet = waitedBullet;
    }

    public int getAmount() {
        return amount;
      }
      public void setAmount(int amount) {
        this.amount = amount;
    }

    public void checkIfReady()
    {

    }

    @Override
    public Ability clone()
    {
        return new WaitAbility(this.name,this.imageResources,this.renewalTime,this.doToCharacterStrategy,this.permission,this.rarity,this.unique,new MoneyPossesStrategy("Mystery Coin",30),this.waitedBullet,this.amount);
    }
}
