package com.example.user.bulletfalls.Game.Elements.Overal.MoveStrategyPackage;

import android.graphics.Point;

import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("follower")
public class Follower implements  CharacterMoveStrategy{


    public Follower() {

    }

    @Override
    public int getQuantum(int speed, EyeOnGame gameInterface, Point actualPosition) {

        if(gameInterface.getHeroPosition().y<actualPosition.y)
        {
            if(speed>0) {
                if(actualPosition.y+speed<gameInterface.getHeroPosition().y)
                {
                    return gameInterface.getHeroPosition().y-actualPosition.y;
                }
                else
                return speed * (-1);
            }
            else {
                if(actualPosition.y+speed<gameInterface.getHeroPosition().y)
                {
                    return actualPosition.y-gameInterface.getHeroPosition().y;
                }
                else
                return speed;
            }
        }
        else if(gameInterface.getHeroPosition().y>actualPosition.y) {
            if(speed>0) {
                if(actualPosition.y+speed>gameInterface.getHeroPosition().y)
                {
                    return gameInterface.getHeroPosition().y-actualPosition.y;
                }
                else
                    return speed;
            }
            else {
                if(actualPosition.y+speed<gameInterface.getHeroPosition().y)
                {
                    return actualPosition.y-gameInterface.getHeroPosition().y;
                }
                else
                    return speed*(-1);
            }
        }
        else return 0; /**Przypadek w którym są na tym samym poziomie*/
    }
}
