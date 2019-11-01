package com.example.user.bulletfalls.GlobalUsage.Supporters;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ScalerSupporter {

    public void basicScale(ImageView imageView){
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setAdjustViewBounds(true);
    }

    public  void scaleByWidth(ImageView imageView, int width)
    {
        imageView.getLayoutParams().width=width;
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

    public  void scaleByHeight(ImageView imageView)
    {
        imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setAdjustViewBounds(true);
    }

    public void inLineScale(ViewGroup view, int weight){
        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(0,-1,weight);
        view.setLayoutParams(layoutParams);
    }

    public ViewGroup inLineScaleReturn(ViewGroup view, int weight){
        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(0,-1,weight);
        view.setLayoutParams(layoutParams);
        return view;
    }

    public ViewGroup inLineScaleReturn(ViewGroup view,int width, int height ,int weight){
        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(width,height,weight);
        view.setLayoutParams(layoutParams);
        return view;
    }




}
