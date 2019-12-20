package com.vupadhi.heyhelp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.customfonts.CustomTextViewBold;
import com.vupadhi.heyhelp.customfonts.CustomTextViewSemiBold;


public class slideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public slideAdapter(Context context) {
        this.context = context;
    }

    //arrays
    public int[] slide_images = {
            R.drawable.onboard_one,
            R.drawable.onboard_two,
            R.drawable.onboard_three,
    };
    public String[] slide_headlines = {
            "We Can serve\n" +
                    "The Comfort you want and\n" +
                    "the service you need.",
            "We Can serve\n" +
                    "The Comfort you want and\n" +
                    "the service you need.",
            "We Can serve\n" +
                    "The Comfort you want and\n" +
                    "the service you need.",

    };
    public String[] slide_descs = {
            "we provide over 25+ services\n" +
                    "at your doorstep.",
            "10000+\n" +
                    "Happy Customers.",
            "500+\n" +
                    "Verified Service Professionals.",
    };

    @Override
    public int getCount() {
        return slide_headlines.length;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (LinearLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_onboard_adapter, container, false);

        ImageView slide_imageview = (ImageView) view.findViewById(R.id.onbarding_image);
        CustomTextViewBold slide_headline = (CustomTextViewBold) view.findViewById(R.id.ctvb_head_text);
        CustomTextViewSemiBold slide_description = (CustomTextViewSemiBold) view.findViewById(R.id.ctvsb_description);

        slide_imageview.setImageResource(slide_images[position]);
        slide_headline.setText(slide_headlines[position]);
        slide_description.setText(slide_descs[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
