package com.example.user.bulletfalls.Missions.Requirements.List;

import com.example.user.bulletfalls.Game.Management.Medium;
import com.example.user.bulletfalls.Missions.Requirements.ConcreteChecker;

public class PlayedGames extends ConcreteChecker {
    @Override
    public int check(Medium medium, int amount) {

        if(medium.getResult().equals("Win")|| medium.getResult().equals("Loose")) return amount+1;
        return amount;
    }
}
