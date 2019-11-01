package com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive;

import com.example.user.bulletfalls.Game.Elements.Bullet.RotateBullet;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BulletPS.class, name = "bulletps"),

})

public class DynamicPS {
    int speed;

    public DynamicPS(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
