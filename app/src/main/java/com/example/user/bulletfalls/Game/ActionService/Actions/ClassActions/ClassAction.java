package com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

public abstract class ClassAction extends Action {
    int counter;
    int timeQuand;
    public ClassAction(int timeQuand) {
            super(ActionType.INNER);
            setPermanentAction();
            this.timeQuand=timeQuand;
            counter=0;
    }

    @Override
    public void doMagic(EyeOnGame eyeOnGame) {
        counter++;
        if(counter%timeQuand==0){
            doClassAction(eyeOnGame);
        }

    }

    protected abstract void doClassAction(EyeOnGame eog);


}
