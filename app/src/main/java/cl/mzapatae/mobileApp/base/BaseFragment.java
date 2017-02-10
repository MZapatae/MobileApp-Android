package cl.mzapatae.mobileApp.base;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

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

public class BaseFragment extends Fragment {
    private static final String TAG = "Base Fragment";

    public interface OnFragmentLoadedListener {
        void onToolbarViewLoaded(Toolbar toolbar);
        void onConstraintLayoutLoaded(ConstraintLayout constraintLayout);
    }





}
