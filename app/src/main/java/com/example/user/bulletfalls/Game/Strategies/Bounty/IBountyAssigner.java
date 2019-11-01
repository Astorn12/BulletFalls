package com.example.user.bulletfalls.Game.Strategies.Bounty;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.Management.Medium;

public interface IBountyAssigner {
    void fillBounty(Medium medium,Bounty bounty);
    void showBounty(LinearLayout minBounty);
}
