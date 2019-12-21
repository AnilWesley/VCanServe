package com.vupadhi.heyhelp.mvp.contract.activity;

import android.content.Context;
import androidx.fragment.app.Fragment;

import com.vupadhi.heyhelp.mvp.base.IBaseContract;
import com.vupadhi.heyhelp.mvp.base.IBaseDataManager;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;

import org.json.JSONObject;

import java.util.Map;

public interface DomesticHelpMaidContract {

    interface IPermissionIds extends IBaseContract

    {
        //todo add permissions
        int FINISH_FLASH_SCREEN = 13;
        int LAUNCH_LOCATION_SCREEN=14;
    }

    /**
     * Declared the methods here which can be used in Login Screen.
     */
    interface IView {
        void replaceRespectiveFragment(Fragment fragment, String[] data, String tag);

    }

    /**
     * Declared the methods here which can be used in Login Screen.
     */
    interface IPresenter {

        void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);
        void domestic_help(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody,String url);
        void savedomestic_help(Context context, APIResponseCallback apiResponseCallback, JSONObject requestBody);


        //This method is use to login.
        // void launchHomeFragment();
    }

    /**
     * Declared the methods here which can be used in Data manager of Login Screen.
     */
    interface IDataManager extends IBaseDataManager {


    }
}
