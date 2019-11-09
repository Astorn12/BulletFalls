package com.example.user.bulletfalls.Game.GameBiznesFunctions.Classes;

import com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions.ClassAction;
import com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions.Healer;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.R;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("healer2")
public class HealerC implements MasterAbility {
    private static int timeQuand=10;
    private static int healHp=30;
    @Override
    public int getImage() {
        return R.drawable.healler;
    }

    @Override
    public String getDescription() {
        return "Healerzy posiadają moc uzdrawiania, co pewien czas niwelują skutki otrzymanych obrażeń.";
    }

    @Override
    public ClassAction action(EyeOnGame eog) {

        return new Healer(timeQuand,healHp);
    }

}
