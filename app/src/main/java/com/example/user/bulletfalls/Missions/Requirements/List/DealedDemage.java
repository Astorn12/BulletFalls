package com.example.user.bulletfalls.Missions.Requirements.List;

import com.example.user.bulletfalls.Game.Management.Medium;
import com.example.user.bulletfalls.Missions.Requirements.ConcreteChecker;

public class DealedDemage extends ConcreteChecker {
    @Override
    public int check(Medium medium, int amount) {

        return amount+medium.dealedDamage();
    }
}
