package cl.mzapatae.mobileApp.helpers.transitions;

import android.annotation.TargetApi;
import android.os.Build;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;

import cl.mzapatae.mobileApp.R;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 09-02-2017
 * E-mail: miguel.zapatae@gmail.com
 *
 */

//TODO: Add, remove or change transitions and targets if necesary
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class FragmentsDrawerTransitionSet extends TransitionSet {

    public FragmentsDrawerTransitionSet() {
        setOrdering(ORDERING_TOGETHER);

        ChangeColorTransition changeColor = new ChangeColorTransition();
        ChangeBounds changeBounds = new ChangeBounds();
        ChangeClipBounds changeClipBounds = new ChangeClipBounds();
        ChangeTransform changeTransform = new ChangeTransform();

        addTransition(changeBounds)
                .addTransition(changeTransform)
                .addTransition(changeClipBounds)
                .addTransition(changeColor)
                .addTarget(R.id.toolbar);
    }


}
