package com.example.user.bulletfalls.Activities.GameListActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Game.Strategies.GameSketch;
import com.example.user.bulletfalls.R;

public class GamesList extends AppCompatActivity {


    ExpandableListAdapter listAdapter;
    ExpandableListView expandableListView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_list);
        expandableListView=(ExpandableListView) findViewById(R.id.gameList);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return ! listAdapter.games.get(groupPosition).canPlay();

            }
        });

        GamesList active=this;
        expandableListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                GameSketch chosenGame=((GameSketch)adapterView.getAdapter().getItem(i));
                if(chosenGame.canPlay()) {


                    String gameName = ((GameSketch) adapterView.getAdapter().getItem(i)).getNameOfTheGame();
                    Intent intent = new Intent(active, Game.class);
                    intent.putExtra("gameSketchName", gameName);
                    active.startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        this.listAdapter= new ExpandableListAdapter(this);
        expandableListView.setAdapter(listAdapter);

        fillGameList();
    }

    private void fillGameList() {


    }


}
