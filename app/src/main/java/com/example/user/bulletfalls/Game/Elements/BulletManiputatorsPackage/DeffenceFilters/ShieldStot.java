package com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.DeffenceFilters;

import android.graphics.Point;
import android.view.View;

import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletMoveStrategyPackage.Linear;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.DefenceFilter;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.example.user.bulletfalls.Game.Elements.Weapon.Weapon;
import com.example.user.bulletfalls.Game.Management.CollisionTester;
import com.example.user.bulletfalls.Game.Management.ICollisionable;

public class ShieldStot extends DefenceFilter {

    Weapon weapon;

    public ShieldStot(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void filter(Bullet bullet, Character character) {
        CollisionTester collisionTester= new CollisionTester();
        if (collisionTester.collisionChecking(weapon,bullet)){
            Point weaponMiddlae=weapon.getMiddle();
            Point bulletMiddle=bullet.getMiddle();
            float a=getA(weapon,bullet);
            float b=getB(weapon,bullet);
            bullet.setBulletMoveStrategy(new Linear(a,b));
        }
    }

    private float getB(ICollisionable shield, ICollisionable bullet) {
        return (float)shield.getMiddle().y-getA(shield,bullet)*(float)shield.getMiddle().x;
    }

    private float getA(ICollisionable shield, ICollisionable bullet) {
        return (float)(shield.getMiddle().y-bullet.getMiddle().y)/(float)(shield.getMiddle().x-bullet.getMiddle().x);
    }
}
