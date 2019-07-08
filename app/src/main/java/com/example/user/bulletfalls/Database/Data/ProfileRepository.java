package com.example.user.bulletfalls.Database.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.user.bulletfalls.Database.DatabaseAdministrator;
import com.example.user.bulletfalls.ProfileActivity.UserProfile;

import java.util.List;

public class ProfileRepository implements Repository<UserProfile> {
    Context context;
    public ProfileRepository(Context context)
    {
        this.context=context;
    }
    @Override
    public List<UserProfile> getAll() {
        return null;
    }
    public boolean hasProfile()
    {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        if(da.getCursor("Profile","id","1").getCount()==0)
        {
            return false;
        }
        else return true;
    }

    @Override
    public UserProfile getById(int  i) {
        DatabaseAdministrator da= new DatabaseAdministrator(context);

        Cursor cursor=da.getAll(null,"Profile");

        cursor.moveToFirst();
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
    public void update(UserProfile userProfile) {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        ContentValues content= new ContentValues();
        content.put("name",userProfile.getName());
        content.put("resource",userProfile.getResource());
        LevelRepository ld= new LevelRepository(context);
        content.put("levelId",ld.getId(userProfile.getLevel().getNumber()));
        content.put("exp",userProfile.getExp());
        da.update("Profile","id",1,content);
    }

    @Override
    public void remove(UserProfile userProfile) {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        da.remove("profile","name",userProfile.getName());
    }
    public void remove(int id)
    {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        da.remove("profile","id",id);
    }
    public void remove(String name)
    {
        DatabaseAdministrator da= new DatabaseAdministrator(context);
        da.remove("profile","name",name);
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
        da.add(content,"profile");
    }
}
