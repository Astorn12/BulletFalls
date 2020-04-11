package com.example.user.bulletfalls.Missions.Requirements;

import com.example.user.bulletfalls.Game.Management.Medium;
import com.example.user.bulletfalls.Profile.UserProfile;

public interface OveralStatisticChecker {

    int check(Medium medium,int amount);
    int check(UserProfile userProfile,int amount);

}
