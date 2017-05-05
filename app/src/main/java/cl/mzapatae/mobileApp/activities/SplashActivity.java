package cl.mzapatae.mobileApp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TabHost;

import cl.mzapatae.mobileApp.R;
import cl.mzapatae.mobileApp.base.BaseActivity;
import cl.mzapatae.mobileApp.helpers.DeviceInfo;
import cl.mzapatae.mobileApp.utils.LocalStorage;

public class SplashActivity extends BaseActivity {
    private static String TAG = "Splash";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        runSplash(this, getIntent());
    }

    private void runSplash(final Context context, final Intent intent) {
        ConfigureApp();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (LocalStorage.getInstance(context).isUserLogged()) {
                    initMainScreen(intent);
                } else {
                    initLandingScreen();
                }
            }
        }, 1500); //Stay splash for 1.5 seg
    }

    private void initLandingScreen() {
        Intent intent = new Intent().setClass(SplashActivity.this, LandingActivity.class);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        startActivity(intent);
        finishAffinity();
    }

    private void initMainScreen(Intent intentParams) {
        Intent intent = new Intent().setClass(SplashActivity.this, MainActivity.class);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        intent.setAction(intentParams.getAction());
        intent.replaceExtras(intentParams);
        startActivity(intent);
        finishAffinity();
    }

    private void ConfigureApp() {
        //TODO: Put Fabric config here!
        DeviceInfo deviceInfo = new DeviceInfo(this);
        saveDeviceInfoData(deviceInfo);
        deviceInfo.generateKeyhashSHA();
        Log.d(TAG, "Saved Device TokenPush: " + LocalStorage.getInstance(this).getPrefTokenPush());
    }

    private void saveDeviceInfoData(DeviceInfo deviceInfo){
        LocalStorage.getInstance(this).setPrefDeviceId(deviceInfo.getAndroidId());
        LocalStorage.getInstance(this).setPrefVersionApp(deviceInfo.getVersionApp());
        LocalStorage.getInstance(this).setPrefModelDevice(deviceInfo.getModelDevice());
        LocalStorage.getInstance(this).setPrefManufacturerDevice(deviceInfo.getManufacturerDevice());
        LocalStorage.getInstance(this).setPrefAndroidVersion(deviceInfo.getAndroidVersion());
        LocalStorage.getInstance(this).setPrefAppName(deviceInfo.getAppName());
    }
}
