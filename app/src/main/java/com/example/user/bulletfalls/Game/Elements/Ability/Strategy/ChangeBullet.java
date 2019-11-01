package com.example.user.bulletfalls.Game.Elements.Ability.Strategy;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.Actions.AbilitysActions.ChangeHeroBullet;
import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=   NumberChangeBullet.class, name = "numberchangebullet"),
        @JsonSubTypes.Type(value=   TimeChangeBullet.class, name = "timerchangebullet")

})
@JsonTypeName("changebullet")
public class ChangeBullet implements StartAction {
    //BulletSpecyfication bulletSpecyfication;
    BulletSpecyfication bulletSpecyfication;
    public ChangeBullet()
    {

    }
    public ChangeBullet(BulletSpecyfication bullet)
    {
        this.bulletSpecyfication =bullet;
    }
    @Override
    public Action prepareAction(EyeOnGame eyeOnGame) {

        return new ChangeHeroBullet(this.bulletSpecyfication);
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setAdditionalDescription(LinearLayout linearLayout) {

    }

    public BulletSpecyfication getBulletSpecyfication() {
        return bulletSpecyfication;
    }

    public void setBulletSpecyfication(BulletSpecyfication bulletSpecyfication) {
        this.bulletSpecyfication = bulletSpecyfication;
    }
}
