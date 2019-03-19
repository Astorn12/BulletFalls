package com.example.user.bulletfalls.GameSupporters.GiveBountyPackage;

import com.example.user.bulletfalls.GameSupporters.MediumTasks.ArchivContainer;
import com.example.user.bulletfalls.GameSupporters.MediumTasks.Medium;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.Currency;
import java.util.List;

public interface BountyAssigner {
    void fillBounty(Medium medium,Bounty bounty);
}
