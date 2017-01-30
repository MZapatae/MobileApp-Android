package cl.mzapatae.mobileLegacy.apiclient.interceptors;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 19-01-17
 * E-mail: miguel.zapatae@gmail.com
 */

import android.util.Base64;

import java.io.IOException;

import cl.mzapatae.mobileLegacy.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuthInterceptor implements Interceptor {
    private static final String TAG = "BasicAuth Interceptor";
    private String mUser;
    private String mPass;


    public BasicAuthInterceptor(String user, String password) {
        this.mUser = user;
        this.mPass = password;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        String authKey = generateAuthKey(mUser, mPass);

        Request requestBuilder = originalRequest.newBuilder()
                .header("Authorization", authKey)
                .method(originalRequest.method(), originalRequest.body())
                .build();

        return chain.proceed(requestBuilder);
    }

    private String generateAuthKey(String user, String pass) {
        String credential = user + ":" + pass;
        return "Basic " + Base64.encodeToString(credential.getBytes(), Base64.NO_WRAP);
    }
}
