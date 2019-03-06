package com.example.user.bulletfalls;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.bulletfalls.Activities.MainActivity;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView scoreLabel= (TextView) findViewById(R.id.scoreLabel);
        TextView highScoreLabel= (TextView) findViewById(R.id.highScoreLabel);

        int score=getIntent().getIntExtra("SCORE",0);
        scoreLabel.setText(score+"");
        SharedPreferences settings= getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore=settings.getInt("HIGH_SCORE",0);
        if(score>highScore)
        {
            highScoreLabel.setText("High Score : "+score);
            //Save
            SharedPreferences.Editor editor=settings.edit();
            editor.putInt("HIGH_SCORE",score);
            editor.commit();
        }
        else
        {
            highScoreLabel.setText("High Score :"+highScore);
        }

        Button button= (Button) findViewById(R.id.tryAgainButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryAgain();
            }
        });
    }
    public void tryAgain()
    {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
