package cl.mzapatae.mobileApp.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.animation.AnimationUtils;

import cl.mzapatae.mobileApp.R;

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

    public interface OnLockDrawerMenuListener {
        void onLockDrawerMenuStatus(boolean lock);
    }

    protected OnToolbarAddedListener setOnViewsCreatedListener(Context context) {
        try {
            return (OnToolbarAddedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnToolbarAddedListener");
        }
    }

    protected OnLockDrawerMenuListener setOnLockDrawerMenuListener(Context context) {
        try {
            return (OnLockDrawerMenuListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnLockDrawerMenuListener");
        }
    }
}
