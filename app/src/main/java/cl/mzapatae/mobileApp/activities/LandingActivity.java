package cl.mzapatae.mobileApp.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import cl.mzapatae.mobileApp.R;
import cl.mzapatae.mobileApp.base.BaseActivity;
import cl.mzapatae.mobileApp.fragments.WelcomeFragment;

public class LandingActivity extends BaseActivity {
    private static final String TAG = "Landing Screen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = WelcomeFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
    }
}

