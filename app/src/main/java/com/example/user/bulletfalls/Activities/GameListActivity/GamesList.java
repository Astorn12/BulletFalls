package com.example.user.bulletfalls.Activities.GameListActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

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

        this.listAdapter= new ExpandableListAdapter(this);
        expandableListView.setAdapter(listAdapter);

        fillGameList();
    }

    private void fillGameList() {


    }


}
