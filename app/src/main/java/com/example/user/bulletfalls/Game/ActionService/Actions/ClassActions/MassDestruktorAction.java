package com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions;

import com.example.user.bulletfalls.Game.Elements.Enemy.Enemy;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

public class MassDestruktorAction extends ClassAction {
    int destructionDamage;
    public MassDestruktorAction(int timeQuand,int destructionDamage) {
        super(timeQuand);
        this.destructionDamage=destructionDamage;
    }

    @Override
    protected void doClassAction(EyeOnGame eog) {

        eog.getHero().getDamage(destructionDamage);

        for(Enemy enemy: eog.getEnemyList()){
            enemy.getDamage(destructionDamage);
        }
    }
}
