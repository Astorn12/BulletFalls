package com.example.user.bulletfalls.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
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
import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.GameSupporters.GroupPackage.Group;
import com.example.user.bulletfalls.GameSupporters.GroupPackage.GroupsContainer;
import com.example.user.bulletfalls.GuiSupporters.BorderSetter;
import com.example.user.bulletfalls.Database.JsonDatabases.HeroesSet;
import com.example.user.bulletfalls.KlasyPomocnicze.RomeLettersConverter;
import com.example.user.bulletfalls.ObjectsOfGame.Hero;
import com.example.user.bulletfalls.R;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.List;

public class GroupHouse extends AppCompatActivity {
    GroupName groupName;
    Group group;

    TextView groupsName;
    TextView groupsDescription;

    ImageView groupsMiniature;
    LinearLayout levelTable;

    TableLayout miniaturesTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_house);
        Intent intent=getIntent();
         groupName=(GroupName)intent.getSerializableExtra("groupName");
        ConstraintLayout house=(ConstraintLayout) findViewById(R.id.house);
        GroupsContainer groupsContainer= new GroupsContainer();
        group=groupsContainer.getGroup(groupName);
        house.setBackgroundColor(group.getBackground());


        pullContainers();
        fillLayout();
    }

    private void pullContainers()
    {
        this.groupsName=(TextView) findViewById(R.id.groupsname);
        this.groupsDescription=(TextView)findViewById(R.id.groupdescription);
        this.groupsMiniature=(ImageView) findViewById(R.id.groupsminiature);
        this.levelTable=(LinearLayout) findViewById(R.id.leveltable);
        this.miniaturesTable=(TableLayout)findViewById(R.id.miniaturestable);
    }

    private void fillLayout()
    {
        this.groupsName.setText(group.getName());
        this.groupsDescription.setText(group.getDescription());
        Glide.with(this).load(group.getMiniature()).into(groupsMiniature);
        fillLevelsTable();
        fillFamilyMembersTable();

    }

    private void fillLevelsTable()
    {

        int level=this.group.getActualLevel();

        for(MutablePair<Float,Integer> p:this.group.getLevelTable())
        {
            LinearLayout ll= new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            TextView v= new TextView(this);
            v.setText(((int)(p.getLeft()*100))+"%");
            TextView t=new TextView(this);
            t.setText(p.getRight()+" ");

            ll.addView(v);
            ll.addView(t);

            this.levelTable.addView(ll);
            if(level==0) ll.setBackgroundColor(Color.YELLOW);
            level--;
        }
    }
    private void fillFamilyMembersTable()
    {
        int mod=5;


        List<Hero> family=HeroesSet.getHeroesList(this,group);
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

            HeroesSet heroSet= new HeroesSet();


            miniature.setBackgroundColor(Color.BLACK);
            BorderSetter borderSetter= new BorderSetter(3,this.groupName.getValue());
            FrameLayout fm=borderSetter.setBorderWB(miniature);
            miniature.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));



            if(!heroSet.ifHasThisHero(h))
            {
                miniature.setColorFilter(Color.WHITE);
                TextView tier= new TextView(this);

                RomeLettersConverter rlc= new RomeLettersConverter();

                tier.setText(rlc.convert(h.getTier()));
                tier.setTextSize(25);
                tier.setTextColor(Color.BLACK);
                tier.setGravity(Gravity.CENTER);
                //tier.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
                fm.addView(tier);
            }

            fm.setLayoutParams(new TableRow.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT,1));
            row.addView(fm);

            counter++;
        }
    }


}
