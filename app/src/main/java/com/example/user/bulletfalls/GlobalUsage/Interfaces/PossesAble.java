package com.example.user.bulletfalls.GlobalUsage.Interfaces;

import com.example.user.bulletfalls.GlobalUsage.Enums.Permission;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.PossesStrategy;

public interface PossesAble {
    PossesStrategy getPossesStrategy();
    void setPossesStrategy(PossesStrategy possesStrategy);
    String getName();
}
