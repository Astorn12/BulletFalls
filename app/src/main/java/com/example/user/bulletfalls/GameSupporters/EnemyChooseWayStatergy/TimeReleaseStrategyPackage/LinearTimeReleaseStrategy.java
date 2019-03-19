package com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.TimeReleaseStrategyPackage;

public class LinearTimeReleaseStrategy implements TimeReleaseStrategy {

    int interval;

    int timer;

    public LinearTimeReleaseStrategy(int interval)
    {
        this.interval=interval;
    }

    @Override
    public void start() {
        timer=0;
    }

    @Override
    public boolean ifRelease() {
        timer++;
        if(timer%interval==0)
        {
            return true;
        }
        return false;
    }
}
