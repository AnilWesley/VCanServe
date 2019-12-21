package com.vupadhi.heyhelp.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vupadhi.heyhelp.adapter.slideAdapter;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.customfonts.CustomTextViewBold;
import com.vupadhi.heyhelp.mvp.contract.activity.OnBoardingActivityContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.OnBoardingActivityImpl;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;

import java.util.Timer;
import java.util.TimerTask;

public class OnBoardingActivity extends BaseAbstractActivity<OnBoardingActivityImpl> implements OnBoardingActivityContract.IView, APIResponseCallback {
    ViewPager viewPager;
    private LinearLayout vllDost;
    private com.vupadhi.heyhelp.adapter.slideAdapter slideAdapter;
    private TextView[] mdots;
    private CustomTextViewBold ctvb_getstarted;

    int currentPage = 0;

    public int[] slide_images = {
            R.drawable.onboard_one,
            R.drawable.onboard_two,
            R.drawable.onboard_three,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        presenter.start();


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_on_boarding, null);
        return view;
    }

    public void viewPagerButtons() {


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (vllDost != null && vllDost.getTag() != null) {
                    ((ImageView) vllDost.getTag()).setImageResource(R.drawable.onboard_dash_gray);
                    ((ImageView) vllDost.getChildAt(position)).setImageResource(R.drawable.onboard_dash_sky);
                    vllDost.setTag(vllDost.getChildAt(position));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupDotBar(final Integer length) {
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(100, 50);
        param.setMargins(5, 0, 5, 0);
        vllDost.removeAllViews();
        for (int i = 0; i < length; i++) {
            int i2;
            ImageView img = new ImageView(getApplicationContext());

            if (i == 0) {
                i2 = R.drawable.onboard_dash_sky;
            } else {
                i2 = R.drawable.onboard_dash_gray;
            }
            img.setImageResource(i2);
            img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            vllDost.addView(img, param);
            if (i == 0) {
                vllDost.setTag(img);
            }
        }
    }


    @Override
    public void setPresenter() {

        presenter = new OnBoardingActivityImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        viewPager = findViewById(R.id.onboard_viewpager);
        ctvb_getstarted = findViewById(R.id.ctvb_getstarted);

        ctvb_getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoardingActivity.this, LoginActivity.class));
            }
        });


        slideAdapter = new slideAdapter(OnBoardingActivity.this);
        vllDost = findViewById(R.id.vDots);
        setupDotBar(slide_images.length);
        viewPagerButtons();
        viewPager.setAdapter(slideAdapter);

        final Handler handler = new Handler();
        TimerTask timer = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        int numPages = viewPager.getAdapter().getCount();
                        currentPage = (currentPage + 1) % numPages;
                        viewPager.setCurrentItem(currentPage);
                    }
                });
            }
        };

/* timer = new Timer(); // This will create a new Thread
timer.schedule(new TimerTask() { // task to be scheduled
@Override
public void run() {
handler.post(Update);
}
}, DELAY_MS, PERIOD_MS);
*/
        Timer time = new Timer();
        time.schedule(timer, 0, 3000);


    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}

