package com.example.user.bulletfalls.Game.Elements.Hero;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.Elements.Ability.Specyfication.AbilitySpecyfication;
import com.example.user.bulletfalls.Game.Elements.Helper.CharacterSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Active.HeroAS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Collection.HeroCS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive.HeroPS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.View.CharacterVS;
import com.example.user.bulletfalls.Game.GameBiznesFunctions.Classes.IClass;
import com.example.user.bulletfalls.Game.Elements.Ability.AbilitiesBar;
import com.example.user.bulletfalls.GlobalUsage.Enums.AE;
import com.example.user.bulletfalls.GlobalUsage.Interfaces.PossesAble;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.PossesStrategy;
import com.example.user.bulletfalls.Storage.Sets.AbilitySet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("herospecyfication")
public class HeroSpecyfication extends CharacterSpecyfication implements PossesAble {

    /**VIEW STATISTICS*/


    /**PASSIVE STATISTICS*/
    AbilitiesBar abilities;

    /**ACTIVE STATISTICS*/
    IClass iClass;

    /**COLLECTION STATISTICS*/
    PossesStrategy possesStrategy;
    int tier;

    public HeroSpecyfication(String name, CharacterVS characterVS, HeroPS heroPS, HeroAS heroAS, HeroCS heroCS){
        super(name, characterVS,heroPS,heroAS,heroCS );



        this.abilities=getBeginAbilitySet();
        this.iClass =heroAS.getIcalss();
        this.possesStrategy=heroCS.getPossesStrategy();
        this.tier=heroCS.getTier();
    }

    private AbilitiesBar getBeginAbilitySet() {
        AbilitySpecyfication begin=AbilitySet.getInstance().getAbility(AE.NOTHING);
        return new AbilitiesBar(begin,begin,begin);
    }

    protected HeroSpecyfication()
    {

    }

    /**BUSINES METHODS*/

    @Override
    public boolean equals(Object o)
    {
        if(! (o instanceof HeroSpecyfication))
            return false;
        if(this.getName().equals(((HeroSpecyfication)o).getName()))
        {
            return  true;
        }
        return false;
    }

    @JsonIgnore
    public LinearLayout getHeroInfo(Context context)
    {
        LinearLayout linearLayout= new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        int fontSize=12;
        TextView t1= new TextView(context);
        t1.setText("HP ");
        t1.setTextSize(fontSize);
        t1.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView i1= new TextView(context);
        i1.setText(getLife()+"");
        i1.setTextSize(fontSize);
        i1.setGravity(Gravity.CENTER_HORIZONTAL);

        LinearLayout l1= new LinearLayout(context);
        l1.setOrientation(LinearLayout.HORIZONTAL);
        l1.addView(t1);
        l1.addView(i1);

        TextView t3= new TextView(context);
        t3.setText("Size ");
        t3.setTextSize(fontSize);
        t3.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView i3= new TextView(context);
        i3.setText(getHeight()+"");
        i3.setTextSize(fontSize);
        i3.setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout l3= new LinearLayout(context);
        l3.setOrientation(LinearLayout.HORIZONTAL);
        l3.addView(t3);
        l3.addView(i3);

        TextView t4= new TextView(context);
        t4.setText("Speed ");
        t4.setTextSize(fontSize);
        t4.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView i4= new TextView(context);
        i4.setText(getSpeed()+"");
        i4.setTextSize(fontSize);
        i4.setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout l4= new LinearLayout(context);
        l4.setOrientation(LinearLayout.HORIZONTAL);
        l4.addView(t4);
        l4.addView(i4);

        linearLayout.addView(l1);
        linearLayout.addView(l3);
        linearLayout.addView(l4);
        i1.setTextColor(Color.BLACK);
        i3.setTextColor(Color.BLACK);
        i4.setTextColor(Color.BLACK);
        t1.setTextColor(Color.BLACK);
        t3.setTextColor(Color.BLACK);
        t4.setTextColor(Color.BLACK);

        return linearLayout;

    }

    /**GETTERS & SETTERS*/

    public AbilitiesBar getAbilities() {
        return abilities;
    }

    public void setAbilities(AbilitiesBar abilities) {
        this.abilities = abilities;
    }

    public IClass getiClass() {
        return iClass;
    }

    public void setiClass(IClass iClass) {
        this.iClass = iClass;
    }

    public PossesStrategy getPossesStrategy() {
        return possesStrategy;
    }

    public void setPossesStrategy(PossesStrategy possesStrategy) {
        this.possesStrategy = possesStrategy;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }


}


