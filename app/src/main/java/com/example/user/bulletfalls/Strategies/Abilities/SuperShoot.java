package com.example.user.bulletfalls.Strategies.Abilities;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;

import com.example.user.bulletfalls.Bullet;
import com.example.user.bulletfalls.Character;
import com.example.user.bulletfalls.Game;
import com.example.user.bulletfalls.Specyfications.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.ViewElement;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("supershoot")
public class SuperShoot implements DoToCharacterStrategy{
    BulletSpecyfication bullet;
    String name;
    public SuperShoot(Bullet bullet)
    {
        this.bullet=new BulletSpecyfication(bullet);

    }
    public SuperShoot(){}
    @Override
    public void doToCharacter(Character character) {

        AnimationDrawable a=character.superShootAnimation();
        if(a!=null)
     checkIfAnimationDoner(character.superShootAnimation(),character);
        else superShoot(character);


    }

    public BulletSpecyfication getBullet() {
        return bullet;
    }

    public void setBullet(BulletSpecyfication bullet) {
        this.bullet = bullet;
    }

    private void checkIfAnimationDoner(AnimationDrawable anim, final Character character){
        final AnimationDrawable a = anim;
        int timeBetweenChecks = 30;
        Handler h = new Handler();

        h.postDelayed(new Runnable(){
            public void run(){
                if (a.getCurrent() != a.getFrame(a.getNumberOfFrames() - 1)){
                    checkIfAnimationDoner(a,character);
                } else{
                superShoot(character);
                }
            }
        }, timeBetweenChecks);
    }

    public void superShoot(Character character)
    {
        Bullet bulletn=bullet.getConvertedBullet(character.getContext()).clone();
        bulletn.setStartingPoint(character.getStartingPointForBullet());
        character.execute();
        bulletn.born();
        bulletn.setFrame(character.getFrame());
        ((Game)character.getContext()).getController().addBullet(bulletn);
        character.setShootAble(true);
        character.setMoveAble(true);
    }


}
