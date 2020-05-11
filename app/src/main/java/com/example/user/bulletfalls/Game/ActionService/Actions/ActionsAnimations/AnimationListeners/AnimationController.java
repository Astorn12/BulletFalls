package com.example.user.bulletfalls.Game.ActionService.Actions.ActionsAnimations.AnimationListeners;

import java.util.LinkedList;
import java.util.List;

public class AnimationController {
    private List<OnAnimationEndListener> listeners= new LinkedList<>();

    public void setOnAnimationEndListener(OnAnimationEndListener listener) {
        this.listeners.add(listener);
    }

    public void animationHasEnded(){
        for(OnAnimationEndListener listener:this.listeners){
            listener.onAnimationEnd();
        }
    }
}
