package cl.mzapatae.mobileLegacy.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cl.mzapatae.mobileLegacy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    @BindView(R.id.text_title) TextView mTextTitle;
    @BindView(R.id.edit_name) EditText mEditName;
    @BindView(R.id.edit_lastname) EditText mEditLastname;
    @BindView(R.id.edit_email) EditText mEditEmail;
    @BindView(R.id.edit_borndate) EditText mEditUsername;
    @BindView(R.id.edit_password) EditText mEditPassword;
    @BindView(R.id.button_register) Button mButtonRegister;

    private Context mContext;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        mContext = getContext();

        return view;
    }

    @OnClick(R.id.button_register)
    public void onClick() {
        registerUser(mEditName.getText().toString(),
                    mEditLastname.getText().toString(),
                    mEditUsername.getText().toString(),
                    mEditEmail.getText().toString(),
                    mEditPassword.getText().toString());

    }

    private void registerUser(String firstName, String lastName, String userName, String email, String password) {

    }
}
