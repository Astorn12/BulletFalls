package com.example.user.bulletfalls.Activities;

import android.content.Intent;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.bulletfalls.Game.Elements.Helper.ToViewConverter;
import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.Family;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamiliesContainer;
import com.example.user.bulletfalls.GlobalUsage.Supporters.GuiSupporters.BorderSetter;
import com.example.user.bulletfalls.Profile.Collection.UserCollection;
import com.example.user.bulletfalls.Storage.Sets.HeroesSet;
import com.example.user.bulletfalls.GlobalUsage.Supporters.RomeLettersConverter;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.GlobalUsage.Supporters.TextViewSupporters.SupporterTextView;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.List;

public class FamilyHouse extends AppCompatActivity {
    FamilyName familyName;
    Family family;

    TextView groupsName;
    LinearLayout groupsDescription;

    ImageView groupsMiniature;
    LinearLayout levelTable;

    TableLayout miniaturesTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_house);
        Intent intent=getIntent();
         familyName =(FamilyName)intent.getSerializableExtra("familyName");
        LinearLayout house=(LinearLayout) findViewById(R.id.house);
        FamiliesContainer familiesContainer = new FamiliesContainer();
        family = familiesContainer.getGroup(familyName);
        house.setBackgroundColor(family.getBackground());


        pullContainers();
        fillLayout();
        this.family.getFamilyAbility().describe(groupsDescription,this.getBoost());
        SupporterTextView supporterTextView= new SupporterTextView();
        supporterTextView.setTextViewsTextSize(groupsDescription,20);




    }

    private void pullContainers()
    {
        this.groupsName=(TextView) findViewById(R.id.groupsname);
        this.groupsDescription=(LinearLayout)findViewById(R.id.groupdescription);
        this.groupsMiniature=(ImageView) findViewById(R.id.groupsminiature);
        this.levelTable=(LinearLayout) findViewById(R.id.leveltable);
        this.miniaturesTable=(TableLayout)findViewById(R.id.miniaturestable);
    }

    private void fillLayout()
    {
        this.groupsName.setText(family.getName());
        //this.groupsDescription.setText(family.getDescription());
        Glide.with(this).load(family.getMiniature()).into(groupsMiniature);
        fillLevelsTable();
        fillFamilyMembersTable();
    }

    private void fillLevelsTable()
    {

        int level=this.family.getActualLevel();

        levelTable.setBackgroundColor(Color.parseColor("#A9A9A9"));
        for(MutablePair<Float,Integer> p:this.family.getLevelTable())
        {
            LinearLayout  ll= new LinearLayout(this);

            ll.setOrientation(LinearLayout.HORIZONTAL);
            TextView v= new TextView(this);
            v.setText(((int)(p.getLeft()*100))+"%   "+this.family.getFamilyAbility().getPrefix());
            TextView t=new TextView(this);
            t.setText(p.getRight()+this.family.getFamilyAbility().getFootnote());

            ll.addView(v);
            ll.addView(t);

            this.levelTable.addView(ll);
            if(level==0) ll.setBackgroundColor(Color.YELLOW);
            level--;

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(0, 20, 0, 0);
            ll.setLayoutParams(layoutParams);

        }
        SupporterTextView supporterTextView= new SupporterTextView();
        supporterTextView.setTextViewsTextSize(levelTable,18);
    }
    private void fillFamilyMembersTable()
    {
        int mod=5;


        List<Hero> family=ToViewConverter.convertHeroes(this,HeroesSet.getInstance().getHeroesList(this.family));


        int counter=0;
        TableRow row= new TableRow(this);
        for(Hero h:family)
        {
            if(counter%mod==0)
            {
                row= new TableRow(this);
                row.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT,1));
                miniaturesTable.addView(row);
            }


            ImageView miniature= new ImageView(this);
            Glide.with(this).load(h.getMiniature()).into(miniature);

            HeroesSet heroSet= HeroesSet.getInstance();


            miniature.setBackgroundColor(Color.BLACK);
            BorderSetter borderSetter= new BorderSetter(3,this.familyName.getValue());
            FrameLayout fm=borderSetter.setBorderWB(miniature);
            miniature.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));



            if(!UserCollection.getInstance().doYouOwnIt(h.getSpecyfication()))
            {
                miniature.setColorFilter(Color.WHITE);
                TextView tier= new TextView(this);

                RomeLettersConverter rlc= new RomeLettersConverter();

                tier.setText(rlc.convert(h.getTier()));
                tier.setTextSize(25);
                tier.setTextColor(Color.BLACK);
                tier.setGravity(Gravity.CENTER);
                System.out.println("TIER"+ h.getTier());
                //tier.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
                fm.addView(tier);
            }

            fm.setLayoutParams(new TableRow.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT,1));
            row.addView(fm);

            counter++;
        }
    }
    @JsonIgnore
    private int getBoost()
    {
        return this.family.getLevelTable().get(this.family.getActualLevel()).getRight();
    }


}
