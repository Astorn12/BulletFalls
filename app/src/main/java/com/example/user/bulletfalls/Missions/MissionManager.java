package com.example.user.bulletfalls.Missions;

import android.content.Context;

import com.example.user.bulletfalls.Game.Management.Medium;
import com.example.user.bulletfalls.Profile.UserProfile;

import java.util.Random;

public class MissionManager  {

    Random random = new Random();

    Context context;

    public MissionManager(Context context) {
        this.context = context;
    }

    public void updateSavingOfTheOveralStatisctics(){

        for(Mission mission: MissionsStore.getInstance().getAllMissionList()){
            mission.updateOveralStatisticSaving(context);
        }
    }



//    public void checkMissions(OveralStatistick... params){
//
//        for(Mission mission: MissionsStore.getInstance().getAllMissionList()){
//            for(OveralStatistick s:params){
//            mission.actForAction(s);
//        }}
//    }
    public void checkMissions(Medium medium){
        int token=generateToken();
        for(Mission mission : MissionsStore.getInstance().getAllMissionList()){
            mission.actForAction(medium, token,context);
        }
    }

    public void checkMissions(Missionable missionable){
        int token= generateToken();
        for(Mission mission : MissionsStore.getInstance().getAllMissionList()){
            mission.actForAction(missionable, token,context);
        }
    }


    public int generateToken(){
        return random.nextInt(1000);
    }







}
