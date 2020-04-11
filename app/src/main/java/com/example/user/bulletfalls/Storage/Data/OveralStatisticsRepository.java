package com.example.user.bulletfalls.Storage.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.user.bulletfalls.Missions.Requirements.OveralStatistick;
import com.example.user.bulletfalls.Storage.DatabaseAdministrator;

public class OveralStatisticsRepository extends Repository<OveralStatistick> {


    public OveralStatisticsRepository(Context context) {
        super(context);
    }



    @Override
    public OveralStatistick getById(int n) {

        DatabaseAdministrator da= new DatabaseAdministrator(context);
        Cursor cursor=da.getById(n,getTableName());
        cursor.moveToFirst();
        return createFromCursor(cursor);
    }
    public boolean exist(String name){
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        Cursor cursor=da.getCursor(getTableName(),"name",name);
       if(cursor.getCount()==0) return false;
       else
       {
           cursor.moveToFirst();
           //OveralStatistick overalStatistick=createFromCursor(cursor);
           return true;
       }

    }

    public OveralStatistick getByName(String name){
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        Cursor cursor=da.getCursor(getTableName(),"name",name);
        cursor.moveToFirst();
        return createFromCursor(cursor);
    }

    public void clear(){
        DatabaseAdministrator da= new DatabaseAdministrator(context);

    }

    @Override
    public void update(OveralStatistick overalStatistick) {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        ContentValues content= new ContentValues();

        content.put("amount",overalStatistick.getAmount());

        da.update(getTableName(),"name",overalStatistick.getName(),content);
    }

    @Override
    public void remove(OveralStatistick overalStatistick) {

    }

    @Override
    public void add(OveralStatistick overalStatistick) {
        DatabaseAdministrator db= new DatabaseAdministrator(context);
        ContentValues content= new ContentValues();
        content.put("name",overalStatistick.getName());
        content.put("amount",overalStatistick.getAmount());
        db.add(content,getTableName());
    }

    @Override
    public OveralStatistick createFromCursor(Cursor cursor) {
        return new OveralStatistick(cursor.getString(1), cursor.getInt(2));
    }

    public void actualize(String entityName,int n){
        OveralStatistick overalStatistick=getByName(entityName);
        overalStatistick.incrementAmount(n);
        update(overalStatistick);
    }
    public void actualize(String entityName){
        actualize(entityName,1);
    }

    @Override
    public String getTableName() {
        return "OverallStatistics";
    }


}
