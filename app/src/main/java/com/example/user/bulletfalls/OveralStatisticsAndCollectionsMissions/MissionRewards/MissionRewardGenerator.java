package com.example.user.bulletfalls.OveralStatisticsAndCollectionsMissions.MissionRewards;

import android.content.Context;
import android.util.AndroidException;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.Elements.Ability.Ability;
import com.example.user.bulletfalls.Game.Elements.Ability.Specyfication.AbilitySpecyfication;
import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Game.Elements.Hero.HeroSpecyfication;
import com.example.user.bulletfalls.GlobalUsage.Enums.AE;
import com.example.user.bulletfalls.GlobalUsage.Enums.BE;
import com.example.user.bulletfalls.GlobalUsage.Enums.HE;
import com.example.user.bulletfalls.MyApplication;
import com.example.user.bulletfalls.OveralStatisticsAndCollectionsMissions.MissionRewards.RewardKinds.AbilityReward;
import com.example.user.bulletfalls.OveralStatisticsAndCollectionsMissions.MissionRewards.RewardKinds.BulletReward;
import com.example.user.bulletfalls.OveralStatisticsAndCollectionsMissions.MissionRewards.RewardKinds.ExpReward;
import com.example.user.bulletfalls.OveralStatisticsAndCollectionsMissions.MissionRewards.RewardKinds.HeroReward;
import com.example.user.bulletfalls.OveralStatisticsAndCollectionsMissions.MissionRewards.RewardKinds.ItemReward;
import com.example.user.bulletfalls.OveralStatisticsAndCollectionsMissions.MissionRewards.RewardKinds.MissionReward;
import com.example.user.bulletfalls.OveralStatisticsAndCollectionsMissions.MissionRewards.RewardKinds.MoneyReward;
import com.example.user.bulletfalls.Profile.Currency;
import com.example.user.bulletfalls.Storage.Data.CurrencyRepository;
import com.example.user.bulletfalls.Storage.Sets.AbilitySet;
import com.example.user.bulletfalls.Storage.Sets.BulletSet;
import com.example.user.bulletfalls.Storage.Sets.HeroesSet;

public class MissionRewardGenerator {





    public static MissionReward itemReward(String string, int amount){
        CurrencyRepository currencyRepository= new CurrencyRepository(MyApplication.getAppContext());
        return new ItemReward(currencyRepository.getCurrency(string),amount);
    }

    public static MissionReward heroReward(HE he){
        HeroesSet heroesSet=HeroesSet.getInstance();
        return new HeroReward(heroesSet.getHero(he.getValue()));
    }

    public static MissionReward expReward(int amount){
        return new ExpReward(amount);
    }

    public static MissionReward moneyReward(int amount){
        return new MoneyReward(amount);
    }
    public static MissionReward abilityReward(AE ae){
        AbilitySet abilitySet= AbilitySet.getInstance();
        return new AbilityReward(abilitySet.getAbility(ae));

    }
    public static MissionReward bulletReward(BE be){
        MissionRewardGenerator missionRewardGenerator =new MissionRewardGenerator();
        BulletSet bulletSet= BulletSet.getInstance();
        return new BulletReward(bulletSet.getBullet(be));
    }


}
