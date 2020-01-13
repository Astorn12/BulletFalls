package com.example.user.bulletfalls.Game.Strategies.Scenario;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.Actions.EmptyAction;

import java.util.Arrays;
import java.util.List;


public class EmptyGameScenario extends UniversalGameScenario {
    @Override
    public List<Action> getScenario() {
        return Arrays.asList(new EmptyAction());
    }

    @Override
    public ViewGroup selfDescribe(Context context) {
        return new LinearLayout(context);
    }
}
