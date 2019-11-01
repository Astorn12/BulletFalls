package com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Active;

import com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletDoToCharacterStrategyPackage.BulletDoToCharacterStrategy;
import com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletMoveStrategyPackage.BulletMoveStrategy;

public class BulletAS {
    BulletMoveStrategy bulletMoveStrategy;
    BulletDoToCharacterStrategy bulletDoToCharacterStrategy;


    public BulletAS(BulletMoveStrategy bulletMoveStrategy, BulletDoToCharacterStrategy bulletDoToCharacterStrategy) {
        this.bulletMoveStrategy = bulletMoveStrategy;
        this.bulletDoToCharacterStrategy = bulletDoToCharacterStrategy;
    }

    public BulletMoveStrategy getBulletMoveStrategy() {
        return bulletMoveStrategy;
    }

    public void setBulletMoveStrategy(BulletMoveStrategy bulletMoveStrategy) {
        this.bulletMoveStrategy = bulletMoveStrategy;
    }

    public BulletDoToCharacterStrategy getBulletDoToCharacterStrategy() {
        return bulletDoToCharacterStrategy;
    }

    public void setBulletDoToCharacterStrategy(BulletDoToCharacterStrategy bulletDoToCharacterStrategy) {
        this.bulletDoToCharacterStrategy = bulletDoToCharacterStrategy;
    }
}
