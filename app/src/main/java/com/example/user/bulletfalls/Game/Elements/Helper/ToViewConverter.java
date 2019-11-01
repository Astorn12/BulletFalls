package com.example.user.bulletfalls.Game.Elements.Helper;

import android.content.Context;

import com.example.user.bulletfalls.BuildConfig;
import com.example.user.bulletfalls.Game.Elements.Beast.Beast;
import com.example.user.bulletfalls.Game.Elements.Beast.BeastSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Enemy.Enemy;
import com.example.user.bulletfalls.Game.Elements.Enemy.EnemySpecyfication;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Game.Elements.Hero.HeroSpecyfication;
import com.example.user.bulletfalls.GlobalUsage.Enums.HE;

import java.util.LinkedList;
import java.util.List;

public class ToViewConverter {

    public static List<Hero> convertHeroes(Context context, List<HeroSpecyfication> specyfications){
        List<Hero> heroes= new LinkedList<>();
        for(HeroSpecyfication specyfication:specyfications){
            heroes.add(new Hero(context,specyfication));
        }
        return heroes;
    }

    public static List<Bullet> convertBullets(Context context, List<BulletSpecyfication> specyfications){
        List<Bullet> bullets= new LinkedList<>();
        for(BulletSpecyfication specyfication:specyfications){
            bullets.add(new Bullet(context,specyfication));
        }
        return bullets;
    }

    public static List<Enemy> convertEnemys(Context context, List<EnemySpecyfication> specyfications){
        List<Enemy> views= new LinkedList<>();
        for(EnemySpecyfication specyfication:specyfications){
            views.add(new Enemy(context,specyfication));
        }
        return views;
    }

    public static List<Beast> convertBeasts(Context context, List<BeastSpecyfication> specyfications){
        List<Beast> views= new LinkedList<>();
        for(BeastSpecyfication specyfication:specyfications){
            views.add(new Beast(context,specyfication));
        }
        return views;
    }

}
