package com.example.user.bulletfalls.Missions;
import com.example.user.bulletfalls.GlobalUsage.Enums.HE;
import com.example.user.bulletfalls.Missions.Requirements.List.ConcreteHeroDamage;
import com.example.user.bulletfalls.Missions.Requirements.List.DealedDemage;
import com.example.user.bulletfalls.Missions.Requirements.List.PlayedGames;
import com.example.user.bulletfalls.Missions.Requirements.List.WinChecker;
import com.example.user.bulletfalls.Missions.Rewards.MissionRewardGenerator;
import com.example.user.bulletfalls.Missions.Requirements.OveralStatistick;
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
                new Mission(new OveralStatistick("win",100,new WinChecker()), MissionRewardGenerator.expReward(1000),"Wygraj 100 walk"),
                new Mission(new OveralStatistick("playgame",100, new PlayedGames()), MissionRewardGenerator.moneyReward(1000),"Zagraj 100 gier"),
                new Mission(new OveralStatistick("damagedealt",100, new DealedDemage()), MissionRewardGenerator.itemReward(CurrencyEnum.Connifer.toString(),20),"Zadaj 100 obrażeń"),
                new Mission(new OveralStatistick("dipperdamage",100, new ConcreteHeroDamage(HE.DIPPERPINES)), MissionRewardGenerator.itemReward(CurrencyEnum.Connifer.toString(),20),"Zadaj 100 obrażeń z Dipperem"),
                new Mission(new OveralStatistick("stanekdamage",100, new ConcreteHeroDamage(HE.STANFORDPINNES)), MissionRewardGenerator.itemReward(CurrencyEnum.Connifer.toString(),20),"Zadaj 100 obrażeń ze Stankiem")
        );
    }

    public List<Mission> getAllMissionList(){
        return this.missionList;
    }


}
