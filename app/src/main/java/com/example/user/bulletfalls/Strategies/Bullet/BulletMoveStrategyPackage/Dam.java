package com.example.user.bulletfalls.Strategies.Bullet.BulletMoveStrategyPackage;

import android.graphics.Point;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=TimeDam.class, name = "timedam"),
        @JsonSubTypes.Type(value=SummonDam.class, name = "summondam")

})
@JsonTypeName("dam")
public class Dam implements BulletMoveStrategy{

    int position;



    public Dam()
    {


    }
    public Dam(int position)
    {
        this.position=position;
    }

    @Override
    public Point getQuantum(int speed,Point current) {
        if(current.x+speed>position)
        {
            return new Point(position-current.x,0);
        }
        else
        {

            return new Point(speed,0);
        }
    }

    @Override
    public BulletMoveStrategy clone() {
        return new Dam(this.position);
    }
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
