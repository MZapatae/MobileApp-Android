package cl.mzapatae.mobileLegacy.apiclient.interceptors;

import java.io.IOException;

import cl.mzapatae.mobileLegacy.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 30-01-17
 * E-mail: miguel.zapatae@gmail.com
 */


public class RequestInterceptor implements Interceptor {
    private static final String TAG = "Request Interceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        Request requestBuilder = originalRequest.newBuilder()
                .header("api-key", BuildConfig.API_SERVER_KEY)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .method(originalRequest.method(), originalRequest.body())
                .build();

        return chain.proceed(requestBuilder);
    }
}
