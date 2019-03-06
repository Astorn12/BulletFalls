package com.example.user.bulletfalls;


import android.graphics.Color;
import android.icu.text.RelativeDateTimeFormatter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.user.bulletfalls.FragmentsSlider.SlidingTabLayout;
import com.example.user.bulletfalls.Interfaces.PossesAble;
import com.example.user.bulletfalls.JsonDatabases.AbilitySet;
import com.example.user.bulletfalls.JsonDatabases.BulletSet;
import com.example.user.bulletfalls.JsonDatabases.HeroesSet;
import com.example.user.bulletfalls.ProfileActivity.UserProfile;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopShelfsFragment extends Fragment {
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    PopupWindow popupWindow;
    FrameLayout main;

    public ShopShelfsFragment() {
        // Required empty public constructor

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop_shelfs, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // BEGIN_INCLUDE (setup_viewpager)
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new SamplePagerAdapter());
        // END_INCLUDE (setup_viewpager)

        // BEGIN_INCLUDE (setup_slidingtablayout)
        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
        main= (FrameLayout) view.findViewById(R.id.main);


        // END_INCLUDE (setup_slidingtablayout)
    }
    // END_INCLUDE (fragment_onviewcreated)

    class SamplePagerAdapter extends PagerAdapter {
        List<Hero> stock;
        List<Ability> abilityStock;
        List<Bullet> bulletStock;
        public SamplePagerAdapter()
        {
            stock= new LinkedList<>();
            abilityStock=new LinkedList<>();
            bulletStock= new LinkedList<>();

            choseStock(4);
            popupWindow= new PopupWindow();
        }
        /**
         * @return the number of pages to display
         */
        @Override
        public int getCount() {
            return 3;
        }

        /**
         * @return true if the value returned from {@link #instantiateItem(ViewGroup, int)} is the
         * same object as the {@link View} added to the {@link ViewPager}.
         */
        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        // BEGIN_INCLUDE (pageradapter_getpagetitle)
        /**
         * Return the title of the item at {@code position}. This is important as what this method
         * returns is what is displayed in the {@link SlidingTabLayout}.
         * <p>
         * Here we construct one using the position value, but for real application the title should
         * refer to the item's contents.
         */
        @Override
        public CharSequence getPageTitle(int position) {

            switch(position)
            {
                case 0: return "Bohaterowie";
                case 1: return "Broń";
                case 2: return "Umiejętność";
                default: return "Sklep";
            }
        }
        // END_INCLUDE (pageradapter_getpagetitle)

        /**
         * Instantiate the {@link View} which should be displayed at {@code position}. Here we
         * inflate a layout from the apps resources and then change the text view to signify the position.
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // Inflate a new layout from our resources
            View view = getActivity().getLayoutInflater().inflate(R.layout.pager_item,
                    container, false);
            // Add the newly created View to the ViewPager
            container.addView(view);


            switch(position)
            {
                case 0:
                    putHeroesOnShelf(view);


                    break;
                case 1:
                    putBulletsOnShelf(view);
                    break;
                case 2:
                    putAbilitiesOnShelf(view);
                    break;

            }
            return view;
        }
        private void putHeroesOnShelf(View view)
        {
            LinearLayout view1= (LinearLayout) view.findViewById(R.id.linearLayout1);
            LinearLayout view2= (LinearLayout) view.findViewById(R.id.linearLayout2);
            LinearLayout view3= (LinearLayout) view.findViewById(R.id.linearLayout3);
            LinearLayout view4= (LinearLayout) view.findViewById(R.id.linearLayout4);
            List<LinearLayout> linears= Arrays.asList(view1,view2,view3,view4);
            for(LinearLayout ll : linears) {

                Hero pointer = stock.get(linears.indexOf(ll));
                HeroesSet heroesSet = new HeroesSet();
                if (!heroesSet.ifHasThisHero(pointer)) {
                    pointer.setColorFilter(Color.BLACK);
                }
                ll.addView(pointer);
                pointer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));
                TextView heroName = new TextView(getContext());
                heroName.setText(pointer.getName());
                ll.addView(heroName);
                heroName.setGravity(Gravity.CENTER_HORIZONTAL);

                setCost(pointer, ll);
                addListener(pointer);
            }
        }
        private void putBulletsOnShelf(View view)
        {
            LinearLayout view1= (LinearLayout) view.findViewById(R.id.linearLayout1);
            LinearLayout view2= (LinearLayout) view.findViewById(R.id.linearLayout2);
            LinearLayout view3= (LinearLayout) view.findViewById(R.id.linearLayout3);
            LinearLayout view4= (LinearLayout) view.findViewById(R.id.linearLayout4);
            List<LinearLayout> linears= Arrays.asList(view1,view2,view3,view4);
            for(LinearLayout ll : linears) {

                Bullet pointer = bulletStock.get(linears.indexOf(ll));
                BulletSet bulletSet = new BulletSet();
                if (!bulletSet.ifHasThisBullet(pointer)) {
                    pointer.setColorFilter(Color.BLACK);
                }
                ll.addView(pointer);
                pointer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));
                TextView heroName = new TextView(getContext());
                heroName.setText(pointer.getName());
                ll.addView(heroName);
                heroName.setGravity(Gravity.CENTER_HORIZONTAL);

                setCost(pointer, ll);
                addListener(pointer);
            }
        }
        private void putAbilitiesOnShelf(View view)
        {
            LinearLayout view1= (LinearLayout) view.findViewById(R.id.linearLayout1);
            LinearLayout view2= (LinearLayout) view.findViewById(R.id.linearLayout2);
            LinearLayout view3= (LinearLayout) view.findViewById(R.id.linearLayout3);
            LinearLayout view4= (LinearLayout) view.findViewById(R.id.linearLayout4);
            List<LinearLayout> linears= Arrays.asList(view1,view2,view3,view4);
            for(LinearLayout ll : linears) {

                Ability ability = abilityStock.get(linears.indexOf(ll));
                //AbilitySet abilitySet= new AbilitySet();
                AbilityView pointer= new AbilityView(getContext(),ability);
                if (!AbilitySet.getInstance().ifHasThisAbility(ability)) {
                    pointer.setColorFilter(Color.BLACK);
                }
                ll.addView(pointer);
                pointer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));
                TextView heroName = new TextView(getContext());
                heroName.setText(pointer.getName());
                ll.addView(heroName);
                heroName.setGravity(Gravity.CENTER_HORIZONTAL);
                setCost(pointer, ll);
                addListener(pointer);
            }
        }

        /**
         * Destroy the item from the {@link ViewPager}. In our case this is simply removing the
         * {@link View}.
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        /**Metods to chose and place right hero, bullet and ability in shelfs
        */
            private int getMod(int listSize,int number,int i)
            {
                Date now= Calendar.getInstance().getTime();
                int day=now.getDay();
                int mod=(int)(listSize/number+0.5);
                return (i+day)%mod;
            }

            private void choseStock (int sizeOfStock)
            {
                choseHeroes(sizeOfStock);
                choseAbilities(sizeOfStock);
                choseBullets(sizeOfStock);
            }

            private void choseHeroes(int sizeOfStock)
            {
                List<Hero>heroes=HeroesSet.getHeroesList(getContext());
                for(int i=0;i<heroes.size();i++)
                {
                    if(getMod(heroes.size(),sizeOfStock,i)==0)
                    {
                        heroes.get(i);
                        stock.add(heroes.get(i));
                    }
                }
            }
            private void  choseAbilities(int n)
            {
                List<Ability>abilities=AbilitySet.getInstance().getAbilityList();
                for(int i=0;i<abilities.size();i++)
                {
                    if(getMod(abilities.size(),n,i)==0)
                    {
                        abilities.get(i);
                        abilityStock.add(abilities.get(i));
                    }
                }
            }

            private void choseBullets(int n)
            {
                List<Bullet>bullets=BulletSet.getBulletList(getContext());

                for(int i=0;i<bullets.size();i++)
                {
                    if(getMod(bullets.size(),n,i)==0)
                    {
                        bullets.get(i);
                        bulletStock.add(bullets.get(i));
                    }
                }
            }

            private void setCost(PossesAble possesAble,LinearLayout linearLayout)
            {
                possesAble.getPossesStrategy().setPossesFotter(linearLayout,getContext());
            }

            private void addListener(final PossesAble hero)
            {
                ((ImageView)hero).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PossesAble h=(PossesAble) v;
                        setPopUpLayout(h);
                        popupWindow.showAtLocation(main,Gravity.CENTER_HORIZONTAL , 10, 10);
                        popupWindow.update(50, 50, 500, 500);
                    }
                });
            }
        private void setPopUpLayout(final PossesAble hero)
        {
            LinearLayout linearLayout= new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            TextView acquisitionName = new TextView(getContext());
            acquisitionName.setText(hero.getName());
            acquisitionName.setGravity(Gravity.CENTER_HORIZONTAL);
            Button yes= new Button(getContext());
            yes.setText("Tak");
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserProfile userProfile= new UserProfile(v.getContext());
                    if(hero.getClass().equals(Hero.class)) {
                        userProfile.buy((Hero)hero);
                    }
                    else if( hero.getClass().equals(Bullet.class))
                    {
                        userProfile.buy((Bullet)hero);
                    }
                    else if( hero.getClass().equals(AbilityView.class))
                    {
                        userProfile.buy(((AbilityView) hero).getAbility());
                    }
                    popupWindow.dismiss();
                    getActivity().finish();
                    getActivity().overridePendingTransition(0, 0);
                    getActivity().startActivity(getActivity().getIntent());
                    getActivity().overridePendingTransition(0, 0);
                }
            });
            Button no= new Button(getContext());
            no.setText("No");
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
            LinearLayout answer= new LinearLayout(getContext());
            answer.setOrientation(LinearLayout.HORIZONTAL);
            answer.addView(yes);
            answer.addView(no);
            linearLayout.addView(acquisitionName);
            setCost(hero,linearLayout);
            linearLayout.addView(answer);
            linearLayout.setBackgroundColor(Color.WHITE);

            popupWindow.setContentView(linearLayout);
        }
    }
    }