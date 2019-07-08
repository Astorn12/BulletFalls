package com.example.user.bulletfalls.Specyfications;

import com.example.user.bulletfalls.Specyfications.Dynamic.DynamicSpecyfication;
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
    int imageResource;
    String name;

    public DisplayedSpecyfication(String name, int imageResource) {
        this.imageResource = imageResource;
        this.name = name;
    }
    protected DisplayedSpecyfication()
    {

    }

    public int getImageResources() {
        return imageResource;
    }

    public void setImageResources(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
