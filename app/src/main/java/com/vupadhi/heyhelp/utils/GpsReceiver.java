package com.vupadhi.heyhelp.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

public class GpsReceiver extends BroadcastReceiver {
    public interface LocationCallBack {
        void turnedOn();
        void turnedOff();
    }

    private final LocationCallBack locationCallBack;

    public GpsReceiver(LocationCallBack iLocationCallBack){
        this.locationCallBack = iLocationCallBack;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            locationCallBack.turnedOn();
        else
            locationCallBack.turnedOff();
    }
}
