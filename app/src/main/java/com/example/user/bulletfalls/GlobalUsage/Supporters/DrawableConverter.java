package com.example.user.bulletfalls.GlobalUsage.Supporters;

import android.content.Context;
import android.content.res.Resources;

public class DrawableConverter {

    public int getDrawableByName(Context context,String name)
    {
        Resources resources = context.getResources();
        return resources.getIdentifier(name, "drawable",context.getPackageName());
    }



}
