package cl.mzapatae.mobileLegacy.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Class to manage persistent data in application
 *
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 25-11-16
 * E-mail: miguel.zapatae@gmail.com
 */

public class LocalStorage {
    private static final String TAG = "LocalStorage";

    private static final String PREF_STORAGE_FILE = "mobileLegacy.storage";
    private static final String PREF_LOGGED_IN = "userLoggedIn";

    private static final String PREF_APP_NAME = "appName";
    private static final String PREF_TOKEN_ID = "tokenId";
    private static final String PREF_DEVICE_ID = "deviceId";
    private static final String PREF_TOKEN_PUSH = "tokenPush";
    private static final String PREF_VERSION_APP = "appVersion";
    private static final String PREF_MODEL_DEVICE = "modelDevice";
    private static final String PREF_ANDROID_VERSION = "androidVersion";
    private static final String PREF_MANUFACTURER_DEVICE = "manufacturerDevice";

    private static SharedPreferences SHARED_PREFERENCE;

    public static void initLocalStorage(Context context) {
        SHARED_PREFERENCE = context.getSharedPreferences(PREF_STORAGE_FILE, Activity.MODE_PRIVATE);
        Log.i(TAG, "Local Storage Init Sucess");
    }

    public static void loginUser(String userToken) {
        SHARED_PREFERENCE.edit().putBoolean(PREF_LOGGED_IN, true).apply();
        SHARED_PREFERENCE.edit().putString(PREF_TOKEN_ID, userToken).apply();
    }

    public static void logoutUser() {
        SHARED_PREFERENCE.edit().putBoolean(PREF_LOGGED_IN, false).apply();
        SHARED_PREFERENCE.edit().remove(PREF_TOKEN_ID).apply();
    }

    public static void setDeviceId(String deviceId) {
        SHARED_PREFERENCE.edit().putString(PREF_DEVICE_ID, deviceId).apply();
    }

    public static void setVersionApp(String versionApp) {
        SHARED_PREFERENCE.edit().putString(PREF_VERSION_APP, versionApp).apply();
    }

    public static void setTokenPush(String tokenPush) {
        SHARED_PREFERENCE.edit().putString(PREF_TOKEN_PUSH, tokenPush).apply();
    }

    public static void setModelDevice(String modelDevice) {
        SHARED_PREFERENCE.edit().putString(PREF_MODEL_DEVICE, modelDevice).apply();
    }

    public static void setManufacturerDevice(String manufacturerDevice) {
        SHARED_PREFERENCE.edit().putString(PREF_MANUFACTURER_DEVICE, manufacturerDevice).apply();
    }

    public static void setAndroidVersion(String androidVersion) {
        SHARED_PREFERENCE.edit().putString(PREF_ANDROID_VERSION, androidVersion).apply();
    }

    public static void setAppName(String appName) {
        SHARED_PREFERENCE.edit().putString(PREF_APP_NAME, appName).apply();
    }

    public static Boolean isUserLogged() {
        return SHARED_PREFERENCE.getBoolean(PREF_LOGGED_IN, false);
    }

    public static String getUserTokenId() {
        return SHARED_PREFERENCE.getString(PREF_TOKEN_ID, "");
    }

    public static String getDeviceId() {
        return SHARED_PREFERENCE.getString(PREF_DEVICE_ID, "");
    }

    public static String getVersionApp() {
        return SHARED_PREFERENCE.getString(PREF_VERSION_APP, "");
    }

    public static String getTokenPush() {
        return SHARED_PREFERENCE.getString(PREF_TOKEN_PUSH, "");
    }

    public static String getModelDevice() {
        return SHARED_PREFERENCE.getString(PREF_MODEL_DEVICE, "");
    }

    public static String getManufacturerDevice() {
        return SHARED_PREFERENCE.getString(PREF_MANUFACTURER_DEVICE, "");
    }

    public static String getAndroidVersion() {
        return SHARED_PREFERENCE.getString(PREF_ANDROID_VERSION, "");
    }

    public static String getAppName() {
        return SHARED_PREFERENCE.getString(PREF_APP_NAME, "");
    }
}
