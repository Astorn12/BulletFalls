package com.example.user.bulletfalls.Game.Elements.Ability.Strategy;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.Actions.ActionsAnimations.AnimationsStrategy;
import com.example.user.bulletfalls.Game.ActionService.Actions.ActionsAnimations.GameAnimation;
import com.example.user.bulletfalls.Game.ActionService.Actions.AnimationAction;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
@JsonTypeName("animatedstartaction")
public class AnimatedStartAction implements StartAction {


    StartAction startAction;
    AnimationsStrategy strategy;
    GameAnimation animation;

    public AnimatedStartAction(StartAction startAction, GameAnimation gameAnimation, AnimationsStrategy animationsStrategy) {
        this.startAction = startAction;
        this.animation=gameAnimation;
        this.strategy=animationsStrategy;
    }
    private AnimatedStartAction(){}

    @Override
    public Action prepareAction(EyeOnGame eyeOnGame) {

        AnimationAction animationAction= new AnimationAction(this.startAction.prepareAction(eyeOnGame),this.animation, this.strategy);
        return animationAction;
    }

    @Override
    public String getDescription() {
        return startAction.getDescription();
    }

    @Override
    public void setAdditionalDescription(LinearLayout linearLayout) {
        startAction.setAdditionalDescription(linearLayout);
    }

    public StartAction getStartAction() {
        return startAction;
    }

    public void setStartAction(StartAction startAction) {
        this.startAction = startAction;
    }

    public AnimationsStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(AnimationsStrategy strategy) {
        this.strategy = strategy;
    }

    public GameAnimation getAnimation() {
        return animation;
    }

    public void setAnimation(GameAnimation animation) {
        this.animation = animation;
    }
}
