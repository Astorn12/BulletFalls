package com.example.user.bulletfalls.Storage.Sets;

import com.example.user.bulletfalls.Game.Strategies.Scenario.EmptyGameScenario;
import com.example.user.bulletfalls.GlobalUsage.Enums.EE;
import com.example.user.bulletfalls.GlobalUsage.Enums.HE;
import com.example.user.bulletfalls.Game.Strategies.GameSketch;
import com.example.user.bulletfalls.Game.Strategies.End.TimeEnd;
import com.example.user.bulletfalls.Game.Strategies.Enemies.EnemyReleaseStrategyPackage.RandomIEnemyReleaseStrategy;
import com.example.user.bulletfalls.Game.Strategies.Enemies.EnemysChooser;
import com.example.user.bulletfalls.Game.Strategies.Enemies.TimeReleaseStrategyPackage.LinearTimeReleaseStrategy;
import com.example.user.bulletfalls.Game.Strategies.Scenario.GirlPower;
import com.example.user.bulletfalls.Game.Strategies.Bounty.ConcreteBountyAssigner;
import com.example.user.bulletfalls.Game.Strategies.Bounty.KillerDecorator;
import com.example.user.bulletfalls.Game.Strategies.Requirements.HeroesRequirements;
import com.example.user.bulletfalls.Game.Strategies.Requirements.LevelRequirements;
import com.example.user.bulletfalls.R;

import java.util.Arrays;
import java.util.List;

public class GameSet {

    private static List<GameSketch> gameSketches= Arrays.asList(
            new GameSketch("Zombie atak?",
                    R.drawable.gnomesgame,
                    new GirlPower(),
                    new EnemysChooser(
                            new RandomIEnemyReleaseStrategy(EnemySet.getInstance().getEnemys(
                                    EE.JEFF.getValue(),EE.RANDOMGNOME.getValue(),EE.SHMEBULOCK.getValue())),
                            new LinearTimeReleaseStrategy(100)),
                    new KillerDecorator(new ConcreteBountyAssigner()),
                    new TimeEnd(30),
                    new LevelRequirements(5)
            ),


            new GameSketch("Dungeon, dungeon and more dungeon",
                    R.drawable.gnomesgame,
                    new GirlPower(),
                    new EnemysChooser(
                            new RandomIEnemyReleaseStrategy(EnemySet.getInstance().getEnemys(
                                    EE.JEFF.getValue(),EE.RANDOMGNOME.getValue(),EE.SHMEBULOCK.getValue())),
                            new LinearTimeReleaseStrategy(2000)),
                    new KillerDecorator(new ConcreteBountyAssigner()),
                    new TimeEnd(30),
                    new HeroesRequirements(HE.STANFORDPINNES,HE.MABELPINES,HE.WANDYCOULDRON)
            ),

            new GameSketch("Get unicorn hair",
                    R.drawable.unicorngame,
                    new EmptyGameScenario(),
                     new EnemysChooser(
                            new RandomIEnemyReleaseStrategy(EnemySet.getInstance().getEnemys(
                                    EE.JEFF.getValue(),EE.RANDOMGNOME.getValue(),EE.SHMEBULOCK.getValue())),
                    new LinearTimeReleaseStrategy(200)),
                    new KillerDecorator(new ConcreteBountyAssigner()),
                    new TimeEnd(30),
                    new LevelRequirements(0)
            ));


    public List<GameSketch> getAll()
    {
        return gameSketches;
    }


    public static GameSketch getSpecyficGame(String name)
    {
        for(GameSketch gs: gameSketches){
            if(gs.getNameOfTheGame().equals(name)) return gs;
        }
        return null;
    }











}
