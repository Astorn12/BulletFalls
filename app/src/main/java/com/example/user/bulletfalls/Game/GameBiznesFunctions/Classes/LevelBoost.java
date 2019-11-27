package com.example.user.bulletfalls.Game.GameBiznesFunctions.Classes;

public class LevelBoost {
    int level;
    int boost;

    public LevelBoost(int level, int boost) {
        this.level = level;
        this.boost = boost;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getBoost() {
        return boost;
    }

    public void setBoost(int boost) {
        this.boost = boost;
    }
}
