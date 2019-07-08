package com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.AppearActionStrategy;

import com.example.user.bulletfalls.GameManagement.Game;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.security.acl.Acl;
@JsonTypeName("nothingappearaction")
public class NothingAppearAction implements AppearAction {
    @Override
    public void action(Game game) {

    }
}
