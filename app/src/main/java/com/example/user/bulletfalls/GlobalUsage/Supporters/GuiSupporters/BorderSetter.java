package com.example.user.bulletfalls.GlobalUsage.Supporters.GuiSupporters;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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

    public FrameLayout setBorderWB(View view)
    {
        FrameLayout fl= new FrameLayout(view.getContext());
        fl.setPadding(thickness,thickness,thickness,thickness);

        fl.setBackgroundColor(color);
        fl.addView(view);
        return fl;
    }

    public void setBorderToLayout(ViewGroup viewGroup)
    {
        viewGroup.setPadding(thickness,thickness,thickness,thickness);
        GradientDrawable border = new GradientDrawable();
        border.setColor(Color.TRANSPARENT); //white background
        border.setStroke(this.thickness, 0xFF000000); //black border with full opacity
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            viewGroup.setBackgroundDrawable(border);
        } else {
            viewGroup.setBackground(border);
        }


    }

    public void setBorder(View view)
    {
        view.setPadding(thickness,thickness,thickness,thickness);
        view.setBackgroundColor(color);
    }
    public FrameLayout setBorderWB(FrameLayout frameLayout)/**Method create border to elements which contained or will contain background*/
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
