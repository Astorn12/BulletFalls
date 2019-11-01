package com.example.user.bulletfalls.Game.ActionService.Actions;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;

public abstract class BoostAction extends Action {
    int boost;

    public BoostAction(ActionType type,int boost) {
        super(type);
        this.boost=boost;
    }

    public int getBoost() {
        return boost;
    }
}
