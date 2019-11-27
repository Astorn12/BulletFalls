package com.example.user.bulletfalls.Game.Elements.Ability.Strategy;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.Actions.AbilitysActions.AngelProtector;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.R;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("protectedball")
public class ProtectedBall implements StartAction {
    int seconds;
    int resource;
    public ProtectedBall(int resource,int seconds){
        this.resource=resource;
        this.seconds=seconds;
    }
    protected ProtectedBall(){

    }
    @Override
    public Action prepareAction(EyeOnGame eyeOnGame) {

        return new AngelProtector(this.seconds, R.drawable.hamsterball);
    }

    @Override
    public String getDescription() {
        return "Bohater wchidzi do kuli homika która odbija pociski przeciwnik, bohater jest bezpieczny na "+this.seconds + "sekund";
    }

    @Override
    public void setAdditionalDescription(LinearLayout linearLayout) {
    }



    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
