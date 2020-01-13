package com.example.user.bulletfalls.OveralStatisticsAndCollectionsMissions;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletMoveStrategyPackage.Linear;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Storage.Data.OveralStatisticsRepository;

public class MissionsActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missions);

        linearLayout= findViewById(R.id.missionscontainer);
        GradientDrawable gradientDrawable= new GradientDrawable();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            gradientDrawable.setColors(new int[]{Color.rgb(20, 180, 142),Color.rgb(8,109,119)});

            linearLayout.setBackground(gradientDrawable);
        }

        loadMissions(linearLayout);




    }

    private void loadMissions(LinearLayout linearLayout) {
       MissionsStore missionStore= MissionsStore.getInstance();

        for(Mission mission:missionStore.getAllMissionList()){
            linearLayout.addView(mission.getVizualization(this));

        }
    }
}
