package com.example.user.bulletfalls.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.user.bulletfalls.Game.Elements.Helper.ToViewConverter;
import com.example.user.bulletfalls.Game.Elements.Hero.ChosenHero;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamiliesContainer;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.Family;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Game.Elements.Hero.HeroProfile;
import com.example.user.bulletfalls.Game.Elements.Hero.HeroSpecyfication;
import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;
import com.example.user.bulletfalls.Profile.Collection.UserCollection;
import com.example.user.bulletfalls.Storage.Sets.HeroesSet;
import com.example.user.bulletfalls.GlobalUsage.Enums.Permission;
import com.example.user.bulletfalls.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skyline.widget.layout.RoundCornerLayout;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class HeroCollection extends AppCompatActivity {

//  LinearLayout linear;
    ScrollView screen;
    LinkedList<Hero> heroes;
    TableLayout table;
    List<Hero> list;


    ImageButton familyFilter;
    ImageButton obligatoryFilter;
    ImageButton sort;
    ImageButton extraFilter;
    PopupMenu menu;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes);
        screen=(ScrollView)this.findViewById(R.id.screen);
        table=(TableLayout)this.findViewById(R.id.table);
        heroes = new LinkedList<>();

        familyFilter=(ImageButton)this.findViewById(R.id.familyfilter);
        obligatoryFilter=(ImageButton)this.findViewById(R.id.obligatoryFilter);
        sort=(ImageButton)this.findViewById(R.id.sort);
        extraFilter=(ImageButton)this.findViewById(R.id.extrafilter);

        bindMenu();


        loadHeroes();




    }

    private void bindMenu() {
        Context context=this;


        this.familyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }



    private void writeToFile(String data, Context context, Hero hero) {
      ObjectMapper mapper= new ObjectMapper();


       try {

           HeroSpecyfication specyfication= hero.getSpecyfication();
           String jsonInString= mapper
                   .writeValueAsString(specyfication);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("heroSpecyfication.txt", Context.MODE_PRIVATE));
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
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void loadHeroes()
    {
        TableRow tableRow = null;
        TableLayout tableLayout=(TableLayout)findViewById(R.id.table);
       int padding=30;
        int n=3;//ilosc kolumn
        int w= tableLayout.getWidth();
        int p=(w-(n+1)*padding)/n;

        list=ToViewConverter.convertHeroes(this,HeroesSet.getInstance().getHeroesList());
       int i=0;
       for( final Hero hero :list)
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



           if(! UserCollection.getInstance().doYouOwnIt(hero.getSpecyfication())){
               hero.setColorFilter(Color.BLACK);
           }

           ImageView imageView=new ImageView(this);
           imageView.setImageResource(hero.getiClass().getImage());


           frameLayout.addView(imageView);

           FrameLayout.LayoutParams layoutParams=new FrameLayout.LayoutParams(50, 50);
           layoutParams.gravity=Gravity.RIGHT;
           layoutParams.setMargins(0,23,23,0);
           imageView.setLayoutParams(layoutParams);

           frameLayout.addView(hero);



           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
               hero.setZ(1);
               imageView.setZ(1);
               //ll.setZ(1);
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
           final int  m= heroes.indexOf(hero);
           final Hero wsk= hero;
           final Activity ac=this;
           if(UserCollection.getInstance().doYouOwnIt(hero.getSpecyfication())) {
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
                       System.out.println("Długie kliknięcie na hero");
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
