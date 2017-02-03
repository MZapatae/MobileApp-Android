package cl.mzapatae.mobileApp.apiclient.interceptors;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 17-01-17
 * E-mail: miguel.zapatae@gmail.com
 */

public class JsonInterceptor implements Interceptor {
    private static final String TAG = "Json Interceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {
        try {
            Log.i(TAG, "Inside intercept callback");
            Request request = chain.request();

            String requestLog = String.format("Sending request to %s", request.url());
            if (request.method().compareToIgnoreCase("post") == 0) {
                requestLog = requestLog + "\n" + "POST Params:\n" + bodyToString(request);
            }

            Log.d(TAG, "Start Request" + "\n" + requestLog);
            Response response = chain.proceed(request);

            String bodyString = response.body().string();
            Log.d("TAG", "Getting String Response:" + "\n" + bodyString);

            Gson gson = new GsonBuilder().setPrettyPrinting().setLenient().create();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(bodyString);
            String prettyJsonString = gson.toJson(je);

            Log.d("TAG", "=================================== JSON Response ===================================");
            Log.d("TAG", "Json Respone: " + prettyJsonString);
            return response.newBuilder().body(ResponseBody.create(response.body().contentType(), bodyString)).build();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            Request request = chain.request();
            Response response = chain.proceed(request);
            return response.newBuilder().body(ResponseBody.create(response.body().contentType(), "{}")).build();
        }
    }


    private static String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}