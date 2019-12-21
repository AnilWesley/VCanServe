package com.vupadhi.heyhelp.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;



import com.github.siyamed.shapeimageview.RoundedImageView;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.customfonts.CustomTextViewSemiBold;

public class HomeSlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public HomeSlideAdapter(Context context) {
        this.context = context;
    }

    //arrays
    public int[] slide_images = {
            R.drawable.service_one,
            R.drawable.service_two,
            R.drawable.service_one,
    };
    public String[] slide_headlines = {
            "We Can serve The Confort you want an the service you need.",
            "We Can serve The Confort you want an the service you need.",
            "We Can serve The Confort you want an the service you need.",

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
        View view = layoutInflater.inflate(R.layout.home_slide_adapter, container, false);

        RoundedImageView slide_imageview = (RoundedImageView) view.findViewById(R.id.iv_image);
        CustomTextViewSemiBold slide_headline = (CustomTextViewSemiBold) view.findViewById(R.id.ctvsb_text);

        slide_imageview.setImageResource(slide_images[position]);
        slide_headline.setText(slide_headlines[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
