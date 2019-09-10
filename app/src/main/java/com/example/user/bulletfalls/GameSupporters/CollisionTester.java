package com.example.user.bulletfalls.GameSupporters;

import com.example.user.bulletfalls.Objects.Bullet;
import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Objects.Enemy;
import com.example.user.bulletfalls.Enums.Shape;
import com.example.user.bulletfalls.GameSupporters.MediumTasks.Hitt;
import com.example.user.bulletfalls.GameSupporters.MediumTasks.Medium;
import com.example.user.bulletfalls.Objects.Hero;
import com.example.user.bulletfalls.Objects.Beast;
import com.example.user.bulletfalls.Specyfications.Dynamic.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.EnemySpecyfication;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.List;

public class CollisionTester {

    List<Bullet> bullets;
    Hero hero;
    List<Enemy> enemies;
    List<Beast> beasts;

    public CollisionTester()
    {

    }

    public void collisionChecking(Hero hero, List<Enemy> enemies, List<Bullet> bullets, Medium medium, List<Beast> beasts)
    {
        this.hero = hero;
        this.enemies = enemies;
        this.bullets = bullets;
        this.beasts = beasts;
        //sprawdzanie obrażeń usyskanych przez bohatera
        for(Bullet bullet : bullets)
        {
            if(bullet.getSpeed()<0&&damageToCharacterChecking(hero, bullet))
            {   int d= bullet.collisionWithCharacterEfect(hero);
                medium.takedDamage(new MutablePair<Integer, BulletSpecyfication>(d,new BulletSpecyfication(bullet)));
                hero.doToBullet(bullet);
            }


           // for(Enemy enemy : enemies)
            for(int i=0;i<enemies.size();i++)
            {
                Enemy enemy= enemies.get(i);
                if(bullet.getSpeed()>0&&damageToCharacterChecking(enemy, bullet)) {

                    int d= bullet.collisionWithCharacterEfect(enemy);
                    medium.enemyHited(new Hitt(new EnemySpecyfication(enemy),new BulletSpecyfication(bullet),d));
                    enemy.doToBullet(bullet);
                }
            }

            for(Beast sb:this.beasts)
            {
                if(bullet.getSpeed()<0&&damageToCharacterChecking(sb, bullet)) {

                    int d= bullet.collisionWithCharacterEfect(sb);
                    //medium.enemyHited(new Hitt(new EnemySpecyfication(enemy),new BulletSpecyfication(bullet),d));
                    sb.doToBullet(bullet);
                }
            }

        }

    }


    private void bulletsColisionChecking()
    {
        for(int i = 0; i< bullets.size(); i++)
        {
          //  if(bullets.get(i).isCollisionAble())
           // {
                for(int j = i+1; j< bullets.size(); j++)
                {

                    Bullet a= bullets.get(i);
                    Bullet b= bullets.get(j);
                    if(ifCollisionHappened(a,b)) {
                        if (bullet2bulletColisionChecking(a, b) && !sameDirectionChecking(a, b)) {
                            int tmpPower = a.getPower();
                            int speeda = a.getSpeed();
                            int speedb = b.getSpeed();

                            int xa = (int) a.getMiddle().x, ya = (int) a.getMiddle().y;
                            int xb = (int) b.getMiddle().x, yb = (int) b.getMiddle().y;

                            int widtha = a.getWidth();
                            int widthb = b.getWidth();

                            a.setPower(a.getPower() - b.getPower());
                            if (a.getPower() < 0) a.setPower(0);
                            b.setPower(b.getPower() - tmpPower);
                            if (b.getPower() < 0) b.setPower(0);
                            int ap = a.getPower(), bp = b.getPower();

                        }
                    }
                }
          //  }
        }
    }
    private boolean ifCollisionHappened(Bullet a , Bullet b)
    {
        if(a.isCollisionAble()|| b.isCollisionAble())
        {
            return true;
        }
        return false;
    }
    private boolean sameDirectionChecking(Bullet a, Bullet b)
    {
        if((a.getSpeed()>0 &b.getSpeed()>0)||(a.getSpeed()<0&&b.getSpeed()<0))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    private boolean bullet2bulletColisionChecking(Bullet a, Bullet b)
    { if(a.getShape().equals(Shape.CIRCLE)&&b.getShape().equals(Shape.CIRCLE)) {
        if (Math.sqrt(Math.pow(a.getMiddle().x - b.getMiddle().x,2 )+ Math.pow( a.getMiddle().y - b.getMiddle().y,2)) > a.getWidth() / 2 + b.getWidth() / 2) {
            return false;
        }
        else
        {

            int x=(int)Math.sqrt(Math.pow( a.getMiddle().x - b.getMiddle().x,2 )+ Math.pow( a.getMiddle().y - b.getMiddle().y,2));
            int y=a.getWidth() / 2 + b.getWidth() / 2;
            return true;
        }
    }
    else
    {
        if(Math.abs(a.getMiddle().x-b.getMiddle().x)<a.getWidth()/2+b.getWidth()/2&&Math.abs(a.getMiddle().y-b.getMiddle().y)<a.getHeight()/2+b.getHeight()/2)
        {
            return true;
        }
        else return false;
    }
    }

    private boolean damageToCharacterChecking(Character a, Bullet b)
    {
        if(Math.abs(a.getMiddle().x-b.getMiddle().x)<a.getWidth()/2+b.getWidth()/2&&Math.abs(a.getMiddle().y-b.getMiddle().y)<a.getHeight()/2+b.getHeight()/2)
        {
            return true;
        }
        else return false;
    }
}
