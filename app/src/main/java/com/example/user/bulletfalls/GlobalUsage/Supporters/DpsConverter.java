package com.example.user.bulletfalls.GlobalUsage.Supporters;

import android.content.Context;

import static java.security.AccessController.getContext;

public class DpsConverter {

    Context context;

    public DpsConverter(Context context) {
        this.context = context;
    }

    public int getPixels(int dps)
    {

        final float scale = context.getResources().getDisplayMetrics().density;
        int pixels = (int) (dps * scale + 0.5f);
        return pixels;
    }
}
