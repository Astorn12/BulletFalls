package com.example.user.bulletfalls;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.JsonDatabases.AbilitySet;
import com.example.user.bulletfalls.JsonDatabases.BulletSet;
import com.example.user.bulletfalls.JsonDatabases.HeroesSet;
import com.example.user.bulletfalls.Enums.Rarity;
import com.example.user.bulletfalls.KlasyPomocnicze.Dimension;
import com.example.user.bulletfalls.KlasyPomocnicze.OnSwipeTouchListener;
import com.example.user.bulletfalls.Specyfications.Characters.HeroSpecyfication;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class ChosenHero extends AppCompatActivity {

    LinearLayout bulletList;
    LinearLayout abilityList;
    ImageView heroPhoto;
    TextView heroName;
    TextView informationsOfHero;
    Hero chosenHero;
    LinkedList <FrameLayout> abilityViews= new LinkedList<>();
    LinkedList <FrameLayout> bullets= new LinkedList<>();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_hero);

        heroName=(TextView) this.findViewById(R.id.heroname);
        heroPhoto=(ImageView) this.findViewById(R.id.herophoto);
        informationsOfHero=(TextView) this.findViewById(R.id.informationohero);
        bulletList=(LinearLayout) this.findViewById(R.id.bulletlist);
        abilityList=(LinearLayout)this.findViewById(R.id.abilitylist);
        LinearLayout heroFrame=(LinearLayout) this.findViewById(R.id.heroFrame);
        heroFrame.setBackground(getResources().getDrawable(R.drawable.gnomeforest));
        Intent intent= getIntent();
        String name=intent.getStringExtra("heroName");
        chosenHero= HeroesSet.getHero(name);
        heroName.setText(chosenHero.name);
        informationsOfHero.setText(chosenHero.story);
        heroPhoto.setImageResource(chosenHero.getImageResources());
        loadAbilityList();
        loadBulletList();
        abilityList.post(new Runnable() {
            @Override
            public void run() {
                matchOwnedBullet(chosenHero,bullets);
                matchOwnedAbilities(chosenHero,abilityViews);
            }
        });
    }
    //load all availlable bullets, putting into viewlist and setting click listeners to each item
    @SuppressLint("ClickableViewAccessibility")
    private void loadBulletList()
    {
        final Activity home=this;
         List<Bullet> bulletsFromDB= BulletSet.getBulletListForHero(chosenHero.getName(),this.getApplicationContext());
        Collections.sort(bulletsFromDB);
        int i=bulletList.getChildCount();
        if(i==0)
        {
            for(final Bullet  b: bulletsFromDB)
            {
                final FrameLayout f= new FrameLayout(this);
                f.addView(b);
                BulletSet bulletSet= new BulletSet();

                bulletList.addView(f);
                bullets.add(f);

                setRarityBackground(f);
               /* f.post(new Runnable() {
                    @Override
                    public void run() {


                        matchOwnedBullet(chosenHero,bullets);

                    }
                });*/
                b.getLayoutParams().width=abilityList.getLayoutParams().height;
                b.getLayoutParams().height=abilityList.getLayoutParams().height;

                if(!bulletSet.ifHasThisBullet(b))
                {
                    b.setColorFilter(Color.BLACK);
                }
                else {
                    f.setOnTouchListener(new OnSwipeTouchListener(this) {
                        @Override
                        public void onSwipeRight() {
                            // Toast.makeText(home, b.getName(), Toast.LENGTH_SHORT).show();
                            //  System.out.println("swipe right 78"+b.getName() );
                            Intent intent = new Intent(home, BulletProfile.class);
                            intent.putExtra("name", b.name);
                            home.startActivity(intent);
                        }

                        @Override
                        public void onClick() {
                            //    System.out.println("onClick");


                            changeRestForegroundToNull(bullets);

                            tickView(f);
                            chosenHero.setBullet(b);
                            HeroesSet.setBulletForHero(chosenHero.name, b);
                            HeroesSet.Save(home);
                            writeToFile(home, chosenHero);

                            //else b.setForeground(null);

                        }
                    });
                }

            }}
    }
    //load all availlable abilities, putting into viewlist and setting click listeners to each item
    @SuppressLint("ClickableViewAccessibility")
    private void loadAbilityList()
    {
        final Activity home= this;
        List<Ability> abilities=AbilitySet.getInstance().getAbilityListForHero(chosenHero.getName());
        Collections.sort(abilities);
        for(final Ability a : abilities)
        {
            final FrameLayout fl= new FrameLayout(getApplicationContext());
            final AbilityView av= new AbilityView(this,a);
            fl.addView(av);
            abilityList.addView(fl);
            abilityViews.add(fl);
            setRarityBackground(fl);
            //setRarityBackground(fl,av.getAbility().getRarity());

            av.getLayoutParams().width=abilityList.getLayoutParams().height;
            av.getLayoutParams().height=abilityList.getLayoutParams().height;

            if(!AbilitySet.getInstance().ifHasThisAbility(a))
            {
                av.setColorFilter(Color.BLACK);
            }
            else
            {
            fl.setOnTouchListener(new OnSwipeTouchListener(this)
            {
                @Override
                public void onSwipeUp() {
                    Intent intent= new Intent(home,AbilityProfile.class);
                    intent.putExtra("name",a.name);
                    home.startActivity(intent);
                }

                @Override
                public void onClick()
                {
                            if(isUnderAbilityLimit(abilityViews,chosenHero.getNumberOfAbilities())) {
                                if (canBeTicked(fl)) {
                                    tickView(fl);
                                    for (int i = 0; i < chosenHero.getAbilities().getAbilities().size(); i++) {
                                        if (chosenHero.getAbilities().getAbilities().get(i).getName().equals("nothing")) {
                                            chosenHero.getAbilities().getAbilities().set(i, av.getAbility());
                                            saveHeroChanges();
                                            break;
                                        }
                                    }
                                }
                            }
                        else {
                                if(isTicked(fl)) {
                                    cleanFrame(fl);
                                    for (int i = 0; i < chosenHero.getAbilities().getAbilities().size(); i++) {
                                        if (chosenHero.getAbilities().getAbilities().get(i).getName().equals(av.getAbility().getName())) {
                                            chosenHero.getAbilities().getAbilities().set(i, AbilitySet.getInstance().getAbility("nothing"));
                                            saveHeroChanges();
                                        }
                                    }
                                }
                        }
                    }
            });}
        }
    }

    public void setRarityBackground(FrameLayout f)
    {

        Rarity rarity;
        ImageView viewElement=null;
        if(f.getChildAt(0).getClass().equals(Bullet.class))
        {
        rarity=((Bullet)f.getChildAt(0)).rarity;
        viewElement=((Bullet)f.getChildAt(0));

        }
        else if(f.getChildAt(0).getClass().equals(AbilityView.class))
        {
            rarity=((AbilityView)f.getChildAt(0)).ability.rarity;
          viewElement=((AbilityView)f.getChildAt(0));

        }
        else
        {
            rarity=Rarity.STARTING;

        }


        switch(rarity)
        {
            case STARTING:
                viewElement.setBackgroundColor(Color.parseColor("#595959"));
                break;
            case COMMON:
                viewElement.setBackgroundColor(Color.parseColor("#645651"));
                break;
            case UNCOMMON:
                viewElement.setBackgroundColor(Color.parseColor("#6d5448"));
                break;
            case RARE:
                viewElement.setBackgroundColor(Color.parseColor("#765140"));
                break;
            case VERYRARE:
                viewElement.setBackgroundColor(Color.parseColor("#7d4e38"));
                break;
            case LEGENDARY:
                viewElement.setBackgroundColor(Color.parseColor("#844b2f"));
                break;
            case UNPRECEDENTED:
                viewElement.setBackgroundColor(Color.parseColor("#8b4726"));
                break;



        }
        GradientDrawable border = new GradientDrawable();
        //border.setColor(Color.parseColor("#ffd700")); //white background
        //border.setStroke(3, Color.parseColor("#ffd700")); //black border with full opacity
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) { f.setBackgroundDrawable(border);
        } else {
            //f.setBackground(border);
        }
    }
    public boolean canBeTicked(FrameLayout fl)
    {
        Ability ability =  ((AbilityView) fl.getChildAt(0)).ability;
        if(ability.unique)
        {
            if(fl.getChildCount()>1)return false;
            else return true;
        }
        else
        {
            if(fl.getChildCount()>chosenHero.getNumberOfAbilities()) return false;
            else return true;
        }
    }

    public boolean isTicked(FrameLayout fl)
    {
        if(fl.getChildCount()>1) return true;
        else return false;
    }
    public void cleanFrame(FrameLayout frame)
    {
        int c=frame.getChildCount();
        for(int i=c-1;i>0;i--)
        {
            frame.removeView(frame.getChildAt(i));
        }
    }
    //this method checking if it is still place for another method in heroe's abilityBar
    public boolean isUnderAbilityLimit(List<FrameLayout> views, int limit)
    {
        int l=0;
        for(FrameLayout fl :views)
            l+=fl.getChildCount()-1;



        if(l>=limit)return false;
        else return true;
    }


    public void changeRestForegroundToNull(List<FrameLayout>bullets)
    {

        for(FrameLayout fl: bullets) {
            for (int i = 1; i <fl.getChildCount();i++) {
                fl.removeView(fl.getChildAt(i));
            }
        }
    }


    public void matchOwnedAbilities(Hero hero, List<FrameLayout> abilities)
    {
     for(Ability a: hero.getAbilities().getAbilities())
     {
         for (FrameLayout v:abilities)
         {
             if (a.getName().equals(((AbilityView)v.getChildAt(0)).ability.getName()))
             {
                 tickView(v);
             }
         }
     }
    }
    public void matchOwnedBullet(Hero hero,LinkedList<FrameLayout> bullets)
    {
        for(FrameLayout b:bullets)
        {
            if(hero.getBullet().getName().equals(((Bullet)b.getChildAt(0)).getName())) tickView(b);
        }
    }

    public void tickView(FrameLayout f)
    {
        int i=f.getChildCount();
        ImageView tick= new ImageView(getApplicationContext());
        tick.setImageResource(R.drawable.greentick);
        f.addView(tick);
        tick.getLayoutParams().width=50;
        tick.getLayoutParams().height=50;
      //  if(f.getWidth()!=0) {
        tick.setX(f.getWidth() - tick.getLayoutParams().width * (i));
    //    }
       // else   tick.setX(0);
        //tick.setX(50);
        tick.setY(0);
        tick.invalidate();
        f.invalidate();
        f.refreshDrawableState();
    }


    private void saveHeroChanges()
    {
        writeToFile(this,chosenHero);
        HeroesSet.setAbilitiesForHero(chosenHero,chosenHero.getAbilities().getAbilities());
        HeroesSet.Save(this);
    }

    private void writeToFile(Context context, Hero hero) {
        ObjectMapper mapper= new ObjectMapper();
        hero.setDimension(new Dimension(200,200));
        if(hero.getBullet()==null) {
            hero.setBullet(BulletSet.getBullet("standard"));
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
}
