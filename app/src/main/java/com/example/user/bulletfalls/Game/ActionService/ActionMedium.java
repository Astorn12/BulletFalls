package com.example.user.bulletfalls.Game.ActionService;

import com.example.user.bulletfalls.Game.Management.EyeOnGame;

public class ActionMedium {
    ActionController beginner;
    ActionController inner;
    ActionController ender;

    public ActionMedium (EyeOnGame eyeOnGame){
        this.beginner= new ActionController(eyeOnGame);
        this.inner= new ActionController(eyeOnGame);
        this.ender= new ActionController(eyeOnGame);
    }

    public void addAction(Action action){
        switch(action.getType()){
            case BEGIN:
                beginner.addAction(action);
                break;
            case INNER:
                inner.addAction(action);
            case END:
                ender.addAction(action);
        }
    }

    public void begin(){
        this.beginner.act();
    }

    public void innerAct(){
        this.inner.act();

    }

    public void end(){
        this.ender.act();
    }

}
