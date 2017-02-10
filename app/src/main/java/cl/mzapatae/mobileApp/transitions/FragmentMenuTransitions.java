package cl.mzapatae.mobileApp.transitions;

import android.annotation.TargetApi;
import android.os.Build;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 09-02-2017
 * E-mail: miguel.zapatae@gmail.com
 */


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class FragmentMenuTransitions extends TransitionSet {

    public FragmentMenuTransitions() {
        setOrdering(ORDERING_TOGETHER);
        addTransition(new ChangeBounds())
                .addTransition(new ChangeTransform())
                .addTransition(new ChangeClipBounds());
    }
}
