package cl.mzapatae.mobileApp.apiclient.interceptors;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 19-01-17
 * E-mail: miguel.zapatae@gmail.com
 */

public class TokenAuthInterceptor implements Interceptor{
    private static final String TAG = "TokenAuth Interceptor";
    private String mUserToken;


    public TokenAuthInterceptor(String token) {
        this.mUserToken = token;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        Request requestBuilder = originalRequest.newBuilder()
                .header("api-key-user", this.mUserToken)
                .method(originalRequest.method(), originalRequest.body())
                .build();

        return chain.proceed(requestBuilder);    }
}
