package com.example.user.bulletfalls.Strategies;

import android.content.Context;
import android.widget.ImageView;

import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.Interfaces.PossesAble;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.PossesStrategy;

public class Test extends android.support.v7.widget.AppCompatImageView implements PossesAble {
    public Test(Context context) {
        super(context);
    }

    @Override
    public PossesStrategy getPossesStrategy() {
        return null;
    }

    @Override
    public void setPossesStrategy(PossesStrategy possesStrategy) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setPermission(Permission permission) {

    }

    @Override
    public Permission getPermission() {
        return null;
    }
}
