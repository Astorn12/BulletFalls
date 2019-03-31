package com.example.user.bulletfalls.Enums;

import android.graphics.Color;

public enum GroupName {
    Null(Color.rgb(0,0,0)),MysteryShack(Color.rgb(230,190,146)),Lumberjack(Color.rgb(167,23,40)),TentOfThelepathy(Color.rgb(152,187,214)),Animal(Color.GREEN),WendyTeam(Color.BLACK),MabelTeam(Color.rgb(247,185,233));

    private final int value;
    GroupName(int black) {
        this.value=black;
    }

    public int getValue()
    {
        return value;
    }


}
