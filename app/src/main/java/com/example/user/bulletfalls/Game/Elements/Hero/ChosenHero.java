package com.example.user.bulletfalls.Game.Elements.Hero;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.user.bulletfalls.Activities.FamilyHouse;
import com.example.user.bulletfalls.Game.Elements.Helper.ToViewConverter;
import com.example.user.bulletfalls.GlobalUsage.Enums.AE;
import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;
import com.example.user.bulletfalls.Game.Elements.Ability.Ability;
import com.example.user.bulletfalls.Game.Elements.Ability.AbilityProfile;
import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.Bullet.BulletProfile;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamiliesContainer;
import com.example.user.bulletfalls.Profile.Collection.UserCollection;
import com.example.user.bulletfalls.Storage.Sets.AbilitySet;
import com.example.user.bulletfalls.Storage.Sets.BulletSet;
import com.example.user.bulletfalls.Storage.Sets.HeroesSet;
import com.example.user.bulletfalls.GlobalUsage.Enums.Rarity;
import com.example.user.bulletfalls.Game.Elements.Ability.Specyfication.AbilitySpecyfication;
import com.example.user.bulletfalls.GlobalUsage.Supporters.Dimension;
import com.example.user.bulletfalls.GlobalUsage.Supporters.OnSwipeTouchListener;
import com.example.user.bulletfalls.R;
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
    LinearLayout informationsOfHero;
    Hero chosenHero;
    LinkedList <FrameLayout> abilityViews= new LinkedList<>();
    LinkedList <FrameLayout> bullets= new LinkedList<>();
    ImageView before;
    ImageView next;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_hero);

        heroName=(TextView) this.findViewById(R.id.heroname);
        heroPhoto=(ImageView) this.findViewById(R.id.herophoto);
        informationsOfHero=(LinearLayout) this.findViewById(R.id.informationohero);
        informationsOfHero.setBackgroundColor(Color.WHITE);
        informationsOfHero.setAlpha(0.5f);
        //SupporterBackground supporterBackground= new SupporterBackground();
       // supporterBackground.setTextViewBackground();

        bulletList=(LinearLayout) this.findViewById(R.id.bulletlist);
        abilityList=(LinearLayout)this.findViewById(R.id.abilitylist);
        final LinearLayout heroFrame=(LinearLayout) this.findViewById(R.id.heroFrame);
       // heroFrame.setBackground(getResources().getDrawable(R.drawable.gnomeforest));

        Glide.with(this)
                .load(R.drawable.gnomeforest)
                .apply(RequestOptions.placeholderOf(R.drawable.gnomeforest))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource,
                                                @Nullable Transition<? super Drawable> transition) {

                        heroFrame.setBackground(resource); //  setCompoundDrawablesWithIntrinsicBounds(resource, null, null, null);

                    }
                });

        /*Glide.with(this).asBitmap().load(R.drawable.gnomeforest).into(new SimpleTarget(heroFrame.getLayoutParams().width, heroFrame.getLayoutParams().height) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(context.getResources(), resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    heroFrame.setBackground(drawable);
                }
            }
        });*/

        Intent intent= getIntent();
        String name=intent.getStringExtra("heroName");
        chosenHero = new Hero(this, HeroesSet.getInstance().getHero(name));
        heroName.setText(chosenHero.getName());
        //informationsOfHero.setText(chosenHero.story);
        heroPhoto.setImageResource(chosenHero.getImage());
        loadAbilityList();
        loadBulletList();
        abilityList.post(new Runnable() {
            @Override
            public void run() {
                matchOwnedBullet(chosenHero,bullets);
                matchOwnedAbilities(chosenHero,abilityViews);
            }
        });
        setHeroInfo();
        fillGroups();

        before=(ImageView)this.findViewById(R.id.before);
        next=(ImageView)this.findViewById(R.id.next);
        final Activity ac=this;
        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hero newHero= getBeforeHero();
                if(!newHero.equals(chosenHero))
                {
                    chosenHero=newHero;
                heroName.setText(chosenHero.getName());
                setHeroInfo();
                Glide.with(ac)
                        .load(chosenHero.getImage())
                        .into(heroPhoto);
                fillGroups();
            }}
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hero newHero = getNextHero();

                if (!newHero.equals(chosenHero)) {
                    chosenHero = newHero;
                    heroName.setText(chosenHero.getName());
                    setHeroInfo();
                    Glide.with(ac)
                            .load(chosenHero.getImage())
                            .into(heroPhoto);
                    fillGroups();
                }
            }
        });
    }

    private Hero getNextHero() {
        List<Hero> markedList= ToViewConverter.convertHeroes(this,HeroesSet.getInstance().getMarkedList(chosenHero.getIndyvidualHeroMarker()));

        for(Hero h:markedList)
        {
            if(h.getName().equals(chosenHero.getName()))
            {
                if(markedList.indexOf(h)+1<markedList.size())
                {
                    return markedList.get(markedList.indexOf(h)+1);
                }
                else return markedList.get(0);
            }
        }
        return null;
    }

    private Hero getBeforeHero() {
        List<Hero> markedList= ToViewConverter.convertHeroes(this,HeroesSet.getInstance().getMarkedList(chosenHero.getIndyvidualHeroMarker()));

        for(Hero h:markedList)
        {
            if(h.getName().equals(chosenHero.getName()))
            {
                if(markedList.indexOf(h)-1>=0)
                {
                    return markedList.get(markedList.indexOf(h)-1);
                }
                else return markedList.get(markedList.size()-1);
            }
        }
        return null;
    }


    //load all availlable bullets, putting into viewlist and setting click listeners to each item
    @SuppressLint("ClickableViewAccessibility")
    private void loadBulletList()
    {
        final Activity home=this;
         List<Bullet> bulletsFromDB=ToViewConverter.convertBullets(this,BulletSet.getInstance().getBulletListForHero(chosenHero.getName(),this.getApplicationContext()));
        Collections.sort(bulletsFromDB);
        int i=bulletList.getChildCount();
        if(i==0)
        {
            for(int j=0;j<bulletsFromDB.size();j++)
            {
                final Bullet b= bulletsFromDB.get(j);
                final FrameLayout f= new FrameLayout(this);
                f.addView(b);
                BulletSet bulletSet= BulletSet.getInstance();

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

                if(!UserCollection.getInstance().doYouOwnIt(b.getSpecyfication()))
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
                            intent.putExtra("name", b.getName());
                            home.startActivity(intent);
                        }


                        @Override
                        public void onClick() {
                            changeRestForegroundToNull(bullets);

                            tickView(f);
                            chosenHero.setBulletSpecyfication(b.getSpecyfication());
                            HeroesSet.getInstance().setBulletForHero(chosenHero.getName(), b.getSpecyfication());
                            HeroesSet.getInstance().save(home);


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
        List<AbilitySpecyfication> abilities=AbilitySet.getInstance().getAbilityListForHero(chosenHero.getName());
        Collections.sort(abilities);
        for(final AbilitySpecyfication a : abilities)
        {
            //a.activate();
            final FrameLayout fl= new FrameLayout(getApplicationContext());
            final Ability av= new Ability(this,a);
            fl.addView(av);
            abilityList.addView(fl);
            abilityViews.add(fl);
            setRarityBackground(fl);
            //setRarityBackground(fl,av.getAbilitySpecyfication().getRarity());

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
                    intent.putExtra("name",a.getName());
                    home.startActivity(intent);
                }

                @Override
                public void onClick()
                {
                            if(isUnderAbilityLimit(abilityViews, chosenHero.getNumberOfAbilities())) {
                                if (canBeTicked(fl)) {
                                    tickView(fl);
                                    for (int i = 0; i < chosenHero.getAbilities().getAbilities().size(); i++) {
                                        if (chosenHero.getAbilities().getAbilities().get(i).getName().equals(AE.NOTHING.getValue())) {
                                            chosenHero.getAbilities().getAbilities().set(i, av.getAbilitySpecyfication());

                                            break;
                                        }
                                    }
                                }
                            }
                        else {
                                if (isTicked(fl)) {
                                    cleanFrame(fl);
                                    for (int i = 0; i < chosenHero.getAbilities().getAbilities().size(); i++) {
                                        if (chosenHero.getAbilities().getAbilities().get(i).getName().equals(av.getAbilitySpecyfication().getName())) {
                                            chosenHero.getAbilities().getAbilities().set(i, AbilitySet.getInstance().getAbility(AE.NOTHING.getValue()));
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
        rarity=((Bullet)f.getChildAt(0)).getSpecyfication().getRarity();
        viewElement=((Bullet)f.getChildAt(0));

        }
        else if(f.getChildAt(0).getClass().equals(Ability.class))
        {
            rarity=((Ability)f.getChildAt(0)).getAbilitySpecyfication().getRarity();
          viewElement=((Ability)f.getChildAt(0));

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
        AbilitySpecyfication abilitySpecyfication =  ((Ability) fl.getChildAt(0)).getAbilitySpecyfication();
        if(abilitySpecyfication.isUnique())
        {
            if(fl.getChildCount()>1)return false;
            else return true;
        }
        else
        {
            if(fl.getChildCount()> chosenHero.getNumberOfAbilities()) return false;
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
     for(AbilitySpecyfication a: hero.getAbilities().getAbilities())
     {
         for (FrameLayout v:abilities)
         {
             if (a.getName().equals(((Ability)v.getChildAt(0)).getAbilitySpecyfication().getName()))
             {
                 tickView(v);
             }
         }
     }
    }
    public void matchOwnedBullet(Hero hero, LinkedList<FrameLayout> bullets)
    {
        for(FrameLayout b:bullets)
        {
            if(hero.getBulletSpecyfication().getName().equals(((Bullet)b.getChildAt(0)).getName())) tickView(b);
        }
    }

    public void tickView(FrameLayout f)
    {
        int i=f.getChildCount();
        ImageView tick= new ImageView(getApplicationContext());
        Glide.with(this).load(R.drawable.greentick).into(tick);
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
        writeToFile(this, chosenHero);
        HeroesSet.getInstance().setAbilitiesForHero(chosenHero, chosenHero.getAbilities().getAbilities());
        HeroesSet.getInstance().save(this);
    }

    private void writeToFile(Context context, Hero hero) {
        ObjectMapper mapper= new ObjectMapper();


        try {

            HeroSpecyfication specyfication=hero.getSpecyfication();
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

    private void setHeroInfo()
    {
        informationsOfHero.removeAllViews();
        int fontSize=20;
        TextView t1= new TextView(this);
        t1.setText("HP ");
        t1.setTextSize(fontSize);
        t1.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView i1= new TextView(this);
        i1.setText(chosenHero.getLife()+"");
        i1.setTextSize(fontSize);
        i1.setGravity(Gravity.CENTER_HORIZONTAL);

        LinearLayout l1= new LinearLayout(this);
        l1.setOrientation(LinearLayout.HORIZONTAL);
        l1.addView(t1);
        l1.addView(i1);



        TextView t3= new TextView(this);
        t3.setText("Size ");
        t3.setTextSize(fontSize);
        t3.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView i3= new TextView(this);
        i3.setText(chosenHero.getHeight()+"");
        i3.setTextSize(fontSize);
        i3.setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout l3= new LinearLayout(this);
        l3.setOrientation(LinearLayout.HORIZONTAL);
        l3.addView(t3);
        l3.addView(i3);

        TextView t4= new TextView(this);
        t4.setText("Speed ");
        t4.setTextSize(fontSize);
        t4.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView i4= new TextView(this);
        i4.setText(chosenHero.getSpeed()+"");
        i4.setTextSize(fontSize);
        i4.setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout l4= new LinearLayout(this);
        l4.setOrientation(LinearLayout.HORIZONTAL);
        l4.addView(t4);
        l4.addView(i4);

        informationsOfHero.addView(l1);

        informationsOfHero.addView(l3);
        informationsOfHero.addView(l4);
        i1.setTextColor(Color.BLACK);

        i3.setTextColor(Color.BLACK);
        i4.setTextColor(Color.BLACK);
        t1.setTextColor(Color.BLACK);

        t3.setTextColor(Color.BLACK);
        t4.setTextColor(Color.BLACK);
        //informationsOfHero.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    public void fillGroups()
    {
        LinearLayout linearLayout= (LinearLayout) findViewById(R.id.groups);
        linearLayout.removeAllViews();
        FamiliesContainer gc= new FamiliesContainer();
        for(final FamilyName g: chosenHero.getFamilyNames())
        {
            ImageView imageView= new ImageView(this);
            int id=gc.getGroup(g).getMiniature();
            //Glide.with(this).load(gc.getGroup(g).getMiniature());
            if(id!=0)Glide.with(this).load(id).into(imageView);

            final ChosenHero ch=this;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(ch,FamilyHouse.class);
                    intent.putExtra("familyName",g);
                    ch.startActivity(intent);
                }
            });
            imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT,1));

            if(id==0) imageView.setColorFilter(Color.BLUE);
            linearLayout.addView(imageView);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        saveHeroChanges();
        //System.out.println("Zapisałem zmiany hero");
        }
}
