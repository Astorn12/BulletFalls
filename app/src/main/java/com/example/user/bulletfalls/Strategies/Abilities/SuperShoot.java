package com.example.user.bulletfalls.Strategies.Abilities;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.widget.LinearLayout;

import com.example.user.bulletfalls.Objects.Bullet;
import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.GameManagement.Game;
import com.example.user.bulletfalls.Specyfications.Dynamic.Bullets.BulletSpecyfication;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("supershoot")
public class SuperShoot implements DoToCharacterStrategy{
    BulletSpecyfication bulletSpecyfication;
    String name;
    public SuperShoot(Bullet bullet)
    {
        this.bulletSpecyfication =new BulletSpecyfication(bullet);

    }
    public SuperShoot(){}
    @Override
    public void doToCharacter(Character character) {

        AnimationDrawable a= character.superShootAnimation();
        if(a!=null)
     checkIfAnimationDoner(character.superShootAnimation(), character);
        else superShoot(character);


    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setAdditionalDescription(LinearLayout linearLayout) {

    }

    public BulletSpecyfication getBulletSpecyfication() {
        return bulletSpecyfication;
    }

    public void setBulletSpecyfication(BulletSpecyfication bulletSpecyfication) {
        this.bulletSpecyfication = bulletSpecyfication;
    }

    private void checkIfAnimationDoner(AnimationDrawable anim, final Character character){
        final AnimationDrawable a = anim;
        int timeBetweenChecks = 30;
        Handler h = new Handler();

        h.postDelayed(new Runnable(){
            public void run(){
                if (a.getCurrent() != a.getFrame(a.getNumberOfFrames() - 1)){
                    checkIfAnimationDoner(a, character);
                } else{
                superShoot(character);
                }
            }
        }, timeBetweenChecks);
    }

    public void superShoot(Character character)
    {
        Bullet bulletn= bulletSpecyfication.getConvertedBullet(character.getContext()).clone();
        bulletn.setStartingPoint(character.getStartingPointForBullet());
        character.execute();
        bulletn.born();
        bulletn.setFrame(character.getFrame());
        ((Game) character.getContext()).getController().addBullet(bulletn);
        character.setShootAble(true);
        character.setMoveAble(true);
    }


}
