package com.example.user.bulletfalls.GlobalUsage.Supporters;

import android.content.Context;

public class SupporterDrawableNaming {

    public boolean exist(String drawableName, Context context)
    {
        int id = context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
        if(id!=0)return true;
        else return false;
    }

    public int getDrawableNumber(String name,Context context)
    {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    public String validateDrawableString(String name)
    {
        String validated = name;
        validated = validated.replaceAll("\\s+", "");
        validated = validated.toLowerCase();
        return validated;
    }
}
