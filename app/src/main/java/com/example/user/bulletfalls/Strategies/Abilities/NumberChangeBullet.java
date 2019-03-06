package com.example.user.bulletfalls.Strategies.Abilities;

import com.example.user.bulletfalls.Bullet;
import com.example.user.bulletfalls.Character;
import com.example.user.bulletfalls.Interfaces.Observer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("numberchangebullet")
public class NumberChangeBullet extends ChangeBullet implements Observer {
    int clip;
    @JsonIgnore
    int currentAmunition;
    @JsonIgnore
    Character character;
    @JsonIgnore
    Bullet standardBullet;

    public NumberChangeBullet(Bullet bullet, int clip)
    {
        super(bullet);
        this.clip=clip;
    }
    public NumberChangeBullet()
    {

    }
    @Override
    public void doToCharacter(Character character) {
        this.character=character;
        this.standardBullet=character.getBullet();
        character.setBullet(this.getBulletSpecyfication());
        character.addObserver(this);
        this.currentAmunition=clip;

    }

    @Override
    public void update(int progress) {

    }

    public int getClip() {
        return clip;
    }

    public void setClip(int clip) {
        this.clip = clip;
    }

       @Override
    public void announce() {
        this.currentAmunition--;
        if(currentAmunition<=0&&character!=null)
        {
            character.setBullet(this.standardBullet);
            //character.removeObserver(this);
            character.cleanObserver(this);

            character=null;
            standardBullet=null;
        }

    }
}
