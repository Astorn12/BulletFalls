package com.example.user.bulletfalls.Game.Strategies.Requirements;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.Elements.Helper.ToViewConverter;
import com.example.user.bulletfalls.GlobalUsage.Enums.HE;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Profile.Collection.UserCollection;
import com.example.user.bulletfalls.Storage.Sets.HeroesSet;
import com.example.user.bulletfalls.GlobalUsage.Supporters.GuiSupporters.LayoutParamsSupporter;

import java.util.LinkedList;
import java.util.List;

public class HeroesRequirements implements IGameRequirements {
    List<HE> heroes;

    public HeroesRequirements(List<HE> heroes) {
        this.heroes = heroes;
    }
    public HeroesRequirements(HE... manyHeroes){
        this.heroes= new LinkedList<>();

        for(HE h: manyHeroes)
        {
            this.heroes.add(h);
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean canPlay(Context context) {
        UserCollection userCollection= UserCollection.getInstance();
        HeroesSet heroesSet= HeroesSet.getInstance();
        for(HE h : this.heroes) {
            if(!userCollection.doYouOwnIt(heroesSet.getHero(h.getValue())))
                return false;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public ViewGroup selfDescribe(Context context) {
        LinearLayout heroesShow= new LinearLayout(context);
        heroesShow.setOrientation(LinearLayout.HORIZONTAL);
        List<Hero> heroesSet=ToViewConverter.convertHeroes(context,HeroesSet.getInstance().getHeroesList());
        LayoutParamsSupporter support= new LayoutParamsSupporter();

        for(HE h: this.heroes)
        {

            for(Hero he: heroesSet)
            {
                if(he.getName().equals(h.getValue()))
                {

                    support.setLayoutParams(he,0,-1,1);


                    heroesShow.addView(he);
                    if(UserCollection.getInstance().doYouOwnIt(he.getSpecyfication()))
                        he.setColorFilter(Color.BLACK);
                    break;
                }

            }

        }

        return heroesShow;
    }
}
