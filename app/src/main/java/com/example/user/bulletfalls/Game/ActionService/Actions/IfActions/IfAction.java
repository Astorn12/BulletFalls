package com.example.user.bulletfalls.Game.ActionService.Actions.IfActions;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

public class IfAction extends Action {
    ActionCondition actionCondition;
    Action action;
    public IfAction(ActionType type,Action action,ActionCondition actionCondition) {
        super(type);
        this.actionCondition=actionCondition;
        this.action=action;
    }

    public void doMagic(EyeOnGame eyeOnGame){
        if(this.actionCondition.fulfil(eyeOnGame)){
            action.doMagic(eyeOnGame);
        }
    }
}
