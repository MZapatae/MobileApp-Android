package cl.mzapatae.mobileApp.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Base Activity created to be extended by every Activity in this application.
 *
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 31-01-2017
 * E-mail: miguel.zapatae@gmail.com
 */

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "Base Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        BaseApplication.activityResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        BaseApplication.activityPaused();
    }
}
