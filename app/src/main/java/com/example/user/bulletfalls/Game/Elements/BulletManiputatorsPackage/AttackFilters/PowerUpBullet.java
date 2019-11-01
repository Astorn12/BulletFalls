package com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackFilters;

import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackFilter;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;
@JsonTypeName("attackfilter")
public class PowerUpBullet extends AttackFilter {

    private int boost;

    public PowerUpBullet(int boost) {
        super();
        this.boost = boost;
    }



    @Override
    public void filter(List<Bullet> bullets, Character character,boolean isShootingTime) {
        for(Bullet b:bullets){
            b.increasePower(boost);
        }
    }
}
