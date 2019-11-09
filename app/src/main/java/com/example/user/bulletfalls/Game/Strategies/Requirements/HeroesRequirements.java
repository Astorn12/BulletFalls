package com.example.user.bulletfalls.Game.Strategies.Requirements;

import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.Elements.Helper.ToViewConverter;
import com.example.user.bulletfalls.GlobalUsage.Enums.HE;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
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
        List<Hero> checkList=ToViewConverter.convertHeroes(context,HeroesSet.getInstance().getHeroesList());

        for(HE h : this.heroes) {

            boolean haveHero=false;
            for(Hero he:checkList)
            {

                if(he.getName().equals(h.getValue())) haveHero=true;
            }
            if(!haveHero) return false;
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
                    break;
                }

            }

        }

        return heroesShow;
    }
}
