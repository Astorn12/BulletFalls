package com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletMoveStrategyPackage;

import android.graphics.Point;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
@JsonTypeName("throw")
public class Throw implements BulletMoveStrategy {
   int angle;
    @JsonIgnore
    int timer;
    public Throw(int angle)
    {

        this.angle=angle;
        this.timer=0;
    }
    private Throw()
    {
    timer=0;
    }


    @Override
    public Point getQuantum(int speed,Point current) {

        Point point=new Point();
        point.x=(int)(speed*Math.cos(Math.toRadians(angle)));

        point.y=(int)(speed*Math.sin(Math.toRadians(angle))-timer*0.2);
        timer++;
        return point;
    }

    @Override
    public BulletMoveStrategy clone() {
        return new Throw(this.angle);
    }

    @Override
    public String describe() {
        return "Throw";
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}
