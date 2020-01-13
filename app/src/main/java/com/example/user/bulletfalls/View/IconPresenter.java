package com.example.user.bulletfalls.View;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class IconPresenter {


    public View presentIcon(Context context, int imageResource, String text){

        FrameLayout mainContainer= new FrameLayout(context);
        LinearLayout.LayoutParams  mainContainerLayoutParams= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mainContainer.setLayoutParams(mainContainerLayoutParams);

        ImageView iconView= new ImageView(context);
        iconView.setImageResource(imageResource);
        iconView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        TextView textView= new TextView(context);
        textView.setText(text);

        textView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        textView.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);

        mainContainer.addView(iconView);

        mainContainer.addView(textView);

        return mainContainer;
    }
}
