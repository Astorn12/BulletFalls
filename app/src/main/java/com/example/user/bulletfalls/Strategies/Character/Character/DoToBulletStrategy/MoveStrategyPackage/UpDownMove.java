package com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.MoveStrategyPackage;

import android.graphics.Point;

import com.example.user.bulletfalls.GameManagement.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("updownmove")
public class UpDownMove implements  CharacterMoveStrategy {
    @Override
    public int getQuantum(int speed,EyeOnGame gameController,Point p) {
        return speed;
    }
}
