package com.example.user.bulletfalls.Game.Strategies.End;

import com.example.user.bulletfalls.Game.Management.Medium;
import com.example.user.bulletfalls.GlobalUsage.Interfaces.ISelfDescriber;

public abstract class EndGame implements ISelfDescriber {
    Medium medium;
    protected final static int interval=20;

    public void setMedium(Medium medium)
    {
        this.medium=medium;
    }
    public abstract boolean check();
}
