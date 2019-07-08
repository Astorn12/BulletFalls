package com.example.user.bulletfalls.Objects;
import com.example.user.bulletfalls.Enums.AE;
import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.Enums.Rarity;
import com.example.user.bulletfalls.Specyfications.AbilitySpecyfication;
import com.example.user.bulletfalls.Specyfications.Dynamic.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Strategies.Abilities.DoToCharacterStrategy;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.MoneyPossesStrategy;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.PossesStrategy;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("waitability")
public class WaitAbilitySpecyfication extends AbilitySpecyfication {

    BulletSpecyfication waitedBulletSpecyfication;
    int amount;

    public WaitAbilitySpecyfication(String name, int imageResources, int renewalTime, DoToCharacterStrategy doToCharacterStrategy, Permission permission, Rarity rarity, boolean unique, PossesStrategy possesStrategy, BulletSpecyfication waitingBulletSpecyfication, int amount)
    {
        super( name, imageResources, renewalTime, doToCharacterStrategy, permission, rarity, unique, possesStrategy);
        this.waitedBulletSpecyfication = waitingBulletSpecyfication;
        this.amount=amount;
        this.active=false;
    }
    public WaitAbilitySpecyfication(AE ae, int imageResources, int renewalTime, DoToCharacterStrategy doToCharacterStrategy, Permission permission, Rarity rarity, boolean unique, PossesStrategy possesStrategy, BulletSpecyfication waitingBulletSpecyfication, int amount)
    {
        super( ae.getValue(), imageResources, renewalTime, doToCharacterStrategy, permission, rarity, unique, possesStrategy);
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
        return new WaitAbilitySpecyfication(this.getName(),this.getImageResources(),this.getRenewalTime(),this.getDoToCharacterStrategy(),this.getPermission(),this.getRarity(),this.isUnique(),new MoneyPossesStrategy("Mystery Coin",30),this.waitedBulletSpecyfication,this.amount);
    }
}
