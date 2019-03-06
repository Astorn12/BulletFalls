package com.example.user.bulletfalls.Strategies.Bullet.BulletMoveStrategyPackage;

import android.graphics.Point;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("summondam")
public class SummonDam extends Dam {
    public SummonDam(int position)
    {
        super(position);
    }
    public SummonDam()
    {

    }

    @Override
    public Point getQuantum(int speed, Point current) {

        if(current.x==position)
        {
            return new Point(0,0);
        }
        else
            return new Point(position,0);
    }

}
