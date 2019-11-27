package com.example.user.bulletfalls.Game.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Game.Elements.Weapon.Weapon;
import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;
import com.example.user.bulletfalls.Game.Management.GameController;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.Family;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamiliesContainer;
import com.example.user.bulletfalls.Game.Elements.Ability.Ability;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.example.user.bulletfalls.GlobalUsage.Supporters.Dimension;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Game.Elements.Helper.Dynamic;
import com.example.user.bulletfalls.Game.Elements.Hero.HeroSpecyfication;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Thread.sleep;

public class Game extends AppCompatActivity {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<Runnable>(128);

    public TextView scoreLabel;
   // private LinearLayout startLabel;

    //Size
    public int screenWidth;
    public int screenHeight;

    //Initialize Class

    private Handler handler= new Handler();
    private Timer timer= new Timer();

    //Status Check

    private boolean action_flg=false;
    private boolean start_flg=false;
    FrameLayout frame;
    LinearLayout abilitiesBar;
    private GameController controller;

    private RelativeLayout beforeGameStatistics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getAndSetScreenSize();
        scoreLabel= (TextView)  findViewById(R.id.scoreLabel);
        //startLabel=(LinearLayout) findViewById(R.id.startLabel);
        Intent intent= getIntent();
        String  name=intent.getStringExtra("gameSketchName");
        controller= new GameController(this,name);
        frame=controller.getGameFrame();
        abilitiesBar=(LinearLayout)findViewById(R.id.abilities);


