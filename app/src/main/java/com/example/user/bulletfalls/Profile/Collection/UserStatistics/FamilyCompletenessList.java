package com.example.user.bulletfalls.Profile.Collection.UserStatistics;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.BeastRaisers.Linear;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamiliesContainer;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.Family;
import com.example.user.bulletfalls.Game.Elements.Hero.HeroProfile;
import com.example.user.bulletfalls.Profile.UserProfile;
import com.example.user.bulletfalls.Storage.Sets.HeroesSet;

public class FamilyCompletenessList extends TableLayout {
    private static final int rowLongitude=4;
    public FamilyCompletenessList(Context context) {
        super(context);
    }

    public void load(){

        FamiliesContainer familiesContainer= new FamiliesContainer();
        UserProfile userProfile = new UserProfile();
        HeroesSet heroesSet= HeroesSet.getInstance();
        int i=0;
        TableRow tableRow=new TableRow(this.getContext());
        for(Family family : familiesContainer.getAll()){
            if(i%rowLongitude==0)
            {
                tableRow= new TableRow(this.getContext());
                tableRow.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT,1));
                this.addView(tableRow);
            }
            ImageView familySymbol=new ImageView(this.getContext());
            familySymbol.setImageResource(family.getMiniature());
            familySymbol.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1));
            familySymbol.setScaleType(ImageView.ScaleType.CENTER_CROP);
            familySymbol.setAdjustViewBounds(true);
            int allMembers=heroesSet.getNumberOfFamilysCandy(family.getGroupName());
            int posesMembers=heroesSet.getPossesedCandyfFromTheFamily(family.getGroupName());

            TextView completeness= new TextView(this.getContext());
            completeness.setText(allMembers+" / "+posesMembers);

            LinearLayout familyStats= new LinearLayout(this.getContext());
            familyStats.setOrientation(HORIZONTAL);
            familyStats.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1));
            familyStats.addView(familySymbol);
            familyStats.addView(completeness);
            familyStats.setPadding(2,2,2,2);

            familyStats.setGravity(Gravity.CENTER_VERTICAL);
            familyStats.setBackgroundColor(Color.RED);
            tableRow.addView(familyStats);
            i++;
        }
    }



}
