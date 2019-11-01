package com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletMoveStrategyPackage;

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

        if(current.x+speed<position)
        {
            return new Point(speed,0);
        }
        else if(current.x<position&&current.x+speed>position)
        {
            return new Point(current.x+speed-position,0);
        }
        else
            return new Point(0,0);
    }

}
