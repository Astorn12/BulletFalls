package com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions;

import com.example.user.bulletfalls.Game.Management.EyeOnGame;

public class Healer extends ClassAction {
    int healHp;

    public Healer(int timeQuand, int healHp) {
        super(timeQuand);
        this.healHp = healHp;
    }

    @Override
    protected void doClassAction(EyeOnGame eog) {
        eog.getHero().heal(this.healHp);
    }
}
