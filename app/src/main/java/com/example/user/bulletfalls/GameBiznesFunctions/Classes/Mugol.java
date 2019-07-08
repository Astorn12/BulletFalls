package com.example.user.bulletfalls.GameBiznesFunctions.Classes;

import com.example.user.bulletfalls.GameManagement.EyeOnGame;
import com.example.user.bulletfalls.Objects.Hero;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("mugol")
public class Mugol implements IClass {
    @Override
    public int getImage() {
        return 0;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void action(EyeOnGame eog, Hero hero) {
    }
}
