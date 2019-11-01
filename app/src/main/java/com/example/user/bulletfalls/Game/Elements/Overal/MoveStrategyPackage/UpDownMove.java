package com.example.user.bulletfalls.Game.Elements.Overal.MoveStrategyPackage;

import android.graphics.Point;

import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("updownmove")
public class UpDownMove implements  CharacterMoveStrategy {
    @Override
    public int getQuantum(int speed,EyeOnGame gameController,Point p) {
        return speed;
    }
}
