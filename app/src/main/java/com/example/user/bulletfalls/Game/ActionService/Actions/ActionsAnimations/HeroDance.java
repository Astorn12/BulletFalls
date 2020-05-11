package com.example.user.bulletfalls.Game.ActionService.Actions.ActionsAnimations;

import com.example.user.bulletfalls.GlobalUsage.Enums.HE;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
public class HeroDance{
    HE heroId;
    int animationResource;
    float scale;

    public HeroDance(HE heroId, int animationResource,float scale) {
        this.heroId = heroId;
        this.animationResource = animationResource;
        this.scale=scale;
    }
    private HeroDance(){}

    public HE getHeroId() {
        return heroId;
    }

    public void setHeroId(HE heroId) {
        this.heroId = heroId;
    }

    public int getAnimationResource() {
        return animationResource;
    }

    public void setAnimationResource(int animationResource) {
        this.animationResource = animationResource;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
