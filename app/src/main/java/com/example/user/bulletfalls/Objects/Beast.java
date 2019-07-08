package com.example.user.bulletfalls.Objects;

import android.content.Context;
import android.graphics.Point;
import android.widget.FrameLayout;

import com.example.user.bulletfalls.Enums.CharacterPositioning;
import com.example.user.bulletfalls.Enums.EBeastType;
import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.Enums.Kind;
import com.example.user.bulletfalls.GameBiznesFunctions.Resistance.IResistance;
import com.example.user.bulletfalls.GameManagement.EyeOnGame;
import com.example.user.bulletfalls.GameManagement.Game;
import com.example.user.bulletfalls.Interfaces.Observer;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.CharacterSpecyfication;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.BeastSpecyfication;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.AppearActionStrategy.AppearAction;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.CharacterMoveStrategiesPackage.DoToBulletStrategy;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;
@JsonTypeName("beast")
public class Beast extends Character {

    EBeastType eBeastType;

    public Beast(Context context, int power, int speed, Point startingPoint, int width, int height, int imageResource, FrameLayout frame, int life, int shootingSpeed, int level, IResistance resistance, Bullet bullet, String name, Kind kind, List<GroupName> groupNames, CharacterPositioning position, DoToBulletStrategy doToBulletStrategy, String indyvidualHeroMarker, Description description, EBeastType eBeastType, AppearAction aa) {
        super(context, power, speed,  width, height,  imageResource, frame, life, shootingSpeed, level, resistance, bullet, name, kind, groupNames, position, doToBulletStrategy, indyvidualHeroMarker, description,aa);

        this.eBeastType=eBeastType;
    }

    public Beast(Context context, BeastSpecyfication sbs) {
        super(context, sbs);
        this.eBeastType=sbs.geteBeastType();
    }

    @Override
    protected Bullet shoot() {
        shootingCounter+=1;
        if(shootingCounter%shootingSpeed==0)
        {
            Bullet enemyBullet =(Bullet)this.bullet.clone();
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
        return new BeastSpecyfication(this);
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

    public Beast clone()
    {
        return new Beast(this.getContext(),  power, speed,  startingPoint,  width,  height,    this.getImageResources(), frame,  life, shootingSpeed,  level, resistance, bullet, name,  kind,  groupNames, position,  doToBulletStrategy,  indyvidualHeroMarker,  description,this.eBeastType,this.getAppearAction());
    }
    @Override
    protected void move(EyeOnGame eyeOnGame)
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
