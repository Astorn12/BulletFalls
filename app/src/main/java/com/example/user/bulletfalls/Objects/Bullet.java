package com.example.user.bulletfalls.Objects;

import android.content.Context;
import android.graphics.Point;
import android.widget.FrameLayout;

import com.example.user.bulletfalls.Enums.BE;
import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.Enums.Rarity;
import com.example.user.bulletfalls.Enums.Shape;
import com.example.user.bulletfalls.GameManagement.EyeOnGame;
import com.example.user.bulletfalls.GameManagement.Game;
import com.example.user.bulletfalls.Interfaces.PossesAble;
import com.example.user.bulletfalls.Specyfications.Dynamic.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage.BulletDoToCharacterStrategy;
import com.example.user.bulletfalls.Strategies.Bullet.BulletMoveStrategyPackage.BulletMoveStrategy;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.MoneyPossesStrategy;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.PossesStrategy;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("bullet1")
public class Bullet extends Dynamic implements Comparable, PossesAble {
    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = RotateBullet.class, name = "rotate"),

    })


    protected boolean collisionAble;
    BulletMoveStrategy bulletMoveStrategy;
    BulletDoToCharacterStrategy bulletDoToCharacterStrategy;
    Shape shape;

    Permission permission;
    Rarity rarity;
    PossesStrategy possesStrategy;

    public Bullet(String name, Context context, int power, int speed, Point startingPoint, int width, int height,  int imageResource, FrameLayout frame, boolean collisionAble, BulletMoveStrategy bulletMoveStrategy, Shape shape, BulletDoToCharacterStrategy bulletDoToCharacterStrategy, Permission perm, Rarity rarity, PossesStrategy possesStrategy) {
        super(context, power, speed, startingPoint, width, height,  imageResource,frame,name);
        //this.controller=controller;
        this.collisionAble=collisionAble;
        this.bulletMoveStrategy=bulletMoveStrategy;
        this.shape=shape;
        this.bulletDoToCharacterStrategy=bulletDoToCharacterStrategy;
        this.permission=perm;
        this.rarity=rarity;
        this.possesStrategy=possesStrategy;

    } public Bullet(BE be, Context context, int power, int speed, Point startingPoint, int width, int height, int randeringFrequency, int imageResource, FrameLayout frame, boolean collisionAble, BulletMoveStrategy bulletMoveStrategy, Shape shape, BulletDoToCharacterStrategy bulletDoToCharacterStrategy, Permission perm, Rarity rarity, PossesStrategy possesStrategy) {
        this(be.getValue(), context,  power,  speed,  startingPoint,  width,  height,   imageResource,  frame,  collisionAble,  bulletMoveStrategy,  shape,  bulletDoToCharacterStrategy, perm, rarity, possesStrategy);
    }

    public Bullet(Context context, BulletSpecyfication jsonBulletSpecyfication)
    {
        super(context, jsonBulletSpecyfication);
        this.collisionAble= jsonBulletSpecyfication.isCollisionAble();
        this.bulletMoveStrategy= jsonBulletSpecyfication.getBulletMoveStrategy();
        this.shape= jsonBulletSpecyfication.getShape();
        this.bulletDoToCharacterStrategy= jsonBulletSpecyfication.getBulletDoToCharacterStrategy();
        this.permission= jsonBulletSpecyfication.getPermission();
        this.rarity= jsonBulletSpecyfication.getRarity();
        this.possesStrategy= jsonBulletSpecyfication.getPossesStrategy();


    }
    public boolean isCollisionAble() {
        return collisionAble;
    }
    public void setCollisionAble(boolean collisionAble) {
        this.collisionAble = collisionAble;
    }
    public void destroy()
    {
        // controller.removeBullet(this);
         ((Game)getContext()).removeObject(this); }
         @Override
    public void born()
    {
       // if(this.startingPoint!=null)
        appear();
       // else System.out.println("Nazwa felernej kulki: "+ this.getName() );
      //  System.out.println("Born");
      //  System.out.println(this.getName());
        // startMoving();
    }
    @Override
    public void move(EyeOnGame eyeOnGame)
    {
        Point tmp=bulletMoveStrategy.getQuantum(this.speed,new Point((int)getX(),(int)getY()));
        ((Game)this.getContext()).setPoint(this,new Point((int)this.getX()+tmp.x,(int)getY()-tmp.y));
        if(getX()+getWidth()>frame.getWidth()||getX()<0||getY()+getHeight()>frame.getHeight()||getY()<0)
        {
            this.power=0;
        }
    }
    public void outOfScreenChecking()
    {
        if(getX()+getWidth()>frame.getWidth()||getX()<0||getY()+getHeight()>frame.getHeight()||getY()<0)
        {
            destroy();

        }
    }
    public Bullet clone()
    {
        Bullet bullet = new Bullet(this.name,this.getContext(),this.power,this.speed,this.startingPoint,this.width,this.height,imageResources,this.frame,this.collisionAble,this.bulletMoveStrategy.clone(),this.getShape(),this.bulletDoToCharacterStrategy.clone(),this.permission,this.rarity,new MoneyPossesStrategy("Mystery Coin",40));

        return bullet;
    }
    public BulletSpecyfication getJsonBullet()
    {

        return new BulletSpecyfication(this);
    }
    public int collisionWithCharacterEfect(Character character)
    {
        int d= character.getDamage(this.power);
        this.bulletDoToCharacterStrategy.doToCharacter(character);
        return d;

    }
    public void boostPower(int boost)
    {
        this.power+=boost;
    }
    public void boostPower(double boost)
    {
        if(boost>0&&boost<1)
        this.power+=power*boost;
    }


    public BulletMoveStrategy getBulletMoveStrategy() {
        return bulletMoveStrategy;
    }

    public void setBulletMoveStrategy(BulletMoveStrategy bulletMoveStrategy) {
        this.bulletMoveStrategy = bulletMoveStrategy;
    }
    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }


    public BulletDoToCharacterStrategy getBulletDoToCharacterStrategy() {
        return bulletDoToCharacterStrategy;
    }

    public void setBulletDoToCharacterStrategy(BulletDoToCharacterStrategy bulletDoToCharacterStrategy) {
        this.bulletDoToCharacterStrategy = bulletDoToCharacterStrategy;
    }

    public void weaken(int value)
    {
        if(value>0) {
            this.power -= value;
            if(power<0)power=0;
        }
    }
    public void strongen(int value)
    {
        if(value>0)
        {
            this.power+=value;
        }
    }
    public void slowDown(int value)
    {
        if(value>0) {
            this.speed -= value;
            if(speed<0)speed=0;
        }
    }

    public void speedUp(int value)
    {
        if(value>0)
        {
            this.speed+=value;
        }
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Bullet changeContext(Context context)
    {
        return new Bullet(this.name,context,this.power,this.speed,null,this.width,this.height,imageResources,this.frame,this.collisionAble,this.bulletMoveStrategy.clone(),this.getShape(),this.bulletDoToCharacterStrategy.clone(),this.permission,this.rarity,new MoneyPossesStrategy("Mystery Coin",40));

    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    @Override
    public int compareTo(Object o) {
         return ((Bullet)o).rarity.ordinal() > this.rarity.ordinal()? -1:1 ;
    }

    public PossesStrategy getPossesStrategy() {
        return possesStrategy;
    }

    public void setPossesStrategy(PossesStrategy possesStrategy) {
        this.possesStrategy = possesStrategy;
    }

    public BulletSpecyfication getSpecyfication()
    {
        return new BulletSpecyfication(this);
    }
}
