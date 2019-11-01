package com.example.user.bulletfalls.Game.Strategies.Scenario;

import android.content.Context;

import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.GlobalUsage.Supporters.SymbolPackage.Symbol;
import com.example.user.bulletfalls.GlobalUsage.Supporters.SymbolPackage.SymbolConcatenator;
import com.example.user.bulletfalls.GlobalUsage.Supporters.SymbolPackage.SymbolMaker;

public abstract class UniversalGameScenario implements IGameScenario {

    private void createSymbol( int when, Symbol then){
        Context context=then.getContext();

        SymbolMaker whenSM= new SymbolMaker(context);
        whenSM.addSymbolContent(when);

        SymbolMaker arrowSM= new SymbolMaker(context);
        arrowSM.addSymbolContent(R.drawable.arrow);

        SymbolConcatenator symbolConcatenator= new SymbolConcatenator(context);

        symbolConcatenator.addSymbol(whenSM.getSymbol());
        symbolConcatenator.addSymbol(arrowSM.getSymbol());
        symbolConcatenator.addSymbol(then);
    }
}
