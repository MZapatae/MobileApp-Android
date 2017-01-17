package cl.mzapatae.mobileLegacy.apiclient;

import android.util.Base64;
import android.util.Log;

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

class HeaderAuthInterceptor implements Interceptor {
    private static final String TAG = "Loggin Interceptor";

    private String mEmail;
    private String mPassword;

    HeaderAuthInterceptor(String email, String password) {
        this.mEmail = email;
        this.mPassword = password;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String credentials = mEmail + ":" + mPassword;
        String basicAuth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

        Log.d(TAG, "Authorization: " + basicAuth);
        Request originalRequest = chain.request();
        Request.Builder requestBuilder = originalRequest.newBuilder()
                .header("Authorization", basicAuth)
                .header("API_KEY", "PRUEBA_API")
                .header("Accept", "application/json")
                .method(originalRequest.method(), originalRequest.body());

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}