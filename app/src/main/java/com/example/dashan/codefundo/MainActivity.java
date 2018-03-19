package com.example.dashan.codefundo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private ViewPager mslideViewpager;
private LinearLayout mDotsLayout;
private SlideAdapter mslideAdapter;
private Button mnext;
private RelativeLayout relativeLayout;
private Button mskip;
private TextView[] Dots;
private int mcurrentpage;
private Boolean firstTime=null;
private boolean isFirstTime(){
    if (firstTime == null) {
        SharedPreferences mPreferences = this.getSharedPreferences("first_time", Context.MODE_PRIVATE);
        firstTime = mPreferences.getBoolean("firstTime", true);
        if (firstTime) {
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putBoolean("firstTime", false);
            editor.apply();
        }

    }
    return firstTime;

}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mslideViewpager=(ViewPager) findViewById(R.id.view_pager);
        mDotsLayout=(LinearLayout) findViewById(R.id.dot_view);
        relativeLayout=(RelativeLayout) findViewById(R.id.relative_main);
       // if(isFirstTime()) {

        mnext = (Button) findViewById(R.id.next_button);
        mskip = (Button) findViewById(R.id.skip_button);

        mslideAdapter = new SlideAdapter(this);
        mslideViewpager.setAdapter(mslideAdapter);
        addDotsIndicator(0);
        mslideViewpager.addOnPageChangeListener(viewListener);
        final String [] Colors={"#f64c73","#20d2bb","#3395ff"};
        mnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mslideViewPager.setCurrentItem(mcurrentpage+1);
                int current = mcurrentpage + 1;
                if (current < Dots.length) {
                    mslideViewpager.setCurrentItem(current);
                   // relativeLayout.setBackground(Drawable.createFromPath(Colors[current]));
                } else {
                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);
                }

            }
        });
        mskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });
        }
       /* else{
            Intent i=new Intent(getApplicationContext(),Login.class);
            startActivity(i);
        }*/
    //}
    public void addDotsIndicator(int pos){
        Dots=new TextView[3];
        mDotsLayout.removeAllViews();
        for(int i=0;i<Dots.length;i++){
            Dots[i]=new TextView(this);
            Dots[i].setText(Html.fromHtml("&#8226"));
            Dots[i].setTextSize(36);
            Dots[i].setGravity(Gravity.CENTER_HORIZONTAL);
            Dots[i].setTextColor(getResources().getColor(R.color.Black));
            mDotsLayout.addView(Dots[i]);
        }
        if(Dots.length>0){
            Dots[pos].setTextColor(getResources().getColor(R.color.AliceBlue));
        }
    }
    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);

            mcurrentpage=position;
            if(position==0){
                mnext.setEnabled(true);
                mnext.setVisibility(View.VISIBLE);
                mskip.setText("Skip");
                mskip.setEnabled(true);
            }
            else if(position==Dots.length-1){
                mnext.setEnabled(true);
                mnext.setText("Finish");
                mskip.setEnabled(true);
                mskip.setText("Skip");
                mnext.setVisibility(View.INVISIBLE);
            }
            else{
                mnext.setEnabled(true);
                mnext.setVisibility(View.VISIBLE);
                mnext.setText("Next");
                mskip.setText("Skip");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    }

