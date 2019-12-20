package com.vupadhi.heyhelp.app.constants;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.vupadhi.heyhelp.app.constants.AppConstants.EventIds.LAUNCH_CHILDCARE_SCREEN;
import static com.vupadhi.heyhelp.app.constants.AppConstants.EventIds.LAUNCH_COOK_SCREEN;
import static com.vupadhi.heyhelp.app.constants.AppConstants.EventIds.LAUNCH_ELDERCARE_SCREEN;
import static com.vupadhi.heyhelp.app.constants.AppConstants.EventIds.LAUNCH_FORGOT_PASSWORD_SCREEN;
import static com.vupadhi.heyhelp.app.constants.AppConstants.EventIds.LAUNCH_HELP_MAID_SCREEN;
import static com.vupadhi.heyhelp.app.constants.AppConstants.EventIds.LAUNCH_HOME_SCREEN;
import static com.vupadhi.heyhelp.app.constants.AppConstants.EventIds.LAUNCH_LOGIN_SCREEN;
import static com.vupadhi.heyhelp.app.constants.AppConstants.EventIds.LAUNCH_PAYMENT_DETAILS_SCREEN;
import static com.vupadhi.heyhelp.app.constants.AppConstants.EventIds.LAUNCH_PAYMENT_METHOD_SCREEN;
import static com.vupadhi.heyhelp.app.constants.AppConstants.EventIds.LAUNCH_PLACING_ORDER_OKAY_SCREEN;
import static com.vupadhi.heyhelp.app.constants.AppConstants.EventIds.LAUNCH_SECURITY_GUARD_SCREEN;
import static com.vupadhi.heyhelp.app.constants.AppConstants.EventIds.LAUNCH_SELECT_ABSENT_DATE_SCREEN;
import static com.vupadhi.heyhelp.app.constants.AppConstants.EventIds.LAUNCH_SET_PASSWORD_SCREEN;
import static com.vupadhi.heyhelp.app.constants.AppConstants.EventIds.LAUNCH_SIGNUP_SCREEN;
import static com.vupadhi.heyhelp.app.constants.AppConstants.EventIds.LAUNCH_VERIFY_SCREEN;


public interface AppConstants {

    String LOG_CAT = ">>logs : ";


    @Retention(RetentionPolicy.CLASS)
    @IntDef({LAUNCH_LOGIN_SCREEN,LAUNCH_HOME_SCREEN,LAUNCH_FORGOT_PASSWORD_SCREEN,LAUNCH_SIGNUP_SCREEN,LAUNCH_VERIFY_SCREEN,LAUNCH_SET_PASSWORD_SCREEN,LAUNCH_HELP_MAID_SCREEN,LAUNCH_PLACING_ORDER_OKAY_SCREEN,LAUNCH_PAYMENT_DETAILS_SCREEN,LAUNCH_CHILDCARE_SCREEN,LAUNCH_ELDERCARE_SCREEN,LAUNCH_COOK_SCREEN,LAUNCH_SECURITY_GUARD_SCREEN,LAUNCH_SELECT_ABSENT_DATE_SCREEN,LAUNCH_PAYMENT_METHOD_SCREEN})
    @interface EventIds {
        int LAUNCH_LOGIN_SCREEN = 101;
        int LAUNCH_HOME_SCREEN = 102;
        int LAUNCH_FORGOT_PASSWORD_SCREEN = 103;
        int LAUNCH_SIGNUP_SCREEN = 104;
        int LAUNCH_VERIFY_SCREEN = 105;
        int LAUNCH_SET_PASSWORD_SCREEN = 106;
        int LAUNCH_HELP_MAID_SCREEN= 107;
        int LAUNCH_PLACING_ORDER_OKAY_SCREEN= 108;
        int LAUNCH_PAYMENT_DETAILS_SCREEN= 109;
        int LAUNCH_CHILDCARE_SCREEN= 110;
        int LAUNCH_ELDERCARE_SCREEN= 111;
        int LAUNCH_COOK_SCREEN= 112;
        int LAUNCH_SECURITY_GUARD_SCREEN= 113;
        int LAUNCH_SELECT_ABSENT_DATE_SCREEN= 114;
        int LAUNCH_PAYMENT_METHOD_SCREEN= 115;

    }

    interface PREFERENCES {
        String IS_LAUNCH_HOME_SCREEN = "isLaunchHomeScreen";
        String KEY_PREF_X_AUTH_TOKEN = "X-AUTH-TOKEN";
        String KEY_HOST_URL = "hostUrl";
    }
}
