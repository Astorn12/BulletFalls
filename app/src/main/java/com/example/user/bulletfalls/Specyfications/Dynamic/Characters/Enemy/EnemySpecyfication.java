package com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy;

import com.example.user.bulletfalls.Enums.CharacterPositioning;
import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.Enums.Kind;
import com.example.user.bulletfalls.GameBiznesFunctions.Resistance.IResistance;
import com.example.user.bulletfalls.Objects.Description;
import com.example.user.bulletfalls.Objects.Enemy;
import com.example.user.bulletfalls.Specyfications.Dynamic.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.CharacterSpecyfication;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.AppearActionStrategy.AppearAction;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.CharacterMoveStrategiesPackage.DoToBulletStrategy;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.MoveStrategyPackage.CharacterMoveStrategy;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

@JsonTypeName("enemyspecyfication")
public class EnemySpecyfication extends CharacterSpecyfication {

    int killValue;
    CharacterMoveStrategy moveStrategy;
    public EnemySpecyfication(Enemy enemy) {
        super(enemy);

        this.killValue= enemy.getKillValue();
        this.moveStrategy= enemy.getMoveStrategy();
    }

    public EnemySpecyfication(String name, int imageResource, int power, int speed, int height, int life, int shootingSpeed, int level, IResistance resistance, BulletSpecyfication bulletSpecyfication, Kind kind, List<GroupName> groupNames, CharacterPositioning characterPositioning, DoToBulletStrategy doToBulletStrategy, String indyvidualHeroMarker, Description description, int miniature, AppearAction appearAction, int killValue, CharacterMoveStrategy moveStrategy) {
        super(name, imageResource, power, speed, height, life, shootingSpeed, level, resistance, bulletSpecyfication, kind, groupNames, characterPositioning, doToBulletStrategy,  indyvidualHeroMarker,  description, miniature, appearAction);
        this.killValue = killValue;
        this.moveStrategy = moveStrategy;
    }

    private EnemySpecyfication() { }

    public int getKillValue() {
        return killValue;
    }

    public void setKillValue(int killValue) {
        this.killValue = killValue;
    }

    public CharacterMoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public void setMoveStrategy(CharacterMoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }






}
