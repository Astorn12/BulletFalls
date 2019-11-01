package com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.DeffenceFilters;

import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.DefenceFilter;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;

public class Stot extends DefenceFilter {
    int boost;

    public Stot(int boost)
    {
        this.boost=boost;
    }

    @Override
    public void filter(Bullet bullet, Character character) {
        bullet.setSpeed(bullet.getSpeed()*(-1));
        bullet.increasePower(this.boost);
    }

    public int getBoost() {
        return boost;
    }
    public void setBoost(int boost) {
        this.boost = boost;
    }
    public void boostAtribute(int boost)
    {
        setBoost(getBoost()+boost);
    }
}
