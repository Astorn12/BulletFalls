package com.example.user.bulletfalls.Game.Management;

import android.graphics.Point;
import android.telephony.IccOpenLogicalChannelResponse;
import android.view.View;

import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.Enemy.EnemySpecyfication;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.example.user.bulletfalls.Game.Elements.Enemy.Enemy;
import com.example.user.bulletfalls.Game.Elements.Helper.Dynamic;
import com.example.user.bulletfalls.Game.Elements.Weapon.Weapon;
import com.example.user.bulletfalls.GlobalUsage.Enums.Shape;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Game.Elements.Beast.Beast;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.GlobalUsage.Exceptions.SuchEnemyDoesntExist;

import org.apache.commons.lang3.tuple.MutablePair;

import java.lang.reflect.Field;
import java.util.List;

public class CollisionTester {

    List<Bullet> bullets;
    Hero hero;
    List<Enemy> enemies;
    List<Beast> beasts;

    public CollisionTester()
    {
    }

    public boolean collisionChecking(ICollisionable first, ICollisionable second){

        if(bothAreCircle(first,second)){

            if (Math.sqrt(Math.pow(getMiddle(first).x - getMiddle(second).x,2 )+ Math.pow( getMiddle(first).y - getMiddle(second).y,2)) > first.getWidth() / 2 + second.getWidth() / 2) {
                return false;
            }
            else
            {
                return true;
            }

        }else{
            if(Math.abs(getMiddle(first).x-getMiddle(second).x)<first.getWidth()/2+second.getWidth()/2&&Math.abs(getMiddle(first).y-getMiddle(second).y)<first.getHeight()/2+second.getHeight()/2)
            {
                return true;
            }
            else return false;
        }
    }

    private boolean bothAreCircle(ICollisionable first, ICollisionable second) {
        return first.getShape().equals(Shape.CIRCLE)&& second.getShape().equals(Shape.CIRCLE);
    }

    private Point getMiddle(ICollisionable collisionable){
            float py=collisionable.getX()+collisionable.getWidth()/2;
            float px=collisionable.getY()+collisionable.getHeight()/2;
            return new Point((int)py,(int)px);
    }

    public void collisionChecking(Hero hero, List<Enemy> enemies, List<Bullet> bullets, Medium medium, List<Beast> beasts){
        this.hero = hero;
        this.enemies = enemies;
        this.bullets = bullets;
        this.beasts = beasts;
        //sprawdzanie obrażeń usyskanych przez bohatera
        for(Bullet bullet : bullets)
        {
            if(bullet.getSpeed()<0&&damageToCharacterChecking(hero, bullet))
            {
               // hero.getAttackDefenceFilter().filterDefence(bullet);
                int d= hero.takeDemage(bullet);
                medium.takedDamage(new MutablePair<Integer, BulletSpecyfication>(d,bullet.getSpecyfication()));

            }
           // for(Enemy enemy : enemies)
            for(int i=0;i<enemies.size();i++)
            {
                Enemy enemy= enemies.get(i);
                if(bullet.getSpeed()>0&&damageToCharacterChecking(enemy, bullet)) {

                    int d= bullet.collisionWithCharacterEfect(enemy);
                    medium.enemyHited(new Hitt(enemy.getSpecyfication(),bullet.getSpecyfication(),d));
                    //enemy.doToBullet(bullet);
                }
            }
            for(Beast sb:this.beasts)
            {
                if(bullet.getSpeed()<0&&damageToCharacterChecking(sb, bullet)) {
                    int d= bullet.collisionWithCharacterEfect(sb);
                    //medium.enemyHited(new Hitt(new EnemySpecyfication(enemy),new BulletSpecyfication(bullet),d));
                    //sb.doToBullet(bullet);
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
    {

    if(a.getShape().equals(Shape.CIRCLE)&&b.getShape().equals(Shape.CIRCLE))
        {
        if (Math.sqrt(Math.pow(a.getMiddle().x - b.getMiddle().x,2 )+ Math.pow( a.getMiddle().y - b.getMiddle().y,2)) > a.getWidth() / 2 + b.getWidth() / 2) {
            return false;
        }
        else
        {
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
        if(!isBulletInActiveArea(b,a) && Math.abs(a.getMiddle().x-b.getMiddle().x)<a.getWidth()/2+b.getWidth()/2&&Math.abs(a.getMiddle().y-b.getMiddle().y)<a.getHeight()/2+b.getHeight()/2)
        {
            return true;
        }
        else return false;
    }

    private boolean isBulletInActiveArea(Bullet bullet, Character character){
        ConvertedShape shape= new ConvertedShape(character);
        ConvertedShape.Area area=shape.getArea();
        return areasChecking(area,bullet);


    }

    private boolean areasChecking(ConvertedShape.Area a, Bullet b)
    {

        if( Math.abs(a.getMiddle().x-b.getMiddle().x)<a.getWidth()/2+b.getWidth()/2&&Math.abs(a.getMiddle().y-b.getMiddle().y)<a.getHeight()/2+b.getHeight()/2)
        {
            return true;
        }
        else return false;
    }


    private class ConvertedShape{
        private final static float dangerousAreaPointer=0.8f;// x percentage of characted shape can't get damage


        private Area area;
        public  ConvertedShape( Character character){
            try {
                convert(character);
            } catch (SuchEnemyDoesntExist suchEnemyDoesntExist) {
                suchEnemyDoesntExist.printStackTrace();
            }
        }

        private void convert(Character character) throws SuchEnemyDoesntExist {
            this.area= new Area(character.getWidth()*dangerousAreaPointer,
                    character.getHeight(),
                    character.getX(),
                    character.getY());

            if(character.getClass()==Hero.class ||character.getClass()==Beast.class){
                area.x-=(1-dangerousAreaPointer)*area.width;
            }else if(character.getClass()==Enemy.class){
                area.x+=(1-dangerousAreaPointer)*area.width;
            }else throw new SuchEnemyDoesntExist(character.getClass().toString());

        }

        public Area getArea() {
            return area;
        }

        private class Area{
            float  width;
            float height;
            float x;
            float y;

            public Area(float width, float height, float x, float y) {
                this.width = width;
                this.height = height;
                this.x = x;
                this.y = y;
            }

            public float getWidth() {
                return width;
            }

            public void setWidth(float width) {
                this.width = width;
            }

            public float getHeight() {
                return height;
            }

            public void setHeight(float height) {
                this.height = height;
            }

            public float getX() {
                return x;
            }

            public void setX(float x) {
                this.x = x;
            }

            public float getY() {
                return y;
            }

            public void setY(float y) {
                this.y = y;
            }

            public Point getMiddle(){
                return new Point((int)(this.getX()+this.getWidth()/2),(int)(this.getY()+this.getHeight()/2));
            }
        }

    }

}
