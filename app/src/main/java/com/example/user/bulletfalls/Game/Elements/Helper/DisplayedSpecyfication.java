package com.example.user.bulletfalls.Game.Elements.Helper;

import com.example.user.bulletfalls.Game.Elements.Ability.Specyfication.AbilitySpecyfication;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value =AbilitySpecyfication.class, name = "abilityspecyfication"),
        @JsonSubTypes.Type(value =DynamicSpecyfication.class, name = "dynamicspecyfication"),

})
public abstract class DisplayedSpecyfication {
    protected String name;
    protected int imageResource;


    public DisplayedSpecyfication(String name, int imageResource) {
        this.imageResource = imageResource;
        this.name = name;
    }
    protected DisplayedSpecyfication()
    {

    }

    public int getImage() {
        return imageResource;
    }

    public void setImage(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
