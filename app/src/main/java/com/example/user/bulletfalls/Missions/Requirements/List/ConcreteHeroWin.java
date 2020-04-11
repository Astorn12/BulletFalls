package com.example.user.bulletfalls.Missions.Requirements.List;

import com.example.user.bulletfalls.Game.Management.Medium;
import com.example.user.bulletfalls.GlobalUsage.Enums.HE;
import com.example.user.bulletfalls.Missions.Requirements.ConcreteChecker;
import com.example.user.bulletfalls.Missions.Requirements.OveralStatisticChecker;
import com.example.user.bulletfalls.Profile.UserProfile;

public class ConcreteHeroWin extends ConcreteChecker {
    private HE he;

    public ConcreteHeroWin(HE he) {
        this.he = he;
    }

    @Override
    public int check(Medium medium,int amount) {
        if(medium.getHeroSpecyfication().getName().equals(he.getValue()) && medium.getResult().equals("Win")) return amount++;


        return 0;
    }


}
