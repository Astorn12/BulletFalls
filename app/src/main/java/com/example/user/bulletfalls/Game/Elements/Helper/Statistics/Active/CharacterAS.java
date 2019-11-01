package com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Active;

import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackDefenceFilter;
import com.example.user.bulletfalls.Game.Elements.Overal.AppearActionStrategy.AppearAction;
import com.example.user.bulletfalls.GlobalUsage.Enums.CharacterPositioning;

public class CharacterAS {
    CharacterPositioning characterPositioning;
    AttackDefenceFilter attackDefenceFilter;
    AppearAction appearAction;

    public CharacterAS(CharacterPositioning characterPositioning, AttackDefenceFilter attackDefenceFilter, AppearAction appearAction) {
        this.characterPositioning = characterPositioning;
        this.attackDefenceFilter = attackDefenceFilter;
        this.appearAction = appearAction;
    }


    public CharacterPositioning getCharacterPositioning() {
        return characterPositioning;
    }

    public void setCharacterPositioning(CharacterPositioning characterPositioning) {
        this.characterPositioning = characterPositioning;
    }

    public AttackDefenceFilter getAttackDefenceFilter() {
        return attackDefenceFilter;
    }

    public void setAttackDefenceFilter(AttackDefenceFilter attackDefenceFilter) {
        this.attackDefenceFilter = attackDefenceFilter;
    }

    public AppearAction getAppearAction() {
        return appearAction;
    }

    public void setAppearAction(AppearAction appearAction) {
        this.appearAction = appearAction;
    }
}
