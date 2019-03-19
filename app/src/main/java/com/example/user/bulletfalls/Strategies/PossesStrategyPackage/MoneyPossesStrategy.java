package com.example.user.bulletfalls.Strategies.PossesStrategyPackage;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Database.DAO.CurrencyDao;
import com.example.user.bulletfalls.Database.DAO.ProfileDao;
import com.example.user.bulletfalls.ProfileActivity.Currency;
import com.example.user.bulletfalls.ProfileActivity.UserProfile;
import com.example.user.bulletfalls.Strategies.Par;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@JsonTypeName("moneypossesstrategy")
public class MoneyPossesStrategy implements PossesStrategy {


    List<MoneyNeed> moneyNeeds;
    int chosenNeed;
    //List<Currency> currencyList;
    public MoneyPossesStrategy(List<MoneyNeed> moneyNeeds)
    {

        this.moneyNeeds=moneyNeeds;
    }

    public MoneyPossesStrategy(String currencyName, int amount)
    {
        this();
        this.moneyNeeds.add(new MoneyNeed(Arrays.asList(new Par<Currency,Integer>(new Currency(currencyName),amount))));
        int x=9;
    }


    public MoneyPossesStrategy()
    {
        this.moneyNeeds= new LinkedList<>();
    }

    @Override
    public boolean tryToPosses(UserProfile userProfile) {
        //return userProfile.pay(new Currency(this.currencyName,0),this.amount);
        chosenNeed=MoneyNeedIndex.getInstance().index;
        return userProfile.pay(moneyNeeds.get(chosenNeed));
    }
    public void choseNeed(int i)
    {
        if(i<moneyNeeds.size()) {
            this.chosenNeed = i;
        }
    }

    @Override
    public void setPossesFotter(LinearLayout linearLayout, Context context) {

        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setBackgroundColor(Color.BLUE);
        if(!MoneyNeedIndex.getInstance().active) {
            for (MoneyNeed mn : this.moneyNeeds) {
                if (moneyNeeds.indexOf(mn) != 0) {
                    TextView minus = new TextView(context);
                    minus.setText("/");
                    linearLayout.addView(minus);
                }
                linearLayout.addView(moneyNeedToLayout(mn, context));
            }
        }
        else
        {
            linearLayout.addView(moneyNeedToLayout(this.moneyNeeds.get(MoneyNeedIndex.getInstance().index), context));
            MoneyNeedIndex.getInstance().off();

        }

    }


    private LinearLayout moneyNeedToLayout(MoneyNeed moneyNeed,Context context)
    {
        LinearLayout needLayout= new LinearLayout(context);

        needLayout.setOrientation(LinearLayout.VERTICAL);


        for(Par<Currency,Integer> p:moneyNeed.getNeed().getList())
        {
            if(moneyNeed.getNeed().getList().indexOf(p)!=0)
            {
                TextView plus= new TextView(context);
                plus.setText("+");
                needLayout.addView(plus);
                plus.setGravity(Gravity.CENTER);
            }
            needLayout.addView(moneyCurrencyIntToLayout(p.getLeft(),p.getRight(),context));
            needLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT,1));
        }

        needLayout.setGravity(Gravity.CENTER);
        return needLayout;
    }

    private LinearLayout moneyCurrencyIntToLayout(Currency currencyy,int amount,Context context)
    {
        ProfileDao profileDao= new ProfileDao(context);
        UserProfile userProfile=profileDao.getById(0);

        ImageView currencyImage= new ImageView(context);
        CurrencyDao cd= new CurrencyDao(context);
        Currency currency=cd.getCurrency(currencyy.getName());
        currencyImage.setImageResource(currency.getResource());
        TextView cost= new TextView(context);
        cost.setText(amount+"");

        CurrencyDao currencyDao= new CurrencyDao(context);

        if(userProfile.hasMoney(currencyDao.getCurrency(currency.getName()),amount))
        {
            cost.setTextColor(Color.GREEN);
        }
        else
        {
            cost.setTextColor(Color.RED);
        }
        LinearLayout footer= new LinearLayout(context);
        footer.setOrientation(LinearLayout.HORIZONTAL);
        footer.setGravity(Gravity.CENTER_HORIZONTAL);
        footer.setGravity(Gravity.CENTER_VERTICAL);
        footer.addView(currencyImage);
        //currencyImage.getLayoutParams().width=100;
        currencyImage.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,0.5f));
        currencyImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        currencyImage.setAdjustViewBounds(true);
        cost.setGravity(Gravity.CENTER_VERTICAL);
        footer.addView(cost);
        cost.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,1));
        footer.setBackgroundColor(Color.WHITE);

        footer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,1));

        return footer;

    }

    public List<MoneyNeed> getMoneyNeeds() {
        return moneyNeeds;
    }

    public void setMoneyNeeds(List<MoneyNeed> moneyNeeds) {
        this.moneyNeeds = moneyNeeds;
    }

    public int getChosenNeed() {
        return chosenNeed;
    }

    public void setChosenNeed(int chosenNeed) {
        this.chosenNeed = chosenNeed;
    }
}
