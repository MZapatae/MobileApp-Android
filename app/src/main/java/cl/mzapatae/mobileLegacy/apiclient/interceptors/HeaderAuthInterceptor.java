package cl.mzapatae.mobileLegacy.apiclient.interceptors;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 17-01-17
 * E-mail: miguel.zapatae@gmail.com
 */

//TODO: Improve with two interceptor (Login with Authorization and Service with Token)
class HeaderAuthInterceptor implements Interceptor {
    private static final String TAG = "Auth Interceptor";

    private String mEmail = null;
    private String mPassword = null;
    private String mServerKey = null;
    private String mTokenUser = null;
    private String authName = null;

    HeaderAuthInterceptor(String tokenUser, String serverKey) {
        this.mTokenUser = tokenUser;
        this.mServerKey = serverKey;
        this.authName = "token-user";
    }

    HeaderAuthInterceptor(String email, String password, String serverKey) {
        this.mEmail = email;
        this.mPassword = password;
        this.mServerKey = serverKey;
        this.authName = "Authorization";
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request originalRequest = chain.request();

        String auth;
        if (mEmail != null && mPassword != null) {
            String credentials = mEmail + ":" + mPassword;
            auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        } else {
            auth = mTokenUser;
        }

        Request requestBuilder = originalRequest.newBuilder()
                .header(authName, auth)
                .header("api-key", mServerKey)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .method(originalRequest.method(), originalRequest.body())
                .build();

        return chain.proceed(requestBuilder);
    }
}