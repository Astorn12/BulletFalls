package com.example.user.bulletfalls.Missions.Requirements.List;

import com.example.user.bulletfalls.Game.Management.Medium;
import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;
import com.example.user.bulletfalls.Missions.Requirements.ConcreteChecker;
import com.example.user.bulletfalls.Missions.Requirements.OveralStatisticChecker;
import com.example.user.bulletfalls.Profile.UserProfile;
import com.example.user.bulletfalls.Storage.Sets.HeroesSet;

public class AmountOfFamiliMamber extends ConcreteChecker {
    private FamilyName name;

    public AmountOfFamiliMamber(FamilyName name) {
        this.name = name;
    }



    @Override
    public int check(UserProfile userProfile,int amount) {
        return HeroesSet.getInstance().getAmountOfOwnedMembers(name);
    }
}
