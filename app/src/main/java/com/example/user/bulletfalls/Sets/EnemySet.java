package com.example.user.bulletfalls.Sets;

import android.content.Context;
import android.graphics.Point;

import com.example.user.bulletfalls.Enums.BE;
import com.example.user.bulletfalls.Enums.CharacterPositioning;
import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.Enums.Kind;
import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.Enums.Rarity;
import com.example.user.bulletfalls.Enums.Shape;
import com.example.user.bulletfalls.GameBiznesFunctions.Resistance.Resistance;
import com.example.user.bulletfalls.Objects.Bullet;
import com.example.user.bulletfalls.Objects.Description;
import com.example.user.bulletfalls.Objects.Enemy;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Specyfications.Dynamic.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.EnemySpecyfication;
import com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage.NoneBulletDoToCharacterStrategy;
import com.example.user.bulletfalls.Strategies.Bullet.BulletMoveStrategyPackage.Horizontal;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.AppearActionStrategy.AppearAction;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.AppearActionStrategy.GnomeAppearAction;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.AppearActionStrategy.NothingAppearAction;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.CharacterMoveStrategiesPackage.NoneDoToBulletStrategy;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.MoveStrategyPackage.UpDownMove;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.MoneyPossesStrategy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class EnemySet{

    public static List<EnemySpecyfication> enemySpecyficationSpecyficationsList = new LinkedList<>();

    Context context;




    public EnemySet(Context context) {
        this.context=context;
    }

    public List<EnemySpecyfication> load()
    {
        //FrameLayout game=(FrameLayout) context.findViewById(R.id.frame);
        AppearAction aa= new NothingAppearAction();
        EnemySpecyfication Gideon= new EnemySpecyfication("Gideon",R.drawable.gideon,10,20,200,100,20,1,new Resistance(0,0),null,Kind.HUMAN,Arrays.asList(GroupName.TentOfThelepathy),CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"gideon",new Description(),R.drawable.gideonminiature,aa,10,new UpDownMove());

        AppearAction gnomePowerAction= new GnomeAppearAction();


        EnemySpecyfication gnome2= new EnemySpecyfication("RandomGnome",R.drawable.gnome2,10,20,200,100,20,1,new Resistance(0,0),null,Kind.MONSTER,Arrays.asList(GroupName.TentOfThelepathy),CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"gideon",new Description(),R.drawable.gideonminiature,gnomePowerAction,10,new UpDownMove());
        EnemySpecyfication jeff= new EnemySpecyfication("Jeff",R.drawable.jeff,10,20,200,100,20,1,new Resistance(0,0),null,Kind.MONSTER,Arrays.asList(GroupName.Gnomes),CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"gideon",new Description(),R.drawable.gideonminiature,gnomePowerAction,10,new UpDownMove());
        EnemySpecyfication shmebulock= new EnemySpecyfication("Shmebulock",R.drawable.shmebulock,10,20,200,100,20,1,new Resistance(0,0),null,Kind.MONSTER,Arrays.asList(GroupName.Gnomes),CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"gideon",new Description(),R.drawable.gideonminiature,gnomePowerAction,10,new UpDownMove());
        EnemySpecyfication gnomesMonster= new EnemySpecyfication("Gnome Monster",R.drawable.gnomemonster,10,20,200,100,20,1,new Resistance(0,0),null,Kind.MONSTER,Arrays.asList(GroupName.Null),CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"gideon",new Description(),R.drawable.gideonminiature,aa,10,new UpDownMove());

        // EnemySpecyfication enemy1 = new EnemySpecyfication("enemy",R.drawable.enemy,10,20,200,100,20,1,0,null,null,null,CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"żaden",new Description(),R.drawable.miniature,aa,10,new UpDownMove());
      //  Enemy enemy2 = new Enemy(context,10,30,new Point(0,0),200,200,20,R.drawable.rinor,null,200,20,1,0,10,null,"Rinor",null,null,CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"żaden",new Description(),new UpDownMove(),aa);
     //   Enemy enemy3 = new Enemy(context,10,20,new Point(0,0),200,200,20,R.drawable.creature,null,30,20,1,0,10,null,"Creature",null,null,CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"żaden",new Description(),new UpDownMove(),aa);
     //   Enemy gideon= new Enemy(context,10,30,new Point(0,0),200,200,20,R.drawable.gideon,null,45,20,1,0,10,null,"Gideon",null,null,CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"żaden",new Description(),new UpDownMove(),aa);
     //   Enemy goblin= new Enemy(context,10,10,new Point(0,0),200,200,20,R.drawable.goblin,null,100,20,1,0,10,null,"Goblin",null,null,CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"żaden",new Description(),new UpDownMove(),aa);
     //   Enemy pacific= new Enemy(context,10,100,new Point(0,0),200,200,20,R.drawable.pacific,null,100,20,1,0,10,null,"Pacific",null,null,CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"żaden",new Description(),new UpDownMove(),aa);
    //    Enemy police1= new Enemy(context,20,2,new Point(0,0),200,200,20,R.drawable.police1,null,500,20,1,0,10,null,"Police1",null,null,CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"żaden",new Description(),new UpDownMove(),aa);


        /**Gnomes*/

       // Enemy gnome2= new Enemy(context,30,10,new Point(0,0),200,200,20,R.drawable.gnome2,null,40,20,1,0,10,null,"Gnome2",null,Arrays.asList(GroupName.Gnomes),CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"gnome",new Description(),new UpDownMove(),gnomePowerAction);
      //  Enemy jeff= new Enemy(context,30,10,new Point(0,0),200,200,20,R.drawable.jeff,null,40,20,1,0,10,null,"Jeff",null,Arrays.asList(GroupName.Gnomes),CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"gnome",new Description(),new UpDownMove(),gnomePowerAction);
      //  Enemy shmebulock= new Enemy(context,30,10,new Point(0,0),200,200,20,R.drawable.shmebulock,null,40,20,1,0,10,null,"Shmebullock",null,Arrays.asList(GroupName.Gnomes),CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"gnome",new Description(),new UpDownMove(),gnomePowerAction);
      //  Enemy gnomesMonster= new Enemy(context,20,2,new Point(0,0),300,300,20,R.drawable.gnomemonster,null,1000,20,1,20,10,null,"Gnome Monster",null,Arrays.asList(GroupName.Null),CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"gnome monster",new Description(),new UpDownMove(),aa);

        List<EnemySpecyfication> enemysCollection= enemySpecyficationSpecyficationsList;

        enemysCollection.add(Gideon);
        enemysCollection.add(gnome2);
        enemysCollection.add(jeff);
        enemysCollection.add(shmebulock);
        enemysCollection.add(gnomesMonster);
       // enemysCollection.add(enemy2);
     //   enemysCollection.add(enemy3);
     //   enemysCollection.add(gideon);
     //   enemysCollection.add(gnome2);
     //   enemysCollection.add(goblin);
     //   enemysCollection.add(pacific);
    //    enemysCollection.add(police1);
     //   enemysCollection.add(jeff);
    //    enemysCollection.add(shmebulock);
     //   enemysCollection.add(gnomesMonster);

        BulletSpecyfication bs= new BulletSpecyfication(BulletSet.getBullet(BE.STANDARD));
        bs.setSpeed(-20);
        for(EnemySpecyfication enemy :enemysCollection)
        {

            enemy.setBulletStrategy(bs.clone());
           // enemy.getBulletSpecyfication().setFrame(game);
        }

       // enemySpecyficationSpecyficationsList = convertToEnemySpecyficationList(enemysCollection);
        return  enemysCollection;
    }

    public Enemy getEnemy(String name)
    {

        for(EnemySpecyfication es: enemySpecyficationSpecyficationsList)
        {
            if(es.getName().equals(name))
            {
                return new Enemy(context,es);
            }
        }
        return null;
    }

    public List<Enemy> getAll()
    {
        return convertToEnemyList(enemySpecyficationSpecyficationsList);
    }

    public List<Enemy> getByGroup(GroupName groupName)
    {
        List<EnemySpecyfication> group= new LinkedList<>();
        for(EnemySpecyfication es: enemySpecyficationSpecyficationsList)
        {
            if(es.isFromGroup(groupName))
            group.add(es);
        }
        return convertToEnemyList(group);
    }
    public List<EnemySpecyfication> convertToEnemySpecyficationList(List<Enemy> enemies)
    {
        List<EnemySpecyfication> enemySpecyficationSpecyfications = new LinkedList<>();
        for(Enemy e: enemies) {
            enemySpecyficationSpecyfications.add(new EnemySpecyfication(e));
        }
        return enemySpecyficationSpecyfications;
    }

    public List<Enemy> convertToEnemyList(List<EnemySpecyfication> enemySpecyficationSpecyfications)
    {
        List<Enemy> enemies = new LinkedList<>();
        for(EnemySpecyfication es: enemySpecyficationSpecyfications) {
            enemies.add(new Enemy(context,es));
        }
        return enemies;
    }



}

