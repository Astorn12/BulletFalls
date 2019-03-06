package com.example.user.bulletfalls.Specyfications;

import com.example.user.bulletfalls.Enums.BulletSpeciality;
import com.example.user.bulletfalls.Specyfications.Characters.CharacterSpecyfication;
import com.example.user.bulletfalls.ViewElement;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BulletSpeciality.class, name = "bullet"),
        @JsonSubTypes.Type(value = CharacterSpecyfication.class, name = "character")

})
public class ViewElementSpecyfication {

    int power;
    int speed;
    int width;
    int height;
    int imageResources;

    public ViewElementSpecyfication(ViewElement viewElement)
    {
        this.power=viewElement.getPower();
        this.speed= viewElement.getSpeed();
        this.width=viewElement.getDimension().getWidth();
        this.height=viewElement.getDimension().getHeight();
        this.imageResources=viewElement.getImageResources();
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
}
