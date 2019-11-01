package com.example.user.bulletfalls.Game.Elements.Helper;

import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive.DynamicPS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.View.DynamicVS;
import com.example.user.bulletfalls.GlobalUsage.Enums.BulletSpeciality;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BulletSpeciality.class, name = "bulletspecyfication"),
        @JsonSubTypes.Type(value = CharacterSpecyfication.class, name = "characterspecyfication")

})
@JsonTypeName("dynamicspecyfication")
public class DynamicSpecyfication extends DisplayedSpecyfication {

    protected int height;
    protected int speed;

    public DynamicSpecyfication(String name, DynamicVS dynamicVS, DynamicPS dynamicPS) {
        super(name, dynamicVS.getImageResource());
        this.height = dynamicVS.getHeigt();
        speed=dynamicPS.getSpeed();
    }

    protected DynamicSpecyfication() {
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed=speed;
    }

    public int getHeight() {
        return height;
    }

    public void changeHeight(int height) {
        this.height = height;
    }
}
