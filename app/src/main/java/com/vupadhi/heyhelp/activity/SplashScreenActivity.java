package com.vupadhi.heyhelp.activity;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.WindowManager;


import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.sharepref.UserSession;
import com.vupadhi.heyhelp.ui.activity.HomeScreenActivity;
import com.vupadhi.heyhelp.ui.activity.OnBoardingActivity;

public class SplashScreenActivity extends AppCompatActivity {

    UserSession userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        userSession=new UserSession(this);

        Handler handler = new Handler();
        handler.postDelayed( new Runnable() {
            @Override
            public void run() {

                if (userSession.isUserLoggedIn()) {
                    Intent i1 = new Intent(SplashScreenActivity.this, HomeScreenActivity.class);
                    startActivity(i1);
                    finish();
                } else {
                    Intent i1 = new Intent( SplashScreenActivity.this, OnBoardingActivity.class );
                    startActivity( i1 );
                    finish();
                }
                /*Intent i1 = new Intent( SplashScreenActivity.this, OnBoardingActivity.class );
                startActivity( i1 );
                finish();*/

            }
        }, 3500 );
    }
}
