package com.example.user.bulletfalls.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.user.bulletfalls.Objects.Bullet;
import com.example.user.bulletfalls.Objects.ChosenHero;
import com.example.user.bulletfalls.Sets.HeroesSet;
import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.Enums.Rarity;
import com.example.user.bulletfalls.Enums.Shape;
import com.example.user.bulletfalls.Objects.Hero;
import com.example.user.bulletfalls.Objects.HeroProfile;
import com.example.user.bulletfalls.KlasyPomocnicze.Dimension;
import com.example.user.bulletfalls.ProfileActivity.Currency;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Specyfications.Characters.HeroSpecyfication;
import com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage.NoneBulletDoToCharacterStrategy;
import com.example.user.bulletfalls.Strategies.Bullet.BulletMoveStrategyPackage.Horizontal;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.MoneyNeed;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.MoneyPossesStrategy;
import com.example.user.bulletfalls.Strategies.Par;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skyline.widget.layout.RoundCornerLayout;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Heroes extends AppCompatActivity {

//  LinearLayout linear;
    ScrollView screen;
    LinkedList<Hero> heroes;
    TableLayout table;
    List<Hero> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes);
        screen=(ScrollView)this.findViewById(R.id.screen);
        table=(TableLayout)this.findViewById(R.id.table);
        heroes= new LinkedList<>();
        loadHeroes();
    }
    private void writeToFile(String data,Context context,Hero hero) {
      ObjectMapper mapper= new ObjectMapper();
       hero.setDimension(new Dimension(200,200));
       if(hero.getBullet()==null) {

           MoneyPossesStrategy moneyPossesStrategy= new MoneyPossesStrategy(Arrays.asList(new MoneyNeed(Arrays.asList(new Par<Currency,Integer>(new Currency("Mystery Coin"),10)))));
           hero.setBullet(new Bullet("standardherobullet", this.getApplicationContext(),10, 20, null, 50, 50, 20, R.drawable.blue, null, false,new Horizontal(),Shape.CIRCLE,new NoneBulletDoToCharacterStrategy(),Permission.YES,Rarity.STARTING,moneyPossesStrategy));
       }

       try {

           HeroSpecyfication specyfication= new HeroSpecyfication(hero);
           String jsonInString= mapper
                   .writeValueAsString(specyfication);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("hero.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(jsonInString);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
            e.getStackTrace();
        }
        catch(Exception ex)
        {
            ex.getStackTrace();
        }
    }
    private void loadHeroes()
    {
        TableRow tableRow = null;
        TableLayout tableLayout=(TableLayout)findViewById(R.id.table);
       int padding=30;
        int n=3;//ilosc kolumn
        int w= tableLayout.getWidth();
        int p=(w-(n+1)*padding)/n;

        list=HeroesSet.getHeroesList(this);
       int i=0;
       for( final Hero hero:list)
       {
           if(i%3==0) {
              tableRow = new TableRow(this);
              tableLayout.addView(tableRow);
           }
           final RoundCornerLayout frameLayout= new RoundCornerLayout(this);
           frameLayout.setRadius(100);
           tableRow.addView(frameLayout);
           frameLayout.post(new Runnable() {
           @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
           @Override
           public void run() {

               hero.colorBackgroundByGroup(frameLayout);

           }
       });

           if(!hero.getPermission().equals(Permission.YES)|| hero.getPermission().equals(Permission.FORALL)) {
               hero.setColorFilter(Color.BLACK);
           }
           frameLayout.addView(hero);
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
               hero.setZ(1);
           }
           int pad=10;
           int padLeft=pad,padTop=pad,padRight=pad,padBottom=pad;
           hero.setPadding(30,30,30,30);

           if(i%3==0) padLeft+=pad; // ramka z lewej strony
           if(i%3==2) padRight+=pad; //ramka z prawej strony
           if(tableLayout.getChildCount()==1) padTop+=2*pad; //ramka z góry
           if(list.size()/3==tableLayout.getChildCount()) padBottom+=2*pad;

           ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) frameLayout.getLayoutParams();
           params.setMargins(padLeft, padTop, padRight, padBottom);
           frameLayout.setLayoutParams(params);
           Display display = getWindowManager(). getDefaultDisplay();
           Point size = new Point();
           display. getSize(size);

           w=size.x;
           p=(size.x-2*pad*n-2*pad)/n;
           frameLayout.getLayoutParams().height=p;
           frameLayout.getLayoutParams().width=p;

           final Context context= this;
           final int  m=heroes.indexOf(hero);
           final Hero wsk= hero;
           final Activity ac=this;
           if(hero.getPermission().equals(Permission.YES)|| hero.getPermission().equals(Permission.FORALL)) {
               hero.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       writeToFile(m + "", context, wsk);
                       if (wsk.getAlpha() > 0.99f) {
                           for (Hero hero : list) {
                               hero.setAlpha(1f);
                           }
                           wsk.setAlpha((float) 0.8);
                       } else {
                           wsk.setAlpha((float) 1);
                       }
                       Intent intent = new Intent(ac, ChosenHero.class);
                       intent.putExtra("heroName", hero.getName());
                       ac.startActivity(intent);
                   }

               });

               hero.setOnLongClickListener(new View.OnLongClickListener() {
                   @Override
                   public boolean onLongClick(View v) {
                       Intent intent = new Intent(getBaseContext(), HeroProfile.class);
                       startActivity(intent);
                       return false;
                   }
               });
           }
           i++;
       }
    }
    @Override
    public void onPause() {
        super.onPause();
    }
    @Override
    public void onStop() {
        super.onStop();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
