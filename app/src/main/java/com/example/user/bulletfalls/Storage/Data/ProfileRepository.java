package com.example.user.bulletfalls.Storage.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.user.bulletfalls.Storage.DatabaseAdministrator;
import com.example.user.bulletfalls.Profile.UserProfile;

import java.util.List;

public class ProfileRepository extends Repository<UserProfile> {

    public ProfileRepository(Context context)
    {
        super(context);
    }

    public boolean hasProfile()
    {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        if(da.getCursor(getTableName(),"id","1").getCount()==0)
        {
            return false;
        }
        else return true;
    }

    @Override
    public UserProfile getById(int  i) {
        DatabaseAdministrator da= new DatabaseAdministrator(context);

        Cursor cursor=da.getAll(null,getTableName());
        cursor.moveToFirst();

        return createFromCursor(cursor);
    }

    @Override
    public void update(UserProfile userProfile) {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        ContentValues content= new ContentValues();
        content.put("name",userProfile.getName());
        content.put("resource",userProfile.getResource());
        LevelRepository ld= new LevelRepository(context);
        content.put("levelId",ld.getId(userProfile.getLevel().getNumber()));
        content.put("exp",userProfile.getExp());
        da.update(getTableName(),"id",1,content);
    }

    @Override
    public void remove(UserProfile userProfile) {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        da.remove(getTableName(),"name",userProfile.getName());
    }
    public void remove(int id)
    {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        da.remove(getTableName(),"id",id);
    }
    public void remove(String name)
    {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        da.remove(getTableName(),"name",name);
    }

    @Override
    public void add(UserProfile userProfile) {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        ContentValues content= new ContentValues();
        content.put("name",userProfile.getName());
        content.put("resource",userProfile.getResource());
        LevelRepository ld= new LevelRepository(context);

        content.put("levelId",ld.getId(userProfile.getLevel().getNumber()));
        content.put("exp",userProfile.getExp());
        da.add(content,getTableName());
    }

    @Override
    public UserProfile createFromCursor(Cursor cursor) {
        UserProfile userProfile= new UserProfile();
        userProfile.setName(cursor.getString(1));
        userProfile.setResource(cursor.getInt(2));
        userProfile.setExp(cursor.getInt(3));

        LevelRepository ld= new LevelRepository(context);

        userProfile.setLevel(ld.getByLevel(cursor.getInt(4)));
        StockRepository sd= new StockRepository(context);
        userProfile.setStock(sd.getAll());
        userProfile.setContext(context);
        return userProfile;
    }

    @Override
    public String getTableName() {
        return "Profile";
    }
}
