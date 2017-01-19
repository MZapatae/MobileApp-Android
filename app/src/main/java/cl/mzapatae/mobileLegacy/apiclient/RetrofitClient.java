package cl.mzapatae.mobileLegacy.apiclient;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

import cl.mzapatae.mobileLegacy.BuildConfig;
import cl.mzapatae.mobileLegacy.apiclient.interceptors.BasicAuthInterceptor;
import cl.mzapatae.mobileLegacy.apiclient.interceptors.JsonInterceptor;
import cl.mzapatae.mobileLegacy.apiclient.interceptors.TokenAuthInterceptor;
import cl.mzapatae.mobileLegacy.datamodel.objects.APIError;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 *          Created on: 17-01-17
 *          E-mail: miguel.zapatae@gmail.com
 */

public class RetrofitClient {
    private static Retrofit mRetrofit;

    private static OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder()
            .addInterceptor(new JsonInterceptor())
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS);

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S setAuthConnection(Class<S> serviceClass, String user, String password) {
        OkHttpClient client = httpBuilder.addInterceptor(new BasicAuthInterceptor(user, password)).build();
        mRetrofit = retrofitBuilder.client(client).build();
        return mRetrofit.create(serviceClass);
    }

    public static <S> S setAuthConnection(Class<S> serviceClass, String userToken) {
        OkHttpClient client = httpBuilder.addInterceptor(new TokenAuthInterceptor(userToken)).build();
        mRetrofit = retrofitBuilder.client(client).build();
        return mRetrofit.create(serviceClass);
    }

    public static APIError parseHttpError(Response<?> response) {
        Converter<ResponseBody, APIError> converter = mRetrofit.responseBodyConverter(APIError.class, new Annotation[0]);
        APIError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            e.printStackTrace();
            return new APIError(-1, "Error Desconocido");
        }
        return error;
    }
}
