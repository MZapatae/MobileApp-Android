package cl.mzapatae.mobileApp.base;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

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

    public interface OnViewsCreatedListener {
        void onToolbarViewLoaded(Toolbar toolbar);
        void onConstraintLayoutLoaded(ConstraintLayout constraintLayout);
    }

    protected OnViewsCreatedListener setOnViewsCreatedListener(Context context) {
        try {
            return (OnViewsCreatedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnViewsCreatedListener");
        }
    }
}
