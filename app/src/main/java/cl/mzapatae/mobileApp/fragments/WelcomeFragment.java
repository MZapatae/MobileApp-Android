package cl.mzapatae.mobileApp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cl.mzapatae.mobileApp.R;
import cl.mzapatae.mobileApp.base.BaseFragment;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 05-14-17
 * E-mail: miguel.zapatae@gmail.com
 */

public class WelcomeFragment extends BaseFragment {
    private static final String TAG = "Welcome FRG";

    @BindView(R.id.button_login) Button mButtonLogin;
    @BindView(R.id.button_signup) Button mButtonSignup;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    public static WelcomeFragment newInstance() {
        return new WelcomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.button_login, R.id.button_signup})
    public void onClick(View view) {
        try {
            Fragment fragment = null;
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            switch (view.getId()) {
                case R.id.button_login:
                    fragment = getFragmentManager().findFragmentByTag(LoginFragment.class.getName());
                    if (!LoginFragment.class.isInstance(fragment))
                        fragment = LoginFragment.newInstance();

                    transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                    transaction.addToBackStack(fragment.getClass().getName());
                    break;

                case R.id.button_signup:
                    fragment = getFragmentManager().findFragmentByTag(RegisterFragment.class.getName());
                    if (!RegisterFragment.class.isInstance(fragment))
                        fragment = RegisterFragment.newInstance();

                    transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                    transaction.addToBackStack(fragment.getClass().getName());
                    break;
            }

            transaction.replace(R.id.fragment_container, fragment);
            transaction.commit();
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

}
