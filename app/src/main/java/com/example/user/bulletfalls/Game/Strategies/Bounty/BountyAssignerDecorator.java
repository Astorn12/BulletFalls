package com.example.user.bulletfalls.Game.Strategies.Bounty;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.user.bulletfalls.GlobalUsage.Interfaces.ISelfDescriber;

abstract public class BountyAssignerDecorator implements IBountyAssigner, ISelfDescriber {
    protected IBountyAssigner IBountyAssigner;

    public BountyAssignerDecorator(IBountyAssigner IBountyAssigner)
    {

        this.IBountyAssigner = IBountyAssigner;
    }

    @Override
    public ViewGroup selfDescribe(Context context) {
        LinearLayout linearLayout= new LinearLayout(context);
        linearLayout.setGravity(Gravity.CENTER);
        this.showBounty(linearLayout);
        this.IBountyAssigner.showBounty(linearLayout);

        return linearLayout;
    }



}
