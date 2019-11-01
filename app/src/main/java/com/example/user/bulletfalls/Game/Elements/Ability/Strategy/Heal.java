package com.example.user.bulletfalls.Game.Elements.Ability.Strategy;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.Actions.AbilitysActions.HealAction;
import com.example.user.bulletfalls.Game.ActionService.Actions.EmptyAction;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("heal")
public class Heal implements StartAction {
    int healValue;
    public Heal(int healValue)
    {

        this.healValue=healValue;
       // this.readyFlag=true;
    }
    public Heal()
    {

    }
    public int getHealValue() {
        return healValue;
    }
    public void setHealValue(int healValue) {
        this.healValue = healValue;

    }
    @Override
    public Action prepareAction(EyeOnGame eyeOnGame) {

       //return new HealAction(this.healValue);
       return new EmptyAction();
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setAdditionalDescription(LinearLayout linearLayout) {

    }


}
