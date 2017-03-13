package cl.mzapatae.mobileApp.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
import cl.mzapatae.mobileApp.datamodel.gson.AuthLoginResponse;
import cl.mzapatae.mobileApp.datamodel.objects.APIError;
import cl.mzapatae.mobileApp.helpers.Crypt;
import cl.mzapatae.mobileApp.utils.DialogManager;
import cl.mzapatae.mobileApp.utils.FormValidator;
import cl.mzapatae.mobileApp.utils.LocalStorage;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment {
    private static final String TAG = "Login Fragment";
    @BindView(R.id.editText_email) TextInputEditText mEditTextEmail;
    @BindView(R.id.editText_password) TextInputEditText mEditTextPassword;
    @BindView(R.id.button_login) Button mButtonLogin;
    @BindView(R.id.inputLayout_email) TextInputLayout mInputLayoutEmail;
    @BindView(R.id.inputLayout_password) TextInputLayout mInputLayoutPassword;

    private Context mContext;
    private String mUserUID = null;
    private String mUserEmail = null;
    private String mUserProviderID = null;
    private String mUserPhotoUrl = null;
    private String mUserToken = null;

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
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
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

    @OnClick({R.id.button_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                if (isLoginValid()) {
                    signInUser(mEditTextEmail.getText().toString(), mEditTextPassword.getText().toString());
                }
                break;
        }
    }

    private boolean isLoginValid() {
        String emailValue = mEditTextEmail.getText().toString().trim();
        String passwordValue = mEditTextPassword.getText().toString().trim();

        mEditTextEmail.setText(emailValue);
        mEditTextPassword.setText(passwordValue);

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
            mInputLayoutEmail.requestFocus();
            mEditTextPassword.setSelection(passwordValue.length());
            Log.d(TAG, "Validation: Fail!");
            Log.i(TAG, "Login Form is Invalid!");
            return false;
        } else {
            Log.d(TAG, "Validation: Success!");
            mInputLayoutPassword.setErrorEnabled(false);
        }
        Log.i(TAG, "Login Form is Valid!");
        return true;
    }

    private void signInUser(String email, String password) {
        try {
            final MaterialDialog loadingDialog = DialogManager.createLoadingDialog(getActivity()).build();
            final Handler runLoadingIndicator = new Handler();
            final Runnable showLoadingIndicator = new Runnable() {
                @Override
                public void run() {
                    loadingDialog.show();
                }
            };

            Crypt crypt = new Crypt();
            String encryptedPass = Base64.encodeToString(crypt.encrypt(password), Base64.NO_WRAP);

            RestServices restServices = RetrofitClient.setAuthConnection(RestServices.class, email, encryptedPass);
            Call<AuthLoginResponse> call = restServices.loginUser(null);
            call.enqueue(new RetrofitCallback<AuthLoginResponse>() {

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
                public void onRequestSuccess(Call<AuthLoginResponse> call, Response<AuthLoginResponse> response) {
                    LocalStorage.loginUser(
                            "0011",
                            mEditTextEmail.getText().toString(),
                            "Nombre",
                            "Apellido",
                            "Apodo",
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
                public void onRequestFailure(Call<AuthLoginResponse> call, APIError error) {
                    DialogManager.createErrorDialog(mContext, RetrofitClient.buildErrorMessage(error));
                }

                @Override
                public void onRequestError(Call<AuthLoginResponse> call, Throwable t) {
                    DialogManager.createErrorDialog(mContext, t.getMessage());
                }
            });
        } catch (Exception e) {
            DialogManager.createErrorDialog(mContext, e.getMessage());
        }
    }
}