package com.example.user.bulletfalls.Strategies.Abilities;

import com.example.user.bulletfalls.Character;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.Standard;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.Stot;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Random;
@JsonTypeName("carpeddiem")
public class CarpedDiem implements DoToCharacterStrategy{
    @Override
    public void doToCharacter(Character character) {
        Random random = new Random();
        int los=random.nextInt(8)+1;

        switch(los){
            case 1:
                character.setImageResource(R.drawable.candy);
                character.getBullet().speedUp(10);
                break;
            case 2:
                character.setImageResource(R.drawable.grenda);
                character.getBullet().boostPower(0.1);
                break;
            case 3:
                character.setImageResource(R.drawable.waddles);
                if(character.getDoToBulletStrategy().getClass().equals(Standard.class))
                {
                    character.setDoToBulletStrategy(new Stot());
                }
                else
                {
                    character.heal(30);
                }
                break;
            case 4:
                character.setImageResource(R.drawable.soos);
                character.boostResistance(10);
                break;
            case 5:
                character.setImageResource(R.drawable.dipper);
                character.speedUpShooting(1);
                break;
            case 6:
                character.setImageResource(R.drawable.mabel);
                character.speedUp(10);

                break;
            case 7:
                character.setImageResource(R.drawable.mcgucket);

                break;



        }

    }
}
