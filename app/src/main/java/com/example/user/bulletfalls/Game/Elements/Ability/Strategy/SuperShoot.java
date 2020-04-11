package com.example.user.bulletfalls.Game.Elements.Ability.Strategy;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("supershoot")
public class SuperShoot implements StartAction {
    private BulletSpecyfication bulletSpecyfication;
    String name;
    public SuperShoot(BulletSpecyfication bullet)
    {
        this.bulletSpecyfication =bullet;

    }
    public SuperShoot(){}
    @Override
    public Action prepareAction(EyeOnGame eyeOnGame) {

        /*AnimationDrawable a= eyeOnGame.superShootAnimation();
        if(a!=null)
     checkIfAnimationDoner(eyeOnGame.superShootAnimation(), eyeOnGame);
        else superShoot(eyeOnGame);
        */

        return new com.example.user.bulletfalls.Game.ActionService.Actions.AbilitysActions.SuperShoot(ActionType.INNER,this.bulletSpecyfication.clone());

    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setAdditionalDescription(LinearLayout linearLayout) {

    }

    public BulletSpecyfication getBulletSpecyfication() {
        return bulletSpecyfication.clone();
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
        Bullet bulletn= new Bullet(character.getContext(),this.bulletSpecyfication.clone());
        //bulletn.setStartingPoint(character.getStartingPointForBullet());

        bulletn.born();
        bulletn.setFrame(character.getFrame());
        ((Game) character.getContext()).getController().addBullet(bulletn);
        character.setShootAble(true);
        character.setMoveAble(true);
    }


}
