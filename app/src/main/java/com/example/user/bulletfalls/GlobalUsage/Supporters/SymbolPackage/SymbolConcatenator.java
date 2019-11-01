package com.example.user.bulletfalls.GlobalUsage.Supporters.SymbolPackage;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.LinearLayout;

public class SymbolConcatenator {

    Symbol overalSymbol;


    public SymbolConcatenator(Context context) {
        this.overalSymbol = new Symbol(context);
        this.overalSymbol.setOrientation(LinearLayout.HORIZONTAL);

    }

    public void setVertical(){
        this.overalSymbol.setOrientation(LinearLayout.VERTICAL);
    }

    public Symbol getSymbol()
    {
        return this.overalSymbol;
    }
    public void addSymbol(Symbol singleSymbol)
    {
        this.overalSymbol.addView(singleSymbol);
    }









}
