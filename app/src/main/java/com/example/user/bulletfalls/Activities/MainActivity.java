
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

import com.example.user.bulletfalls.Activities.GameListActivity.GamesList;
import com.example.user.bulletfalls.Game.Elements.Enemy.EnemySpecyfication;
import com.example.user.bulletfalls.Profile.Collection.UserCollection;
import com.example.user.bulletfalls.Storage.DatabaseAdministrator;
import com.example.user.bulletfalls.Game.Management.ArchivCurrencyContainer;
import com.example.user.bulletfalls.Storage.Sets.AbilitySet;
import com.example.user.bulletfalls.Storage.Sets.BeastsSet;
import com.example.user.bulletfalls.Storage.Sets.BulletSet;
import com.example.user.bulletfalls.Storage.Sets.EnemySet;
import com.example.user.bulletfalls.Storage.Sets.HeroAbilityBulletMapper;
import com.example.user.bulletfalls.Storage.Sets.HeroesSet;
import com.example.user.bulletfalls.Game.Elements.Enemy.Enemy;
import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Game.Management.GameController;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.P2P.P2PConnection;
import com.example.user.bulletfalls.P2P.P2PGame;
import com.example.user.bulletfalls.Profile.Currency;
import com.example.user.bulletfalls.Profile.ProfileScreen;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Shop.Shop;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.MoneyNeed;
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
        DatabaseAdministrator da=new DatabaseAdministrator(this);

        da.actualization(this);
        MutablePair<Currency,Integer> mutablePair= new MutablePair<>(currency,8);
        try {
             s= objectMapper.writeValueAsString(mutablePair);
             int x=0;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        UserCollection.getInstance().loadCollection();
        if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){}
        else {
                HeroesSet.getInstance().clear();
                BulletSet.getInstance().clear();
                AbilitySet.getInstance().clear();
            if (BulletSet.getInstance().isEmpty()) {
                BulletSet.getInstance().AddToDatabaseTest(this);
                BulletSet.getInstance().save(this);
                BulletSet.getInstance().clear();
                BulletSet.getInstance().load(this);//to powinno się znaleźć później tutaj normalnie
            }
            if (BeastsSet.getInstance().isEmpty()) {
                BeastsSet.getInstance().AddToDatabaseTest(this);
                BeastsSet.getInstance().save(this);
                BeastsSet.getInstance().clear();
                BeastsSet.getInstance().load(this);
            }
           if (AbilitySet.getInstance().isEmpty()) {
                AbilitySet.getInstance().AddToDatabaseTest();
                AbilitySet.getInstance().save(this);
                AbilitySet.getInstance().clear();
                AbilitySet.getInstance().load(this);
            }
            if (HeroesSet.getInstance().isEmpty()) {
                HeroesSet.getInstance().AddToDatabaseTest(this);
                HeroesSet.getInstance().save(this);
                HeroesSet.getInstance().clear();
                HeroesSet.getInstance().load(this);
            }
            if (HeroAbilityBulletMapper.isEmpty()) {
                HeroAbilityBulletMapper.AddToDatabaseTest(this);
                HeroAbilityBulletMapper.Save(this);
                HeroAbilityBulletMapper.clear();
                HeroAbilityBulletMapper.Load(this);
            }


            }
        EnemySet.getInstance().load(this);
        //ładowanie bazy danych SQLite

        //this.deleteDatabase("profileDB.db");

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

        //GameSketch.getInstance().setStrategies(new EnemysChooser(new RandomIEnemyReleaseStrategy(convertToSpecyfication(getDefalultEnemyList())),new LinearTimeReleaseStrategy(200)),R.drawable.fightbackground,"Walka", bountyAssignerDecorator);
        Intent intent = new Intent(this, Game.class);
        intent.putExtra("gameSketchName","Zombie atak?");
        this.startActivity(intent);
    }

    private List<EnemySpecyfication> convertToSpecyfication(List<Enemy> enemies)
    {
        List<EnemySpecyfication> enemySpecyficationSpecyfications = new LinkedList<>();
        for(Enemy enemy : enemies)
        {
            enemySpecyficationSpecyfications.add(enemy.getSpecyfication());
        }
        return enemySpecyficationSpecyfications;
    }

    public void chooseHero(View view) {
        Intent intent = new Intent(this, HeroCollection.class);
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
    public void chooseGame(View view)
    {
        Intent intent= new Intent(this,GamesList.class);
        this.startActivity(intent);
    }
}
