package com.example.user.bulletfalls.Missions.Requirements;

import android.content.Context;

import com.example.user.bulletfalls.Missions.Missionable;
import com.example.user.bulletfalls.Storage.Data.OveralStatisticsRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

public  class OveralStatistick {

    String name;
    int amount;
    int token;
    @JsonIgnore
    OveralStatisticChecker checker;

    public OveralStatistick(String name, int amount,OveralStatisticChecker checker) {
        this.name = name;
        this.amount = amount;
        this.checker=checker;
        //token=0;
    }

    public OveralStatistick(String name, int i) {
        this.name=name;
        this.amount=i;
    }

    public void incrementAmount(int n){
        if(n>0){
            this.amount+=n;
        }
        else try {
            throw new NegativeNumberException();
        } catch (NegativeNumberException e) {
            e.printStackTrace();
        }
    }

    private void save(Context context){
        OveralStatisticsRepository overalStatisticsRepository= new OveralStatisticsRepository(context);
        overalStatisticsRepository.update(this);
    }

    public void update(Missionable missionable, int token,Context context) {
        if (this.token!=token) {
            this.token = token;
            int newAmount=missionable.acceptChecking(this.checker,this.amount);
            if(newAmount!=amount){
                this.amount =newAmount;
                this.save(context);
            }
        }
    }

    private class NegativeNumberException extends Throwable {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public OveralStatisticChecker getChecker() {
        return checker;
    }

    public void setChecker(OveralStatisticChecker checker) {
        this.checker = checker;
    }
}
