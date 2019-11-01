package com.example.user.bulletfalls.Game.ActionService.Actions.IfActions.ActionConditions;

import com.example.user.bulletfalls.Game.ActionService.Actions.IfActions.ActionCondition;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

import java.util.Arrays;
import java.util.List;

public class ACGirlHero implements ActionCondition {

    List<String> girls=Arrays.asList("Wendy","Mabel","Pacyfika");

    @Override
    public boolean fulfil(EyeOnGame eyeOnGame) {
        for(String s:girls){
            if(eyeOnGame.getHero().getIndyvidualHeroMarker().equals(s)) return true;
        }
        return false;
    }


}
