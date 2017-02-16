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
                onSuccess(call, response);
            } else {
                APIError error = RetrofitClient.parseHttpError(response);
                onFailure(call, error);
            }
        } catch (Exception e) {
            onError(call, e);
        }
        onFinish();
    }

    public final void onFailure(Call<T> call, Throwable t) {
        onError(call, t);
        onFinish();
    }

    public abstract void onStart();

    public abstract void onFinish();

    public abstract void onSuccess(Call<T> call, Response<T> response);

    public abstract void onFailure(Call<T> call, APIError error);

    public abstract void onError(Call<T> call, Throwable t);

}






