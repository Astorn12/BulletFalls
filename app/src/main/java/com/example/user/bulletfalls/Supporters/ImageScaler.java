package com.example.user.bulletfalls.Supporters;

import android.widget.ImageView;
import android.widget.LinearLayout;

public class ImageScaler {

    public  void scaleByWidth(ImageView imageView, int width)
    {
        imageView.getLayoutParams().width=width;
      //  imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setAdjustViewBounds(true);
    }

    public  void scaleByHeight(ImageView imageView,int height)
    {
        imageView.getLayoutParams().height=height;
        //imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setAdjustViewBounds(true);
    }
}
