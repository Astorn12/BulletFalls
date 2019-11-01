package com.example.user.bulletfalls.Activities.GameListActivity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.example.user.bulletfalls.Game.Strategies.GameSketch;
import com.example.user.bulletfalls.Storage.Sets.GameSet;

import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    List<GameSketch> games;
    Context context;

    public ExpandableListAdapter(Context context) {
        GameSet gameSet= new GameSet();
        this.games=gameSet.getAll();
        this.context=context;
    }

    @Override
    public int getGroupCount() {
        return this.games.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.games.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.games.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return this.games.get(groupPosition).getHeaderView(this.context);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return this.games.get(groupPosition).getExpandableView(this.context);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
