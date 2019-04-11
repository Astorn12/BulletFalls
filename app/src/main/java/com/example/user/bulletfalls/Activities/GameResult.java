package com.example.user.bulletfalls.Activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.bulletfalls.Objects.Ability;
import com.example.user.bulletfalls.Objects.AbilityView;
import com.example.user.bulletfalls.GameSupporters.GiveBountyPackage.Bounty;
import com.example.user.bulletfalls.GameSupporters.MediumTasks.GameSummary;
import com.example.user.bulletfalls.GameSupporters.MediumTasks.Medium;
import com.example.user.bulletfalls.ProfileActivity.LevelBar;
import com.example.user.bulletfalls.ProfileActivity.UserProfile;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Specyfications.Characters.HeroSpecyfication;

public class GameResult extends AppCompatActivity {
    HeroSpecyfication hero;
    Medium medium;
    String gameName;
    Bounty bounty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);
        /**To trzba jakoś uzyskać z gry*/
        GameSummary gameSummary=GameSummary.getInstance();
        this.medium=gameSummary.getMedium();
        this.hero=gameSummary.getHeroSpecyfication();
        this.gameName=gameSummary.getNameOfGame();
        this.bounty=gameSummary.getBounty();
        check();
    }
    private void check()
    {
        LinearLayout userStatsBar=(LinearLayout) findViewById(R.id.userstatsbar);
        ImageView fightingHero= (ImageView) findViewById(R.id.fightinghero);
        LinearLayout archiveStats=( LinearLayout)findViewById(R.id.staty);
        LinearLayout exp=(LinearLayout) findViewById(R.id.exp);
        LinearLayout money=(LinearLayout) findViewById(R.id.money);
        LinearLayout items=(LinearLayout) findViewById(R.id.items);
        TextView moneyNumber=(TextView) findViewById(R.id.moneyNumber);
        TextView expText=(TextView) findViewById(R.id.exptext);
        LevelBar levelBar= new LevelBar(this);
        FrameLayout frameLayout=levelBar.get();

        LinearLayout.LayoutParams paramsForLevelBar= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        paramsForLevelBar.setMargins(10,10,10,10);
        frameLayout.setLayoutParams(paramsForLevelBar);
        userStatsBar.addView(frameLayout);/**adding level bar*/
        Glide.with(this).load(hero.getImageResources()).into(fightingHero);

        moneyNumber.setText(bounty.getMoney()+"");
        expText.setText(bounty.getExp()+"pkt");
        expText.setTextColor(Color.YELLOW);
        addWallet(userStatsBar);/**dodawanie wallet*/
        addStatistics(archiveStats);
    }
    private void addWallet(LinearLayout linearLayout)
    {
        UserProfile userProfile= new UserProfile(this);
        userProfile.addWallet(linearLayout,this);
    }
    private void addStatistics(LinearLayout stats)
    {
        int size= 16;
        TextView killed= new TextView(this);
        killed.setText("Zniszczeni przeciwnicy");
        killed.setTextSize(size);
        killed.setGravity(Gravity.CENTER);

        stats.addView(killed);

        TextView killedBount= new TextView(this);
        killedBount.setText(medium.getKilledEnemys().size()+"");
        killedBount.setGravity(Gravity.CENTER);
        stats.addView(killedBount);

        TextView shoted= new TextView(this);
        shoted.setText("Wystrzelone kulki");
        shoted.setTextSize(size);
        shoted.setGravity(Gravity.CENTER);
        stats.addView(shoted);

        TextView shotedBullets=new TextView(this);
        shotedBullets.setText(medium.getShootedByHero()+"");
        shotedBullets.setGravity(Gravity.CENTER);
        stats.addView(shotedBullets);

        TextView taken= new TextView(this);
        taken.setText("Przyjęte obrażenia");
        taken.setGravity(Gravity.CENTER);
        taken.setTextSize(size);
        stats.addView(taken);

        TextView takenDamage=new TextView(this);
        takenDamage.setText(medium.getTakenDamage()+"");
        takenDamage.setGravity(Gravity.CENTER);
        stats.addView(takenDamage);

        TextView abilitiesText= new TextView(this);
        abilitiesText.setText("Użyte umiejętności specjalne");
        abilitiesText.setTextSize(size);
        abilitiesText.setGravity(Gravity.CENTER);
        stats.addView(abilitiesText);
        stats.addView(addAbilitiesStats());
    }
    private LinearLayout addAbilitiesStats()
    {
        LinearLayout abilitiesStats= new LinearLayout(this);
        abilitiesStats.setOrientation(LinearLayout.HORIZONTAL);
        abilitiesStats.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));

        for(Ability a: hero.getAbilities().getAbilities())
        {
            LinearLayout abilitystats= new LinearLayout(this);
            abilitystats.setOrientation(LinearLayout.VERTICAL);

            AbilityView abilityView= new AbilityView(this,a);
            abilitystats.addView(abilityView);

            TextView textView= new TextView(this);
            textView.setText(medium.getAbilityUseCounter(a)+"");
            abilitystats.addView(textView);

            abilitiesStats.addView(abilitystats);
        }
        return abilitiesStats;
    }
}
