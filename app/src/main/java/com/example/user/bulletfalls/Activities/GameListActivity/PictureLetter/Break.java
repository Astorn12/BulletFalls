package com.example.user.bulletfalls.Activities.GameListActivity.PictureLetter;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Break extends androidx.appcompat.widget.AppCompatImageView {
    public Break(Context context) {
        super(context);
        this.setLayoutParams(new LinearLayout.LayoutParams(26,ViewGroup.LayoutParams.MATCH_PARENT));
        this.setBackgroundColor(Color.BLACK);
    }
}
