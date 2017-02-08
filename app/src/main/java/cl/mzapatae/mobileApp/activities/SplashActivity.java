package cl.mzapatae.mobileApp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import cl.mzapatae.mobileApp.R;
import cl.mzapatae.mobileApp.base.BaseActivity;
import cl.mzapatae.mobileApp.utils.DeviceInfo;
import cl.mzapatae.mobileApp.utils.LocalStorage;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        runSplash();
    }

    private void runSplash() {
        ConfigureApp();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (LocalStorage.isUserLogged()) {
                    initMainScreen();
                }
                else {
                    initLandingScreen();
                }
            }
        }, 1500); //Stay splash for 1.5 seg
    }

    private void initLandingScreen() {
        Intent intent = new Intent().setClass(SplashActivity.this, LandingActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finishAffinity();
    }


    private void initMainScreen() {
        Intent intent = new Intent().setClass(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finishAffinity();
    }

    private void ConfigureApp() {
        //Put Fabric config here!
        DeviceInfo deviceInfo = new DeviceInfo(this);
        saveDeviceInfoData(deviceInfo);
        deviceInfo.generateKeyhashSHA();
    }

    private void saveDeviceInfoData(DeviceInfo deviceInfo){
        LocalStorage.setPrefDeviceId(deviceInfo.getAndroidId());
        LocalStorage.setPrefVersionApp(deviceInfo.getVersionApp());
        LocalStorage.setPrefModelDevice(deviceInfo.getModelDevice());
        LocalStorage.setPrefManufacturerDevice(deviceInfo.getManufacturerDevice());
        LocalStorage.setPrefAndroidVersion(deviceInfo.getAndroidVersion());
        LocalStorage.setPrefAppName(deviceInfo.getAppName());
    }
}
