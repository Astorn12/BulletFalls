package com.example.user.bulletfalls.Game.Elements.Overal.MoveStrategyPackage;

import android.graphics.Point;

import com.example.user.bulletfalls.Game.Management.EyeOnGame;

import org.apache.commons.lang3.NotImplementedException;

public class StaticRottationShooter implements CharacterMoveStrategy{
    @Override
    public int getQuantum(int speed, EyeOnGame gameInterface, Point actualPoint) {
        throw new NotImplementedException("Nie zaimplenemtowana StatucRotationShooter move strateg");
        //return 0;
    }
}
