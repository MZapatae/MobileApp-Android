package cl.mzapatae.mobileApp.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import cl.mzapatae.mobileApp.R;
import cl.mzapatae.mobileApp.enums.Animation;

/**
 * Base fragment created to be extended by every fragment in this application. This class provides
 * fragments transaction and some methods
 * common to every fragment.
 *
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 25-11-16
 * E-mail: miguel.zapatae@gmail.com
 */

public class FragmentBase extends Fragment {

    public void replaceFragment(Fragment fragment, String tag, Animation animation, boolean addBackstack) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
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
        transaction.replace(R.id.fragment_container, fragment, tag);
        if (addBackstack) transaction.addToBackStack(tag);
        transaction.commit();
    }
}
