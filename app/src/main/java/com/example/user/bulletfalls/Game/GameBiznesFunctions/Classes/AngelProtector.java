package com.example.user.bulletfalls.Game.GameBiznesFunctions.Classes;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions.ClassAction;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.R;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("angelprotector")
public class AngelProtector  implements IClass{
    @Override
    public int getImage() {
        return R.drawable.angelprotector;
    }

    @Override
    public String getDescription() {
        return "Klada Angel Protectors to specjalna klasa bohateów obdażona umiejętnością przyzwania magicznej anielskiej tarczy," +
                "Tarcza przyzywana jest określony czas i może wytrzymac ilość obrażeń lub pojedyńcze obrażenia w zależności od konfiguracji.";
    }

    @Override
    public ClassAction action(EyeOnGame eog) {
        return null;
    }


}
