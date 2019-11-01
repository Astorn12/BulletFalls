package com.example.user.bulletfalls.Game.Elements.Helper;


import android.content.Context;

import com.example.user.bulletfalls.Game.Elements.Beast.BeastSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletDoToCharacterStrategyPackage.BulletDoToCharacterStrategy;
import com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletMoveStrategyPackage.BulletMoveStrategy;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackDefenceFilter;
import com.example.user.bulletfalls.Game.Elements.Enemy.EnemySpecyfication;
import com.example.user.bulletfalls.Game.Elements.Helper.Sizers.BulletScale;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Active.CharacterAS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Collection.CharacterCS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive.CharacterPS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.View.CharacterVS;
import com.example.user.bulletfalls.Game.Elements.Hero.HeroSpecyfication;
import com.example.user.bulletfalls.GlobalUsage.Enums.CharacterPositioning;
import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;

import com.example.user.bulletfalls.GlobalUsage.Enums.Kind;
import com.example.user.bulletfalls.Game.Elements.Overal.AppearActionStrategy.AppearAction;
import com.example.user.bulletfalls.GlobalUsage.Supporters.DrawableConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = HeroSpecyfication.class, name = "heropecyfication"),
        @JsonSubTypes.Type(value = EnemySpecyfication.class, name = "enemyspecyfication"),
        @JsonSubTypes.Type(value = BeastSpecyfication.class, name = "beastspecyfication")

})
@JsonTypeName("characterspecyfication")
public class CharacterSpecyfication extends DynamicSpecyfication {

    /**VIEW STATISTICS*/
    Description description;

    /**PASSIVE STATISTICS*/
    int life;
    int shootingSpeed;
    BulletSpecyfication bulletSpecyfication;

    /**ACTIVE STATISTICS*/
    CharacterPositioning characterPositioning;
    AttackDefenceFilter attackDefenceFilter;
    AppearAction appearAction;

    /**COLLECTION STATISTICS*/
    String indyvidualHeroMarker;
    List<FamilyName> familyNames;
    List<Kind> kinds;


    int irrealWidth;
    int irrealHeight;

    public CharacterSpecyfication(String name, CharacterVS characterVS, CharacterPS characterPS, CharacterAS characterAS,CharacterCS characterCS){
        super(name,characterVS,characterPS);

        this.description=characterVS.getDescription();

        this.life=characterPS.getLife();
        this.shootingSpeed=characterPS.getShootingSpeed();
        this.bulletSpecyfication=characterPS.getBulletSpecyfication();

        this.characterPositioning=characterAS.getCharacterPositioning();
        this.attackDefenceFilter=characterAS.getAttackDefenceFilter();
        this.appearAction=characterAS.getAppearAction();

        this.indyvidualHeroMarker=characterCS.getIndyvidualHeroMarker();
        this.familyNames=characterCS.getFamilyNames();
        this.kinds=characterCS.getKind();
    }
    protected CharacterSpecyfication(){}


    /**BIZNES METHODS*/
    @JsonIgnore
    public boolean isFromGroup(FamilyName familyName)
    {
        if(this.familyNames !=null) {
            for (FamilyName g : this.familyNames) {
                if (g.equals(familyName)) return true;
            }

            return false;}
        else return false;
    }

    /**GETTERS & SETTERS*/
    public int getLife() {
        return life;
    }
    public void setLife(int life) {
        this.life = life;
    }

    public int getShootingSpeed() {
        return shootingSpeed;
    }
    public void setShootingSpeed(int shootingSpeed) {
        this.shootingSpeed = shootingSpeed;
    }

    public BulletSpecyfication getBulletSpecyfication() {
        return bulletSpecyfication;
    }
    public void setBulletSpecyfication(BulletSpecyfication bulletSpecyfication) {
        this.bulletSpecyfication = bulletSpecyfication;
    }

    public CharacterPositioning getCharacterPositioning() {
        return characterPositioning;
    }
    public void setCharacterPositioning(CharacterPositioning characterPositioning) {
        this.characterPositioning = characterPositioning;
    }

    public List<FamilyName> getFamilyNames() {
        return familyNames;
    }

    public void setFamilyNames(List<FamilyName> familyNames) {
        this.familyNames = familyNames;
    }

    public int getIrrealWidth() {
        return irrealWidth;
    }

    public void setIrrealWidth(int irrealWidth) {
        this.irrealWidth = irrealWidth;
    }

    public int getIrrealHeight() {
        return irrealHeight;
    }

    public void setIrrealHeight(int irrealHeight) {
        this.irrealHeight = irrealHeight;
    }

    public String getIndyvidualHeroMarker() {
        return indyvidualHeroMarker;
    }

    public void setIndyvidualHeroMarker(String indyvidualHeroMarker) {
        this.indyvidualHeroMarker = indyvidualHeroMarker;
    }



    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }


    public AppearAction getAppearAction() {
        return appearAction;
    }

    public void setAppearAction(AppearAction appearAction) {
        this.appearAction = appearAction;
    }

    public void setBulletStrategy(BulletSpecyfication bulletSpecyfication) {
        this.bulletSpecyfication=bulletSpecyfication;
    }

    public AttackDefenceFilter getAttackDefenceFilter() {
        return attackDefenceFilter;
    }

    public void setAttackDefenceFilter(AttackDefenceFilter attackDefenceFilter) {
        this.attackDefenceFilter = attackDefenceFilter;
    }

    public List<Kind> getKinds() {
        return kinds;
    }

    public void setKinds(List<Kind> kinds) {
        this.kinds = kinds;
    }

    @JsonIgnore
    public int getMiniature(Context context){
        DrawableConverter dc= new DrawableConverter();
        String miniatureName=context.getResources().getResourceName(this.getImage())+"miniature";
        return dc.getDrawableByName(context,miniatureName);
    }




}
