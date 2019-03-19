package com.example.user.bulletfalls;

import android.graphics.Color;
import android.text.Layout;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class BorderSetter {

    int thickness;
    int color;

    public BorderSetter(int thickness, int color) {
        this.thickness = thickness;
        this.color = color;
    }

    public void setBorder(ViewGroup viewGroup)
    {
        viewGroup.setPadding(thickness,thickness,thickness,thickness);
        viewGroup.setBackgroundColor(color);
    }
    public FrameLayout setBorderWB(FrameLayout frameLayout)/**Method create border to elements which contained or will contein background*/
    {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) frameLayout.getLayoutParams();
        FrameLayout border= new FrameLayout(frameLayout.getContext());
        border.setBackgroundColor(color);
        border.addView(frameLayout);



        border.setLayoutParams(frameLayout.getLayoutParams());
        border.setLayoutParams(lp);


        FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(thickness,thickness,thickness,thickness);
        frameLayout.setLayoutParams(params);
        return border;
    }


}
