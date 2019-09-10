package com.example.user.bulletfalls.Strategies.Abilities.SummonerPackage.BeastRaisers;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Random;

@JsonTypeName("random")
public class RandomRaiser implements IBeastRaiser{
    int min;
    int max;
    private RandomRaiser() {

    }

    public RandomRaiser(int min, int max) {
        this.min = min;
        this.max = max;
        if(min>=max) try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public int beastsGroupSize() {
        Random random= new Random();
        return random.nextInt(max-min)+min;
    }
}
