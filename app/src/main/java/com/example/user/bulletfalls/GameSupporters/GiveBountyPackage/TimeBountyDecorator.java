package com.example.user.bulletfalls.GameSupporters.GiveBountyPackage;

import com.example.user.bulletfalls.GameSupporters.MediumTasks.Medium;
/**Zwykłe "linearne" naliczanie nagrody za czas*/
public class TimeBountyDecorator extends BountyAssignerDecorator {
    int time;
    public TimeBountyDecorator(BountyAssigner bountyAssigner) {
        super(bountyAssigner);
        this.time=5;
    }
    /**Możliwość ustalenia co ile czasu będzie się naliczała jaka nagroda*/
    public TimeBountyDecorator(BountyAssigner bountyAssigner,int time)
    {
        super(bountyAssigner);
        this.time=time;
    }
    @Override
    public void fillBounty(Medium medium, Bounty bounty) {
        bounty.addMoney((int)medium.getDuration()/(time*1000));
        this.bountyAssigner.fillBounty(medium,bounty);
    }
}
