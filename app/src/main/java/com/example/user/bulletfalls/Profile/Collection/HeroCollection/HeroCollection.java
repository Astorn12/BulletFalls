package com.example.user.bulletfalls.Profile.Collection.HeroCollection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.Elements.Hero.ChosenHero;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamiliesContainer;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.Family;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Game.Elements.Hero.HeroProfile;
import com.example.user.bulletfalls.Game.Elements.Hero.HeroSpecyfication;
import com.example.user.bulletfalls.Game.GameBiznesFunctions.SuperPowers.AngelProtector;
import com.example.user.bulletfalls.Game.GameBiznesFunctions.SuperPowers.Breeder;
import com.example.user.bulletfalls.Game.GameBiznesFunctions.SuperPowers.HealerC;
import com.example.user.bulletfalls.Game.GameBiznesFunctions.SuperPowers.MassDestructor;
import com.example.user.bulletfalls.Game.GameBiznesFunctions.SuperPowers.SuperShooter;
import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;
import com.example.user.bulletfalls.GlobalUsage.Supporters.RomeLettersConverter;
import com.example.user.bulletfalls.Profile.Collection.HeroCollection.FiltersAndSorters.CollectionFilterComposite;
import com.example.user.bulletfalls.Profile.Collection.HeroCollection.FiltersAndSorters.FamilyFilter;
import com.example.user.bulletfalls.Profile.Collection.HeroCollection.FiltersAndSorters.FeatureMenuItem;
import com.example.user.bulletfalls.Profile.Collection.HeroCollection.FiltersAndSorters.HeroCollectionSorter;
import com.example.user.bulletfalls.Profile.Collection.HeroCollection.FiltersAndSorters.MasterAbilityFilter;
import com.example.user.bulletfalls.Profile.Collection.HeroCollection.FiltersAndSorters.SelectorMenuAdapter;
import com.example.user.bulletfalls.Profile.Collection.UserCollection;
import com.example.user.bulletfalls.Storage.Sets.HeroesSet;
import com.example.user.bulletfalls.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skydoves.powermenu.CircularEffect;
import com.skydoves.powermenu.CustomPowerMenu;
import com.skydoves.powermenu.MenuAnimation;
import com.skydoves.powermenu.OnMenuItemClickListener;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;
import com.skyline.widget.layout.RoundCornerLayout;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class HeroCollection extends AppCompatActivity {

//  LinearLayout linear;
    ScrollView screen;
    TableLayout table;

    ImageButton familyFilter;
    ImageButton featuresFilterButton;
    ImageButton sorter;
    ImageButton extraFilter;

    CollectionFilterComposite<HeroSpecyfication> filterComposite;

    PowerMenu familiesMenu;
    CustomPowerMenu featureFilter;
    PowerMenu sorterMenu;

    String displayMode;


    TextView title;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes);
        displayMode=null;
        screen=(ScrollView)this.findViewById(R.id.screen);
        table=(TableLayout)this.findViewById(R.id.table);

        familyFilter=(ImageButton)this.findViewById(R.id.familyfilter);
        featuresFilterButton=(ImageButton)this.findViewById(R.id.obligatoryFilter);
        sorter=(ImageButton)this.findViewById(R.id.sort);
        extraFilter=(ImageButton)this.findViewById(R.id.extrafilter);

        this.filterComposite= new CollectionFilterComposite<>();
        createMenus();
        bindMenus();

        repaintHeroesCollection(HeroesSet.getInstance().getAll());
    }

    private void createMenus(){
        createFamilyFilter();
        createFeatureFilter();
        createSorterFilter();
    }

    private void createSorterFilter(){
        OnMenuItemClickListener<PowerMenuItem> onSortMenuItemClickListener = new OnMenuItemClickListener<PowerMenuItem>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClick(int position, PowerMenuItem item) {
                filterComposite.remove(new HeroCollectionSorter(null));

                displayMode=sorterMenu.getItemList().get(position).getTitle();
                title.setText(displayMode);
                switch(displayMode){
                    case "Default":
                        break;
                    case "Speed":
                        filterComposite.add(new HeroCollectionSorter( new Comparator<HeroSpecyfication>() {
                            @Override
                            public int compare(HeroSpecyfication o1, HeroSpecyfication o2) {

                                if(o1.getSpeed()==o2.getSpeed()) return 0;
                                else return o1.getSpeed()<o2.getSpeed() ?1 :-1;
                            }
                        }));
                        break;
                    case "Life":
                        filterComposite.add(new HeroCollectionSorter( new Comparator<HeroSpecyfication>() {
                            @Override
                            public int compare(HeroSpecyfication o1, HeroSpecyfication o2) {
                                if(o1.getLife()==o2.getLife())return 0;
                                else return o1.getLife()<o2.getLife() ?1 :-1;
                            }
                        }));
                        break;
                    case "Shooting speed":
                        filterComposite.add(new HeroCollectionSorter( new Comparator<HeroSpecyfication>() {
                            @Override
                            public int compare(HeroSpecyfication o1, HeroSpecyfication o2) {
                                if(o1.getShootingSpeed()==o2.getShootingSpeed()) return 0;
                                else return o1.getShootingSpeed()<o2.getShootingSpeed() ?1 :-1;
                            }
                        }));
                        break;
                    case "Tier":
                        filterComposite.add(new HeroCollectionSorter( new Comparator<HeroSpecyfication>() {
                            @Override
                            public int compare(HeroSpecyfication o1, HeroSpecyfication o2) {
                                if(o1.getTier()==o2.getTier()) return 0;
                                else return o1.getTier()<o2.getTier() ?1 :-1;
                            }
                        }));
                        break;
                }
                sorterMenu.dismiss();
                filter();
            }
        };


        this.sorterMenu= new PowerMenu.Builder(this)
                .addItem(new PowerMenuItem("Default"))
                .addItem(new PowerMenuItem("Speed"))
                .addItem(new PowerMenuItem("Life"))
                .addItem(new PowerMenuItem("Shooting speed"))
                .addItem(new PowerMenuItem("Tier"))
                .setTextGravity(Gravity.CENTER)
                .setMenuRadius(10f)
                .setMenuShadow(10f)
                .setCircularEffect(CircularEffect.BODY)
                .setSelectedMenuColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setAnimation(MenuAnimation.SHOWUP_TOP_LEFT)
                .setOnMenuItemClickListener(onSortMenuItemClickListener)
                .build();

    }

    private void createFeatureFilter(){
         OnMenuItemClickListener<FeatureMenuItem> onFeatureMenuItemClickListener = new OnMenuItemClickListener<FeatureMenuItem>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClick(int position, FeatureMenuItem item) {
                item.changeState();
                featureFilter.getAdapter().notifyDataSetInvalidated();
                title.setText("Collection");
                if(item.isSelected()){
                    filterComposite.add(new MasterAbilityFilter(item.getMasterAbility()));
                }
                else filterComposite.remove(new MasterAbilityFilter(item.getMasterAbility()));
            }
        };

        View.OnClickListener onBackgroundClickListener= new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                featureFilter.dismiss();
                filter();
            }
        };

        this.featureFilter= new CustomPowerMenu.Builder(this,new SelectorMenuAdapter())
                .addItem(new FeatureMenuItem(new AngelProtector()))
                .addItem(new FeatureMenuItem(new MassDestructor()))
                .addItem(new FeatureMenuItem(new SuperShooter()))
                .addItem(new FeatureMenuItem(new Breeder()))
                .addItem(new FeatureMenuItem(new HealerC()))
                .setMenuRadius(30f)
                .setMenuShadow(30f)
                .setOnMenuItemClickListener(onFeatureMenuItemClickListener)
                .setAnimation(MenuAnimation.SHOWUP_TOP_LEFT)
                .setWidth(300)
                .setOnBackgroundClickListener(onBackgroundClickListener)
                .build();
        featureFilter.getAdapter().notifyDataSetInvalidated();
    }

    private void createFamilyFilter(){
        FamiliesContainer familiesContainer= new FamiliesContainer();
        List<PowerMenuItem> items= new LinkedList<>();
        for(Family family: familiesContainer.getAll()){
            int miniature= family.getMiniature();
            items.add(new PowerMenuItem("", miniature));
        }

        OnMenuItemClickListener<PowerMenuItem> onMenuItemClickListener = new OnMenuItemClickListener<PowerMenuItem>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClick(int position, PowerMenuItem item) {
                filterComposite.clearFamilyFilters();
                if(familiesMenu.getSelectedPosition()==position){
                    familiesMenu.setSelectedPosition(-1);
                }else {
                    FamilyName familyName=familiesContainer.getAll().get(position).getGroupName();
                    filterComposite.add(new FamilyFilter(familiesContainer.getAll().get(position).getGroupName()));
                    familiesMenu.setSelectedPosition(position);

                }
                filter();
                familiesMenu.dismiss();
            }
        };

        this.familiesMenu= new PowerMenu.Builder(this)
                .addItemList(items)
                .setTextGravity(Gravity.CENTER)
                .setMenuRadius(10f)
                .setMenuShadow(10f)
                .setCircularEffect(CircularEffect.BODY)
                .setSelectedTextColor(Color.WHITE)
                .setSelectedMenuColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setOnMenuItemClickListener(onMenuItemClickListener)
                .setAnimation(MenuAnimation.SHOWUP_TOP_LEFT)
                .build();
    }

    private void bindMenus() {

        this.familyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                familiesMenu.showAsDropDown(familyFilter);
            }
        });


        this.featuresFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                featureFilter.showAsDropDown(featuresFilterButton);
            }
        });
        this.sorter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sorterMenu.showAsDropDown(sorter);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void filter(){
        repaintHeroesCollection(this.filterComposite.filter(HeroesSet.getInstance().getAll()));
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
    private void repaintHeroesCollection(List<HeroSpecyfication> filteredHeroes)
    {
        TableRow tableRow = null;

        table.removeAllViews();

        List<Hero> currentlyShowedHeroList = new LinkedList<>();

        int padding=30;
        int n=3;//ilosc kolumn
        int w= table.getWidth();
        int p=(w-(n+1)*padding)/n;

       int i=0;
       for( final HeroSpecyfication heroSpecyfication :filteredHeroes)
       {
           if(i%3==0) {
              tableRow = new TableRow(this);
              table.addView(tableRow);
           }
           final RoundCornerLayout frameLayout= new RoundCornerLayout(this);
           frameLayout.setRadius(100);
           tableRow.addView(frameLayout);
           Hero hero = new Hero(this,heroSpecyfication);
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
           imageView.setImageResource(hero.getMasterAbility().getImage());


           frameLayout.addView(imageView);

           FrameLayout.LayoutParams layoutParams=new FrameLayout.LayoutParams(50, 50);
           layoutParams.gravity=Gravity.RIGHT;
           layoutParams.setMargins(0,23,23,0);
           imageView.setLayoutParams(layoutParams);

           frameLayout.addView(hero);
           currentlyShowedHeroList.add(hero);

           if(displayMode!=null){
               TextView textView= new TextView(this);
               String sTmp="";
              switch(displayMode){
                  case "Speed":
                      sTmp=hero.getSpeed()+"";
                      break;
                  case "Life":
                     sTmp=hero.getLife()+"";
                     break;
                  case "Shooting speed":
                      sTmp=hero.getShootingSpeed()+"";
                      break;
                  case "Tier":
                      RomeLettersConverter rome= new RomeLettersConverter();
                      sTmp=rome.convert(hero.getTier())+"";
                      break;
              }
              textView.setText(sTmp);
              textView.setGravity(Gravity.BOTTOM);
              textView.setGravity(Gravity.CENTER_VERTICAL);
              textView.setTextSize(20);
              textView.setBackgroundColor(Color.WHITE);
              textView.getBackground().setAlpha(125);
              FrameLayout.LayoutParams layoutParams1=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,1);
              layoutParams1.gravity=Gravity.BOTTOM|Gravity.CENTER;
               textView.setLayoutParams(layoutParams1);
              frameLayout.addView(textView);
              textView.setZ(1);

           }

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
           if(table.getChildCount()==1) padTop+=2*pad; //ramka z góry
           if(filteredHeroes.size()/3==table.getChildCount()) padBottom+=2*pad;

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
           final int  m= 0;
           final Hero wsk= hero;
           final Activity ac=this;
           if(UserCollection.getInstance().doYouOwnIt(hero.getSpecyfication())) {
               hero.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       writeToFile(m + "", context, wsk);
                       if (wsk.getAlpha() > 0.99f) {
                           for (Hero hero : currentlyShowedHeroList) {
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
