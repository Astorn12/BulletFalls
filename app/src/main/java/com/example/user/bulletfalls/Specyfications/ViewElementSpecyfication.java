package com.example.user.bulletfalls.Specyfications;

import com.example.user.bulletfalls.Enums.BulletSpeciality;
import com.example.user.bulletfalls.GameSupporters.MediumTasks.Named;
import com.example.user.bulletfalls.Specyfications.Characters.CharacterSpecyfication;
import com.example.user.bulletfalls.Objects.ViewElement;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class ViewElementSpecyfication implements Named {
    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = BulletSpeciality.class, name = "bulletspecyfication"),
            @JsonSubTypes.Type(value = CharacterSpecyfication.class, name = "characterspecyfication")

    })
    int power;
    int speed;
    int width;
    int height;
    int imageResources;
    String name;

    public ViewElementSpecyfication(ViewElement viewElement)
    {
        this.power=viewElement.getPower();
        this.speed= viewElement.getSpeed();
        this.width=viewElement.getDimension().getWidth();
        this.height=viewElement.getDimension().getHeight();
        this.imageResources=viewElement.getImageResources();
        this.name=viewElement.getName();
    }

    public ViewElementSpecyfication() {
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

    public int getImageResources() {
        return imageResources;
    }

    public void setImageResources(int imageResources) {
        this.imageResources = imageResources;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
