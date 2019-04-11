package com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy;

import com.example.user.bulletfalls.Objects.Bullet;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("stot")
public class Stot implements DoToBulletStrategy {


    int boost;
    public Stot(int boost)
    {
    this.boost=boost;
    }
    public Stot()
    {

    }
    @Override
    public void doToBullet(Bullet bullet) {

        bullet.setSpeed(bullet.getSpeed()*(-1));
        bullet.boostPower(this.boost);
    }
    public int getBoost() {
        return boost;
    }

    public void setBoost(int boost) {
        this.boost = boost;
    }
    @Override
    public void boostAtribute(int boost)
    {
        setBoost(getBoost()+boost);
    }

}
