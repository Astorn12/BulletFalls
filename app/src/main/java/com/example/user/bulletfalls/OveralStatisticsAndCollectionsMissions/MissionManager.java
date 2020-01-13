package com.example.user.bulletfalls.OveralStatisticsAndCollectionsMissions;

import android.content.Context;

import com.example.user.bulletfalls.Game.Management.Medium;

import java.util.LinkedList;
import java.util.List;

public class MissionManager  {


    public void updateSavingOfTheOveralStatisctics(Context context){

        for(Mission mission: MissionsStore.getInstance().getAllMissionList()){
            mission.updateOveralStatisticSaving(context);
        }
    }

    public void checkMissions(Medium medium){

    }

    public void checkMissions(OveralStatistick... params){
        for(Mission mission: MissionsStore.getInstance().getAllMissionList()){
            for(OveralStatistick s:params){
            mission.actForAction(s);
        }}
    }








}
