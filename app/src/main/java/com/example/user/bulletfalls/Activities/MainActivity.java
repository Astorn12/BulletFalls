
package com.example.user.bulletfalls.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.Database.DatabaseAdministrator;
import com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.EnemyReleaseStrategyPackage.RandomEnemyReleaseStrategy;
import com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.EnemysChooser;
import com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.TimeReleaseStrategyPackage.LinearTimeReleaseStrategy;
import com.example.user.bulletfalls.GameSupporters.GameStrategy;
import com.example.user.bulletfalls.GameSupporters.GiveBountyPackage.BountyAssigner;
import com.example.user.bulletfalls.GameSupporters.GiveBountyPackage.ConcreteBountyAssigner;
import com.example.user.bulletfalls.GameSupporters.GiveBountyPackage.HunterDecorator;
import com.example.user.bulletfalls.GameSupporters.GiveBountyPackage.TimeBountyDecorator;
import com.example.user.bulletfalls.GameSupporters.MediumTasks.ArchivCurrencyContainer;
import com.example.user.bulletfalls.Sets.AbilitySet;
import com.example.user.bulletfalls.Sets.BulletSet;
import com.example.user.bulletfalls.Sets.EnemySet;
import com.example.user.bulletfalls.Sets.HeroAbilityBulletMapper;
import com.example.user.bulletfalls.Sets.HeroesSet;
import com.example.user.bulletfalls.Objects.Enemy;
import com.example.user.bulletfalls.GameManagement.Game;
import com.example.user.bulletfalls.GameManagement.GameController;
import com.example.user.bulletfalls.Objects.Hero;
import com.example.user.bulletfalls.P2PPackage.P2PConnection;
import com.example.user.bulletfalls.P2PPackage.P2PGame;
import com.example.user.bulletfalls.ProfileActivity.Currency;
import com.example.user.bulletfalls.ProfileActivity.ProfileScreen;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.ShopPackage.Shop;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.EnemySpecyfication;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.MoneyNeed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public TextView scoreLabel;
    FrameLayout frame;
    Hero hero;
    LinkedList<Enemy> enemies;
    private GameController controller;
    private final int MEMORY_ACCESS=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArchivCurrencyContainer archivCurrencyContainer= new ArchivCurrencyContainer();
        archivCurrencyContainer.add(new Currency("Mystery Coins"),5);
        MoneyNeed moneyNeed= new MoneyNeed(archivCurrencyContainer);
        ObjectMapper objectMapper= new ObjectMapper();
        Currency currency= new Currency("Mystery Coin");
        String s;
        MutablePair<Currency,Integer> mutablePair= new MutablePair<>(currency,8);
        try {
             s= objectMapper.writeValueAsString(mutablePair);
             int x=0;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){}
        else {
                HeroesSet.clear();
                BulletSet.clear();
                AbilitySet.getInstance().clear();
            if (BulletSet.isEmpty()) {
                BulletSet.AddToDatabaseTest(this);
                BulletSet.save(this);
                BulletSet.clear();
                BulletSet.load(this);//to powinno się znaleźć później tutaj normalnie
            }
           if (AbilitySet.getInstance().isEmpty()) {
                AbilitySet.getInstance().AddToDatabaseTest(this);
                AbilitySet.getInstance().save(this);
                AbilitySet.getInstance().clear();
                AbilitySet.getInstance().load(this);
            }
            if (HeroesSet.isEmpty()) {
                HeroesSet.AddToDatabaseTest(this);
                HeroesSet.Save(this);
                HeroesSet.clear();
                HeroesSet.Load(this);
            }
            if (HeroAbilityBulletMapper.isEmpty()) {
                HeroAbilityBulletMapper.AddToDatabaseTest(this);
                HeroAbilityBulletMapper.Save(this);
                HeroAbilityBulletMapper.clear();
                HeroAbilityBulletMapper.Load(this);
            }


            }
        EnemySet enemySet= new EnemySet(this);


        enemySet.load();
        //ładowanie bazy danych SQLite

        //this.deleteDatabase("profileDB.db");
        DatabaseAdministrator da=new DatabaseAdministrator(this);

        da.actualization(this);
        //ProfileRepository profileDao= new ProfileRepository(this);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode)
        {
            case MEMORY_ACCESS:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {

                }
                else
                {

                }
        }
    }
    public void startGame(View view)
    {
        BountyAssigner bountyAssigner=
                new TimeBountyDecorator(
                new HunterDecorator(
                new ConcreteBountyAssigner())
        );
        GameStrategy.getInstance().setStrategies(new EnemysChooser(new RandomEnemyReleaseStrategy(convertToSpecyfication(getDefalultEnemyList())),new LinearTimeReleaseStrategy(200)),R.drawable.fightbackground,"Walka",bountyAssigner);
        Intent intent = new Intent(this, Game.class);
        this.startActivity(intent);
    }

    private List<EnemySpecyfication> convertToSpecyfication(List<Enemy> enemies)
    {
        List<EnemySpecyfication> enemySpecyficationSpecyfications = new LinkedList<>();
        for(Enemy enemy : enemies)
        {
            enemySpecyficationSpecyfications.add(new EnemySpecyfication(enemy));
        }
        return enemySpecyficationSpecyfications;
    }
    private List<Enemy> getDefalultEnemyList()
    {
        /*FrameLayout game=(FrameLayout) this.findViewById(R.id.frame);
        EnemySpecyfication enemy1= new EnemySpecyfication(this,10,20,new Point(0,0),200,200,20,R.drawable.enemy,game,100,20,1,0,10,null,"enemy",null,null,CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"żaden",new Description(),new Follower());
        EnemySpecyfication enemy2= new EnemySpecyfication(this,10,30,new Point(0,0),200,200,20,R.drawable.rinor,game,200,20,1,0,10,null,"Rinor",null,null,CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"żaden",new Description(),new Follower());
        EnemySpecyfication enemy3= new EnemySpecyfication(this,10,20,new Point(0,0),200,200,20,R.drawable.creature,game,30,20,1,0,10,null,"Creature",null,null,CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"żaden",new Description(),new Follower());
        EnemySpecyfication gideon= new EnemySpecyfication(this,10,30,new Point(0,0),200,200,20,R.drawable.gideon,game,45,20,1,0,10,null,"Gideon",null,null,CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"żaden",new Description(),new Follower());
        EnemySpecyfication gnome2= new EnemySpecyfication(this,30,10,new Point(0,0),200,200,20,R.drawable.gnome2,game,40,20,1,0,10,null,"Gnome2",null,null,CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"żaden",new Description(),new Follower());
        EnemySpecyfication goblin= new EnemySpecyfication(this,10,10,new Point(0,0),200,200,20,R.drawable.goblin,game,100,20,1,0,10,null,"Goblin",null,null,CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"żaden",new Description(),new Follower());
        EnemySpecyfication pacific= new EnemySpecyfication(this,10,100,new Point(0,0),200,200,20,R.drawable.pacific,game,100,20,1,0,10,null,"Pacific",null,null,CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"żaden",new Description(),new Follower());
        EnemySpecyfication police1= new EnemySpecyfication(this,20,2,new Point(0,0),200,200,20,R.drawable.police1,game,500,20,1,0,10,null,"Police1",null,null,CharacterPositioning.RIGHTRANDOM,new NoneDoToBulletStrategy(),"żaden",new Description(),new Follower());
        List<EnemySpecyfication> enemysCollection= new LinkedList<>();
        enemysCollection.add(enemy1);
        enemysCollection.add(enemy2);
        enemysCollection.add(enemy3);
        enemysCollection.add(gideon);
        enemysCollection.add(gnome2);
        enemysCollection.add(goblin);
        enemysCollection.add(pacific);
        enemysCollection.add(police1);
        for(EnemySpecyfication enemy:enemysCollection)
        {
            enemy.setBulletSpecyfication(new BulletSpecyfication("defaultenemybullet",this,10,20,null,50,50,20,R.drawable.blue,null,false,new Horizontal(),Shape.CIRCLE,new NoneBulletDoToCharacterStrategy(),Permission.YES,Rarity.COMMON,new MoneyPossesStrategy("Mystery Coin",10)).clone());
            enemy.getBulletSpecyfication().setFrame(game);
        }
        return  enemysCollection;*/
        EnemySet set= new EnemySet(this);
        return set.getAll();
    }
    public void chooseHero(View view) {
        Intent intent = new Intent(this, Heroes.class);
        this.startActivity(intent);
    }
    public void P2PGame(View view) {
        Intent intent= new Intent(this,P2PConnection.class);
        this.startActivity(intent);

    }
    public void showProfile(View view) {
        Intent intent= new Intent(this,ProfileScreen.class);
        this.startActivity(intent);
    }
    public void ToGame(View view) {
        Intent intent= new Intent(this,P2PGame.class);
        this.startActivity(intent);
    }
    public void mysteryMap(View view) {
        Intent intent= new Intent(this,MysterymMap.class);
        this.startActivity(intent);
    }
    public void shoppingtime(View view) {
        Intent intent= new Intent(this,Shop.class);
        this.startActivity(intent);
    }
}
