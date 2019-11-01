package com.example.user.bulletfalls.Game.ActionService;

import com.example.user.bulletfalls.Game.Management.EyeOnGame;

import java.util.LinkedList;
import java.util.List;

public class ActionController {
    List<Action> innerActions;
    List<Action> garbage;
    EyeOnGame eyeOnGame;

    public ActionController(EyeOnGame eyeOnGame) {
        this.innerActions = new LinkedList<>();
        this.garbage= new LinkedList<>();
        this.eyeOnGame=eyeOnGame;
    }

    public void act(){
        doAction();
        cleanUsedActions();
    }
    public void addAction(Action action){
        this.innerActions.add(action);
    }


    private void doAction(){
        for(int i = 0; i<this.innerActions.size(); i++){
            Action action = innerActions.get(i);
            action.doMagic(eyeOnGame);
            if(action.removAble)
                garbage.add(action);
        }
    }

    private void cleanUsedActions(){
        for(int i=0;i<garbage.size();i++){
            Action innerAction = garbage.get(i);
            innerActions.remove(innerAction);
        }
        garbage.clear();
    }

}
