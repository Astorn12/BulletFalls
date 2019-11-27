
package com.example.user.bulletfalls.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.user.bulletfalls.Activities.GameListActivity.GamesList;
import com.example.user.bulletfalls.Profile.Collection.HeroCollection.HeroCollection;
import com.example.user.bulletfalls.Profile.Collection.UserCollection;
import com.example.user.bulletfalls.Profile.LevelBar;
import com.example.user.bulletfalls.Profile.UserProfile;
import com.example.user.bulletfalls.Storage.DatabaseAdministrator;
import com.example.user.bulletfalls.Game.Management.ArchivCurrencyContainer;
import com.example.user.bulletfalls.Storage.Sets.AbilitySet;
import com.example.user.bulletfalls.Storage.Sets.BeastsSet;
import com.example.user.bulletfalls.Storage.Sets.BulletSet;
import com.example.user.bulletfalls.Storage.Sets.EnemySet;
import com.example.user.bulletfalls.Storage.Sets.HeroAbilityBulletMapper;
import com.example.user.bulletfalls.Storage.Sets.HeroesSet;
import com.example.user.bulletfalls.Game.Activities.Game;
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

public class MainActivity extends AppCompatActivity {
//wszelkie prawa autorskie złamane w trakcie tworzenia tej wspaniałej aplikacji
    private LinearLayout headLine;
    private final int MEMORY_ACCESS=5;


    float[] lastTouchDownXY = new float[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this.headLine =(LinearLayout) this.findViewById(R.id.mainwallet);



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


       // fillHeadLine();

    }


    @Override
    public void onResume(){
        super.onResume();


    }




    private void fillMainWallet() {
        UserProfile userProfile= new UserProfile(this);
        userProfile.addWallet(this.headLine,this);
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
    public void startGame()
    {

        //GameSketch.getInstance().setStrategies(new EnemysChooser(new RandomIEnemyReleaseStrategy(convertToSpecyfication(getDefalultEnemyList())),new LinearTimeReleaseStrategy(200)),R.drawable.fightbackground,"Walka", bountyAssignerDecorator);
        Intent intent = new Intent(this, Game.class);
        intent.putExtra("gameSketchName","Zombie atak?");
        this.startActivity(intent);
    }

    public void chooseHero() {

    }
    public void P2PGame() {
        Intent intent= new Intent(this,P2PConnection.class);
        this.startActivity(intent);

    }

    public void ToGame() {
        Intent intent= new Intent(this,P2PGame.class);
        this.startActivity(intent);
    }
    public void mysteryMap() {
        Intent intent= new Intent(this,MysterymMap.class);
        this.startActivity(intent);
    }



    public void showProfile(View view) {
        Intent intent= new Intent(this,ProfileScreen.class);
        this.startActivity(intent);
    }

    public void choseGame(View view) {
        Intent intent= new Intent(this,GamesList.class);
        this.startActivity(intent);
    }

    public void showCollection(View view) {
        Intent intent = new Intent(this, HeroCollection.class);
        this.startActivity(intent);
    }

    public void showShop(View view) {
        Intent intent= new Intent(this,Shop.class);
        this.startActivity(intent);
    }

    public void showFamiliesMap(View view) {
        Intent intent= new Intent(this,FamiliesMap.class);
        this.startActivity(intent);
    }
}
