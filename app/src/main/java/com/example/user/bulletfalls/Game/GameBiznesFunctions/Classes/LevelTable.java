package com.example.user.bulletfalls.Game.GameBiznesFunctions.Classes;

import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;
import com.example.user.bulletfalls.Profile.UserProfile;
import com.example.user.bulletfalls.Storage.Sets.HeroesSet;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LevelTable {

    private List<LevelBoost> levelBoostTable;

    public LevelTable(List<LevelBoost> levelBoostTable)
    {
      this.levelBoostTable=levelBoostTable;
    }

    public int getBoostAccordingToLevel(int currentLevel)
    {

        int boost=0;
        for (LevelBoost levelBoost:this.levelBoostTable){
            if(levelBoost.level<=currentLevel){
                boost=levelBoost.boost;
            }
            else break;
        }
        return boost;
    }

}
