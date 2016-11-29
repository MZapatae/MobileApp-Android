package cl.mzapatae.mobileLegacy.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class, return all device data. Version Apps, Model Name, Android id, Android Version
 *
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 25-11-16
 * E-mail: miguel.zapatae@gmail.com
 */

public class DeviceInfo {
    private static final String TAG = "Device Info";

    private Context mContext;
    private String mAndroidId;
    private String mAndroidVersion;
    private String mVersionApp;
    private String mModelDevice;
    private String mManufacturerDevice;
    private String mAppName;
    private int mVersionCode;

    public DeviceInfo(Context context){
        this.mContext = context;

        PackageInfo pInfo;
        try {
            pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            mAndroidId = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
            mVersionApp = pInfo.versionName;
            mVersionCode = pInfo.versionCode;
            mModelDevice = android.os.Build.MODEL;
            mManufacturerDevice = Build.MANUFACTURER;
            mAndroidVersion = Build.VERSION.RELEASE;
            mAppName = getApplicationName(context);

            Log.i(TAG, "Android ID: " + mAndroidId);
            Log.i(TAG, "Version BaseApplication: " + mVersionApp);
            Log.i(TAG, "Model Device: " + mModelDevice);
            Log.i(TAG, "Manufacturer Device: " + mManufacturerDevice);
            Log.i(TAG, "Android Version: " + mAndroidVersion);
            Log.i(TAG, "App Name: " + mAppName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void generateKeyhashSHA() {
        PackageInfo info;
        try {
            info = mContext.getPackageManager().getPackageInfo(Constants.PACKAGE_APP, PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.d("Android SHA HashKey", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }

    private static String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }


    public String getAndroidId() {
        return mAndroidId;
    }

    public String getVersionApp() {
        return mVersionApp;
    }

    public int getVersionCode() {
        return mVersionCode;
    }

    public String getModelDevice() {
        return mModelDevice;
    }

    public String getManufacturerDevice() {
        return mManufacturerDevice;
    }

    public String getAndroidVersion() {
        return mAndroidVersion;
    }

    public String getAppName() {
        return mAppName;
    }
}
