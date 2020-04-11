package com.example.user.bulletfalls.Profile.Collection.UserStatistics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;

import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.BeastRaisers.Linear;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamiliesContainer;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.Family;
import com.example.user.bulletfalls.Game.Elements.Hero.HeroProfile;
import com.example.user.bulletfalls.Profile.UserProfile;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Storage.Sets.HeroesSet;

public class FamilyCompletenessList extends TableLayout {
    private static final int rowLongitude=3;
    public FamilyCompletenessList(Context context) {
        super(context);
    }

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void load(){

        FamiliesContainer familiesContainer= new FamiliesContainer();

        HeroesSet heroesSet= HeroesSet.getInstance();
        int i=0;
        TableRow tableRow=new TableRow(this.getContext());
        for(Family family : familiesContainer.getAll()){
            if(i%rowLongitude==0)
            {
                tableRow= new TableRow(this.getContext());
                tableRow.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,150));
                this.addView(tableRow);
            }
            ImageView familySymbol=new ImageView(this.getContext());
            familySymbol.setImageResource(family.getMiniature());
            familySymbol.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1));
            familySymbol.setScaleType(ImageView.ScaleType.CENTER_CROP);
            familySymbol.setAdjustViewBounds(true);
            int allMembers=heroesSet.getNumberOfFamilysCandy(family.getGroupName());
            int posesMembers=heroesSet.getPossesedCandyfFromTheFamily(family.getGroupName());

            TextView completeness= new TextView(this.getContext());
            completeness.setText(posesMembers+" / "+allMembers);
            completeness.setGravity(Gravity.CENTER);
            Typeface typeface = ResourcesCompat.getFont(this.getContext(), R.font.monoton_regular);
            completeness.setTypeface(typeface);
            if(posesMembers==allMembers) completeness.setTextColor(Color.YELLOW);
            completeness.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1));

            TextView familyAbility= new TextView(this.getContext());
            familyAbility.setText(family.getFamilyBonusDescription());
            familyAbility.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1));
            familyAbility.setGravity(Gravity.CENTER);
            LinearLayout imageAndAmount= new LinearLayout(this.getContext());
            imageAndAmount.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1));

            imageAndAmount.addView(familySymbol);
            imageAndAmount.addView(completeness);



            LinearLayout familyStats= new LinearLayout(this.getContext());
            familyStats.setOrientation(VERTICAL);
            //MarginLayoutParams marginLayoutParams=new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
            familyStats.setLayoutParams(new TableRow.LayoutParams(0, 250,1));

            familyStats.addView(imageAndAmount);
            familyStats.addView(familyAbility);
            familyStats.setPadding(2,2,2,2);

            familyStats.setGravity(Gravity.CENTER_VERTICAL);
            familyStats.setBackgroundColor(Color.RED);

            tableRow.addView(familyStats);
            i++;
        }
    }





}
