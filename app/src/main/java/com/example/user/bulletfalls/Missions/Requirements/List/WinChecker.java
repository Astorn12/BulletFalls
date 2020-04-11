package com.example.user.bulletfalls.Missions.Requirements.List;

import com.example.user.bulletfalls.Game.Management.Medium;
import com.example.user.bulletfalls.Missions.Requirements.ConcreteChecker;
import com.example.user.bulletfalls.Missions.Requirements.OveralStatisticChecker;
import com.example.user.bulletfalls.Profile.UserProfile;

public class WinChecker extends ConcreteChecker {
    @Override
    public int check(Medium medium,int amount) {
        if(medium.getResult().equals("Win")) return amount++;
        return amount;
    }


}
