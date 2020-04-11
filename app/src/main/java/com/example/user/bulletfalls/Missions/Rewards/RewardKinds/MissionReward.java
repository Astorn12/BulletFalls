package com.example.user.bulletfalls.Missions.Rewards.RewardKinds;

import android.content.Context;
import android.view.View;

public interface MissionReward {


    View getIcon(Context context);

    void rewardUser(Context context);
}
