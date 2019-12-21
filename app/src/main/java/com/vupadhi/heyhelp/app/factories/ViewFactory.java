package com.vupadhi.heyhelp.app.factories;

import androidx.annotation.IntDef;

import com.vupadhi.heyhelp.ui.activity.Child_care;
import com.vupadhi.heyhelp.ui.activity.Cook_screen;
import com.vupadhi.heyhelp.ui.activity.Elder_care;
import com.vupadhi.heyhelp.ui.activity.ForgotPasswordActivity;
import com.vupadhi.heyhelp.ui.activity.Help_maid_screen;
import com.vupadhi.heyhelp.ui.activity.HomeScreenActivity;
import com.vupadhi.heyhelp.ui.activity.LoginActivity;
import com.vupadhi.heyhelp.ui.activity.OtpActivity;
import com.vupadhi.heyhelp.ui.activity.Payment_details_screen;
import com.vupadhi.heyhelp.ui.activity.Payment_method_screen;
import com.vupadhi.heyhelp.ui.activity.Placing_order_okay;
import com.vupadhi.heyhelp.ui.activity.Register_okay_screen;
import com.vupadhi.heyhelp.app.controller.ApplicationController;
import com.vupadhi.heyhelp.ui.activity.ResetPasswordActivity;
import com.vupadhi.heyhelp.ui.activity.Security_gaurd_screen;
import com.vupadhi.heyhelp.ui.activity.Select_absent_date_screen;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.vupadhi.heyhelp.app.factories.ViewFactory.ScreenIds.CHILDCARE_SCREEN;
import static com.vupadhi.heyhelp.app.factories.ViewFactory.ScreenIds.COOK_SCREEN;
import static com.vupadhi.heyhelp.app.factories.ViewFactory.ScreenIds.ELDERCARE_SCREEN;
import static com.vupadhi.heyhelp.app.factories.ViewFactory.ScreenIds.FORGOT_PASSWORD_SCREEN;
import static com.vupadhi.heyhelp.app.factories.ViewFactory.ScreenIds.HELP_MAID_SCREEN;
import static com.vupadhi.heyhelp.app.factories.ViewFactory.ScreenIds.HOME_SCREEN;
import static com.vupadhi.heyhelp.app.factories.ViewFactory.ScreenIds.LOGIN_SCREEN;
import static com.vupadhi.heyhelp.app.factories.ViewFactory.ScreenIds.PAYMENT_DETAILS_SCREEN;
import static com.vupadhi.heyhelp.app.factories.ViewFactory.ScreenIds.PAYMENT_METHOD_SCREEN;
import static com.vupadhi.heyhelp.app.factories.ViewFactory.ScreenIds.PLACING_ORDER_OKAY_SCREEN;
import static com.vupadhi.heyhelp.app.factories.ViewFactory.ScreenIds.SECURITY_GUARD_SCREEN;
import static com.vupadhi.heyhelp.app.factories.ViewFactory.ScreenIds.SELECT_ABSENT_DATE_SCREEN;
import static com.vupadhi.heyhelp.app.factories.ViewFactory.ScreenIds.SET_PASSWORD_SCREEN;
import static com.vupadhi.heyhelp.app.factories.ViewFactory.ScreenIds.SIGNUP_SCREEN;
import static com.vupadhi.heyhelp.app.factories.ViewFactory.ScreenIds.VERIFY_SCREEN;


//import static com.iprismtech.proffers.app.constants.AppConstants.EventIds.LAUNCH_SIGNUP_SCREEN;


/**
 * Created by prasad on 05/07/2017.
 * ViewFactory.java The Class which returns the Class (Screen) to the
 * application frame. Developer should use this class to get the reference of
 * any screen in the application. He should not create the screen by him/her
 * self
 */
public class ViewFactory {

    @Retention(RetentionPolicy.CLASS)
    @IntDef({LOGIN_SCREEN, HOME_SCREEN, VERIFY_SCREEN, FORGOT_PASSWORD_SCREEN, SET_PASSWORD_SCREEN, SIGNUP_SCREEN, HELP_MAID_SCREEN, PLACING_ORDER_OKAY_SCREEN, PAYMENT_DETAILS_SCREEN, CHILDCARE_SCREEN, ELDERCARE_SCREEN, COOK_SCREEN,SECURITY_GUARD_SCREEN,SELECT_ABSENT_DATE_SCREEN,PAYMENT_METHOD_SCREEN})
    public @interface ScreenIds {
        int LOGIN_SCREEN = 1001;
        int HOME_SCREEN = 1002;
        int SIGNUP_SCREEN = 1004;
        int VERIFY_SCREEN = 1005;
        int FORGOT_PASSWORD_SCREEN = 1006;
        int SET_PASSWORD_SCREEN = 1007;
        int HELP_MAID_SCREEN = 1008;
        int PLACING_ORDER_OKAY_SCREEN = 1009;
        int PAYMENT_DETAILS_SCREEN = 1010;
        int CHILDCARE_SCREEN = 1011;
        int ELDERCARE_SCREEN = 1012;
        int COOK_SCREEN = 1013;
        int SECURITY_GUARD_SCREEN = 1014;
        int SELECT_ABSENT_DATE_SCREEN = 1015;
        int PAYMENT_METHOD_SCREEN = 1016;

    }

    private ApplicationController mApplicationController = null;


    private ViewFactory() {
        mApplicationController = ApplicationController.getInstance();
    }


    public static Class getActivityClass(@ScreenIds int id) {

        switch (id) {

            case LOGIN_SCREEN: {
                return LoginActivity.class;

            }
            case HOME_SCREEN: {
                return HomeScreenActivity.class;

            }

            case SIGNUP_SCREEN: {
                return Register_okay_screen.class;

            }

            case VERIFY_SCREEN: {
                return OtpActivity.class;

            }


            case FORGOT_PASSWORD_SCREEN: {
                return ForgotPasswordActivity.class;

            }

            case SET_PASSWORD_SCREEN: {
                return ResetPasswordActivity.class;

            }

            case HELP_MAID_SCREEN: {
                return Help_maid_screen.class;

            }
            case PLACING_ORDER_OKAY_SCREEN: {
                return Placing_order_okay.class;

            }
            case PAYMENT_DETAILS_SCREEN: {
                return Payment_details_screen.class;

            }
            case CHILDCARE_SCREEN: {
                return Child_care.class;

            }
            case ELDERCARE_SCREEN: {
                return Elder_care.class;

            }
            case COOK_SCREEN: {
                return Cook_screen.class;

            }

            case SECURITY_GUARD_SCREEN: {
                return Security_gaurd_screen.class;

            }

            case SELECT_ABSENT_DATE_SCREEN: {
                return Select_absent_date_screen.class;

            }
            case PAYMENT_METHOD_SCREEN: {
                return Payment_method_screen.class;

            }


            default:
                throw new IllegalStateException("Invalid screen id");
        }
    }


}