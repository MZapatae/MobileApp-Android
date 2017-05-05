package cl.mzapatae.mobileApp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import cl.mzapatae.mobileApp.BuildConfig;

/**
 * Class to manage persistent data in application
 *
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 25-11-16
 * E-mail: miguel.zapatae@gmail.com
 */

//TODO: Improve with Singleton
public class LocalStorage {
    private static final String TAG = "LocalStorage";

    private static LocalStorage mInstance = null;

    private static final String PREF_STORAGE_FILE = "mobileApp.storage"; //TODO: Rename File

    private static final String PREF_APP_NAME = "appName";
    private static final String PREF_DEVICE_ID = "deviceId";
    private static final String PREF_TOKEN_PUSH = "tokenPush";
    private static final String PREF_VERSION_APP = "appVersion";
    private static final String PREF_MODEL_DEVICE = "modelDevice";
    private static final String PREF_ANDROID_VERSION = "androidVersion";
    private static final String PREF_MANUFACTURER_DEVICE = "manufacturerDevice";

    private static final String PREF_LOGGED_IN = "userLoggedIn";
    private static final String PREF_USER_UID = "userUid";
    private static final String PREF_USER_EMAIL = "userEmail";
    private static final String PREF_USER_AVATAR = "userAvatar";
    private static final String PREF_USER_FIRSTNAME = "userFirstName";
    private static final String PREF_USER_LASTNAME = "userLastName";
    private static final String PREF_USER_DISPLAYNAME = "userDisplayName";
    private static final String PREF_USER_PROVIDER = "userProvider";
    private static final String PREF_USER_TOKEN = "userTokenId";

    private static SharedPreferences SHARED_PREFERENCE;

    private LocalStorage(Context context) {
        SHARED_PREFERENCE = context.getSharedPreferences(PREF_STORAGE_FILE, Activity.MODE_PRIVATE);
        Log.i(TAG, "Local Storage Init Sucess");
    }

    public static LocalStorage getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new LocalStorage(context);
        }
        return mInstance;
    }

    public static void loginUser(String uid, String email,String firstName, String lastName, String displayName, String avatarUrl, String userProvider, String userToken) {
        SHARED_PREFERENCE.edit().putBoolean(PREF_LOGGED_IN, true).apply();
        SHARED_PREFERENCE.edit().putString(PREF_USER_UID, uid).apply();
        SHARED_PREFERENCE.edit().putString(PREF_USER_TOKEN, userToken).apply();
        SHARED_PREFERENCE.edit().putString(PREF_USER_EMAIL, email).apply();
        SHARED_PREFERENCE.edit().putString(PREF_USER_AVATAR, avatarUrl).apply();
        SHARED_PREFERENCE.edit().putString(PREF_USER_FIRSTNAME, firstName).apply();
        SHARED_PREFERENCE.edit().putString(PREF_USER_LASTNAME, lastName).apply();
        SHARED_PREFERENCE.edit().putString(PREF_USER_DISPLAYNAME, displayName).apply();
        SHARED_PREFERENCE.edit().putString(PREF_USER_PROVIDER, userProvider).apply();

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Login User Success!");
            Log.d(TAG, "User Logged Info:");
            Log.d(TAG, " - User ID: " + uid);
            Log.d(TAG, " - User Email: " + email);
            Log.d(TAG, " - User Full Name: " + firstName + " " + lastName);
            Log.d(TAG, " - User Display Name: " + displayName);
            Log.d(TAG, " - User Provider: " + userProvider);
            Log.d(TAG, " - User Avatar Url: " + avatarUrl);
            Log.d(TAG, " - User Token: " + userToken);
        }
    }

    public String getPrefUserUid() {
        return SHARED_PREFERENCE.getString(PREF_USER_UID, "");
    }

    public String getPrefUserToken() {
        return SHARED_PREFERENCE.getString(PREF_USER_TOKEN, "");
    }

    public String getPrefUserProvider() {
        return SHARED_PREFERENCE.getString(PREF_USER_PROVIDER, "");
    }

    public String getPrefUserDisplayname() {
        return SHARED_PREFERENCE.getString(PREF_USER_DISPLAYNAME, "");
    }

    public String getPrefUserLastname() {
        return SHARED_PREFERENCE.getString(PREF_USER_LASTNAME, "");
    }

    public String getPrefUserFirstname() {
        return SHARED_PREFERENCE.getString(PREF_USER_FIRSTNAME, "");
    }

    public String getPrefUserEmail() {
        return SHARED_PREFERENCE.getString(PREF_USER_EMAIL, "");
    }

    public String getPrefUserAvatar() {
        return SHARED_PREFERENCE.getString(PREF_USER_AVATAR, "");
    }

    public Boolean isUserLogged() {
        return SHARED_PREFERENCE.getBoolean(PREF_LOGGED_IN, false);
    }

    public String getPrefUserTokenId() {
        return SHARED_PREFERENCE.getString(PREF_USER_TOKEN, "");
    }

    public String getPrefDeviceId() {
        return SHARED_PREFERENCE.getString(PREF_DEVICE_ID, "");
    }

    public String getPrefVersionApp() {
        return SHARED_PREFERENCE.getString(PREF_VERSION_APP, "");
    }

    public String getPrefTokenPush() {
        return SHARED_PREFERENCE.getString(PREF_TOKEN_PUSH, "");
    }

    public String getPrefModelDevice() {
        return SHARED_PREFERENCE.getString(PREF_MODEL_DEVICE, "");
    }

    public String getPrefManufacturerDevice() {
        return SHARED_PREFERENCE.getString(PREF_MANUFACTURER_DEVICE, "");
    }

    public String getPrefAndroidVersion() {
        return SHARED_PREFERENCE.getString(PREF_ANDROID_VERSION, "");
    }

    public String getPrefAppName() {
        return SHARED_PREFERENCE.getString(PREF_APP_NAME, "");
    }

    public void logoutUser() {
        SHARED_PREFERENCE.edit().putBoolean(PREF_LOGGED_IN, false).apply();
        SHARED_PREFERENCE.edit().remove(PREF_USER_TOKEN).apply();
    }

    public void setPrefDeviceId(String deviceId) {
        SHARED_PREFERENCE.edit().putString(PREF_DEVICE_ID, deviceId).apply();
    }

    public void setPrefVersionApp(String versionApp) {
        SHARED_PREFERENCE.edit().putString(PREF_VERSION_APP, versionApp).apply();
    }

    public void setPrefTokenPush(String tokenPush) {
        SHARED_PREFERENCE.edit().putString(PREF_TOKEN_PUSH, tokenPush).apply();
    }

    public void setPrefModelDevice(String modelDevice) {
        SHARED_PREFERENCE.edit().putString(PREF_MODEL_DEVICE, modelDevice).apply();
    }

    public void setPrefManufacturerDevice(String manufacturerDevice) {
        SHARED_PREFERENCE.edit().putString(PREF_MANUFACTURER_DEVICE, manufacturerDevice).apply();
    }

    public void setPrefAndroidVersion(String androidVersion) {
        SHARED_PREFERENCE.edit().putString(PREF_ANDROID_VERSION, androidVersion).apply();
    }

    public void setPrefAppName(String appName) {
        SHARED_PREFERENCE.edit().putString(PREF_APP_NAME, appName).apply();
    }
}
