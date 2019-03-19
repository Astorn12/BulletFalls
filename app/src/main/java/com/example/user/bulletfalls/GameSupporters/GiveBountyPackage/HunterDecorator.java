package com.example.user.bulletfalls.GameSupporters.GiveBountyPackage;

import com.example.user.bulletfalls.GameSupporters.MediumTasks.Medium;
import com.example.user.bulletfalls.Specyfications.Characters.EnemySpecyfication;

public class HunterDecorator extends BountyAssignerDecorator {
    public HunterDecorator(BountyAssigner bountyAssigner) {
        super(bountyAssigner);
    }

    @Override
    public void fillBounty(Medium medium, Bounty bounty) {
        int n=0;
        for (EnemySpecyfication e:medium.getKilledEnemys()
             ) {
            n+=e.getKillValue();
        }
        bounty.addExp(n);
        this.bountyAssigner.fillBounty(medium,bounty);
    }
}
