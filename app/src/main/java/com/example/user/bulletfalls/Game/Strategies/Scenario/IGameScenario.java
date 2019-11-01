package com.example.user.bulletfalls.Game.Strategies.Scenario;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.Management.GameController;
import com.example.user.bulletfalls.GlobalUsage.Interfaces.ISelfDescriber;

import java.util.List;

public interface IGameScenario extends ISelfDescriber {

     List<Action> getScenario();
}
