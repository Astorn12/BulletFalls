package com.example.user.bulletfalls.OveralStatisticsAndCollectionsMissions;
import com.example.user.bulletfalls.OveralStatisticsAndCollectionsMissions.MissionRewards.MissionRewardGenerator;
import com.example.user.bulletfalls.Storage.Data.CurrencyEnum;

import java.util.Arrays;
import java.util.List;

public class MissionsStore {

    List<Mission> missionList;

    private static MissionsStore instanceOf= new MissionsStore();



    private MissionsStore(){
            this.missionList=loadAllMissions();
    }

    public static  MissionsStore getInstance(){
      return instanceOf;
    }



    private List<Mission> loadAllMissions(){
        return Arrays.asList(
                new Mission(new OveralStatistick("win",100), MissionRewardGenerator.expReward(1000),"Wygraj 100 walk"),
                new Mission(new OveralStatistick("playgame",100), MissionRewardGenerator.moneyReward(1000),"Zagraj 100 gier"),
                new Mission(new OveralStatistick("damagedealt",100), MissionRewardGenerator.itemReward(CurrencyEnum.Connifer.toString(),20),"Zadaj 100 obrażeń")
        );
    }

    public List<Mission> getAllMissionList(){
        return this.missionList;
    }
}
