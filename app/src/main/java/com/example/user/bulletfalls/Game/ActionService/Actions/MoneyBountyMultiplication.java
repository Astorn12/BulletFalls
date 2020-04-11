package com.example.user.bulletfalls.Game.ActionService.Actions;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.Elements.Items.Item;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.Game.Management.Medium;
import com.example.user.bulletfalls.Game.Strategies.Bounty.Bounty;
import com.example.user.bulletfalls.Profile.Currency;

public class MoneyBountyMultiplication extends Action {
    int percentage;
    public MoneyBountyMultiplication(ActionType type, int percentage) {
        super(type);
        this.percentage=percentage;
    }

    @Override
    public void doMagic(EyeOnGame eyeOnGame) {
        Medium medium= eyeOnGame.getMedium();
        Bounty bounty=medium.getBounty();
        int money=bounty.getMoney();
        int extraMoney=(int) (money*((float)this.percentage/100));
       //medium.addMoney(extraMoney);

    }
}
