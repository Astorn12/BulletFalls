package com.example.user.bulletfalls.Objects;

import android.content.Context;
import android.graphics.Point;
import android.widget.FrameLayout;

import com.example.user.bulletfalls.Enums.CharacterPositioning;
import com.example.user.bulletfalls.Enums.EBeastType;
import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.Enums.Kind;
import com.example.user.bulletfalls.GameManagement.Game;
import com.example.user.bulletfalls.Interfaces.Observer;
import com.example.user.bulletfalls.Specyfications.Characters.CharacterSpecyfication;
import com.example.user.bulletfalls.Specyfications.Characters.SummonedBeastSpecyfication;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.DoToBulletStrategy;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;
@JsonTypeName("summonedbeast")
public class SummonedBeast extends Character{

    EBeastType eBeastType;

    public SummonedBeast(Context context, int power, int speed, Point startingPoint, int width, int height, int randeringFrequency, int imageResource, FrameLayout frame, int life, int shootingSpeed, int level, int resistance, Bullet bullet, String name, Kind kind, List<GroupName> groupNames, CharacterPositioning position, DoToBulletStrategy doToBulletStrategy, String indyvidualHeroMarker, Description description,EBeastType eBeastType) {
        super(context, power, speed,  width, height, randeringFrequency, imageResource, frame, life, shootingSpeed, level, resistance, bullet, name, kind, groupNames, position, doToBulletStrategy, indyvidualHeroMarker, description);

        this.eBeastType=eBeastType;
    }

    public SummonedBeast(Context context, SummonedBeastSpecyfication sbs) {
        super(context, sbs);
        this.eBeastType=sbs.geteBeastType();
    }

    @Override
    protected Bullet shoot() {


        shootingCounter+=1;
        if(shootingCounter%shootingSpeed==0)
        {

            Bullet enemyBullet=(Bullet)this.bullet.clone();
            enemyBullet.startingPoint=this.getStartingPointForBullet();
            enemyBullet.born();
            enemyBullet.setFrame(this.frame);
            return enemyBullet;
        }
        else return null;
    }

    @Override
    public Point getStartingPointForBullet() {
        switch(this.eBeastType)
        {
            case ENEMY:
                return new Point((int)(getX()-getWidth()),(int)(getY()+getHeight()/2));

            case HERO:
                return new Point((int)(getX()+getWidth()),(int)(getY()+getHeight()/2));
            default:
                return null;
        }
    }

    @Override
    public void died() {
        ((Game)getContext()).removeLifeText(this.textLife);
        ((Game)getContext()).removeObject(this);
    }
    @Override
    public CharacterSpecyfication getSpecyfication() {
        return new SummonedBeastSpecyfication(this);
    }

    @Override
    public void addObserver(Observer observer) {

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void execute() {

    }

    public EBeastType geteBeastType() {
        return eBeastType;
    }

    public void seteBeastType(EBeastType eBeastType) {
        this.eBeastType = eBeastType;
    }

    public SummonedBeast clone()
    {
        return new SummonedBeast(this.getContext(),  power, speed,  startingPoint,  width,  height,  randeringFrequency,  this.getImageResources(), frame,  life, shootingSpeed,  level, resistance,  bullet, name,  kind,  groupNames, position,  doToBulletStrategy,  indyvidualHeroMarker,  description,this.eBeastType);
    }
    @Override
    protected void move()
    {
        uploatLifeView();
        ((Game)this.getContext()).moveViewElement(this,0,speed);
        if(this.getY()<0-speed)
        {
            ((Game) this.getContext()).setViewElement(this,(int)this.getX(),0);
            this.speed*=-1;
        }
        else if (getY()>frame.getHeight()-this.getHeight()-speed)
        {
            ((Game)this.getContext()).setViewElement(this,(int)this.getX(),frame.getHeight() - this.getHeight());
            this.speed*=-1;
        }
    }
    @Override
    public void born()
    {
        super.born();
        this.moveAble=true;
    }
}
