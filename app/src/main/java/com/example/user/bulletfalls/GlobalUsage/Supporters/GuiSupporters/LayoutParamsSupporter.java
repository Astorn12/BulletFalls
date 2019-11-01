package com.example.user.bulletfalls.GlobalUsage.Supporters.GuiSupporters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class LayoutParamsSupporter {

    public void setLayoutParams(View view, int width, int height) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        view.setLayoutParams(layoutParams);
    }

    public void setLayoutParams(View view, int width, int height, int weight) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        layoutParams.weight = weight;
        view.setLayoutParams(layoutParams);
    }
}
