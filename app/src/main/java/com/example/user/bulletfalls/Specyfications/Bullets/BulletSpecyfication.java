package com.example.user.bulletfalls.Specyfications.Bullets;

import android.content.Context;

import com.example.user.bulletfalls.Bullet;
import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.Enums.Rarity;
import com.example.user.bulletfalls.Enums.Shape;
import com.example.user.bulletfalls.Specyfications.ViewElementSpecyfication;
import com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage.BulletDoToCharacterStrategy;
import com.example.user.bulletfalls.Strategies.Bullet.BulletMoveStrategyPackage.BulletMoveStrategy;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.PossesStrategy;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RotateBulletSpecyfication.class, name = "rotate"),


})
@JsonTypeName("bullet")
public class BulletSpecyfication extends ViewElementSpecyfication {

    boolean collisionAble;

    BulletMoveStrategy bulletMoveStrategy;
    BulletDoToCharacterStrategy bulletDoToCharacterStrategy;
    Shape shape;
    Permission permission;
    String name;
    Rarity rarity;
    PossesStrategy possesStrategy;
    public BulletDoToCharacterStrategy getBulletDoToCharacterStrategy() {
        return bulletDoToCharacterStrategy;
    }

    public void setBulletDoToCharacterStrategy(BulletDoToCharacterStrategy bulletDoToCharacterStrategy) {
        this.bulletDoToCharacterStrategy = bulletDoToCharacterStrategy;
    }

    public BulletSpecyfication(Bullet bullet) {
        super(bullet);
        this.collisionAble=bullet.isCollisionAble();

        this.bulletMoveStrategy=bullet.getBulletMoveStrategy();
        this.shape=bullet.getShape();
        this.bulletDoToCharacterStrategy=bullet.getBulletDoToCharacterStrategy();
        this.permission=bullet.getPermission();
        this.name=bullet.getName();
        this.rarity=bullet.getRarity();
        this.possesStrategy=bullet.getPossesStrategy();
    }
    public BulletSpecyfication()
    {

    }
    public Bullet getConvertedBullet(Context context)
    {
        return new Bullet(context,this);
    }

    public BulletMoveStrategy getBulletMoveStrategy() {
        return bulletMoveStrategy;
    }

    public void setBulletMoveStrategy(BulletMoveStrategy bulletMoveStrategy) {
        this.bulletMoveStrategy = bulletMoveStrategy;
    }

    public boolean isCollisionAble() {
        return collisionAble;
    }

    public void setCollisionAble(boolean collisionAble) {
        this.collisionAble = collisionAble;
    }
    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public PossesStrategy getPossesStrategy() {
        return possesStrategy;
    }

    public void setPossesStrategy(PossesStrategy possesStrategy) {
        this.possesStrategy = possesStrategy;
    }
}
