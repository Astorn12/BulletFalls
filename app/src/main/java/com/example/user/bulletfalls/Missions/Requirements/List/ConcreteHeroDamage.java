package com.example.user.bulletfalls.Missions.Requirements.List;

import com.example.user.bulletfalls.Game.Management.Medium;
import com.example.user.bulletfalls.GlobalUsage.Enums.HE;
import com.example.user.bulletfalls.Missions.Requirements.ConcreteChecker;

public class ConcreteHeroDamage extends ConcreteChecker {

    private HE he;

    public ConcreteHeroDamage(HE he) {
        this.he = he;
    }

    @Override
    public int check(Medium medium, int amount) {
        if(medium.getHeroSpecyfication().getName().equals(he.getValue())) {
            return amount+medium.dealedDamage();
        }

        return amount;
    }
}
