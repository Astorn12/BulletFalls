
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.bulletfalls.Activities.GameListActivity.GamesList;
import com.example.user.bulletfalls.Game.Elements.Enemy.EnemySpecyfication;
import com.example.user.bulletfalls.Profile.Collection.HeroCollection.HeroCollection;
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


    float[] lastTouchDownXY = new float[2];
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

        mapStarting();
    }
    private void mapStarting(){
        final ImageView transparentMap=new ImageView(this);
        /*Glide.with(this)
                .load(R.drawable.coloredmainmap)
                .into(transparentMap);*/

       // transparentMap.setX(0);
        //transparentMap.setY(0);

        ImageView map = new ImageView(this);


        Glide.with(this)
                .load(R.drawable.mainmap)
                .into(map);
        final Activity activity=this;

        Display display = getWindowManager(). getDefaultDisplay();
        Point size = new Point();
        display. getSize(size);
        FrameLayout l=(FrameLayout) this.findViewById(R.id.mainmapframe);
        transparentMap.setAlpha(0f);
        map.setAdjustViewBounds(true);
        transparentMap.setAdjustViewBounds(true);
        map.setAlpha(0.5f);
        transparentMap.setAlpha(0.5f);

        l.addView(map);
        l.addView(transparentMap);
        transparentMap.setImageResource(R.drawable.coloredmainmap);
        transparentMap.setScaleType(ImageView.ScaleType.CENTER_CROP);
        map.setScaleType(ImageView.ScaleType.CENTER_CROP);

        map.post(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap= ((BitmapDrawable)transparentMap.getDrawable()).getBitmap();


                transparentMap.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                            lastTouchDownXY[0] = event.getX();
                            lastTouchDownXY[1] = event.getY();
                        }
                        return false;
                    }
                });
                transparentMap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Matrix inverse = new Matrix();
                        ((ImageView)v).getImageMatrix().invert(inverse);

                        inverse.mapPoints(lastTouchDownXY);

                        int  x = Integer.valueOf((int)lastTouchDownXY[0]);
                        int  y = Integer.valueOf((int)lastTouchDownXY[1]);
                        System.out.println(x+" "+y);


                        int pixel= bitmap.getPixel(x,y);
                        int color= Color.rgb(Color.red(pixel),Color.green(pixel),Color.blue(pixel));
                        int id=0;
                        String title="";
                        if(color==Color.rgb(30,26,189)){
                            Log.i("mYSTERY Atraction"," MysteryShack");

                            id=R.drawable.mysteryshack;
                            title="Mystery Shack";
                            startGame();

                        }else if(color==Color.rgb(242,169,29)){
                            Log.i("mYSTERY Atraction"," YeroyalDiscountPuttHutt");
                            title="Yeroyal Discount Putt Hutt";
                            id=R.drawable.yeroyaldiscountputthutt;
                            chooseHero();
                        }else if(color==Color.rgb(242,29,169)){
                            Log.i("mYSTERY Atraction","Tent of Thelepathy ");
                            title="Tent of Thelepathy";
                            id=R.drawable.tentofthelepathy;
                            chooseGame();
                        }
                        else if(color==Color.rgb(217,242,10)){
                            Log.i("mYSTERY Atraction"," Petting Zoo");
                            title="Petting Zoo";
                            id=R.drawable.pettingzoofarm;
                        }
                        else if(color==Color.rgb(164,97,10)){
                            Log.i("mYSTERY Atraction","Beaver Museum ");
                            title="Beaver Museum";
                        }
                        else if(color==Color.rgb(182,27,19)){
                            title="Camp";
                        }
                        else if(color==Color.rgb(246,27,27)){
                            title="Yarn Ball";
                        }
                        else if(color==Color.rgb(155,162,74)){
                            title="The Big Things";
                        }
                        else if(color==Color.rgb(162,140,74)){
                            title="Logland";
                        }
                        else if(color==Color.rgb(113,106,105)){
                            title="The Giant Pan";
                        }
                        else if(color==Color.rgb(83,162,74)){
                            title="Upside Down Town";
                        }
                        else if(color==Color.rgb(161,20,182)){
                            title="House Shoe";
                        }
                        else if(color==Color.rgb(20,172,182)){
                            title="Mystery Mountain";//
                        }
                        else if(color==Color.rgb(20,182,31)){
                            title="Corn Maze";
                        }
                        else if(color==Color.rgb(244,37,81)){
                            title="Neon Ville";
                        }
                        else if(color==Color.rgb(244,37,81)){
                            title="Sceptic Ridge RV Park";
                        }

                        if(id!=0) {
                            Intent intent = new Intent(activity, NotReadyYet.class);
                            intent.putExtra("id", id);
                            intent.putExtra("title", title);
                            activity.startActivity(intent);
                        }
                    }
                });

            }
        });
        //map.setX(0);
        //map.setY(150);

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
        Intent intent = new Intent(this, HeroCollection.class);
        this.startActivity(intent);
    }
    public void P2PGame() {
        Intent intent= new Intent(this,P2PConnection.class);
        this.startActivity(intent);

    }
    public void showProfile() {
        Intent intent= new Intent(this,ProfileScreen.class);
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
    public void shoppingtime() {
        Intent intent= new Intent(this,Shop.class);
        this.startActivity(intent);
    }
    public void chooseGame()
    {
        Intent intent= new Intent(this,GamesList.class);
        this.startActivity(intent);
    }
}
