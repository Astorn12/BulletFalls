package com.example.user.bulletfalls.Activities;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.bulletfalls.Game.Elements.Ability.Specyfication.AbilitySpecyfication;
import com.example.user.bulletfalls.Game.Elements.Ability.Ability;
import com.example.user.bulletfalls.Game.Strategies.Bounty.Bounty;
import com.example.user.bulletfalls.Game.Management.GameSummary;
import com.example.user.bulletfalls.Game.Management.Medium;
import com.example.user.bulletfalls.Profile.LevelBar;
import com.example.user.bulletfalls.Profile.UserProfile;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Game.Elements.Hero.HeroSpecyfication;
import com.example.user.bulletfalls.GlobalUsage.Supporters.ScalerSupporter;

public class GameResult extends AppCompatActivity {
    HeroSpecyfication heroSpecyfication;
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
        this.heroSpecyfication =gameSummary.getHeroSpecyficationSpecyfication();
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
        LinearLayout items=(LinearLayout) findViewById(R.id.bonuty);
        TextView moneyNumber=(TextView) findViewById(R.id.moneyNumber);
        TextView expText=(TextView) findViewById(R.id.exptext);
        LevelBar levelBar= new LevelBar(this);
        FrameLayout frameLayout=levelBar.get();

        LinearLayout.LayoutParams paramsForLevelBar= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        paramsForLevelBar.setMargins(10,10,10,10);
        frameLayout.setLayoutParams(paramsForLevelBar);
        userStatsBar.addView(frameLayout);/**adding level bar*/
        Glide.with(this).load(heroSpecyfication.getImage()).into(fightingHero);

