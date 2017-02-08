package cl.mzapatae.mobileApp.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import cl.mzapatae.mobileApp.R;
import cl.mzapatae.mobileApp.enums.Animation;

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

    /**
     * This method for simple fragment replace, add validation for avoid
     * java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState.
     * Trigger in example, when user load fragment from drawer menu and inmediate the navigation button "recent apps".
     *
     * @param fragment : Fragment to replace
     * @param animation : Animation for the transacion
     * @param addBackstack : Add or not the transaction to backstack
     * @param supportFragmentManager
     * @return true: if the transaction is commited, false: if transaction is not commited because the app is in background
     */
    public static boolean replaceFragmentTransaction(Fragment fragment, Animation animation, boolean addBackstack, FragmentManager supportFragmentManager) {
        try {
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            switch (animation) {
                case BLINK:
                    transaction.setCustomAnimations(R.anim.blink, R.anim.blink, R.anim.blink, R.anim.blink);
                    break;
                case SLIDE_TO_LEFT:
                    transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                    break;
                case SLIDE_TO_RIGHT:
                    transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                    break;
                case FADE:
                    transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                    break;
            }
            transaction.replace(R.id.fragment_container, fragment, fragment.getClass().getName());
            if (addBackstack) transaction.addToBackStack(fragment.getClass().getName());
            transaction.commit();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
            return false;
        }
    }
}
