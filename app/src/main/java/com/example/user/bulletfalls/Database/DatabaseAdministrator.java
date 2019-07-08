package com.example.user.bulletfalls.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.bulletfalls.Database.Data.CurrencyRepository;
import com.example.user.bulletfalls.Database.Data.LevelRepository;
import com.example.user.bulletfalls.Database.Data.ProfileRepository;
import com.example.user.bulletfalls.Database.Data.StockRepository;
import com.example.user.bulletfalls.ProfileActivity.Currency;
import com.example.user.bulletfalls.ProfileActivity.Level;
import com.example.user.bulletfalls.ProfileActivity.UserProfile;
import com.example.user.bulletfalls.R;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.Arrays;
import java.util.List;

public class DatabaseAdministrator extends SQLiteOpenHelper {

    Context context;
    public DatabaseAdministrator(Context context) {
        super(context, "profileDB.db", null, 1);
        this.context=context;


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating of table currencies
        db.execSQL("create table Currencies(" +
                "id integer primary key autoincrement," +
                "name text," +
                "resource integer);");
        //creating of  table stock
        db.execSQL("create table Stock(" +
                "id integer primary key autoincrement," +
                "amount integer," +
                "currencyId integer," +
                "foreign key (currencyId) references Currencies(id) );");
        db.execSQL("create table Levels(" +
                "id integer primary key autoincrement," +
                "levelNr integer," +
                "requiredXp integer);");
        /**creating of table profile*/
        db.execSQL("create table Profile(" +
                "id integer primary key autoincrement," +
                "name text," +
                "resource integer," +
                "exp integer," +
                "levelId integer," +
                "foreign key (levelId) references Levels(id));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);
    }

    private void profileBegin()
    {
        UserProfile userProfile= new UserProfile();
        userProfile.setResource(R.drawable.cartoonmy);
        userProfile.setName("Oskar");
        LevelRepository ld= new LevelRepository(context);
        Level level= ld.getByLevel(1);
        userProfile.setLevel(level);
        userProfile.setExp(0);
        ProfileRepository profileDao= new ProfileRepository(context);
        profileDao.add(userProfile);
    }

    public void actualization(Context context)
    {
        clearAll();//miejsce ustawienia sandboxa, zakomentowanie oznacza zapisywanie modyfikacji

        LevelRepository ld= new LevelRepository(context);
        List<Level> levels =Arrays.asList
                (new Level(1,10),
                        new Level(2,20),
                        new Level(3,40),
                        new Level(4,60),
                        new Level(5,100),
                        new Level(6,150),
                        new Level(7,400),
                        new Level(8,450),
                        new Level(9,500),
                        new Level(10,600),
                        new Level(11,700),
                        new Level(12,800),
                        new Level(13,900),
                        new Level(14,1500),
                        new Level(15,2000),
                        new Level(16,2200),
                        new Level(17,2700),
                        new Level(18,2900),
                        new Level(19,3000));

        for(Level l:levels)
        {
            if(getCursor("Levels","levelNr",l.getNumber()+"").getCount()==0)
            {
                ld.add(l);
            }
        }
        ProfileRepository profileDao= new ProfileRepository(context);

        //if(!profileDao.hasProfile())
        //{
            profileBegin();
       // }

        CurrencyRepository cd= new CurrencyRepository(context);
        Currency mysteryCoin= new Currency("Mystery Coin",R.drawable.mysterycoin);
        Currency conifer= new Currency("Conifer symbol",R.drawable.dippercaptree);
        //  cd.save(mysteryCoin);
         // cd.save(conifer);
          List<Currency> currencyList= Arrays.asList(mysteryCoin,conifer);
            for(Currency c : currencyList)
            {
                Cursor cursor=getCursor("Currencies","name",c.getName());
                int i=cursor.getCount();
                cursor.close();
                if(i==0)
                {
                    cd.add(c);
                }
            }


          StockRepository sd= new StockRepository(context);
        MutablePair<Currency,Integer> mysteryCoinStock=new MutablePair<Currency, Integer>(mysteryCoin,50);
        MutablePair<Currency,Integer> coniferStock=new MutablePair<Currency, Integer>(conifer,20);
         List<MutablePair<Currency,Integer>> stockList= Arrays.asList(mysteryCoinStock,coniferStock);
         for(MutablePair<Currency,Integer> p: stockList)
         {
             if(getCursor("Stock","currencyId",getId("Currencies","name",p.getLeft().getName())+"").getCount()==0)
             {
                 sd.add(p);
             }
         }

    }
    private void clearAll()
    {
        SQLiteDatabase db= getWritableDatabase();
       // db.delete("Profile",null,null);
        db.delete("Stock",null,null);
        db.delete("Currencies",null,null);
        db.delete("Levels",null,null);
        db.delete("Profile",null,null);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" +"Levels" + "'");
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" +"Profile" + "'");
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" +"Currencies" + "'");
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" +"Stock" + "'");
        db.close();
    }

    public void add(ContentValues contentValues,String tableName)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.insertOrThrow(tableName,null,contentValues);
        db.close();
    }

    public Cursor getAll(String[] columns,String tableName)
    {
        SQLiteDatabase db= getReadableDatabase();
        return db.query(tableName,columns,null,null,null,null,null);
    }

    public void remove(String table,String column,int id)
    {
        SQLiteDatabase db=getWritableDatabase();
        String[] arguments={""+id};
        db.delete(table,column+"=?",arguments);
        db.close();
    }
    public void remove(String table,String column,String name)
    {
        SQLiteDatabase db=getWritableDatabase();
        String[] arguments={""+name};
        db.delete(table,column+"=?",arguments);
        db.close();
    }

    public void update(String table,String column,String name,ContentValues content)
    {
        SQLiteDatabase db=getWritableDatabase();
        String args[]={name};
        db.update(table,content,column+"=?",args);
        db.close();
    }
    public void update(String table,String column,Integer id,ContentValues content)
    {
        SQLiteDatabase db=getWritableDatabase();
        String args[]={id+""};
        db.update(table,content,column+"=?",args);
        db.close();
    }

    public Cursor getCursor(String table,String column,String name)
    {
        SQLiteDatabase db= getReadableDatabase();
        return db.query(table,null,column+"=?",new String[]{name},null,null,null);
    }

    public int getId(String table, String column, String value)
    {
        SQLiteDatabase db= getReadableDatabase();
        String args[]={value};
        String columns[]={"id"};
        Cursor cursor= db.query(table,columns,column+"=?",args,null,null,null,null);
        cursor.moveToFirst();
        int i= cursor.getInt(0);
        db.close();
        return  i;
    }
    public Cursor getById(int id,String table)
    {
        SQLiteDatabase db= getWritableDatabase();
        String args[]={id+""} ;
        Cursor cursor= db.query(table,null,"id=?",args,null,null,null,null);
        return cursor;
    }

}
