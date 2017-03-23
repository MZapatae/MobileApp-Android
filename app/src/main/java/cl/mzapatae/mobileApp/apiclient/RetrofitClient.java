package cl.mzapatae.mobileApp.apiclient;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import cl.mzapatae.mobileApp.BuildConfig;
import cl.mzapatae.mobileApp.apiclient.interceptors.BasicAuthInterceptor;
import cl.mzapatae.mobileApp.apiclient.interceptors.RequestInterceptor;
import cl.mzapatae.mobileApp.apiclient.interceptors.TokenAuthInterceptor;
import cl.mzapatae.mobileApp.datamodel.gson.ErrorResponse;
import cl.mzapatae.mobileApp.datamodel.objects.APIError;
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
 * Created on: 17-01-17
 * E-mail: miguel.zapatae@gmail.com
 */

public class RetrofitClient {
    private static final RetrofitClient instance = new RetrofitClient();
    private OkHttpClient.Builder mHttpBuilder;
    private static Retrofit.Builder mRetrofitBuilder;


    public static RetrofitClient getInstance() {
        return instance;
    }
    //TODO: The 'RequestInterceptor()' add a Header with 'api-key' value for ServerAuth defined in file gradle.properties. Remove if necesary

    private RetrofitClient() {
        mHttpBuilder = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .addInterceptor(new RequestInterceptor())
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS);

        mRetrofitBuilder = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE)
                .addConverterFactory(GsonConverterFactory.create());
    }

    public <S> S setAuthConnection(Class<S> serviceClass, String user, String password) {
        OkHttpClient client = mHttpBuilder.addInterceptor(new BasicAuthInterceptor(user, password)).build();
        return mRetrofitBuilder.client(client).build().create(serviceClass);
    }

    public <S> S setAuthConnection(Class<S> serviceClass, String userToken) {
        OkHttpClient client = mHttpBuilder.addInterceptor(new TokenAuthInterceptor(userToken)).build();
        return mRetrofitBuilder.client(client).build().create(serviceClass);
    }

    public <S> S setConnection(Class<S> serviceClass) {
        OkHttpClient client = mHttpBuilder.build();
        return mRetrofitBuilder.client(client).build().create(serviceClass);
    }

    public static APIError parseHttpError(Response<?> response) {
        Converter<ResponseBody, APIError> converter = mRetrofitBuilder.build()
                .responseBodyConverter(APIError.class, new Annotation[0]);
        APIError error;
        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            e.printStackTrace();
            return new APIError(500, "Error no reconocido", new ArrayList<ErrorResponse>());
        }
        return error;
    }

    public static String buildErrorMessage(APIError error) {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append(error.message());
        if (!error.errorList().isEmpty()) {
            errorMessage.append("\n\n");
            for (ErrorResponse errorDetail : error.errorList()) {
                errorMessage.append(errorDetail.getMessage());
                errorMessage.append("\n");
            }
        }
        return errorMessage.toString();
    }
}
