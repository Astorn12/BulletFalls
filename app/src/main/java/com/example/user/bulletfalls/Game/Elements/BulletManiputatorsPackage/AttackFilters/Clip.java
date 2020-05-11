package com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackFilters;

import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackFilter;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;

import java.util.List;

public class Clip extends AttackFilter {
    int bulletAmount;
    BulletSpecyfication bs;

    public Clip(int bulletAmount, BulletSpecyfication bs) {
        this.bulletAmount = bulletAmount;
        this.bs = bs;
    }

    @Override
    public void filter(List<Bullet> bullets, Character character,boolean isShootingTime) {
        for(int i=0;i<bullets.size();i++){
            if(bulletAmount<=0) {
                this.setRemovAble(true);
                break;
            }
            Bullet oldBullet=bullets.get(i);
            if(character.isStandardBullet(oldBullet)){
                Bullet newBullet=new Bullet(oldBullet.getContext(),this.bs);
                newBullet.setStartingCoordinates(oldBullet.getStartingCoordinates());
                bullets.set(i,newBullet);
            }
            this.bulletAmount--;
        }
    }
}