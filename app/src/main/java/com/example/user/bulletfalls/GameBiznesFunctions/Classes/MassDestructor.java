package com.example.user.bulletfalls.GameBiznesFunctions.Classes;

import com.example.user.bulletfalls.GameManagement.EyeOnGame;
import com.example.user.bulletfalls.Objects.Hero;
import com.example.user.bulletfalls.R;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("massdestructor")
public class MassDestructor  implements IClass {
    @Override
    public int getImage() {
        return  R.drawable.massdestruction;
    }

    @Override
    public String getDescription() {
        return "Mass Destruktorzy to herosi gotowi do walki z dużą grupą przeciwników, co okreslony czas obraczają całe pole bitwy" +
                "magicznym osłabieniem, powodujące obrażenia wszystkim postacią." +
                "!!!Uwaga działa też na sprzymierzone Bestie i na samego Destruktora";
    }

    @Override
    public void action(EyeOnGame eog, Hero hero) {

    }
}
