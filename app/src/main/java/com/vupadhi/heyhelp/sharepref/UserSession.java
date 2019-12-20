package com.vupadhi.heyhelp.sharepref;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import com.vupadhi.heyhelp.ui.activity.LoginActivity;

import java.util.HashMap;

/**
 * Created by iprismTech on 12/07/2017.
 */

public class UserSession  {
    // Shared Preferences reference
    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    public static final String PREFER_NAME = "VcanServe";

    public static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_nCLIENTID = "nClientID";

    public static final String KEY_nUSERTYPE = "nUserType";

    public static final String KEY_NAME = "username";

    public static final String KEY_LOCATION = "location";

    public static final String KEY_EMAIL = "email";

    public static final String KEY_MOBILENUMBER = "mobilenumber";

    public static final String KEY_PROFILE = "image";

   // public static final String KEY_UNIVERSITY = "university";

    public static final String KEY_TIME = "time";

    public static final String LOGIN_ID = "id";

    public static final String KEY_LOCNAME = "locname";


    public static final String KEY_DEVICE_TOKEN = "devicetoken";

   // public static final Boolean KEY_IS_LOGIN = false;
   // private static final String IS_LOGIN = "IsLoggedIn";


    // Constructor
    public UserSession(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }



//    public void setKeyDeviceToken(String Id) {
//        // Storing login value as TRUE
//
//        // Storing name in pref
//        editor.putString(KEY_DEVICE_TOKEN, Id);
//
//        editor.commit();
//    }

    //Create login session
    public void StoreUserDetails(String nClientid,String nUserType,String name,String location,Boolean islogin) {
        // Storing login value as TRUE
        editor.putString(KEY_nCLIENTID,nClientid);
        editor.putString(KEY_nUSERTYPE,nUserType);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_LOCATION, location);
//
//
//        editor.putString(KEY_EMAIL, emailId);
//        editor.putString(KEY_MOBILENUMBER, mobilenumber);
//
//        editor.putString(KEY_PROFILE, profileImg);


        editor.putBoolean(IS_LOGIN, islogin);

        editor.commit();


    }

    public void StoreLocDetails(String locname) {
        // Storing login value as TRUE

        editor.putString(KEY_LOCNAME, locname);


        editor.commit();


    }

   /* public void StoreAdsDetails(String adsid) {
        // Storing login value as TRUE
        editor.putString(KEY_ADSID, adsid);


        editor.commit();


    }*/

    public void StoreTimeDetails(String time) {
        // Storing login value as TRUE

        editor.putString(KEY_TIME, time);


        editor.commit();


    }


    public String getSingleField(String key) {
        return pref.getString(key, null);
    }

//    public boolean checkLogin() {
//        // Check login status
//        if (!this.isUserLoggedIn()) {
//
//            // user is not logged in redirect him to Login Activity
//            Intent i = new Intent(_context, LoginActivity.class);
//
//            // Closing all the Activities from stack
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//            // Add new Flag to start new Activity
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            // Staring Login Activity
//            _context.startActivity(i);
//
//            return true;
//        }
//        return false;
//    }

    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {

        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_nCLIENTID, pref.getString(KEY_nCLIENTID, null));
        user.put(KEY_nUSERTYPE, pref.getString(KEY_nUSERTYPE, null));

        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_LOCATION, pref.getString(KEY_LOCATION, null));
//
//
//        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
//
//        user.put(KEY_MOBILENUMBER, pref.getString(KEY_MOBILENUMBER, null));
//
//
//
//        user.put(IS_LOGIN, pref.getString(IS_LOGIN, null));
//        user.put(KEY_PROFILE, pref.getString(KEY_PROFILE,null));



        user.put(KEY_DEVICE_TOKEN, pref.getString(KEY_DEVICE_TOKEN, ""));

        return user;
    }

    public HashMap<String, String> getTimeDetails() {

        //Use hashmap to store user credentials
        HashMap<String, String> timedetails = new HashMap<String, String>();


        timedetails.put(KEY_TIME, pref.getString(KEY_TIME, null));


        return timedetails;
    }

    public HashMap<String, String> getLocDetails() {

        //Use hashmap to store user credentials
        HashMap<String, String> weekdetails = new HashMap<String, String>();


        weekdetails.put(KEY_LOCNAME, pref.getString(KEY_LOCNAME, null));


        return weekdetails;
    }




    /**
     * Clear session details
     */
    public void logoutUser() {

        // Clearing all user data from Shared Preferences
        editor.remove(KEY_nCLIENTID);
        editor.remove(KEY_nUSERTYPE);
        editor.remove(KEY_NAME);
        editor.remove(KEY_LOCATION);
//
//        editor.remove(KEY_EMAIL);
//        editor.remove(KEY_MOBILENUMBER);
//
//        editor.remove(KEY_PROFILE);

        editor.remove(IS_LOGIN);


        editor.commit();

        Intent i = new Intent(_context, LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        _context.startActivity(i);
        ((Activity) _context).finish();

    }

    /**
     * When user is logout then change the login screen details as user logout.
     */
    public void changeUserLoginScreenStatus() {
        editor.putBoolean(IS_LOGIN, false);

        editor.commit();
    }

    // set the status of the user for read and unread here.


    public void setDeviceToken(String deviceToken) {

        editor.putString(KEY_DEVICE_TOKEN, deviceToken);

        editor.commit();

    }



    // Check for login
    public boolean isUserLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }


}
