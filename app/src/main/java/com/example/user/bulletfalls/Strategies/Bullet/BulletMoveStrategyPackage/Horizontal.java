package com.example.user.bulletfalls.Strategies.Bullet.BulletMoveStrategyPackage;

import android.graphics.Point;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("horizontal")
public class Horizontal implements BulletMoveStrategy {



    public Horizontal() {

    }
    @Override
    public Point getQuantum(int speed,Point current) {

        Point point=new Point();
        point.x=speed;
        point.y=0;

        return point;
    }

    @Override
    public BulletMoveStrategy clone() {
        return new Horizontal();
    }

    @Override
    public String describe() {
        return "Horizontal";
    }
}
