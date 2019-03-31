package com.example.user.bulletfalls.Strategies.Abilities;

import com.example.user.bulletfalls.ObjectsOfGame.Bullet;
import com.example.user.bulletfalls.ObjectsOfGame.Character;
import com.example.user.bulletfalls.Specyfications.Bullets.BulletSpecyfication;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=   NumberChangeBullet.class, name = "numberchangebullet"),
        @JsonSubTypes.Type(value=   TimeChangeBullet.class, name = "timerchangebullet")

})
@JsonTypeName("changebullet")
public class ChangeBullet implements DoToCharacterStrategy{
    //Bullet bullet;
    BulletSpecyfication bulletSpecyfication;
    public ChangeBullet()
    {

    }
    public ChangeBullet(Bullet bullet)
    {
        this.bulletSpecyfication=new BulletSpecyfication(bullet);
    }
    @Override
    public void doToCharacter(Character character) {
        character.setBullet(bulletSpecyfication);
    }

    public BulletSpecyfication getBulletSpecyfication() {
        return bulletSpecyfication;
    }

    public void setBulletSpecyfication(BulletSpecyfication bulletSpecyfication) {
        this.bulletSpecyfication = bulletSpecyfication;
    }
}
