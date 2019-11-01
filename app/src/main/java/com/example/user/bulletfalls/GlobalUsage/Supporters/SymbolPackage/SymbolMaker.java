package com.example.user.bulletfalls.GlobalUsage.Supporters.SymbolPackage;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SymbolMaker {
    Context context;
    FrameLayout symbol;
    LinearLayout linearLayout;
    Symbol overal;

    public SymbolMaker(Context context){
        this.overal= new Symbol(context);
        overal.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams overalParams= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        overal.setLayoutParams(overalParams);
        this.overal.setGravity(Gravity.CENTER_HORIZONTAL);
        this.context=context;

        this.symbol= new FrameLayout(context);
        LinearLayout.LayoutParams symbolParams= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT);
        symbol.setLayoutParams(symbolParams);



        this.linearLayout=new LinearLayout(context);


        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);

        this.linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        this.linearLayout.setGravity(Gravity.CENTER_VERTICAL);

        this.linearLayout.addView(symbol);


        this.overal.addView(linearLayout);

        this.overal.setPadding(2,2,2,2);

    }

    public void setListUse()
    {
        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,0,1);
        overal.setLayoutParams(layoutParams);
    }


    public Symbol getSymbol()
    {

        return this.overal;
    }
    public void addSymbolContent(int symbolResource)
    {
        ImageView symbolImage= new ImageView(context);
        symbolImage.setImageResource(symbolResource);
        symbolImage.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
        keepImageResolution(symbolImage);

        this.symbol.addView(symbolImage);
    }

   public void addText(String text)
   {
       TextView textView= new TextView(context);
       textView.setText(text);

       this.linearLayout.addView(textView);
   }


    public void addFootage(int footageResource)
    {
        ImageView footage= new ImageView(context);
        footage.setImageResource(footageResource);

        setFootagePossitioning(footage);

        keepImageResolution(footage);

        symbol.addView(footage);
    }

    public void addTextToForeground(String text){
        TextView textView= new TextView(context);
        textView.setText(text);
        textView.setTextSize(28);
        textView.setGravity(Gravity.CENTER);

        this.symbol.addView(textView);

    }


    public void addTitle(String title)
    {
        TextView titleView= new TextView(context);
        titleView.setText(title);
        titleView.setGravity(Gravity.CENTER);

        this.overal.addView(titleView,0);
    }

    private void setFootagePossitioning(ImageView imageView){
        FrameLayout.LayoutParams frameParams= new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,40);
        frameParams.gravity=Gravity.BOTTOM+Gravity.RIGHT;
        imageView.setLayoutParams(frameParams);
    }

    private void keepImageResolution(ImageView imageView)
    {
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setAdjustViewBounds(true);
    }




}
