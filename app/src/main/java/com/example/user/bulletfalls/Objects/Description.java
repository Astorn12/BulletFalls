package com.example.user.bulletfalls.Objects;

public class Description {
    String description;
    int height;
    public Description(String description,int height)
    {
        this.description=description;
        this.height=height;
    }

    public Description()
    {
        this.description="";
        this.height=0;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
