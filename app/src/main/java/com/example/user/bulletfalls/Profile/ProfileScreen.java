package com.example.user.bulletfalls.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.BeastRaisers.Linear;
import com.example.user.bulletfalls.Profile.Collection.UserStatistics.FamilyCompletenessList;
import com.example.user.bulletfalls.R;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.List;

public class ProfileScreen extends AppCompatActivity {


    TextView userName;
    ImageView userPhoto;
    LinearLayout currienciesList;
    //LinearLayout familyStats;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilescreen);
        scrollView=findViewById(R.id.scrollviewfamilystats);
        userName=(TextView) findViewById(R.id.name);
        userPhoto=(ImageView)findViewById(R.id.photo);
        currienciesList=(LinearLayout) findViewById(R.id.list);
        //familyStats=(LinearLayout) findViewById(R.id.familyStats);
        UserProfile up=new UserProfile(this);
        userName.setText(up.getName());
        Glide.with(this).load(up.resource).into(userPhoto);
        userPhoto.invalidate();
        currenciesListLoader();
        addFamiliesStats();
    }

    private void addFamiliesStats(){
        //ScrollView scrollView= new ScrollView(this);

        FamilyCompletenessList fcl=new FamilyCompletenessList(this);

        //scrollView.addView(fcl);


        fcl.setBackgroundColor(Color.BLUE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fcl.load();
        }
        this.scrollView.addView(fcl);
    }

    private void currenciesListLoader()
    {
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f
        );

        LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParam.setMargins(5,10,10,5);
        //StockRepository sd= new StockRepository(this);
        UserProfile userProfile= new UserProfile(this);
        //List<MutablePair<Currency,Integer>> list = sd.getAll();
        List<MutablePair<Currency,Integer>> list = userProfile.getStock();

        for(MutablePair<Currency,Integer> mp : list)
        {
            LinearLayout ll= new LinearLayout(this);
            ImageView iv= new ImageView(this);
            TextView tv= new TextView(this);

            ll.setOrientation(LinearLayout.HORIZONTAL);
            iv.setImageResource(mp.left.getResource());
            tv.setText(mp.right+"");
            iv.setLayoutParams(param);
            tv.setLayoutParams(param);
            tv.setGravity(Gravity.CENTER_VERTICAL);
            ll.addView(iv);
            ll.addView(tv);
            ll.setLayoutParams(layoutParam);
            ll.setGravity(Gravity.CENTER_HORIZONTAL);
            currienciesList.addView(ll);
            ll.getLayoutParams().height=120;
        }
    }
}



















