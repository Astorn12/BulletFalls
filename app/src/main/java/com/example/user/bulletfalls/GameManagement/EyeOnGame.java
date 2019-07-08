package com.example.user.bulletfalls.GameManagement;

import android.graphics.Point;

import com.example.user.bulletfalls.Objects.Enemy;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.EnemySpecyfication;

import java.util.List;

public class EyeOnGame {
    private GameController gameController;

    public EyeOnGame(GameController gameController) {
        this.gameController = gameController;
    }


    public Point getHeroPosition()
    {
        return  new Point((int)this.gameController.hero.getX(),(int)this.gameController.getHero().getY());
    }


    public List<EnemySpecyfication> getReadOnlyCurrentlyEnemyList() {
        return gameController.getReadOnlyCurrentEnemyList();
    }

    public List<Enemy> getEnemyList() {
        return gameController.getEnemyList();
    }

     public void mutate(List<EnemySpecyfication> toMutate, List<EnemySpecyfication> mutationOutcome) {
        this.gameController.mutate(toMutate,mutationOutcome);
     }



}
