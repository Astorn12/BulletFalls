package com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamilyAbilityPackage.FamilyAbility;
import com.example.user.bulletfalls.GlobalUsage.Supporters.DpsConverter;
import com.example.user.bulletfalls.GlobalUsage.Supporters.ScalerSupporter;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.List;

public class Family {
    FamilyName name;
    FamilyBoostTable boostTable;
    FamilyAbility familyAbility;
    int background;
    int miniature;
    String description;
    int abilitiMiniature;
    public Family(FamilyName gm, FamilyBoostTable gt, FamilyAbility ga, int background, int miniature, String description)
    {
        this.name=gm;
        this.boostTable=gt;
        this.familyAbility =ga;
        this.background=background;
        this.miniature=miniature;
        this.description=description;

    }

   /* public Family(FamilyName gm, FamilyBoostTable gt, FamilyAbility ga)
    {
        this(gm,gt,ga,0,0,"");

    }*/

    public Action boost()
    {
        return  familyAbility.boostGame(boostTable.getBoostCount(name));
    }


    public int getBackground() {
        return background;
    }

    public int getMiniature() {
        return miniature;
    }

    public String getDescription() {
        return description;
    }

    public FamilyName getGroupName() {
        return name;
    }

    public String getName()
    {
        return this.getGroupName().toString();
    }

    public List<MutablePair<Float,Integer>> getLevelTable()
    {
        return this.boostTable.getLevelTable();
    }

    public int getActualLevel()
    {
        return this.boostTable.getLevel(this.name);
    }

    public int getAbilitiMiniature() {
        return abilitiMiniature;
    }

    public void setAbilitiMiniature(int abilitiMiniature) {
        this.abilitiMiniature = abilitiMiniature;
    }

    public FamilyAbility getFamilyAbility() {
        return familyAbility;
    }

    public TextView getBoostTextView(Context context){
        TextView textView= new TextView(context);
        textView.setText("+"+this.getDescription());
        textView.setTextColor(this.getBackground());
        return textView;
    }

    private String getBoostText(){
        String boost="";
        boost+=this.getFamilyAbility().getPrefix();
        boost+=this.boostTable.getBoostCount(this.getGroupName())+"";
        boost+=this.getFamilyAbility().getFootnote();
        return boost;
    }

    public String getBosotNumberWithSign(){
        String boost="";
        boost+=this.getFamilyAbility().getPrefix();
        boost+=this.boostTable.getBoostCount(this.getGroupName())+"";
        return boost;
    }

    public LinearLayout getGroupField(Context context){
        DpsConverter dpsConverter= new DpsConverter(context);
        ScalerSupporter scalerSupporter= new ScalerSupporter();

        LinearLayout linearLayout= new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        ImageView logo= new ImageView(context);
        logo.setImageResource(this.getMiniature());
        scalerSupporter.basicScale(logo);
        TextView boostText= new TextView(context);
        boostText.setText(this.getBoostText());
        boostText.setGravity(Gravity.CENTER);

        linearLayout.addView(logo);
        linearLayout.addView(boostText);

        linearLayout.setBackgroundColor(this.getBackground());

        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,dpsConverter.getPixels(40)));

        return linearLayout;
    }
}
