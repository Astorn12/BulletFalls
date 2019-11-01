package com.example.user.bulletfalls.Game.Strategies.Bounty;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.Elements.Enemy.EnemySpecyfication;
import com.example.user.bulletfalls.Game.Management.Medium;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.GlobalUsage.Supporters.SymbolPackage.SymbolMaker;

public class KillerDecorator extends BountyAssignerDecorator {
    public KillerDecorator(IBountyAssigner IBountyAssigner) {
        super(IBountyAssigner);
    }

    @Override
    public void fillBounty(Medium medium, Bounty bounty) {
        int n=0;
        for (EnemySpecyfication e:medium.getKilledEnemys()
             ) {
            n+=e.getKillValue();
        }
        bounty.addExp(n);
        if(this.IBountyAssigner !=null)
            this.IBountyAssigner.fillBounty(medium,bounty);
    }

    @Override
    public void showBounty(LinearLayout minBounty) {
        SymbolMaker symbolMaker= new SymbolMaker(minBounty.getContext());
        symbolMaker.addSymbolContent(R.drawable.stars);
        symbolMaker.addTextToForeground("Exp x2");



        minBounty.addView(symbolMaker.getSymbol());
    }


}
