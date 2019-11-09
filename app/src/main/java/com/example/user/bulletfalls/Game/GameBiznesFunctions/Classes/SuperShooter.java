package com.example.user.bulletfalls.Game.GameBiznesFunctions.Classes;

import com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions.ClassAction;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.R;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("supershooter")
public class SuperShooter  implements MasterAbility {
    @Override
    public int getImage() {
        return R.drawable.shooter;
    }

    @Override
    public String getDescription() {
        return "Super Shooterzy to mistrzowie w fachu strzelecki, zwiększają oni moc co trzeciego strzłu, lepiej niech przeciwnik liczy " +
                "liczy kulki, aby znowy nie oberwać srogim pociskiem.";
    }

    @Override
    public ClassAction action(EyeOnGame eog) {
        return null;
    }


}
