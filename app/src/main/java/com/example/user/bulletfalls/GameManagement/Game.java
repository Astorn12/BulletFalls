package com.example.user.bulletfalls.GameManagement;

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
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.bulletfalls.ObjectsOfGame.AbilityView;
import com.example.user.bulletfalls.ObjectsOfGame.Bullet;
import com.example.user.bulletfalls.ObjectsOfGame.Character;
import com.example.user.bulletfalls.ObjectsOfGame.Description;
import com.example.user.bulletfalls.ObjectsOfGame.Enemy;
import com.example.user.bulletfalls.Enums.CharacterPositioning;
import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.Enums.Rarity;
import com.example.user.bulletfalls.Enums.Shape;
import com.example.user.bulletfalls.GameSupporters.GameStrategy;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage.NothingDoToCharacter;
import com.example.user.bulletfalls.Strategies.Bullet.BulletMoveStrategyPackage.Horizontal;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.Standard;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.MoneyPossesStrategy;
import com.example.user.bulletfalls.ObjectsOfGame.ViewElement;

import java.util.LinkedList;
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
    private LinearLayout startLabel;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getAndSetScreenSize();
        scoreLabel= (TextView)  findViewById(R.id.scoreLabel);
        startLabel=(LinearLayout) findViewById(R.id.startLabel);
        controller= new GameController(this,GameStrategy.getInstance().getEnemysChooser(this));
        frame=controller.game;
        abilitiesBar=(LinearLayout)findViewById(R.id.abilities);
    }

    private void showBoosts()
    {



    }

    private List<Enemy> getDefalultEnemyList()
    {
        FrameLayout game=(FrameLayout) this.findViewById(R.id.frame);
        Enemy enemy1= new Enemy(this,10,20,new Point(0,0),200,200,20,R.drawable.enemy,game,100,20,1,0,10,null,"enemy",null,null,CharacterPositioning.RIGHTRANDOM,new Standard(),"żaden",new Description());
        Enemy enemy2= new Enemy(this,10,30,new Point(0,0),200,200,20,R.drawable.rinor,game,200,20,1,0,10,null,"Rinor",null,null,CharacterPositioning.RIGHTRANDOM,new Standard(),"żaden",new Description());
        Enemy enemy3= new Enemy(this,10,20,new Point(0,0),200,200,20,R.drawable.creature,game,30,20,1,0,10,null,"Creature",null,null,CharacterPositioning.RIGHTRANDOM,new Standard(),"żaden",new Description());
        Enemy gideon= new Enemy(this,10,30,new Point(0,0),200,200,20,R.drawable.gideon,game,45,20,1,0,10,null,"Gideon",null,null,CharacterPositioning.RIGHTRANDOM,new Standard(),"żaden",new Description());
        Enemy gnome2= new Enemy(this,30,10,new Point(0,0),200,200,20,R.drawable.gnome2,game,40,20,1,0,10,null,"Gnome2",null,null,CharacterPositioning.RIGHTRANDOM,new Standard(),"żaden",new Description());
        Enemy goblin= new Enemy(this,10,10,new Point(0,0),200,200,20,R.drawable.goblin,game,100,20,1,0,10,null,"Goblin",null,null,CharacterPositioning.RIGHTRANDOM,new Standard(),"żaden",new Description());
        Enemy pacific= new Enemy(this,10,100,new Point(0,0),200,200,20,R.drawable.pacific,game,100,20,1,0,10,null,"Pacific",null,null,CharacterPositioning.RIGHTRANDOM,new Standard(),"żaden",new Description());
        Enemy police1= new Enemy(this,20,2,new Point(0,0),200,200,20,R.drawable.police1,game,500,20,1,0,10,null,"Police1",null,null,CharacterPositioning.RIGHTRANDOM,new Standard(),"żaden",new Description());

        List<Enemy> enemysCollection= new LinkedList<>();

        enemysCollection.add(enemy1);
        enemysCollection.add(enemy2);
        enemysCollection.add(enemy3);
        enemysCollection.add(gideon);
        enemysCollection.add(gnome2);
        enemysCollection.add(goblin);
        enemysCollection.add(pacific);
        enemysCollection.add(police1);
        for(Enemy enemy:enemysCollection)
        {
            enemy.setBullet(new Bullet("defaultenemybullet",this,10,20,null,50,50,20,R.drawable.blue,null,false,new Horizontal(),Shape.CIRCLE,new NothingDoToCharacter(),Permission.YES,Rarity.COMMON,new MoneyPossesStrategy("Mystery Coin",10)).clone());
           enemy.getBullet().setFrame(game);
        }
        return  enemysCollection;
    }

    public void setX(ViewElement vm, int x)
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
        if(start_flg==false)
        {
            start_flg=true;
            startLabel.setVisibility(View.GONE);
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
    public void addView(final ViewElement viewElement) {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                frame.addView(viewElement);
                viewElement.getLayoutParams().height=viewElement.getHeightp();
                viewElement.getLayoutParams().width=viewElement.getWidthp();
               // viewElement.getLayoutParams().height=viewElement.width*(viewElement.getDrawable().getIntrinsicHeight()/viewElement.getDrawable().getIntrinsicWidth());

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
                viewCharacter.getLayoutParams().height=viewCharacter.getHeightp();
                viewCharacter.getLayoutParams().width=viewCharacter.getWidthp();
                TextView textView= new TextView(getApplicationContext());

                frame.addView(textView);
                textView.setX(viewCharacter.getX()-30);
                textView.setY(viewCharacter.getY()+30);
                textView.setText("pietnascie");
            }
        });
    }
    public void removeObject(final ViewElement viewElement)
    {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                viewElement.setVisibility(View.GONE);
                frame.removeView(viewElement);
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

    public void moveViewElement(final ViewElement viewElement, final int x, final int y)
    {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                viewElement.setX(viewElement.getX()+x);
                viewElement.setY(viewElement.getY()+y);
            }
        });

    }

    public void setViewElement(final ViewElement viewElement, final int x, final int y)
    {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                viewElement.setX(x);
                viewElement.setY(y);
            }
        });

    }

    public void uploadLifeView(final Character character, final TextView textLife)
    {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                textLife.setText(character.getLife()+"");

                textLife.setTextColor(Color.WHITE);
                textLife.setTextSize(14);
                textLife.measure(0,0);
              //  float x=character.getX();
              //  float y=character.getY();


                textLife.setX(character.getX()+character.getWidthp()/2-textLife.getMeasuredWidth()/2);
                textLife.setY(character.getY()-textLife.getMeasuredHeight());
           //     float a=textLife.getX();
           //     float b=textLife.getY();
            }
        });
    }
    public void setViewImageMatrix(final Matrix matrix,final ViewElement viewElement)
    {
        final Game game=this;
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                game.removeObject(viewElement);
                viewElement.setImageMatrix(matrix);
                game.addView(viewElement);
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

    public void setAbilitiesBar(List<AbilityView> abilities)
    {

        for(AbilityView ability:abilities)
        {
            abilitiesBar.addView(ability);
            ability.getLayoutParams().width=abilitiesBar.getWidth()/abilities.size();
            abilitiesBar.getLayoutParams().height=200;
            //abilitiesBar.setGravity(Gravity.BOTTOM);
        }
    }

    public void setY(ViewElement vm, int y) {
        try {
            vm.setY(y);
        }
        catch(Exception ex)
        {

        }
    }

    public void setPoint(final ViewElement vm,final Point point)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                vm.setX(point.x);
                vm.setY(point.y);
            }
        });

    }
    public void changeResource(final ViewElement viewElement,final int resource)
    {
        final Game pointer=this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(pointer).load(resource).into(viewElement);
               //viewElement.setImageResource(resource);
            }
        });
    }
    public void changeResourceForAnimation(final ViewElement viewElement,final int resource, final int oldResourse)
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
                viewElement.setImageResource(resource);

            }
        });
    }
    public void changeForegroundForAnimation(final ViewElement viewElement,final int resource)
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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    viewElement.setForeground(getResources().getDrawable(resource));
                }

            }
        });
    }


    public void bigChangeResource(final ViewElement viewElement,final int resource)
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
                       bigChange(viewElement,drawable[0]);
                        return null;
                    }

                };
                asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
    }

    private void bigChange(final ViewElement viewElement, final Drawable drawable)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               // viewElement.setImageDrawable(drawable);
                viewElement.setImageDrawable(drawable);
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
}
