package cl.mzapatae.mobileLegacy.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cl.mzapatae.mobileLegacy.R;
import cl.mzapatae.mobileLegacy.base.FragmentBase;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends FragmentBase {
    private static final String TAG = "Login Fragment";

    @BindView(R.id.edit_email) EditText mEditEmail;
    @BindView(R.id.edit_password) EditText mEditPassword;
    @BindView(R.id.button_login) Button mButtonLogin;

    private Context mContext;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @OnClick({R.id.button_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                loginUser(mEditEmail.getText().toString(), mEditPassword.getText().toString());
                break;
        }
    }

    private void loginUser(String email, String password) {

    }
}
