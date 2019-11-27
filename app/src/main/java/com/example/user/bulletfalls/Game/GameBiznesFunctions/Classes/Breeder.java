package com.example.user.bulletfalls.Game.GameBiznesFunctions.Classes;

import com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions.BreaderAction;
import com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions.ClassAction;
import com.example.user.bulletfalls.Game.Elements.Beast.BeastSpecyfication;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.GlobalUsage.Exceptions.IncorrectBeastNameException;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Storage.Sets.BeastsSet;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Arrays;

@JsonTypeName("breeder")
public class Breeder extends MasterAbility {
    BeastSpecyfication beastSpecyfication;
    public Breeder() {

        this.levelTable=new LevelTable( Arrays.asList(
                new LevelBoost(4,10),
                new LevelBoost(10,20),
                new LevelBoost(15,30),
                new LevelBoost(20,40),
                new LevelBoost(30,50),
                new LevelBoost(50,120)
        ));
        try {
            this.beastSpecyfication= BeastsSet.getInstance().getBreederPupil();
        } catch (IncorrectBeastNameException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getImage() {
        return R.drawable.summoner;
    }

    @Override
    public String getDescription() {
        return "Breeder jest największym miłośnikiem zwierząt, ma naturalny dar to opieki nad nimi" +
                " a zwierzęta świetnie się przy nim czują i są przyjaźnie do niego nastawione. Ale uważaj, jeden niewłaściwy ruch w stronę Breedera a " +
                "rzuci się na ciebie nie jedna paszcza uzbrojona w ostre kły ";
    }

    @Override
    public ClassAction action(EyeOnGame eog) {
        return new BreaderAction(this.timeQuant,this.beastSpecyfication);
    }

}
