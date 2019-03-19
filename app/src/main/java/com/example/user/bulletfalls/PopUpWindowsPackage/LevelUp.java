package com.example.user.bulletfalls.PopUpWindowsPackage;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.user.bulletfalls.ProfileActivity.LevelBar;

public class LevelUp extends PopupWindow {

    int newLevel;
    public LevelUp(Context context, int newLevel)
    {
        super(context);
        this.newLevel=newLevel;
        this.setContentView(getLayout(context));


    }

    private LinearLayout getLayout(Context context)
    {
        LinearLayout content= new LinearLayout(context);
        content.setOrientation(LinearLayout.VERTICAL);


        TextView levelUp= new TextView(context);
        levelUp.setText("Level Up!");

        LinearLayout jump=new LinearLayout(context);
        TextView tv= new TextView(context);
        tv.setText((newLevel-1)+" ->"+newLevel);


        FrameLayout levelBar;
        LevelBar levelBar1= new LevelBar(context);
        levelBar=levelBar1.get();

        LinearLayout award= new LinearLayout(context);


        content.addView(levelUp);
        content.addView(jump);
        content.addView(levelBar);
        content.addView(award);

        content.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                dismiss();
                return false;
            }
        });

        return content;
     }


}