        showBeforeGameStatistics();
    }

    private void showBeforeGameStatistics(){

        HeroSpecyfication pointer= this.controller.getHero().getSpecyfication();
        FamiliesContainer familiesContainer = new FamiliesContainer();

        LayoutInflater inflater = LayoutInflater.from(this);
        this.beforeGameStatistics = (RelativeLayout) inflater.inflate(R.layout.beforegameinfo, null, false);
        this.beforeGameStatistics.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        this.frame.addView(this.beforeGameStatistics);

        LinearLayout abilities=(LinearLayout)findViewById(R.id.BGIabilitylist);
        LinearLayout heroDescription=(LinearLayout) findViewById(R.id.BGIherodescription);

        LinearLayout superPower=(LinearLayout)findViewById(R.id.BGIsuperpower);
        LinearLayout boostDescription=(LinearLayout)findViewById(R.id.BGIboostdescription);
        ImageView imageView= (ImageView) findViewById(R.id.heroImage);
        imageView.setImageResource(pointer.getImage());



        abilities.addView(this.controller.getHero().getAbilities().getAbilitiesBar(this));

        TextView bulletName= (TextView)findViewById(R.id.BGIBulletText);
        bulletName.setText(pointer.getBulletSpecyfication().getName());
        ImageView bullet= (ImageView)findViewById(R.id.BGIBulletImage);
        bullet.setImageResource(pointer.getBulletSpecyfication().getImage());
        heroDescription.addView(pointer.getHeroInfo(this));



        for(FamilyName familyName : pointer.getFamilyNames())
        {
            Family family = familiesContainer.getGroup(familyName);
            boostDescription.addView(family.getGroupField(this));
        }

    }

    public void setX(Dynamic vm, int x)
    {
        try {
        vm.setX(x);
    }
    catch(Exception ex)
    {

    }
    }

    public boolean onTouchEvent(MotionEvent me)
    {
        this.frame.removeView(this.beforeGameStatistics);
        if(start_flg==false)
        {
            start_flg=true;
            //startLabel.setVisibility(View.GONE);
            controller.start();
        }else
        {
            if(me.getAction()==MotionEvent.ACTION_DOWN)
            {
                action_flg=true;
                controller.pressON();
            }
            else if(me.getAction()==MotionEvent.ACTION_UP)
            {
                action_flg=false;
                controller.pressOFF();
            }
        }
        return true;
    }

    private void getAndSetScreenSize()
    {
        WindowManager wm= getWindowManager();
        Display disp= wm.getDefaultDisplay();
        Point size= new Point();
        disp.getSize(size);
        screenWidth=size.x;
        screenHeight=size.y;
    }

    /**Methods which are connected with fact that imageViews could be modified only by acticity where they have been puted*/
    public void addView(final Dynamic dynamic) {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                frame.addView(dynamic);
                com.example.user.bulletfalls.GlobalUsage.Supporters.Dimension d= dynamic.getDimension();
                dynamic.getLayoutParams().height= d.getHeight();
                dynamic.getLayoutParams().width= d.getWidth();
            }
        });
    }

    public void addWeapon(final Weapon weapon, Dimension d) {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                frame.addView(weapon);
                weapon.getLayoutParams().height= d.getHeight();
                weapon.getLayoutParams().width= d.getWidth();
            }
        });

    }
    public void addLifeInformation(final TextView textView)
    {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                frame.addView(textView);
            }
        });
    }
    public void addViewCharacter(final Character viewCharacter) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // Stuff that updates the UI
                frame.addView(viewCharacter);
                viewCharacter.getLayoutParams().height= viewCharacter.getDimension().getHeight();
                viewCharacter.getLayoutParams().width= viewCharacter.getDimension().getWidth();
                TextView textView= new TextView(getApplicationContext());

                frame.addView(textView);
                textView.setX(viewCharacter.getX()-30);
                textView.setY(viewCharacter.getY()+30);
                textView.setText("pietnascie");
            }
        });
    }
    public void removeObject(final Dynamic dynamic)
    {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                dynamic.setVisibility(View.GONE);
                frame.removeView(dynamic);
            }
        });

    }

    public void removeObject(final ImageView dynamic)
    {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                dynamic.setVisibility(View.GONE);
                frame.removeView(dynamic);
            }
        });

    }

    public void setScoreLabel(String text)
    {try {
        scoreLabel.setText("Score: "+text);
        scoreLabel.setTextColor(Color.WHITE);
    }
    catch(Exception e)
    {

    }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();

        // controller.stop();
    }
    public void removeLifeText(final TextView textView)
    {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                textView.setVisibility(View.GONE);
                frame.removeView(textView);
            }
        });
    }

    public void moveViewElement(final Dynamic dynamic, final int x, final int y)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                dynamic.setX(dynamic.getX()+x);
                dynamic.setY(dynamic.getY()+y);
            }
        });

    }

    public void setViewElement(final View dynamic, final int x, final int y)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                dynamic.setX(x);
                dynamic.setY(y);
            }
        });
    }

    public void moveHero(final Hero hero, final int x, final int y)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                hero.setX(hero.getX()+x);
                hero.setY(hero.getY()+y);
                for (Weapon w:hero.getWeaponList()){
                    w.setX(w.getX()+x);
                    w.setY(w.getY()+y);
                }
            }
        });
    }
    public void setHero(final Hero hero, final int x, final int y)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (Weapon w:hero.getWeaponList()){
                    w.setX(w.getX()+x-hero.getX());
                    w.setY(w.getY()+y-hero.getY());
                }
                hero.setX(x);
                hero.setY(y);
            }
        });
    }


    public void uploadLifeView(final Character character, final TextView textLife)
    {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                if (textLife != null) {
                    // Stuff that updates the UI
                    textLife.setText(character.getLife() + "");
                    textLife.setTextColor(Color.WHITE);
                    textLife.setTextSize(14);
                    textLife.measure(0, 0);
                    //  float x=character.getX();
                    //  float y=character.getY();


                    textLife.setX(character.getX() + character.getDimension().getWidth() / 2 - textLife.getMeasuredWidth() / 2);
                    textLife.setY(character.getY() - textLife.getMeasuredHeight());
                    //     float a=textLife.getX();
                    //     float b=textLife.getY();
                }
            }
        });
    }
    public void setViewImageMatrix(final Matrix matrix,final Dynamic dynamic)
    {
        final Game game=this;
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                game.removeObject(dynamic);
                dynamic.setImageMatrix(matrix);
                game.addView(dynamic);
            }
        });
    }
    public void setImageBitmap(final Bitmap bMapRotate,final ImageView imageView)
    {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                imageView.setImageBitmap(bMapRotate);
            }
        });
    }

    public void rotateImage(final float degree, final ImageView imageView)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.setRotation((imageView.getRotation()+degree)%360);

            }
        });

    }

    public void setAbilitiesBar(List<Ability> abilities)
    {

        for(Ability ability:abilities)
        {
            abilitiesBar.addView(ability);
            ability.getLayoutParams().width=abilitiesBar.getWidth()/abilities.size();

            //abilitiesBar.setGravity(Gravity.BOTTOM);
        }
    }

    public void setY(Dynamic vm, int y) {
        try {
            vm.setY(y);
        }
        catch(Exception ex)
        {

        }
    }

    public void setPoint(final Dynamic vm, final Point point)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                vm.setX(point.x);
                vm.setY(point.y);
            }
        });

    }

    public void setPoint(final ImageView vm, final Point point)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                vm.setX(point.x);
                vm.setY(point.y);
            }
        });

    }
    public void changeResource(final Dynamic dynamic, final int resource)
    {
        final Game pointer=this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Glide.with(pointer).load(resource).into(viewElement);
               dynamic.setImageResource(resource);
            }
        });
    }

    public void rechangeImageAfterAnimation(final Dynamic dynamic, final int resource)
    {
        final Game pointer=this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dynamic.setImageResource(resource);
            }
        });
    }
    public void changeResourceForAnimation(final Dynamic dynamic, final int resource, final int oldResourse)
    {
        final Game pointer=this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                 //Glide
                        //.with(pointer)
                       // .load(oldResourse)
                       // .transition(GenericTransitionOptions.with(resource))
                      //  .into(viewElement);
                dynamic.setImageResource(resource);
            }
        });
    }
    public Drawable changeForegroundForAnimation(final Character dynamic, final int resource)
    {
        final Drawable[] drawable = new Drawable[1];
        runOnUiThread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {

                //Glide
                //.with(pointer)
                // .load(oldResourse)
                // .transition(GenericTransitionOptions.with(resource))
                //  .into(viewElement);

                   // dynamic.setForeground(getResources().getDrawable(resource));

                    dynamic.setForeground(getDrawable(resource));



            }
        });
        return drawable[0];
    }

    public Drawable startAnimation(final Character dynamic, final int resource)
    {
        final Drawable[] drawable = new Drawable[1];
        runOnUiThread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {

                //Glide
                //.with(pointer)
                // .load(oldResourse)
                // .transition(GenericTransitionOptions.with(resource))
                //  .into(viewElement);

                // dynamic.setForeground(getResources().getDrawable(resource));
                int oldId=dynamic.getImage();
                int w= dynamic.getLayoutParams().width;
                int h=dynamic.getLayoutParams().height;
                dynamic.setForeground(getDrawable(resource));
                drawable[0] =dynamic.getForeground();
                AnimationDrawable animation = (AnimationDrawable)dynamic.getForeground();

                dynamic.getLayoutParams().width=w;
                dynamic.getLayoutParams().height=h;

                animation.start();
                dynamic.checkIfAnimationDoner(animation,oldId);
            }
        });
        return drawable[0];
    }


    public void bigChangeResource(final Dynamic dynamic, final int resource)
    {
        runOnUiThread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                //viewElement.setImageResource(resource);
                final Drawable[] drawable = new Drawable[1];
                AsyncTask<Integer,Integer,Integer> asyncTask=new AsyncTask<Integer,Integer,Integer>() {
                    @Override
                    protected Integer doInBackground(Integer... params) {
                       drawable[0] = getResources().getDrawable(resource, getApplicationContext().getTheme());
                       bigChange(dynamic,drawable[0]);
                        return null;
                    }

                };
                asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
    }

    private void bigChange(final Dynamic dynamic, final Drawable drawable)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               // viewElement.setImageDrawable(drawable);
                dynamic.setImageDrawable(drawable);
            }
        });
    }
    public void startAnimation(final Point start,final AnimationDrawable animationDrawable)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    public GameController getController()// to jest tylne wejście ;)
    {
        return this.controller;
    }

    public void clearColorFilter(final ImageView imageView)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.clearColorFilter();
            }
        });
    }

    public EyeOnGame getEyeOnGame()
    {
        return new EyeOnGame(this.controller);
    }
    @Override
    public void onStop() {
        super.onStop();
       // super.onDestroy();
    }


    public void changeForeground(View view, int resource) {
        runOnUiThread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
                if(resource==0)view.setForeground(null);
                else view.setForeground(getResources().getDrawable(resource));
            }
        });
    }
}
