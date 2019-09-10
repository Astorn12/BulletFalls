package com.example.user.bulletfalls.Strategies.Bullet.BulletMoveStrategyPackage;


import android.graphics.Point;
import android.widget.ImageView;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=Horizontal.class, name = "horizontal"),
        @JsonSubTypes.Type(value=Throw.class, name = "throw"),
        @JsonSubTypes.Type(value=Dam.class, name = "dam")
})
public interface BulletMoveStrategy {

     Point getQuantum(int speed,Point current);
     BulletMoveStrategy clone();
      String describe();
}
