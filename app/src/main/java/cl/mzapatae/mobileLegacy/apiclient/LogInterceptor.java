package cl.mzapatae.mobileLegacy.apiclient;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
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

class LogInterceptor implements Interceptor {
    private static final String TAG = "Loggin Interceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {
        try {
            Log.i(TAG, "Inside intercept callback");
            Request request = chain.request();
            long t1 = System.nanoTime();

            String requestLog = String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers());
            if (request.method().compareToIgnoreCase("post") == 0) {
                requestLog = "\n" + requestLog + "\n" + bodyToString(request);
            }

            Log.d(TAG, "Start Request" + "\n" + requestLog);
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            String responseLog = String.format(Locale.US,"Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers());

            String bodyString = response.body().string();
            Log.d("TAG", "Getting Log Response" + "\n" + responseLog + "\n");
            Log.d("TAG", "Getting String Response:" + "\n" + bodyString);

            Gson gson = new GsonBuilder().setPrettyPrinting().setLenient().create();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(bodyString);
            String prettyJsonString = gson.toJson(je);

            Log.d("TAG", "=================================== JSON Response ===================================");
            Log.d("TAG", "Json Respone: " + prettyJsonString);
            return response.newBuilder().body(ResponseBody.create(response.body().contentType(), bodyString)).build();
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
            return null;
            /*
            new MaterialDialog.Builder(get)
                    .title(R.string.dialog_title_alert)
                    .content(t.getMessage())
                    .positiveText(R.string.button_accept)
                    .show();*/
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