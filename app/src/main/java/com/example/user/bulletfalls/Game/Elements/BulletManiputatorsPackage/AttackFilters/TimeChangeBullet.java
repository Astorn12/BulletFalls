package com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackFilters;

import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackFilter;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class TimeChangeBullet extends AttackFilter {

    private long actingTime;
    long startingTime;
    BulletSpecyfication bulletSpecyfication;

    public TimeChangeBullet(long actingTime, BulletSpecyfication bulletSpecyfication) {
        this.actingTime = actingTime;
        this.bulletSpecyfication = bulletSpecyfication;
    }

    @Override
    public void filter(List<Bullet> bullets, Character character, boolean isShootingTime) {
        if(isShootingTime){
            if(this.startingTime==0)
            {
                this.startingTime=System.currentTimeMillis();
                for(int i=0;i<bullets.size();i++){
                    if(character.isStandardBullet(bullets.get(i))){
                        Bullet newBullet=new Bullet(character.getContext(),this.bulletSpecyfication);
                        bullets.set(i,newBullet);
                        //newBullet.setFrame(((Game)character.getContext()).getController().getGameFrame());
                        //newBullet.born();
                        character.prepateYourBullet(newBullet);
                    }
                }
            }
            else if(System.currentTimeMillis()-this.startingTime>this.actingTime){
                this.setRemovAble(true);
            }
            else{
                for(int i=0;i<bullets.size();i++){
                    if(character.isStandardBullet(bullets.get(i))){
                        Bullet newBullet=new Bullet(character.getContext(),this.bulletSpecyfication);
                        bullets.set(i,newBullet);
                        //newBullet.setFrame(((Game)character.getContext()).getController().getGameFrame());
                        //newBullet.born();
                        character.prepateYourBullet(newBullet);
                    }
                }
            }
        }
    }

    public int getActingTime() {
        return (int)(actingTime/1000);
    }

    public void setActingTime(int actingTime) {
        this.actingTime =1000*actingTime;
    }
}
