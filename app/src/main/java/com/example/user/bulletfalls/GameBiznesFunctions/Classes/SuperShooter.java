package com.example.user.bulletfalls.GameBiznesFunctions.Classes;

import com.example.user.bulletfalls.GameManagement.EyeOnGame;
import com.example.user.bulletfalls.Objects.Hero;
import com.example.user.bulletfalls.R;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("supershooter")
public class SuperShooter  implements IClass {
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
    public void action(EyeOnGame eog, Hero hero) {

    }
}
