package com.vupadhi.heyhelp.network.listener;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vupadhi.heyhelp.network.constants.NetworkConstants;


/**
 * Created by prasad on 05/07/2017
 */

public interface APIResponseCallback {

    void onSuccessResponse(@NetworkConstants.RequestCode int requestId, @NonNull String responseString,
                           @Nullable Object object);

    void onFailureResponse(@NetworkConstants.RequestCode int requestId, @NonNull String errorString);

}
