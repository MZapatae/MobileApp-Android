package cl.mzapatae.mobileApp.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import cl.mzapatae.mobileApp.utils.LocalStorage;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 26-04-17
 * E-mail: miguel.zapatae@gmail.com
 */


public class FcmInitService extends FirebaseInstanceIdService {
    private static final String TAG = "FCM Init Service";

    @Override
    public void onTokenRefresh() {
        String fcmToken = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "New Firebase Push Token:" + fcmToken);
        LocalStorage.getInstance(getApplicationContext()).setPrefTokenPush(fcmToken);
        //TODO: Save Token in Backend
        //TODO: Generate and add the file "google-services.json" into folder root/app
    }
}
