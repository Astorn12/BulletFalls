package com.example.user.bulletfalls.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.user.bulletfalls.R;

public class MysterymMap extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysterym_map);

        final ImageView transparentMap=new ImageView(this);
        transparentMap.setImageResource(R.drawable.coloredmap);
        transparentMap.setX(0);
        transparentMap.setY(0);
        ImageView map = new ImageView(this);
       // map.setImageResource(R.drawable.orginalmap);

        Glide.with(this)
                .load(R.drawable.orginalmap)
                .into(map);
        final Activity activity=this;
        map.post(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap= ((BitmapDrawable)transparentMap.getDrawable()).getBitmap();



                transparentMap.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        Matrix inverse = new Matrix();
                        ((ImageView)v).getImageMatrix().invert(inverse);
                        float[] touchPoint = new float[] {event.getX(), event.getY()};
                        inverse.mapPoints(touchPoint);

                        int  x = Integer.valueOf((int)touchPoint[0]);
                        int  y = Integer.valueOf((int)touchPoint[1]);
                        System.out.println(x+" "+y);


                        int pixel= bitmap.getPixel(x,y);
                        int color= Color.rgb(Color.red(pixel),Color.green(pixel),Color.blue(pixel));
                        int id=0;
                        String title="";
                        if(color==Color.rgb(30,26,189)){
                            Log.i("mYSTERY Atraction"," MysteryShack");

                            id=R.drawable.mysteryshack;
                            title="Mystery Shack";
                        }else if(color==Color.rgb(242,169,29)){
                            Log.i("mYSTERY Atraction"," YeroyalDiscountPuttHutt");
                            title="Yeroyal Discount Putt Hutt";
                            id=R.drawable.yeroyaldiscountputthutt;

                        }else if(color==Color.rgb(242,29,169)){
                            Log.i("mYSTERY Atraction","Tent of Thelepathy ");
                            title="Tent of Thelepathy";
                            id=R.drawable.tentofthelepathy;
                        }
                        else if(color==Color.rgb(217,242,10)){
                            Log.i("mYSTERY Atraction"," Petting Zoo");
                            title="Petting Zoo";
                            id=R.drawable.pettingzoofarm;
                        }
                        else if(color==Color.rgb(164,97,10)){
                            Log.i("mYSTERY Atraction","Beaver Museum ");
                            title="Beaver Museum";
                        }
                        else if(color==Color.rgb(182,27,19)){
                            title="Camp";
                        }
                        else if(color==Color.rgb(246,27,27)){
                            title="Yarn Ball";
                        }
                        else if(color==Color.rgb(155,162,74)){
                            title="The Big Things";
                        }
                        else if(color==Color.rgb(162,140,74)){
                            title="Logland";
                        }
                        else if(color==Color.rgb(113,106,105)){
                            title="The Giant Pan";
                        }
                        else if(color==Color.rgb(83,162,74)){
                            title="Upside Down Town";
                        }
                        else if(color==Color.rgb(161,20,182)){
                            title="House Shoe";
                        }
                        else if(color==Color.rgb(20,172,182)){
                            title="Mystery Mountain";//
                        }
                        else if(color==Color.rgb(20,182,31)){
                            title="Corn Maze";
                        }
                        else if(color==Color.rgb(244,37,81)){
                            title="Neon Ville";
                        }
                        else if(color==Color.rgb(244,37,81)){
                            title="Sceptic Ridge RV Park";
                        }

                        if(id!=0) {
                            Intent intent = new Intent(activity, NotReadyYet.class);
                            intent.putExtra("id", id);
                            intent.putExtra("title", title);
                            activity.startActivity(intent);
                        }

                        return false;
                    }
                });
            }
        });
        map.setX(0);
        map.setY(0);
        Display display = getWindowManager(). getDefaultDisplay();
        Point size = new Point();
        display. getSize(size);
        FrameLayout l=(FrameLayout) this.findViewById(R.id.mainframe);
        transparentMap.setAlpha(0f);
        l.addView(map);
        l.addView(transparentMap);
        transparentMap.setScaleType(ImageView.ScaleType.CENTER_CROP);
        map.setScaleType(ImageView.ScaleType.CENTER_CROP);
       // transparentMap.getLayoutParams().width=map.getLayoutParams().width;
        //transparentMap.getLayoutParams().height=map.getLayoutParams().height;











    }

}
