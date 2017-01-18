package cl.mzapatae.mobileLegacy.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cl.mzapatae.mobileLegacy.R;
import cl.mzapatae.mobileLegacy.apiclient.RestServices;
import cl.mzapatae.mobileLegacy.apiclient.RetrofitClient;
import cl.mzapatae.mobileLegacy.base.FragmentBase;
import cl.mzapatae.mobileLegacy.datamodel.objects.APIError;
import cl.mzapatae.mobileLegacy.datamodel.gson.AuthLoginResponse;
import cl.mzapatae.mobileLegacy.utils.DialogManager;
import cl.mzapatae.mobileLegacy.utils.FormValidator;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends FragmentBase {
    private static final String TAG = "Login Fragment";

    @BindView(R.id.edit_email) TextInputEditText mEditEmail;
    @BindView(R.id.edit_password) TextInputEditText mEditPassword;
    @BindView(R.id.button_login) Button mButtonLogin;
    @BindView(R.id.edit_layout_email) TextInputLayout mEditLayoutEmail;
    @BindView(R.id.edit_layout_password) TextInputLayout mEditLayoutPassword;

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
                    signInUser(mEditEmail.getText().toString(), mEditPassword.getText().toString());
                }
                break;
        }
    }

    private boolean isLoginValid() {
        String emailValue = mEditEmail.getText().toString().trim();
        String passwordValue = mEditPassword.getText().toString().trim();

        mEditEmail.setText(emailValue);
        mEditPassword.setText(passwordValue);

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
            mEditLayoutEmail.requestFocus();
            mEditPassword.setSelection(passwordValue.length());
            Log.d(TAG, "Validation: Fail!");
            Log.i(TAG, "Login Form is Invalid!");
            return false;
        } else {
            Log.d(TAG, "Validation: Success!");
            mEditLayoutPassword.setErrorEnabled(false);
        }
        Log.i(TAG, "Login Form is Valid!");
        return true;
    }

    private void signInUser(String email, String password) {
        final RestServices restServices = RetrofitClient.newConnection(RestServices.class, email, password, "PRUEBA_API");

        Call<AuthLoginResponse> call = restServices.loginUser("dummy");
        call.enqueue(new retrofit2.Callback<AuthLoginResponse>() {
            @Override
            public void onResponse(Call<AuthLoginResponse> call, Response<AuthLoginResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        DialogManager.showSimpleAlert(mContext, response.body().getMetaResponse().getMessage());
                    } else {
                        APIError error = RetrofitClient.parseHttpError(response);
                        DialogManager.showSimpleAlert(mContext, error.message());
                    }
                } catch (Exception e) {
                    DialogManager.showSimpleAlert(mContext, "Error: Response Corrupto");
                }
            }

            @Override
            public void onFailure(Call<AuthLoginResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                DialogManager.showSimpleAlert(mContext, t.getMessage());
            }
        });
    }
}