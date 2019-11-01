package com.example.user.bulletfalls.Shop;


import android.content.Intent;
import android.graphics.Color;
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

import com.bumptech.glide.GenericTransitionOptions;
import com.example.user.bulletfalls.Game.Elements.Ability.Specyfication.AbilitySpecyfication;
import com.example.user.bulletfalls.Game.Elements.Ability.Ability;
import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Helper.ToViewConverter;
import com.example.user.bulletfalls.Game.Elements.Hero.HeroSpecyfication;
import com.example.user.bulletfalls.GlobalUsage.Enums.Permission;
import com.example.user.bulletfalls.Profile.Collection.UserCollection;
import com.example.user.bulletfalls.Shop.FragmentsSlider.SlidingTabLayout;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.GlobalUsage.Interfaces.PossesAble;
import com.example.user.bulletfalls.Storage.Sets.AbilitySet;
import com.example.user.bulletfalls.Storage.Sets.BulletSet;
import com.example.user.bulletfalls.Storage.Sets.HeroesSet;
import com.example.user.bulletfalls.Profile.UserProfile;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.MoneyNeedIndex;

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
        Intent intent=getActivity().getIntent();


        intent.getIntExtra("itemnumber",0);

        mViewPager.setCurrentItem(intent.getIntExtra("itemnumber",0));
        // END_INCLUDE (setup_viewpager)

        // BEGIN_INCLUDE (setup_slidingtablayout)
        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);

        //mSlidingTabLayout.get

        main= (FrameLayout) view.findViewById(R.id.main);


        // END_INCLUDE (setup_slidingtablayout)
    }

    // END_INCLUDE (fragment_onviewcreated)

    class SamplePagerAdapter extends PagerAdapter {
        List<Hero> stock;
        List<AbilitySpecyfication> abilitySpecyficationStock;
        List<Bullet> bulletStock;
        public SamplePagerAdapter()
        {
            stock= new LinkedList<>();
            abilitySpecyficationStock =new LinkedList<>();
            bulletStock = new LinkedList<>();

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
        private List<LinearLayout> getSelfs(View view)
        {
            LinearLayout view1= (LinearLayout) view.findViewById(R.id.linearLayout1);
            LinearLayout view2= (LinearLayout) view.findViewById(R.id.linearLayout2);
            LinearLayout view3= (LinearLayout) view.findViewById(R.id.linearLayout3);
            LinearLayout view4= (LinearLayout) view.findViewById(R.id.linearLayout4);
            view1.setGravity(Gravity.CENTER);
            view2.setGravity(Gravity.CENTER);
            view3.setGravity(Gravity.CENTER);
            view4.setGravity(Gravity.CENTER);
            return Arrays.asList(view1,view2,view3,view4);
        }

        private void putViewsOnShelf(View view,List<? extends ImageView> images)
        {
            List<LinearLayout> linears= getSelfs(view);
            for(LinearLayout ll : linears) {

                int index=linears.indexOf(ll);
                if(index<images.size()) {
                    ImageView pointer = stock.get(index);

                    LinearLayout footer = (LinearLayout) ll.getChildAt(1);
                    footer.setGravity(Gravity.CENTER);
                    ((LinearLayout) ll.getChildAt(0)).addView(pointer);
                    pointer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));
                    TextView heroName = new TextView(getContext());
                    ((LinearLayout) ll.getChildAt(0)).addView(heroName);
                    heroName.setGravity(Gravity.CENTER_HORIZONTAL);
                    if(pointer.getClass().equals(Hero.class)) {
                        Hero hero =(Hero)pointer;
                        heroName.setText(hero.getName());
                        setCost(hero.getSpecyfication(), footer);
                        addListener(footer, hero.getSpecyfication());
                    }else if(pointer.getClass().equals(Bullet.class)){
                        Bullet bullet = (Bullet)pointer;
                        heroName.setText(bullet.getName());
                        setCost(bullet.getSpecyfication(), footer);
                        addListener(footer, bullet.getSpecyfication());

                    }else if(pointer.getClass().equals(AbilitySpecyfication.class)){
                        Ability ability = (Ability)pointer;

                        heroName.setText(ability.getSpecyfication().getName());
                        setCost(ability.getSpecyfication(), footer);
                        addListener(footer, ability.getSpecyfication());
                    }
                    //ll.addView(heroName);



                }
            }
        }

        private void putHeroesOnShelf(View view)
        {
            List<LinearLayout> linears= getSelfs(view);
            for(LinearLayout ll : linears) {

                int index=linears.indexOf(ll);
                if(index<stock.size()) {
                    Hero pointer = stock.get(index);
                    pointer.setColorFilter(Color.BLACK);
                    LinearLayout footer = (LinearLayout) ll.getChildAt(1);
                    footer.setGravity(Gravity.CENTER);
                    ((LinearLayout) ll.getChildAt(0)).addView(pointer);
                    pointer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));
                    TextView heroName = new TextView(getContext());
                    heroName.setText(pointer.getName());

                    ((LinearLayout) ll.getChildAt(0)).addView(heroName);
                    heroName.setGravity(Gravity.CENTER_HORIZONTAL);

                    setCost(pointer.getSpecyfication(), footer);
                    addListener(footer, pointer.getSpecyfication());
                }
                }
        }
        private void putBulletsOnShelf(View view)
        {
            List<LinearLayout> linears= getSelfs(view);
            for(LinearLayout ll : linears) {
                int index=linears.indexOf(ll);
                if(index< bulletStock.size()) {
                    LinearLayout footer = (LinearLayout) ll.getChildAt(1);
                    LinearLayout header = (LinearLayout) ll.getChildAt(0);
                    footer.setGravity(Gravity.CENTER);
                    Bullet pointer = bulletStock.get(linears.indexOf(ll));
                    pointer.setColorFilter(Color.BLACK);
                    header.addView(pointer);
                    pointer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));
                    TextView heroName = new TextView(getContext());
                    heroName.setText(pointer.getName());
                    header.addView(heroName);
                    heroName.setGravity(Gravity.CENTER_HORIZONTAL);
                    setCost(pointer.getSpecyfication(), footer);
                    addListener(footer, pointer.getSpecyfication());
                }
                }
        }
        private void putAbilitiesOnShelf(View view)
        {
            List<LinearLayout> linears= getSelfs(view);
            for(LinearLayout ll : linears) {
                int index=linears.indexOf(ll);
                if(index< abilitySpecyficationStock.size()) {
                    LinearLayout footer = (LinearLayout) ll.getChildAt(1);
                    footer.setGravity(Gravity.CENTER);
                    AbilitySpecyfication abilitySpecyfication = abilitySpecyficationStock.get(linears.indexOf(ll));
                    Ability pointer = new Ability(getContext(), abilitySpecyfication);
                    pointer.setColorFilter(Color.BLACK);
                    ((LinearLayout) ll.getChildAt(0)).addView(pointer);
                    pointer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));
                    TextView heroName = new TextView(getContext());
                    heroName.setText(pointer.getSpecyfication().getName());
                    ((LinearLayout) ll.getChildAt(0)).addView(heroName);
                    heroName.setGravity(Gravity.CENTER_HORIZONTAL);
                    setCost(pointer.getSpecyfication(), footer);
                    addListener(footer, pointer.getSpecyfication());
                }
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
            private int getMod(int listSize,int number,int i) throws ArithmeticException
            {
                Date now= Calendar.getInstance().getTime();
                int day=now.getDay();
                int mod=(int)((float)listSize/(float)number+0.5);

                return (i + day) % mod;
                }
                public int getId(int listSize)
                {
                    Date now= Calendar.getInstance().getTime();
                    int day=now.getDay();
                    int month=now.getMonth();
                    if(listSize==0) throw new ArithmeticException();
                    return (day*month) % listSize;
                }

            private void choseStock (int sizeOfStock)
            {
                choseHeroes(sizeOfStock);
                choseAbilities(sizeOfStock);
                choseBullets(sizeOfStock);
            }

            private void choseHeroes(int sizeOfStock)
            {
                List<Hero>heroeList=ToViewConverter.convertHeroes(getContext(),HeroesSet.getInstance().getHeroesList());

                List<Hero> heroes = new LinkedList<>();
                for(Hero h:heroeList)
                {
                    if(!UserCollection.getInstance().doYouOwnIt(h.getSpecyfication()))
                    {
                        heroes.add(h);
                    }
                }

                for(int i=0;i<sizeOfStock;i++)
                {
                    if(heroes.size()>0) {
                        int n = getId(heroes.size());
                        if (n < heroes.size()) {
                            stock.add(heroes.get(n));
                            heroes.remove(n);
                            i--;
                        }
                    }
                }
            }
            private void  choseAbilities(int sizeOfStock)
            {
                List<AbilitySpecyfication>abilitieList=AbilitySet.getInstance().getAbilities();

                List<AbilitySpecyfication> abilities= new LinkedList<>();
                for(AbilitySpecyfication a:abilitieList)
                {
                    if(! UserCollection.getInstance().doYouOwnIt(a))
                    {
                        abilities.add(a);
                    }
                }
                for(int i=0;i<sizeOfStock;i++)
                {
                    if(abilities.size()>0) {
                        int n=getId(abilities.size());
                        if (n<abilities.size()) {

                            abilitySpecyficationStock.add(abilities.get(n));
                            abilities.remove(n);
                            i--;
                        }
                    }
                }
            }
            private void choseBullets(int sizeOfStock)
            {
                List<Bullet> bulletList =ToViewConverter.convertBullets(getContext(),BulletSet.getInstance().getBulletList());
                List<Bullet> bullets = new LinkedList<>();
                for(Bullet b: bulletList)
                {
                    boolean possesFlaf=!UserCollection.getInstance().doYouOwnIt(b.getSpecyfication());
                    if(possesFlaf)
                    {
                        bullets.add(b);
                    }
                }
                for(int i=0;i<sizeOfStock;i++)
                {
                    if(bullets.size()>0) {
                        int n = getId(bullets.size());
                        if (n < bullets.size()) {
                            bulletStock.add(bullets.get(n));
                            bullets.remove(n);
                            i--;
                        }
                    }
                }
            }


            private void setCost(PossesAble possesAble,LinearLayout linearLayout)
            {
                possesAble.getPossesStrategy().setPossesFotter(linearLayout,getContext());
            }



        private void addListener(LinearLayout footer,final PossesAble hero)
        {

            for(int i=0;i<footer.getChildCount();i+=2) {
                final LinearLayout possesNeed=(LinearLayout) footer.getChildAt(i);
                final int finalI = i-i/2;
                possesNeed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MoneyNeedIndex.getInstance().setIndex(finalI);
                        //mViewPager.setCurrentItem(2);

                        //PossesAble h = (PossesAble) v;
                        setPopUpLayout(hero,possesNeed);
                        popupWindow.showAtLocation(main, Gravity.CENTER_HORIZONTAL, 10, 10);
                        popupWindow.update(50, 50, 500, 500);
                    }
                });
            }
            }
        private void setPopUpLayout(final PossesAble possesAble, final LinearLayout ll)
        {
            LinearLayout linearLayout= new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            TextView acquisitionName = new TextView(getContext());
            acquisitionName.setText(possesAble.getName());
            acquisitionName.setGravity(Gravity.CENTER_HORIZONTAL);
            Button yes= new Button(getContext());
            yes.setText("Tak");
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    UserProfile userProfile= new UserProfile(v.getContext());
                    if(possesAble.getClass().equals(HeroSpecyfication.class)) {
                        if( userProfile.buy(possesAble))
                        {
                        }
                    }
                    else if( possesAble.getClass().equals(BulletSpecyfication.class))
                    {
                        if(userProfile.buy(possesAble))
                        {
                        }
                    }
                    else if( possesAble.getClass().equals(AbilitySpecyfication.class))
                    {
                        if(userProfile.buy(possesAble))
                        {
                        }
                    }

                    //n= mViewPager.getCurrentItem();
                    popupWindow.dismiss();
                   getActivity().finish();
                   getActivity().overridePendingTransition(0, 0);
                   Intent intent =getActivity().getIntent();
                   intent.putExtra("itemnumber",mViewPager.getCurrentItem());
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
            setCost(possesAble,linearLayout);
            linearLayout.addView(answer);
            linearLayout.setBackgroundColor(Color.WHITE);

            popupWindow.setContentView(linearLayout);
        }
    }
}