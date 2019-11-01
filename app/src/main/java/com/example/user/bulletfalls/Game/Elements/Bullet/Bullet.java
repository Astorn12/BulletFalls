package com.example.user.bulletfalls.Game.Elements.Bullet;

import android.content.Context;
import android.graphics.Point;
import android.widget.FrameLayout;

import com.example.user.bulletfalls.Game.Elements.Helper.Sizers.BulletScale;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Active.BulletAS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Collection.BulletCS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive.BulletPS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.View.DynamicVS;
import com.example.user.bulletfalls.GlobalUsage.Enums.BE;
import com.example.user.bulletfalls.GlobalUsage.Enums.Permission;
import com.example.user.bulletfalls.GlobalUsage.Enums.Rarity;
import com.example.user.bulletfalls.GlobalUsage.Enums.Shape;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.example.user.bulletfalls.Game.Elements.Helper.Dynamic;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.GlobalUsage.Interfaces.PossesAble;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletDoToCharacterStrategyPackage.BulletDoToCharacterStrategy;
import com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletMoveStrategyPackage.BulletMoveStrategy;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.MoneyPossesStrategy;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.PossesStrategy;
import com.example.user.bulletfalls.Storage.Sets.BulletSet;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("bullet1")
public class Bullet extends Dynamic implements Comparable {
    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = RotateBullet.class, name = "rotate"),

    })


    /**VIEW STATISTICS*/

    /**PASSIVE STATISTICS*/
    boolean collisionAble;
    Shape shape;
    int power;
    BulletScale bulletScale;

    /**ACTIVE STATISTICS*/
    BulletMoveStrategy bulletMoveStrategy;
    BulletDoToCharacterStrategy bulletDoToCharacterStrategy;

    /**COLLECTION STATISTICS*/
    Rarity rarity;
    PossesStrategy possesStrategy;


    Point startingCoordinates;
    public Bullet(Context context,BulletSpecyfication specyfication){
        super(context,specyfication);

        this.collisionAble=specyfication.isCollisionAble();
        this.shape=specyfication.getShape();
        this.power=specyfication.getPower();
        this.bulletScale=specyfication.getBulletScale();

        this.bulletMoveStrategy=specyfication.getBulletMoveStrategy();
        this.bulletDoToCharacterStrategy=specyfication.getBulletDoToCharacterStrategy();

        this.rarity=specyfication.getRarity();
        this.possesStrategy=specyfication.getPossesStrategy();

    }



    /**BIZNES METHODS*/

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

    @Override
    public void setStartingPoint() {
        this.setX(this.startingCoordinates.x);
        this.setY(this.startingCoordinates.y);
    }

    @Override
    public Object clone() {
        return new Bullet(this.getContext(),this.getSpecyfication());
    }

    public BulletSpecyfication getSpecyfication(){
        System.out.println(this.name);

        BE be=BE.getEnumByString(this.name);
        return new BulletSpecyfication(be,
                new DynamicVS(this.image,this.height),
                new BulletPS(this.speed,this.shape,this.collisionAble,this.power,this.bulletScale),
                new BulletAS(this.bulletMoveStrategy,this.bulletDoToCharacterStrategy),
                new BulletCS(this.getRarity(),this.possesStrategy));
    }

    public int collisionWithCharacterEfect(Character character)
    {
        int d= character.getDamage(this.power);
        this.bulletDoToCharacterStrategy.doToCharacter(character);
        return d;
    }
    public void changeDirection(){
        this.speed*=-1;
    }


    @Override
    public int compareTo(Object o) {
        return ((Bullet)o).rarity.ordinal() > this.rarity.ordinal()? -1:1 ;
    }
    /**INCREASE & DECREASE METHODS*/
    public void increasePower(int boost)
    {
        this.power+=boost;
    }
    public void increasePower(double boost)
    {
        if(boost>0&&boost<1)
        this.power+=power*boost;
    }

    public void decreasePower(int value)
    {
        if(value>0) {
            this.power -= value;
            if(power<0)power=0;
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
    public void multiplyPower( float f){
        this.power*=f;
    }

    /**GETTERS & SETTERS*/

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

    public BulletScale getBulletScale() {
        return bulletScale;
    }

    public void setBulletScale(BulletScale bulletScale) {
        this.bulletScale = bulletScale;
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

    public Point getStartingCoordinates() {
        return startingCoordinates;
    }

    public void setStartingCoordinates(Point startingCoordinates) {
        this.startingCoordinates = startingCoordinates;
    }
}
