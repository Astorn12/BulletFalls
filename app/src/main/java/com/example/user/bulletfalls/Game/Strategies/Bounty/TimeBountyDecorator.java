package com.example.user.bulletfalls.Game.Strategies.Bounty;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.Management.Medium;
/**Zwykłe "linearne" naliczanie nagrody za czas*/
public class TimeBountyDecorator extends BountyAssignerDecorator {
    int time;
    public TimeBountyDecorator(IBountyAssigner IBountyAssigner) {
        super(IBountyAssigner);
        this.time=5;
    }
    /**Możliwość ustalenia co ile czasu będzie się naliczała jaka nagroda*/
    public TimeBountyDecorator(IBountyAssigner IBountyAssigner, int time)
    {
        super(IBountyAssigner);
        this.time=time;
    }
    @Override
    public void fillBounty(Medium medium, Bounty bounty) {
        bounty.addMoney((int)medium.getDuration()/(time*1000));
        if(this.IBountyAssigner !=null)
        this.IBountyAssigner.fillBounty(medium,bounty);
    }

    @Override
    public void showBounty(LinearLayout minBounty) {
        TextView textView= new TextView(minBounty.getContext());
        textView.setText("1 gold for each second");
        minBounty.addView(textView);
    }


}
