package com.example.user.bulletfalls.GlobalUsage.Enums;

import android.graphics.Color;

public enum FamilyName {
    Null(Color.rgb(0,0,0)),
    MysteryShack(Color.rgb(230,190,146)),
    Lumberjack(Color.rgb(167,23,40)),
    TentOfThelepathy(Color.rgb(152,187,214)),
    WendyTeam(Color.BLACK),
    MabelTeam(Color.rgb(247,185,233)),
    Gnomes(Color.rgb(126,202,155)),
    Ramirez(Color.rgb(125,125,125)),
    Scientists(Color.rgb(125,125,125));

    private final int value;
    FamilyName(int black) {
        this.value=black;
    }

    public int getValue()
    {
        return value;
    }


}