        moneyNumber.setText(bounty.getMoney()+"");
        expText.setText(bounty.getExp()+"pkt");
        expText.setTextColor(Color.YELLOW);
        addWallet(userStatsBar);/**dodawanie wallet*/
        addStatistics(archiveStats);
        fillItems(items);
    }

    private void fillItems(LinearLayout items) {

        if (this.bounty.getItemsList().size()==0)
        {
            ImageView blindEye= new ImageView(this);
           // blindEye.setImageResource(R.drawable.blindeye);
            Glide.with(this).load(R.drawable.blindeye);
            items.addView(blindEye);
        }
        else
            for(int i=0;i< this.bounty.getItemsList().size();i++)
            {
                LinearLayout horizontal= new LinearLayout(this);
                ImageView currencyView= new ImageView(this);
               // currencyView.setImageResource(this.bounty.getItemsList().get(i).left.getResource());
                Glide.with(this).load(this.bounty.getItemsList().get(i).left.getResource()).into(currencyView);
                TextView amount= new TextView(this);
                amount.setText(this.bounty.getItemsList().getList().get(i).right+"");
                horizontal.setOrientation(LinearLayout.HORIZONTAL);
                horizontal.addView(currencyView);
                horizontal.addView(amount);
                items.addView(horizontal);
            }
    }

    private void addWallet(LinearLayout linearLayout)
    {
        UserProfile userProfile= new UserProfile(this);
        userProfile.addWallet(linearLayout,this);
}
    private void addStatistics(LinearLayout stats)
    {
        int size= 16;
        final int iconsSize=200;
        LinearLayout l1= new LinearLayout(this);
        l1.setGravity(Gravity.CENTER);
        l1.setOrientation(LinearLayout.HORIZONTAL);

        final ImageView fightingdipper=new ImageView(this);
        //fightingdipper.setImageResource(R.drawable.dipperfighting);
        Glide.with(this).load(R.drawable.dipperfighting).into(fightingdipper);
        fightingdipper.setLayoutParams(new LinearLayout.LayoutParams(-1,-2,1));
        fightingdipper.post(new Runnable() {
            @Override
            public void run() {

                new ScalerSupporter().scaleByHeight(fightingdipper,iconsSize);
            }
        });

        TextView killedBount= new TextView(this);
        killedBount.setText(medium.getKilledEnemys().size()+"");
        killedBount.setGravity(Gravity.CENTER);
        killedBount.setLayoutParams(new LinearLayout.LayoutParams(-1,-2,1));
        killedBount.setTextSize(30);


        l1.addView(fightingdipper);
        l1.addView(killedBount);
        stats.addView(l1);


        LinearLayout l2= new LinearLayout(this);
        l2.setOrientation(LinearLayout.HORIZONTAL);
        l2.setGravity(Gravity.CENTER);

        final ImageView dipandmebshootinggnome=new ImageView(this);
        //dipandmebshootinggnome.setImageResource(R.drawable.dipandmebshootedgnome);
        Glide.with(this).load(R.drawable.dipandmebshootedgnome).into(dipandmebshootinggnome);
        dipandmebshootinggnome.setLayoutParams(new LinearLayout.LayoutParams(-1,-2,1));
        dipandmebshootinggnome.post(new Runnable() {
            @Override
            public void run() {

                new ScalerSupporter().scaleByHeight(dipandmebshootinggnome,iconsSize);
            }
        });
        TextView shotedBullets=new TextView(this);
        shotedBullets.setText(medium.getShootedByHero()+"");
        shotedBullets.setTextSize(30);
        shotedBullets.setGravity(Gravity.CENTER);
        shotedBullets.setLayoutParams(new LinearLayout.LayoutParams(-1,-2,1));

        l2.addView(dipandmebshootinggnome);
        l2.addView(shotedBullets);
        stats.addView(l2);


        LinearLayout l3= new LinearLayout(this);
        l3.setOrientation(LinearLayout.HORIZONTAL);
        l3.setGravity(Gravity.CENTER);
        final ImageView demagedDipper=new ImageView(this);
        //demagedDipper.setImageResource(R.drawable.demmageddipper);
        Glide.with(this).load(R.drawable.demmageddipper).into(demagedDipper);
        demagedDipper.setLayoutParams(new LinearLayout.LayoutParams(-1,-2,1));
        demagedDipper.post(new Runnable() {
            @Override
            public void run() {
                new ScalerSupporter().scaleByHeight(demagedDipper,iconsSize);
            }
        });
        TextView takenDamage=new TextView(this);
        takenDamage.setText(medium.getTakenDamage()+"");
        takenDamage.setGravity(Gravity.CENTER);
       // takenDamage.setGravity(Gravity.CENTER);
        takenDamage.setTextSize(30);
        takenDamage.setLayoutParams(new LinearLayout.LayoutParams(-1,-2,1));
        l3.addView(demagedDipper);
        l3.addView(takenDamage);

        stats.addView(l3);

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
       // abilitiesStats.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

        for(AbilitySpecyfication a: heroSpecyfication.getAbilities().getAbilities())
        {
            LinearLayout abilityPlusCount= new LinearLayout(this);
            abilityPlusCount.setOrientation(LinearLayout.VERTICAL);

            Ability ability= new Ability(this,a);
            //abilityView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
            //abilitystats.addView(abilityView);

            FrameLayout frame= new FrameLayout(this);

            TextView count= new TextView(this);
            count.setText(medium.getAbilityUseCounter(a)+"");
            count.setTextSize(50);
            count.setAlpha(1);
            count.setGravity(Gravity.CENTER);
            //count.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0));
            //abilitystats.addView(textView);

            //abilitystats.setLayoutParams(new LinearLayout.LayoutParams(0,-2,1));
            //abilitiesStats.addView(abilitystats);
            frame.setForegroundGravity(Gravity.CENTER);
            frame.addView(ability);
            frame.addView(count);

            frame.setLayoutParams(new LinearLayout.LayoutParams(0,-1,1));
            frame.setBackgroundColor(Color.BLUE);
            abilitiesStats.addView(frame);
           // ability.setLayoutParams(new LinearLayout.LayoutParams(0,-1,1));
            //abilitiesStats.addView(ability);
        }
        abilitiesStats.setLayoutParams(new LinearLayout.LayoutParams(-1,-1));
        abilitiesStats.setBackgroundColor(Color.YELLOW);
        return abilitiesStats;
    }
}
