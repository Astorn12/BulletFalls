package com.example.user.bulletfalls.Profile.Collection.HeroCollection.FiltersAndSorters;

import android.graphics.Color;

import com.example.user.bulletfalls.Game.GameBiznesFunctions.SuperPowers.MasterAbility;

public class FeatureMenuItem {

    MasterAbility masterAbility;
    boolean isSelected;

    public FeatureMenuItem(MasterAbility masterAbility) {
        this.masterAbility=masterAbility;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int changeState() {
        if(isSelected) {
            isSelected=false;
            return Color.WHITE;
        }
        else {
            isSelected=true;
            return Color.RED;
        }
    }

    public MasterAbility getMasterAbility() {
        return masterAbility;
    }

    public void setMasterAbility(MasterAbility masterAbility) {
        this.masterAbility = masterAbility;
    }
}
