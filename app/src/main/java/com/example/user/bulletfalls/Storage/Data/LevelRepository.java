package com.example.user.bulletfalls.Storage.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.user.bulletfalls.Storage.DatabaseAdministrator;
import com.example.user.bulletfalls.Profile.Level;

import java.util.LinkedList;
import java.util.List;

public class LevelRepository extends Repository<Level> {

    public LevelRepository(Context context) {
        super(context);
    }


    @Override
    public Level getById(int n) {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        Cursor cursor=da.getById(n,getTableName());
        cursor.moveToFirst();
        return new Level(cursor.getInt(2),cursor.getInt(3));
    }

    @Override
    public void update(Level level) {
    DatabaseAdministrator da= new DatabaseAdministrator(context);
    ContentValues cv= new ContentValues();
    cv.put("requiredXp",level.getRequiredXp());
    da.update(getTableName(),"levelNr",level.getNumber()+"",cv);
    }

    @Override
    public void remove(Level level) {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        da.remove(getTableName(),"levelNr",level.getNumber()+"");
    }

    @Override
    public void add(Level level) {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        ContentValues content= new ContentValues();
        content.put("levelNr",level.getNumber());
        content.put("requiredXp",level.getRequiredXp());
        da.add(content,getTableName());
    }

    @Override
    public Level createFromCursor(Cursor cursor) {
        return new Level(cursor.getInt(2),cursor.getInt(3));
    }


    public Level getByLevel(int level)
    {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        Cursor cursor=da.getCursor(getTableName(),"levelNr",level+"");
        cursor.moveToFirst();
        return new Level(cursor.getInt(1),cursor.getInt(2));
    }


    public int getId(int leverNr)
    {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
       return da.getId(getTableName(),"levelNr",leverNr+"");
    }

    public Level getNextLevel(int lev)
    {
        return this.getByLevel(lev+1);
    }

    public Level getNextLevel(Level lev)
    {
        return this.getByLevel(lev.getNumber()+1);
    }

    @Override
    public String getTableName() {
        return "Levels";
    }


}
