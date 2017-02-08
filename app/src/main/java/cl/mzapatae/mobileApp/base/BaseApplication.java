package cl.mzapatae.mobileApp.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import cl.mzapatae.mobileApp.utils.LocalStorage;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 25-11-16
 * E-mail: miguel.zapatae@gmail.com
 */

public class BaseApplication extends Application {
    private static final String TAG = "Base Application";
    private static boolean sActivityVisible;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        LocalStorage.initLocalStorage(base);
    }

    public static boolean isActivityVisible() {
        return sActivityVisible;
    }

    public static void activityResumed() {
        sActivityVisible = true;
    }

    public static void activityPaused() {
        sActivityVisible = false;
    }
}
