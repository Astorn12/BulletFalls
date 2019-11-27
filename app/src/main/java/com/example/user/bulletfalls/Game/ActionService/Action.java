package com.example.user.bulletfalls.Game.ActionService;

import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.Game.Management.GameController;

public abstract class Action {
    ActionType type;
    boolean removAble;

    public Action(ActionType type) {
        this.type = type;
        this.removAble=true;
    }


    public abstract void doMagic(EyeOnGame eyeOnGame);

    public ActionType getType() {
        return type;
    }

    private void setRemovAble(boolean removAble) {
        this.removAble = removAble;
    }
    public void setPermanentAction(){
        setRemovAble(false);
    }

    public void endActionDuration(){
        setRemovAble(true);
    }


}
