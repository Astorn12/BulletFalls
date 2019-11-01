package com.example.user.bulletfalls.Game.Elements.Ability.Specyfication;
import com.example.user.bulletfalls.GlobalUsage.Enums.AE;
import com.example.user.bulletfalls.GlobalUsage.Enums.Permission;
import com.example.user.bulletfalls.GlobalUsage.Enums.Rarity;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.StartAction;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.MoneyPossesStrategy;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.PossesStrategy;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("waitability")
public class WaitAbilitySpecyfication extends AbilitySpecyfication {

    BulletSpecyfication waitedBulletSpecyfication;
    int amount;

    public WaitAbilitySpecyfication(String name, int imageResources, int renewalTime, StartAction startAction , Rarity rarity, boolean unique, PossesStrategy possesStrategy, BulletSpecyfication waitingBulletSpecyfication, int amount)
    {
        super( name, imageResources, renewalTime, startAction,  rarity, unique, possesStrategy);
        this.waitedBulletSpecyfication = waitingBulletSpecyfication;
        this.amount=amount;
        this.active=false;
    }
    public WaitAbilitySpecyfication(AE ae, int imageResources, int renewalTime, StartAction startAction , Rarity rarity, boolean unique, PossesStrategy possesStrategy, BulletSpecyfication waitingBulletSpecyfication, int amount)
    {
        super( ae.getValue(), imageResources, renewalTime, startAction, rarity, unique, possesStrategy);
        this.waitedBulletSpecyfication = waitingBulletSpecyfication;
        this.amount=amount;
        this.active=false;
    }
    public WaitAbilitySpecyfication()
    {}

    public BulletSpecyfication getWaitedBulletSpecyfication() {
        return waitedBulletSpecyfication;
    }

    public void setWaitedBulletSpecyfication(BulletSpecyfication waitedBulletSpecyfication) {
        this.waitedBulletSpecyfication = waitedBulletSpecyfication;
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
    public AbilitySpecyfication clone()
    {
        return new WaitAbilitySpecyfication(this.getName(),this.getImage(),this.getRenewalTime(),this.getStartAction(),this.getRarity(),this.isUnique(),new MoneyPossesStrategy("Mystery Coin",30),this.waitedBulletSpecyfication,this.amount);
    }
}
