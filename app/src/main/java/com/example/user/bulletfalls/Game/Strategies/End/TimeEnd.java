package com.example.user.bulletfalls.Game.Strategies.End;

import android.content.Context;
import android.view.ViewGroup;

import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.GlobalUsage.Supporters.SymbolPackage.SymbolMaker;

public class TimeEnd extends EndGame {

    int duration;//w milisekundach


    public TimeEnd(int duration) {//konstuktor w sekundach
        this.duration = duration*1000;
    }

    @Override
    public ViewGroup selfDescribe(Context context) {

        SymbolMaker symbolMaker= new SymbolMaker(context);
        symbolMaker.addSymbolContent(R.drawable.time);
        symbolMaker.addText("50 s");
        symbolMaker.addTitle("Ending");

        /*ScalerSupporter imageScaler= new ScalerSupporter();

        LinearLayout linearLayout= new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        ImageView clock= new ImageView(context);
        imageScaler.scaleByHeight(clock);
        clock.setImageResource(R.drawable.time);
        linearLayout.addView(clock);

        TextView textView= new TextView(context);
        textView.setText("50 s");
        linearLayout.addView(textView);

        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT));*/
        //return linearLayout;
        return symbolMaker.getSymbol();
    }

    @Override
    public boolean check() {
        return false;
    }
}
