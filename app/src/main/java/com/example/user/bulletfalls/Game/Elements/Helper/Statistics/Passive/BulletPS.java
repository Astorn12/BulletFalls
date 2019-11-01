package com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive;

import com.example.user.bulletfalls.Game.Elements.Helper.Dynamic;
import com.example.user.bulletfalls.Game.Elements.Helper.Sizers.BulletScale;
import com.example.user.bulletfalls.GlobalUsage.Enums.Shape;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("buleltps")
public class BulletPS extends DynamicPS {

    Shape shape;
    boolean collisionAble;
    int power;
    BulletScale bulletScale;


    public BulletPS(int speed, Shape shape, boolean collisionAble, int power, BulletScale bulletScale) {
        super(speed);
        this.shape = shape;
        this.collisionAble = collisionAble;
        this.power = power;
        this.bulletScale = bulletScale;
    }

    /**GETTERS & SETTERS*/
    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public boolean isCollisionAble() {
        return collisionAble;
    }

    public void setCollisionAble(boolean collisionAble) {
        this.collisionAble = collisionAble;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public BulletScale getBulletScale() {
        return bulletScale;
    }

    public void setBulletScale(BulletScale bulletScale) {
        this.bulletScale = bulletScale;
    }
}
