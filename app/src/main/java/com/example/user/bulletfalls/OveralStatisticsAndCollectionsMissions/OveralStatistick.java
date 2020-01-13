package com.example.user.bulletfalls.OveralStatisticsAndCollectionsMissions;

import android.content.Context;

import com.example.user.bulletfalls.Storage.Data.OveralStatisticsRepository;

public  class OveralStatistick {

    String name;
    int amount;


    public OveralStatistick(String name, int amount) {
        this.name = name;
        this.amount = amount;

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

    public void save(Context context){
        OveralStatisticsRepository overalStatisticsRepository= new OveralStatisticsRepository(context);
        overalStatisticsRepository.update(this);
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



}
