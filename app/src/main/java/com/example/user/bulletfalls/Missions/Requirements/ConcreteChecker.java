package com.example.user.bulletfalls.Missions.Requirements;

import com.example.user.bulletfalls.Game.Management.Medium;
import com.example.user.bulletfalls.Profile.UserProfile;

public abstract  class ConcreteChecker implements OveralStatisticChecker {
    @Override
    public int check(Medium medium, int amount) {
        return amount;
    }

    @Override
    public int check(UserProfile userProfile, int amount) {
        return amount;
    }
}
