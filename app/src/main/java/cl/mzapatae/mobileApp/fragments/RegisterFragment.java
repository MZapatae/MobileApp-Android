package cl.mzapatae.mobileApp.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cl.mzapatae.mobileApp.R;
import cl.mzapatae.mobileApp.activities.MainActivity;
import cl.mzapatae.mobileApp.apiclient.RestServices;
import cl.mzapatae.mobileApp.apiclient.RetrofitCallback;
import cl.mzapatae.mobileApp.apiclient.RetrofitClient;
import cl.mzapatae.mobileApp.base.BaseFragment;
import cl.mzapatae.mobileApp.datamodel.gson.AuthRegisterResponse;
import cl.mzapatae.mobileApp.datamodel.objects.APIError;
import cl.mzapatae.mobileApp.utils.DialogManager;
import cl.mzapatae.mobileApp.utils.FormValidator;
import cl.mzapatae.mobileApp.utils.LocalStorage;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 05-14-17
 * E-mail: miguel.zapatae@gmail.com
 */

public class RegisterFragment extends BaseFragment {
    private static final String TAG = "MobileApp - Register";
    @BindView(R.id.text_title) TextView mTextTitle;
    @BindView(R.id.editText_name) TextInputEditText mEditTextName;
    @BindView(R.id.editText_lastname) TextInputEditText mEditTextLastname;
    @BindView(R.id.editText_email) TextInputEditText mEditTextEmail;
    @BindView(R.id.editText_username) TextInputEditText mEditTextUsername;
    @BindView(R.id.editText_password) TextInputEditText mEditTextPassword;
    @BindView(R.id.button_register) Button mButtonRegister;
    @BindView(R.id.inputLayout_name) TextInputLayout mInputLayoutName;
    @BindView(R.id.inputLayout_lastname) TextInputLayout mInputLayoutLastname;
    @BindView(R.id.inputLayout_email) TextInputLayout mInputLayoutEmail;
    @BindView(R.id.inputLayout_username) TextInputLayout mInputLayoutUsername;
    @BindView(R.id.inputLayout_password) TextInputLayout mInputLayoutPassword;

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
                    registerUser(mEditTextEmail.getText().toString(), mEditTextPassword.getText().toString());
                }
                break;
        }
    }

    private boolean isRegisterValid() {
        String nameValue = mEditTextName.getText().toString().trim();
        String lastnameValue = mEditTextLastname.getText().toString().trim();
        String usernameValue = mEditTextUsername.getText().toString().trim();
        String emailValue = mEditTextEmail.getText().toString().trim();
        String passwordValue = mEditTextPassword.getText().toString().trim();

        mEditTextName.setText(nameValue);
        mEditTextLastname.setText(lastnameValue);
        mEditTextUsername.setText(usernameValue);
        mEditTextEmail.setText(emailValue);
        mEditTextPassword.setText(passwordValue);

        Log.d(TAG, "Validate Name: " + nameValue);
        if (!FormValidator.isValidName(nameValue)) {
            mInputLayoutName.setError(getString(R.string.error_invalid_first_name));
            mInputLayoutName.requestFocus();
            mEditTextName.setSelection(nameValue.length());
            Log.d(TAG, "Validation: Fail!");
            Log.i(TAG, "Login Form is Invalid!");
            return false;
        } else {
            Log.d(TAG, "Validation: Success!");
            mInputLayoutName.setErrorEnabled(false);
        }

        Log.d(TAG, "Validate LastName: " + lastnameValue);
        if (!FormValidator.isValidLastName(lastnameValue)) {
            mInputLayoutLastname.setError(getString(R.string.error_invalid_last_name));
            mInputLayoutLastname.requestFocus();
            mEditTextLastname.setSelection(lastnameValue.length());
            Log.d(TAG, "Validation: Fail!");
            Log.i(TAG, "Login Form is Invalid!");
            return false;
        } else {
            Log.d(TAG, "Validation: Success!");
            mInputLayoutLastname.setErrorEnabled(false);
        }

        Log.d(TAG, "Validate Username: " + usernameValue);
        if (!FormValidator.isValidUsername(usernameValue)) {
            mInputLayoutUsername.setError(getString(R.string.error_invalid_user_name));
            mInputLayoutUsername.requestFocus();
            mEditTextUsername.setSelection(usernameValue.length());
            Log.d(TAG, "Validation: Fail!");
            Log.i(TAG, "Login Form is Invalid!");
            return false;
        } else {
            Log.d(TAG, "Validation: Success!");
            mInputLayoutUsername.setErrorEnabled(false);
        }

        Log.d(TAG, "Validate Email: " + emailValue);
        if (!FormValidator.isValidEmail(emailValue)) {
            mInputLayoutEmail.setError(getString(R.string.error_invalid_email));
            mInputLayoutEmail.requestFocus();
            mEditTextEmail.setSelection(emailValue.length());
            Log.d(TAG, "Validation: Fail!");
            Log.i(TAG, "Login Form is Invalid!");
            return false;
        } else {
            Log.d(TAG, "Validation: Success!");
            mInputLayoutEmail.setErrorEnabled(false);
        }

        Log.d(TAG, "Validate Password: ********");
        if (!FormValidator.isValidPassword(passwordValue)) {
            mInputLayoutPassword.setError(getString(R.string.error_invalid_password));
            mInputLayoutPassword.requestFocus();
            mEditTextPassword.setSelection(passwordValue.length());
            Log.d(TAG, "Validation: Fail!");
            Log.i(TAG, "Login Form is Invalid!");
            return false;
        } else {
            Log.d(TAG, "Validation: Success!");
            mInputLayoutPassword.setErrorEnabled(false);
        }
        Log.i(TAG, "Register Form is Valid!");
        return true;
    }

    private void registerUser(String email, String password) {
        final MaterialDialog loadingDialog = DialogManager.createLoadingDialog(getActivity()).build();
        final Handler runLoadingIndicator = new Handler();
        final Runnable showLoadingIndicator = new Runnable() {
            @Override
            public void run() {
                loadingDialog.show();
            }
        };

        RestServices restServices = RetrofitClient.getInstance().setConnection(RestServices.class);
        Call<AuthRegisterResponse> call = restServices.registerUser(email, password);
        call.enqueue(new RetrofitCallback<AuthRegisterResponse>() {

            @Override
            public void onStart() {
                runLoadingIndicator.postDelayed(showLoadingIndicator, 600);
            }

            @Override
            public void onFinish() {
                runLoadingIndicator.removeCallbacks(showLoadingIndicator);
                if (loadingDialog.isShowing()) loadingDialog.dismiss();
            }

            @Override
            public void onRequestSuccess(Call<AuthRegisterResponse> call, Response<AuthRegisterResponse> response) {
                LocalStorage.loginUser(
                        response.body().getResponse().getIdResource(),
                        mEditTextEmail.getText().toString(),
                        mEditTextName.getText().toString(),
                        mEditTextLastname.getText().toString(),
                        mEditTextUsername.getText().toString(),
                        "",
                        "EmailProvider",
                        response.body().getResponse().getTokenUser()
                );

                Intent intent = new Intent().setClass(mContext, MainActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                getActivity().finishAffinity();
            }

            @Override
            public void onRequestFailure(Call<AuthRegisterResponse> call, APIError error) {
                DialogManager.showErrorDialog(mContext, RetrofitClient.buildErrorMessage(error));
            }

            @Override
            public void onRequestError(Call<AuthRegisterResponse> call, Throwable t) {
                DialogManager.showErrorDialog(mContext, t.getMessage());
            }
        });
    }
}