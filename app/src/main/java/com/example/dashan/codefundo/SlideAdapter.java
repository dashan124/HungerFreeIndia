package com.example.dashan.codefundo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by dashan on 16/3/18.
 */

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public SlideAdapter(Context context){
        this.context=context;
    }
    public int[] slide_images={
            R.drawable.donate,
            R.drawable.savefood,
            R.drawable.foodbank
    };
    public String[] slide_headings={
            "Donate Food",
            "save food ",
            "send to foodbank"
    };
    public String[] slide_descriptions={
            "donate food find a food bank near you",
            "please donot waste food it is the result" +
                    " of farmer's hardwork please save it",
            "give it to food bank so it reach to poor "
    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(RelativeLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView slideImageView=(ImageView) view.findViewById(R.id.image_view);
        TextView slideheadingtext=(TextView) view.findViewById(R.id.text_heading);
        TextView slidetextdescription=(TextView) view.findViewById(R.id.text_descri);
        slideImageView.setImageResource(slide_images[position]);
        slideheadingtext.setText(slide_headings[position]);
        slidetextdescription.setText(slide_descriptions[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}

