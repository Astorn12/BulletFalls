package com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackFilters;

import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackFilter;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;

import java.util.List;

public class ShootGun extends AttackFilter {
    List<Bullet> shootGunLoading;

    public ShootGun(List<Bullet> bullets) {

        this.shootGunLoading = bullets;
    }
    @Override
    public void filter(List<Bullet> bullets, Character character, boolean isShootingTime) {
            for(Bullet b:this.shootGunLoading){
                bullets.add(b);
            }

        this.setRemovAble(true);
    }
}
