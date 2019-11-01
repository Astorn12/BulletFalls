package com.example.user.bulletfalls.Game.ActionService.Actions;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.R;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CarpediemAction extends Action {
    private static List<Integer> list= Arrays.asList(R.drawable.candy,R.drawable.grenda,R.drawable.waddles,R.drawable.soos,R.drawable.dipper,
            R.drawable.mabel,R.drawable.mcgucket);
    public CarpediemAction(ActionType type) {
        super(type);

    }

    @Override
    public void doMagic(EyeOnGame eyeOnGame) {
        Hero character=eyeOnGame.getHero();
        Random random = new Random();
        int los=random.nextInt(8)+1;
        Game game=(Game)eyeOnGame.getHero().getContext();
        game.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch(los){
                    case 1:
                        character.setImageResource(list.get(0));

                        break;
                    case 2:
                        character.setImageResource(list.get(1));

                        break;
                    case 3:
                        character.setImageResource(list.get(2));

                        break;
                    case 4:
                        character.setImageResource(list.get(3));

                        break;
                    case 5:
                        character.setImageResource(list.get(4));
                        character.speedUpShooting(1);
                        break;
                    case 6:
                        character.setImageResource(list.get(5));
                        character.speedUp(10);

                        break;
                    case 7:
                        character.setImageResource(list.get(6));

                        break;
                }
            }
        });

    }
}
