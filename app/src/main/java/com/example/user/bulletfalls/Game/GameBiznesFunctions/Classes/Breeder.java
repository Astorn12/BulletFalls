package com.example.user.bulletfalls.Game.GameBiznesFunctions.Classes;

import com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions.ClassAction;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.R;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("breeder")
public class Breeder implements MasterAbility {
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
        return null;
    }


}
