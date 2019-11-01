package com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackFilters;

import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.PositioningSupporters.MultiBulletPositioner;
import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackFilter;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;

import java.util.List;

public class MultiShoot extends AttackFilter {

    int amountOfBullets;
    BulletSpecyfication bulletSpecyfication;

    public MultiShoot(int amountOfBullets, BulletSpecyfication bulletSpecyfication) {
        this.amountOfBullets = amountOfBullets;
        this.bulletSpecyfication = bulletSpecyfication;
    }

    @Override
    public void filter(List<Bullet> bullets, Character character,boolean isShootingTime) {
        MultiBulletPositioner m= new MultiBulletPositioner();
        List<Bullet> newBullets=m.steadilyVerticalPositioning(new Bullet(character.getContext(),bulletSpecyfication),amountOfBullets,character);
        for(Bullet b:newBullets){
            bullets.add(b);
            b.setFrame(((Game)b.getContext()).getController().getGameFrame());
            b.born();

        }
        this.setRemovAble(true);
    }
}
