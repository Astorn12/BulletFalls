package com.example.user.bulletfalls.Specyfications.Characters;


import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Objects.Description;
import com.example.user.bulletfalls.Enums.CharacterPositioning;
import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.Specyfications.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Specyfications.ViewElementSpecyfication;
import com.example.user.bulletfalls.Enums.Kind;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.DoToBulletStrategy;
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
        @JsonSubTypes.Type(value = SummonedBeastSpecyfication.class, name = "summonedbeastspecyfication")

})
@JsonTypeName("characterspecyfication")
public class CharacterSpecyfication extends ViewElementSpecyfication {

    int life;
    int shootingSpeed;
    int level;
    int resistance;
    BulletSpecyfication bullet;
    //int idBullet;//tutaj zamieniliśmy obiekt bullet na id bullet
    Kind kind;

    List<GroupName> groupNames;
    CharacterPositioning characterPositioning;
    public DoToBulletStrategy doToBulletStrategy;
    int irrealWidth;

    int irrealHeight;

    String indyvidualHeroMarker;
    String story;
    Description description;
    int miniature;

    public CharacterSpecyfication(Character character) {
        super(character);
        this.life=character.getLife();
        this.shootingSpeed=character.getShootingSpeed();
        this.level=character.getLevel();
        this.resistance=character.getResistance();
        this.groupNames =character.getGroupNames();
        this.kind=character.getKind();
        this.characterPositioning=character.getPosition();
        this.bullet=character.getBullet().getJsonBullet();//new JsonBullet(character.getBullet());
        this.doToBulletStrategy=character.getDoToBulletStrategy();
        this.irrealHeight= character.getIrrealHeight();
        this.irrealWidth=character.getIrrealWidth();
        this.indyvidualHeroMarker=character.getIndyvidualHeroMarker();
        this.story=character.getStory();
        this.description=character.getDescription();
        this.miniature=character.getMiniature();

    }
    public CharacterSpecyfication() {

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

    public int getResistance() {
        return resistance;
    }
    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public BulletSpecyfication getBullet() {
        return bullet;
    }
    public void setBullet(BulletSpecyfication bullet) {
        this.bullet = bullet;
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

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
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
}
