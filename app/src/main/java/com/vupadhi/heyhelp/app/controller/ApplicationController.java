package com.vupadhi.heyhelp.app.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.vupadhi.heyhelp.app.application.MyApplication;
import com.vupadhi.heyhelp.app.constants.AppConstants;
import com.vupadhi.heyhelp.app.factories.ViewFactory;


/**
 * Created by prasad on 05-07-17.
 * ApplicationController.java
 * <p/>
 * The ApplicationController Class, which helps to manage different Controllers,
 * Models, Views. This Class will be initialize as the platform requirement.
 */

public class ApplicationController {

    /**
     * private instance of ApplicationController for singleton Design Pattern
     */
    private static ApplicationController instance;

    /**
     * private instance of UIController for managing different AbstractViews
     */
    public UiController uiController;

    /**
     * private instance of ViewFactory for fast accessing.
     */
    public ViewFactory viewFactory;

    public Activity mActivity;
    public Context mContext;

    private MyApplication application;

    /**
     * Constructor of ApplicationController
     */
    private ApplicationController() {
        uiController = UiController.getInstance();
    }

    /**
     * Get Single instance of ApplicationController
     *
     * @return ApplicationController single instance
     */
    public static ApplicationController getInstance() {
        if (instance == null) {
            // creating new instance of application controller
            instance = new ApplicationController();
        }
        return instance;
    }
//
//    /**
//     * This Function will get called from LoginActivity or Any Activity which is
//     * going to display first screen after launching this application
//     */
//    public void initialize() {
//
//        // initialize the ModelFacade
//        modelFacade.initialize();
//
//        // set the reference for UI Controller
//        uiController = UIController.getInstance();
//
//        // initialize the UIController
//        uiController.initialize();
//
//        // set the viewFactory reference for further use in code.
//        viewFactory = ViewFactory.getInstance();
//
//    }

    /**
     * returns the current mActivity
     *
     * @return
     */
    public Activity getActivity() {
        return mActivity;
    }


    public void setActivity(@NonNull Activity mActivity) {
        this.mActivity = mActivity;
    }


    public MyApplication getApplication() {
        return application;
    }


    public void setApplication(MyApplication application) {
        this.application = application;
    }


    public Context getContext() {
        return mContext;
    }


    public void setContext(@NonNull Context mContext) {
        this.mContext = mContext;
    }


    public void handleEvent(@AppConstants.EventIds int eventId) {
        handleEvent(eventId, null);
    }


    /**
     * Handle Event Based on Event ID and Object
     *
     * @param eventId      Event Id based on user actions
     * @param eventObjects It stores the data for the given Event. so it can forward to
     *                     other events
     */

    @SuppressLint("WrongConstant")
    public void handleEvent(@AppConstants.EventIds int eventId, Object eventObjects) {
        Log.d(AppConstants.LOG_CAT, "handleEvent : " + eventId);

        switch (eventId) {

            case AppConstants.EventIds.LAUNCH_LOGIN_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.LOGIN_SCREEN);
                break;

            case AppConstants.EventIds.LAUNCH_HOME_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.HOME_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_FORGOT_PASSWORD_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.FORGOT_PASSWORD_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_SIGNUP_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.SIGNUP_SCREEN, (Bundle) eventObjects);
                break;

            case AppConstants.EventIds.LAUNCH_VERIFY_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.VERIFY_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_SET_PASSWORD_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.SET_PASSWORD_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_HELP_MAID_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.HELP_MAID_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_PLACING_ORDER_OKAY_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.PLACING_ORDER_OKAY_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_PAYMENT_DETAILS_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.PAYMENT_DETAILS_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_CHILDCARE_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.CHILDCARE_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_ELDERCARE_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.ELDERCARE_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_COOK_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.COOK_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_SECURITY_GUARD_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.SECURITY_GUARD_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_SELECT_ABSENT_DATE_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.SELECT_ABSENT_DATE_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_PAYMENT_METHOD_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.PAYMENT_METHOD_SCREEN);
                break;


            default:
                throw new IllegalStateException("Invalid Event id");
        }
    }
}
