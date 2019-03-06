package com.example.user.bulletfalls.Strategies.Abilities;

import com.example.user.bulletfalls.Character;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("heal")
public class Heal implements DoToCharacterStrategy {
    int healValue;
    public Heal(int healValue)
    {

        this.healValue=healValue;
       // this.readyFlag=true;
    }
    public Heal()
    {

    }
    public int getHealValue() {
        return healValue;
    }
    public void setHealValue(int healValue) {
        this.healValue = healValue;

    }
    @Override
    public void doToCharacter(Character character) {
        if(character.getName().equals("Soos"))
        {
            character.heal(healValue*2);
        }
        else character.heal(healValue);
        //character.powerAnimation();
    }



}
