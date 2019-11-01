package com.example.user.bulletfalls.Game.Strategies.Requirements;

import android.content.Context;

import com.example.user.bulletfalls.GlobalUsage.Interfaces.ISelfDescriber;

public interface IGameRequirements extends ISelfDescriber {
    boolean canPlay(Context context);
}
