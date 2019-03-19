package com.example.user.bulletfalls.Interfaces;

import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.PossesStrategy;

public interface PossesAble {
    public PossesStrategy getPossesStrategy();
    public void setPossesStrategy(PossesStrategy possesStrategy);
    public String getName();
    public void setPermission(Permission permission);
    Permission getPermission();


}
