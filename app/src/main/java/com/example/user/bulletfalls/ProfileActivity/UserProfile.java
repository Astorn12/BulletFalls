package com.example.user.bulletfalls.ProfileActivity;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.bulletfalls.Database.Data.StockRepository;
import com.example.user.bulletfalls.Specyfications.AbilitySpecyfication;
import com.example.user.bulletfalls.Objects.Bullet;
import com.example.user.bulletfalls.Database.Data.CurrencyEnum;
import com.example.user.bulletfalls.Database.Data.LevelRepository;
import com.example.user.bulletfalls.Database.Data.ProfileRepository;
import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.GameSupporters.GiveBountyPackage.Bounty;
import com.example.user.bulletfalls.GameSupporters.MediumTasks.ArchivContainer;
import com.example.user.bulletfalls.Objects.Hero;
import com.example.user.bulletfalls.Sets.AbilitySet;
import com.example.user.bulletfalls.Sets.BulletSet;
import com.example.user.bulletfalls.Sets.HeroesSet;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.MoneyNeed;
import com.example.user.bulletfalls.Strategies.Par;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class UserProfile {

    String name;
    int resource;
    Level level;
    List<MutablePair<Currency,Integer>> stock;
    Context context;
    int exp;


    public UserProfile(String name, int resource, List<MutablePair<Currency,Integer>> stock, int exp, Context context)
    {
        this.name=name;
        this.resource=resource;
        this.stock=stock;
        this.context=context;
        this.exp=exp;
    }

    public UserProfile(Context context)
    {
        this.stock= new LinkedList<>();
        load(context);
        this.context=context;
    }

    public UserProfile() {

    }

    public void load(Context context)
    {
        ProfileRepository fd= new ProfileRepository(context);
        UserProfile temporary=fd.getById(1);
        this.name=temporary.getName();
        this.level=temporary.getLevel();
        this.resource=temporary.getResource();
        this.stock=temporary.getStock();
        this.exp=temporary.getExp();
    }

    private void save()
    {

    }
    public void save(String path)
    {

    }


    public boolean hasMoney(Currency currency, int amount )//thi method if for checking,it does not participate in buying
    {
        boolean flag=false;
        for(MutablePair<Currency,Integer> c:stock)
        {
            if(currency.getName().equals(c.left.name))
            {
                if(c.right>=amount) {
                    flag = true;
                    break;
                }
                }
        }
        return flag;
    }
    public boolean hasMoney(MutablePair<Currency,Integer>pair)
    {
        return hasMoney(pair.getLeft(),pair.getRight());
    }
    public boolean hasMoney(Par<Currency,Integer>pair)
    {
        return hasMoney(pair.getLeft(),pair.getRight());
    }

    public boolean hasMoney(MoneyNeed moneyNeed)
    {
        for(int i=0;i<moneyNeed.getNeed().size();i++)
        {
            if(hasMoney(moneyNeed.getNeed().get(i)))
            {

            }
            else
            {

                return false;
            }
        }
        return true;
    }



    public boolean pay(Currency currency, int amount)
    {
        return modyfyStock(currency,amount*(-1));
    }

    public boolean pay(MoneyNeed moneyNeed)
    {
        return modyfyStock(moneyNeed);
    }

    public boolean earn(Currency currency, int amount)
    {
       return modyfyStock(currency,amount);
    }

    private  boolean modyfyStock(Currency currency, int amount)
    {
        boolean flag=false;
        //MutablePair<Currency,Integer> pointer=null;
        for(MutablePair<Currency,Integer> c:stock)
        {
            if(currency.getName().equals(c.left.name))
            {
                    if(amount*(-1)<=c.getRight()) {
                        c.setRight(c.getRight() + amount);
                        flag = true;
                        break;
                    }
            }
        }
       // if(pointer!=null)
        //{
            //pointer.set  -=amount;
        //}
        save();
        save(context);
        return flag;
    }
    private boolean modyfyStock(MoneyNeed moneyNeed)
    {
        boolean  flag;
        List<MutablePair<Currency,Integer>> stockClone=new ArchivContainer<Currency>(stock).cloneList();

            for(Par<Currency,Integer> pair :moneyNeed.getNeed().getList())
            {

                if(modyfyStock(pair.getLeft(),pair.getRight()*(-1)))
                {

                }
                else
                {
                    this.stock=stockClone;
                    return false;
                }
            }
        save();
        save(context);
        return true;
    }
////////////////////////////GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public List<MutablePair<Currency, Integer>> getStock() {
        return stock;
    }

    public void setStock(List<MutablePair<Currency, Integer>> stock) {
        this.stock = stock;

    }


    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    private void save(Context context)
    {
        ProfileRepository pd= new ProfileRepository(context);
        pd.update(this);
        StockRepository sd= new StockRepository(context);
        sd.update(this.stock);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
    public void gainLevel()
    {
        this.level.number+=1;

        save(context);
    }

    public boolean buy(Hero hero)
    {
        if(hero.getPossesStrategy().tryToPosses(this))
        {
            hero.setPermission(Permission.YES);
            HeroesSet.givePermission(hero,context);
            return true;
        }
        return false;
    }
    public boolean buy(Bullet bullet)
    {
        if(bullet.getPossesStrategy().tryToPosses(this))
        {
            bullet.setPermission(Permission.YES);

            BulletSet.givePermission(bullet,context);
            return true;
        }
        return false;
    }
    public boolean buy(AbilitySpecyfication abilitySpecyfication)
    {
        if(abilitySpecyfication.getPossesStrategy().tryToPosses(this))
        {
            abilitySpecyfication.setPermission(Permission.YES);
            AbilitySet.getInstance().givePermission(abilitySpecyfication,context);
            return true;
        }
        return false;
    }

    public int getExp() {
        return exp;
    }
    public void gainExp(int e)
    {

        while(this.exp+e>=level.getRequiredXp())
        {
            e=this.exp+e-level.getRequiredXp();
            LevelRepository levelRepository = new LevelRepository(context);
            this.level= levelRepository.getNextLevel(this.level);
            this.exp=0;
        }


        this.exp+=e;


      save(context);
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
    public void addWallet(LinearLayout wallet,Context cont) {
        //LinearLayout wallet= (LinearLayout) findViewById(R.id.wallet);


        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        param.setMargins(5, 5, 5, 5);

        LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1
        );
        //layoutParam.setMargins(5,10,10,5);

        //UserProfile userProfile = new UserProfile(cont);

        List<MutablePair<Currency, Integer>> list = getStock();
        Collections.reverse(list);
        for (MutablePair<Currency, Integer> mp : list) {

            LinearLayout ll = new LinearLayout(cont);
            ImageView iv = new ImageView(cont);
            TextView tv = new TextView(cont);

            ll.setOrientation(LinearLayout.HORIZONTAL);

            Glide.with(this.getContext()).load(mp.left.getResource()).into(iv);
            tv.setText(mp.right + "");
            iv.setLayoutParams(param);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setAdjustViewBounds(true);
            tv.setLayoutParams(param);
            tv.setGravity(Gravity.CENTER_VERTICAL);

            ll.addView(iv);
            ll.addView(tv);
            ll.setLayoutParams(layoutParam);
            ll.setGravity(Gravity.LEFT);
            wallet.addView(ll);
        }
    }

    public void makeOfBounty(Bounty bounty)
    {
        gainExp(bounty.getExp());
        earn(new Currency(CurrencyEnum.MysteryCoin.toString()),bounty.getMoney());
        ArchivContainer<Currency> ar=bounty.getItemsList();

        for(int i=0;i<ar.size();i++)
        {
            earn(ar.get(i).getLeft(),ar.get(i).getRight());
        }



    }
}
