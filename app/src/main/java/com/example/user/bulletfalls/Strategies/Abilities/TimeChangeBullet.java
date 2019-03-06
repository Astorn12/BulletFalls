package com.example.user.bulletfalls.Strategies.Abilities;

import com.example.user.bulletfalls.Bullet;
import com.example.user.bulletfalls.Character;
import com.example.user.bulletfalls.Interfaces.Observer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("timechangebullet")

public class TimeChangeBullet extends ChangeBullet implements Observer {
    long actingTime;
    @JsonIgnore
    long startingTime;
    @JsonIgnore
    Character character;
    @JsonIgnore
    Bullet standardBullet;
    public TimeChangeBullet(Bullet bullet, int actingTime)
    {
        super(bullet);
       this.setActingTime(actingTime);
    }

    public TimeChangeBullet()
    {

    }

    @Override
    public void doToCharacter(Character character) {
        this.character=character;
        this.standardBullet=character.getBullet();
        character.setBullet(this.getBulletSpecyfication());
        character.addObserver(this);
        this.startingTime=System.currentTimeMillis();
    }


    public int getActingTime() {
        return (int)(actingTime/1000);
    }

    public void setActingTime(int actingTime) {
        this.actingTime =1000*actingTime;
    }


    @Override
    public void update(int progress) {

    }

    @Override
    public void announce() {
       // System.out.println(System.currentTimeMillis()-this.startingTime+" > "+actingTime);
        if(System.currentTimeMillis()-this.startingTime>=this.actingTime&&character!=null)
        {
            character.setBullet(this.standardBullet);
            //character.removeObserver(this);
            character.cleanObserver(this);

            character=null;
            standardBullet=null;
            //this.startingTime=0;
        }

    }
}
