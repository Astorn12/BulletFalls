package com.example.user.bulletfalls.Specyfications.Dynamic;

import com.example.user.bulletfalls.Enums.BulletSpeciality;
import com.example.user.bulletfalls.Specyfications.DisplayedSpecyfication;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.CharacterSpecyfication;
import com.example.user.bulletfalls.Objects.Dynamic;
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

    int power;
    int speed;
    int width;
    int height;


    public DynamicSpecyfication(Dynamic dynamic)
    {
        super(dynamic.getName(),dynamic.getImageResources());
        this.power= dynamic.getPower();
        this.speed= dynamic.getSpeed();
        this.width= dynamic.getDimension().getWidth();
        this.height= dynamic.getDimension().getHeight();
    }

    public DynamicSpecyfication(String name, int imageResource, int power, int speed,  int height) {
        super(name, imageResource);
        this.power = power;
        this.speed = speed;
        //this.width = width;
        this.height = height;
        //this.width=30;
    }

    protected DynamicSpecyfication() {
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
