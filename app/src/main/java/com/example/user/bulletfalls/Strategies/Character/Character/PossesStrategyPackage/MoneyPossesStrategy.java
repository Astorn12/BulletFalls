package com.example.user.bulletfalls.Strategies.Character.Character.PossesStrategyPackage;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Database.DAO.CurrencyDao;
import com.example.user.bulletfalls.Database.DAO.ProfileDao;
import com.example.user.bulletfalls.ProfileActivity.Currency;
import com.example.user.bulletfalls.ProfileActivity.UserProfile;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

@JsonTypeName("moneypossesstrategy")
public class MoneyPossesStrategy implements PossesStrategy {

    String currencyName;
    int amount;
    //List<Currency> currencyList;
    public MoneyPossesStrategy(String currencyName, int amount)
    {
        this.currencyName=currencyName;
        this.amount=amount;
    }

    private MoneyPossesStrategy()
    {}

    @Override
    public boolean tryToPosses(UserProfile userProfile) {
        return userProfile.pay(new Currency(this.currencyName,0),this.amount);
    }

    @Override
    public void setPossesFotter(LinearLayout linearLayout, Context context) {
        ProfileDao profileDao= new ProfileDao(context);
        UserProfile userProfile=profileDao.getById(0);

        ImageView currencyImage= new ImageView(context);
        CurrencyDao cd= new CurrencyDao(context);
        Currency currency=cd.getCurrency(this.currencyName);
        currencyImage.setImageResource(currency.getResource());
        TextView cost= new TextView(context);
        cost.setText(this.amount+"");

        CurrencyDao currencyDao= new CurrencyDao(context);

        if(userProfile.hasMoney(currencyDao.getCurrency(this.currencyName),this.amount))
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
        currencyImage.getLayoutParams().width=100;


        cost.setGravity(Gravity.CENTER_VERTICAL);
        footer.addView(cost);


        linearLayout.addView(footer);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setBackgroundColor(Color.BLUE);
        footer.setBackgroundColor(Color.WHITE);

        footer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,2));
        footer.getLayoutParams().height=300;


    }


    public String getCurrency() {
        return currencyName;
    }

    public void setCurrency(String currencyName) {
        this.currencyName = currencyName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
