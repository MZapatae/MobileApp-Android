package cl.mzapatae.mobileLegacy.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cl.mzapatae.mobileLegacy.R;
import cl.mzapatae.mobileLegacy.utils.FormValidator;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    private static final String TAG = "MobileApp - Register";

    @BindView(R.id.text_title) TextView mTextTitle;
    @BindView(R.id.edit_name) TextInputEditText mEditName;
    @BindView(R.id.edit_lastname) TextInputEditText mEditLastname;
    @BindView(R.id.edit_email) TextInputEditText mEditEmail;
    @BindView(R.id.edit_username) TextInputEditText mEditUsername;
    @BindView(R.id.edit_password) TextInputEditText mEditPassword;
    @BindView(R.id.button_register) Button mButtonRegister;
    @BindView(R.id.edit_layout_name) TextInputLayout mEditLayoutName;
    @BindView(R.id.edit_layout_lastname) TextInputLayout mEditLayoutLastname;
    @BindView(R.id.edit_layout_email) TextInputLayout mEditLayoutEmail;
    @BindView(R.id.edit_layout_username) TextInputLayout mEditLayoutUsername;
    @BindView(R.id.edit_layout_password) TextInputLayout mEditLayoutPassword;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        mContext = getContext();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @OnClick({R.id.button_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_register:
                if (isRegisterValid()) {
                    registerUser(mEditEmail.getText().toString(), mEditPassword.getText().toString());
                }
                break;
        }
    }

    private boolean isRegisterValid() {
        String nameValue = mEditName.getText().toString().trim();
        String lastnameValue = mEditLastname.getText().toString().trim();
        String usernameValue = mEditUsername.getText().toString().trim();
        String emailValue = mEditEmail.getText().toString().trim();
        String passwordValue = mEditPassword.getText().toString().trim();

        mEditName.setText(nameValue);
        mEditLastname.setText(lastnameValue);
        mEditUsername.setText(usernameValue);
        mEditEmail.setText(emailValue);
        mEditPassword.setText(passwordValue);

        Log.d(TAG, "Validate Name: " + nameValue);
        if (!FormValidator.isValidName(nameValue)) {
            mEditLayoutName.setError(getString(R.string.error_invalid_first_name));
            mEditLayoutName.requestFocus();
            mEditName.setSelection(nameValue.length());
            Log.d(TAG, "Validation: Fail!");
            Log.i(TAG, "Login Form is Invalid!");
            return false;
        } else {
            Log.d(TAG, "Validation: Success!");
            mEditLayoutName.setErrorEnabled(false);
        }

        Log.d(TAG, "Validate LastName: " + lastnameValue);
        if (!FormValidator.isValidLastName(lastnameValue)) {
            mEditLayoutLastname.setError(getString(R.string.error_invalid_last_name));
            mEditLayoutLastname.requestFocus();
            mEditLastname.setSelection(lastnameValue.length());
            Log.d(TAG, "Validation: Fail!");
            Log.i(TAG, "Login Form is Invalid!");
            return false;
        } else {
            Log.d(TAG, "Validation: Success!");
            mEditLayoutLastname.setErrorEnabled(false);
        }

        Log.d(TAG, "Validate Username: " + usernameValue);
        if (!FormValidator.isValidUsername(usernameValue)) {
            mEditLayoutUsername.setError(getString(R.string.error_invalid_user_name));
            mEditLayoutUsername.requestFocus();
            mEditUsername.setSelection(usernameValue.length());
            Log.d(TAG, "Validation: Fail!");
            Log.i(TAG, "Login Form is Invalid!");
            return false;
        } else {
            Log.d(TAG, "Validation: Success!");
            mEditLayoutUsername.setErrorEnabled(false);
        }

        Log.d(TAG, "Validate Email: " + emailValue);
        if (!FormValidator.isValidEmail(emailValue)) {
            mEditLayoutEmail.setError(getString(R.string.error_invalid_email));
            mEditLayoutEmail.requestFocus();
            mEditEmail.setSelection(emailValue.length());
            Log.d(TAG, "Validation: Fail!");
            Log.i(TAG, "Login Form is Invalid!");
            return false;
        } else {
            Log.d(TAG, "Validation: Success!");
            mEditLayoutEmail.setErrorEnabled(false);
        }

        Log.d(TAG, "Validate Password: ********");
        if (!FormValidator.isValidPassword(passwordValue)) {
            mEditLayoutPassword.setError(getString(R.string.error_invalid_password));
            mEditLayoutPassword.requestFocus();
            mEditPassword.setSelection(passwordValue.length());
            Log.d(TAG, "Validation: Fail!");
            Log.i(TAG, "Login Form is Invalid!");
            return false;
        } else {
            Log.d(TAG, "Validation: Success!");
            mEditLayoutPassword.setErrorEnabled(false);
        }
        Log.i(TAG, "Register Form is Valid!");
        return true;
    }


    private void registerUser(String email, String password) {

    }
}