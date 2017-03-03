package cl.mzapatae.mobileApp.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.animation.AnimationUtils;

import cl.mzapatae.mobileApp.R;
import cl.mzapatae.mobileApp.helpers.enums.Animation;

/**
 * Base fragment created to be extended for fragments loaded with DrawerMenu
 *
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 25-11-16
 * E-mail: miguel.zapatae@gmail.com
 */

public class BaseFragment extends Fragment {
    private static final String TAG = "Base Fragment";

    public interface OnToolbarAddedListener {
        void onToolbarAdded(Toolbar toolbar);
    }

    protected OnToolbarAddedListener setOnViewsCreatedListener(Context context) {
        try {
            return (OnToolbarAddedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnToolbarAddedListener");
        }
    }

    @Override
    public android.view.animation.Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter) {
            return AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        } else {
            return AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
        }
    }
}
