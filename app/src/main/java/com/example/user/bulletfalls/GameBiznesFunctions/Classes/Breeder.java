package com.example.user.bulletfalls.GameBiznesFunctions.Classes;

import com.example.user.bulletfalls.GameManagement.EyeOnGame;
import com.example.user.bulletfalls.Objects.Hero;
import com.example.user.bulletfalls.R;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("breeder")
public class Breeder implements IClass {
    @Override
    public int getImage() {
        return R.drawable.summoner;
    }

    @Override
    public String getDescription() {
        return "Breeder jest największym miłośnikiem zwierząt, ma naturalny dar to opieki nad mini a zwierzęta świetnie się przy nim czują i są przyjaźnie do niego nastawione. Ale uważaj, jeden niewłaściwy ruch w stronę Breedera a " +
                "rzuci się na ciebie nie jedna paszcza uzbrojona w ostre kły ";
    }

    @Override
    public void action(EyeOnGame eog, Hero hero) {
    }
}
