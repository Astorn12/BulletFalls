package com.example.user.bulletfalls.GameBiznesFunctions.Classes;

import com.example.user.bulletfalls.GameManagement.EyeOnGame;
import com.example.user.bulletfalls.Objects.Hero;
import com.example.user.bulletfalls.R;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("healer2")
public class HealerC implements IClass {
    @Override
    public int getImage() {
        return R.drawable.healler;
    }

    @Override
    public String getDescription() {
        return "Healerzy posiadają moc uzdrawiania, co pewien czas niwelują skutki otrzymanych obrażeń.";
    }

    @Override
    public void action(EyeOnGame eog, Hero hero) {

    }
}
