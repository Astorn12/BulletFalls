package com.example.user.bulletfalls.Specyfications.Dynamic.Characters;


import com.example.user.bulletfalls.GameBiznesFunctions.Resistance.IResistance;
import com.example.user.bulletfalls.Objects.Bullet;
import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Objects.Description;
import com.example.user.bulletfalls.Enums.CharacterPositioning;
import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.Specyfications.Dynamic.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.BeastSpecyfication;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.EnemySpecyfication;
import com.example.user.bulletfalls.Specyfications.Dynamic.DynamicSpecyfication;
import com.example.user.bulletfalls.Enums.Kind;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.AppearActionStrategy.AppearAction;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.CharacterMoveStrategiesPackage.DoToBulletStrategy;
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

    int life;
    int shootingSpeed;
    int level;
    IResistance resistance;
    BulletSpecyfication bulletSpecyfication;
    //int idBullet;//tutaj zamieniliśmy obiekt bulletSpecyfication na id bulletSpecyfication
    Kind kind;

    List<GroupName> groupNames;
    CharacterPositioning characterPositioning;
    public DoToBulletStrategy doToBulletStrategy;
    int irrealWidth;

    int irrealHeight;

    String indyvidualHeroMarker;

    Description description;
    int miniature;
    AppearAction appearAction;
    public CharacterSpecyfication(Character character) {
        super(character);
        this.life= character.getLife();
        this.shootingSpeed= character.getShootingSpeed();
        this.level= character.getLevel();
        this.resistance= character.getResistance();
        this.groupNames = character.getGroupNames();
        this.kind= character.getKind();
        this.characterPositioning= character.getPosition();
        this.bulletSpecyfication = character.getBullet().getJsonBullet();//new JsonBullet(character.getBulletSpecyfication());
        this.doToBulletStrategy= character.getDoToBulletStrategy();
        this.irrealHeight= character.getIrrealHeight();
        this.irrealWidth= character.getIrrealWidth();
        this.indyvidualHeroMarker= character.getIndyvidualHeroMarker();
        this.description= character.getDescription();
        this.miniature= character.getMiniature();
        this.appearAction=character.getAppearAction();

    }
    public CharacterSpecyfication() {

    }

    public CharacterSpecyfication(String name, int imageResource, int power, int speed, int height, int life, int shootingSpeed, int level, IResistance resistance, BulletSpecyfication bulletSpecyfication, Kind kind, List<GroupName> groupNames, CharacterPositioning characterPositioning, DoToBulletStrategy doToBulletStrategy, String indyvidualHeroMarker, Description description, int miniature, AppearAction appearAction) {
        super(name, imageResource, power, speed, height);
        this.life = life;
        this.shootingSpeed = shootingSpeed;
        this.level = level;
        this.resistance = resistance;
        this.bulletSpecyfication = bulletSpecyfication;
        this.kind = kind;
        this.groupNames = groupNames;
        this.characterPositioning = characterPositioning;
        this.doToBulletStrategy = doToBulletStrategy;
        this.irrealWidth = irrealWidth;
        this.irrealHeight = irrealHeight;
        this.indyvidualHeroMarker = indyvidualHeroMarker;
        this.description = description;
        this.miniature = miniature;
        this.appearAction = appearAction;
    }

    public DoToBulletStrategy getDoToBulletStrategy() {
        return doToBulletStrategy;
    }
    public void setDoToBulletStrategy(DoToBulletStrategy doToBulletStrategy) {
        this.doToBulletStrategy = doToBulletStrategy;
    }

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

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public IResistance getResistance() {
        return resistance;
    }
    public void setResistance(IResistance resistance) {
        this.resistance = resistance;
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

    public Kind getKind() {
        return kind;
    }
    public void setKind(Kind kind) {
        this.kind = kind;
    }



    public List<GroupName> getGroupNames() {
        return groupNames;
    }
    public void setGroupNames(List<GroupName> groupNames) {
        this.groupNames = groupNames;
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

    public int getMiniature() {
        return miniature;
    }

    public void setMiniature(int miniature) {
        this.miniature = miniature;
    }
    @JsonIgnore
    public boolean isFromGroup(GroupName groupName)
    {
        if(this.groupNames!=null) {
            for (GroupName g : this.groupNames) {
                if (g.equals(groupName)) return true;
            }

        return false;}
        else return false;
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
}
