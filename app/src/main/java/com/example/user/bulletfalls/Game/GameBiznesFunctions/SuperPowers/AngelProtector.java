package com.example.user.bulletfalls.Game.GameBiznesFunctions.SuperPowers;

import com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions.AngelProtectorAction;
import com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions.ClassAction;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.R;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Arrays;

@JsonTypeName("angelprotector")
public class AngelProtector extends MasterAbility {


    public AngelProtector() {

        this.levelTable=new LevelTable( Arrays.asList(
                    new LevelBoost(0,1),
                    new LevelBoost(4,3),
                    new LevelBoost(10,4),
                    new LevelBoost(15,5),
                    new LevelBoost(20,6),
                    new LevelBoost(30,7),
                    new LevelBoost(50,8)
        ));
    }

    @Override
    public int getImage() {
        return R.drawable.angelprotector;
    }

    @Override
    public String getDescription() {
        return "Klasa Angel Protectors to specjalna klasa bohaterów obdażona umiejętnością przyzwania magicznej anielskiej tarczy," +
                "Tarcza przyzywana jest określony czas i może wytrzymac ilość obrażeń lub pojedyńcze obrażenia w zależności od konfiguracji.";
    }

    @Override
    public ClassAction action(EyeOnGame eog) {
        return new AngelProtectorAction(this.timeQuant,R.drawable.angelprotectorshield,this.getValueAccordingToLevel(eog.getGameContext()));
    }



}
