package com.example.mahmoud.timerandanim;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;


public class MainActivity extends ActionBarActivity {
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView MyImage = (ImageView) findViewById(R.id.imageView);


        //  Way TO Make Translate Anim By XML File

        Animation MyAnimation = AnimationUtils.loadAnimation(this, R.anim.move_ltr);
        MyImage.startAnimation(MyAnimation);



      /*
        //  Way TO Make Translate Anim By Coding

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        TranslateAnimation animation = new TranslateAnimation(0.0f, width, 0.0f, 0.0f);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animation.setDuration(1 * 60 * 1000);  // animation duration
        animation.setFillAfter(true);
        MyImage.startAnimation(animation);  // start animation
*/

       final int ImageFrames[] = {R.drawable.frame_0, R.drawable.frame_1, R.drawable.frame_2, R.drawable.frame_3, R.drawable.frame_4};

        Timer MyTimer=new Timer();
        final Handler myHandler=new Handler() ;
        final Runnable myRunnable=new Runnable() {
            @Override
            public void run() {
                MyImage.setImageResource(ImageFrames[count]);
                count++;
                if(count==5){
                    count=0;
                }
            }
        };

        TimerTask myTask=new TimerTask() {
            @Override
            public void run() {
                myHandler.post(myRunnable);
            }
        };

        MyTimer.schedule(myTask,0,(int)(.08*1000));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
