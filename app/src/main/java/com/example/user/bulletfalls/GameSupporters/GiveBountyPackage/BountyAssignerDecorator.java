package com.example.user.bulletfalls.GameSupporters.GiveBountyPackage;

import com.example.user.bulletfalls.GameSupporters.MediumTasks.Medium;

abstract public class BountyAssignerDecorator implements BountyAssigner {
    protected BountyAssigner bountyAssigner;

    public BountyAssignerDecorator(BountyAssigner bountyAssigner)
    {
        this.bountyAssigner=bountyAssigner;
    }



}
