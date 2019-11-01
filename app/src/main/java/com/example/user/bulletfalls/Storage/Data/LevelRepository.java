package com.example.user.bulletfalls.Storage.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.user.bulletfalls.Storage.DatabaseAdministrator;
import com.example.user.bulletfalls.Profile.Level;

import java.util.LinkedList;
import java.util.List;

public class LevelRepository implements Repository<Level> {
    Context context;
    public LevelRepository(Context context)
    {
        this.context=context;
    }
    @Override
    public List<Level> getAll() {
        List<Level> levelList= new LinkedList<>();
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        Cursor cursor = da.getAll(null,"Levels");
        cursor.moveToFirst();
        if(cursor.getCount()>0)
        {
            do {
                Level level= new Level(cursor.getInt(2),cursor.getInt(3));
                levelList.add(level);

            }while(cursor.moveToNext());
        }
        return levelList;
    }

    @Override
    public Level getById(int n) {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        Cursor cursor=da.getById(n,"Levels");
        cursor.moveToFirst();
        return new Level(cursor.getInt(2),cursor.getInt(3));
    }

    @Override
    public void update(Level level) {
    DatabaseAdministrator da= new DatabaseAdministrator(context);
    ContentValues cv= new ContentValues();
    cv.put("requiredXp",level.getRequiredXp());
    da.update("Levels","levelNr",level.getNumber()+"",cv);
    }

    @Override
    public void remove(Level level) {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        da.remove("Levels","levelNr",level.getNumber()+"");
    }

    @Override
    public void add(Level level) {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        ContentValues content= new ContentValues();
        content.put("levelNr",level.getNumber());
        content.put("requiredXp",level.getRequiredXp());
        da.add(content,"Levels");
    }

    public Level getByLevel(int level)
    {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        Cursor cursor=da.getCursor("Levels","levelNr",level+"");
        cursor.moveToFirst();
        return new Level(cursor.getInt(1),cursor.getInt(2));
    }


    public int getId(int leverNr)
    {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
       return da.getId("Levels","levelNr",leverNr+"");
    }

    public Level getNextLevel(int lev)
    {
        return this.getByLevel(lev+1);
    }

    public Level getNextLevel(Level lev)
    {
        return this.getByLevel(lev.getNumber()+1);
    }


}
