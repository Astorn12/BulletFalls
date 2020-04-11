package com.example.user.bulletfalls.Game.GameBiznesFunctions.SuperPowers;

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
