package com.example.user.bulletfalls.Shop;

import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.Elements.Helper.ToViewConverter;
import com.example.user.bulletfalls.GlobalUsage.Enums.Permission;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Storage.Sets.HeroesSet;
import com.example.user.bulletfalls.Profile.Currency;
import com.example.user.bulletfalls.Profile.UserProfile;
import com.example.user.bulletfalls.R;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Shop extends AppCompatActivity {

    List<Hero> stock;
    LinearLayout main;
    PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        main=(LinearLayout) findViewById(R.id.shopmain);
        //tableLayout=(TableLayout)findViewById(R.id.shelves);
        stock= new LinkedList<>();
        choseStock(4);
        //loadShop();
        //putOnShelf();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        ShopShelfsFragment fragment=new ShopShelfsFragment();
        transaction.replace(R.id.shopShelf,fragment);
        transaction.commit();


        popupWindow= new PopupWindow();
        showWallet();

    }


  /*  private void addListener(final Hero hero)
    {


        hero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hero h=(Hero)v;
                setPopUpLayout(h);
                popupWindow.showAtLocation(main,Gravity.CENTER_HORIZONTAL , 10, 10);
                popupWindow.update(50, 50, 500, 500);


            }
        });
    }*/

   /* private void setPopUpLayout(final Hero hero)
    {
        LinearLayout linearLayout= new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView acquisitionName = new TextView(this);
        acquisitionName.setText(hero.getName());
        acquisitionName.setGravity(Gravity.CENTER_HORIZONTAL);


        Button yes= new Button(this);
        yes.setText("Tak");
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfile userProfile= new UserProfile(v.getContext());
                userProfile.buy(hero.getSpecyfication());
                popupWindow.dismiss();
            }
        });
        Button no= new Button(this);
        no.setText("No");
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        LinearLayout answer= new LinearLayout(this);
        answer.setOrientation(LinearLayout.HORIZONTAL);
        answer.addView(yes);
        answer.addView(no);
        linearLayout.addView(acquisitionName);
        setCost(hero,linearLayout);
        linearLayout.addView(answer);
        linearLayout.setBackgroundColor(Color.WHITE);



        popupWindow.setContentView(linearLayout);
    }*/


    private void choseStock(int sizeOfStock)
    {
        List<Hero> heroes =ToViewConverter.convertHeroes(this,HeroesSet.getInstance().getHeroesList());
        Date now= Calendar.getInstance().getTime();
        int day=now.getDay();
        int mod=(int)(heroes.size()/sizeOfStock+0.5);
        for(int i = 0; i< heroes.size(); i++)
        {
            if((i+day)%mod==0)
            {
                stock.add(heroes.get(i));
            }
        }
    }

    private void loadShop()
    {
        TableLayout tableLayout= new TableLayout(this);
            tableLayout.setPadding(30,30,30,30);
            int n=2;
            TableRow shelf=null;
            for(int i=0;i<stock.size();i++)
            {
                if(i%n==0) {
                     shelf = new TableRow(this);
                    tableLayout.addView(shelf);
                }
               LinearLayout frameLayout= new LinearLayout(this);
                frameLayout.setBackgroundColor(Color.TRANSPARENT);

                shelf.addView(frameLayout);
                //frameLayout.setLayoutParams(getLayoutForFrameLayout());
                frameLayout.setOrientation(LinearLayout.VERTICAL);
                stock.get(i).setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT));
                frameLayout.addView(stock.get(i));
                LinearLayout footer= new LinearLayout(this);
                footer.setOrientation(LinearLayout.HORIZONTAL);
                footer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT));
                setCost(stock.get(i),footer);

        }
    }

    private void setCost(Hero hero, LinearLayout linearLayout)
    {
        hero.getPossesStrategy().setPossesFotter(linearLayout,this);

    }


    private FrameLayout.LayoutParams getLayoutForFrameLayout()
    {
        FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT,  1);



        return params;
    }

    private void showWallet()
    {
        LinearLayout wallet= (LinearLayout) findViewById(R.id.wallet);


        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        param.setMargins(5,5,5,5);

        LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1
        );
        //layoutParam.setMargins(5,10,10,5);

        UserProfile userProfile= new UserProfile(this);

        List<MutablePair<Currency,Integer>> list = userProfile.getStock();
        Collections.reverse(list);
        for(MutablePair<Currency,Integer> mp : list)
        {

            LinearLayout ll= new LinearLayout(this);
            ImageView iv= new ImageView(this);
            TextView tv= new TextView(this);

            ll.setOrientation(LinearLayout.HORIZONTAL);
            iv.setImageResource(mp.left.getResource());
            tv.setText(mp.right+"");
            iv.setLayoutParams(param);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setAdjustViewBounds(true);
            tv.setLayoutParams(param);
            tv.setGravity(Gravity.CENTER_VERTICAL);

            ll.addView(iv);
            ll.addView(tv);
            ll.setLayoutParams(layoutParam);
            ll.setGravity(Gravity.LEFT);
            wallet.addView(ll);

        }
    }
}
