package cl.mzapatae.mobileApp.apiclient;

import cl.mzapatae.mobileApp.datamodel.objects.APIError;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 15-02-17
 * E-mail: miguel.zapatae@gmail.com
 */


public abstract class RetrofitCallback<T> implements Callback<T> {
    private static final String TAG = "Retrofit Callback";

    protected RetrofitCallback() {
        onStart();
    }

    public final void onResponse(Call<T> call, Response<T> response) {
        try {
            if (response.isSuccessful()) {
                onRequestSuccess(call, response);
            } else {
                APIError error = RetrofitClient.parseHttpError(response);
                onRequestFailure(call, error);
            }
        } catch (Exception e) {
            onRequestError(call, e);
        }
        onFinish();
    }

    public final void onFailure(Call<T> call, Throwable t) {
        onRequestError(call, t);
        onFinish();
    }

    public abstract void onStart();

    public abstract void onFinish();

    public abstract void onRequestSuccess(Call<T> call, Response<T> response);

    public abstract void onRequestFailure(Call<T> call, APIError error);

    public abstract void onRequestError(Call<T> call, Throwable t);

}






