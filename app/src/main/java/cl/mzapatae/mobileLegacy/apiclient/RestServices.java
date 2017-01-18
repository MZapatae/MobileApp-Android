package cl.mzapatae.mobileLegacy.apiclient;

import cl.mzapatae.mobileLegacy.datamodel.gson.AuthLoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 17-01-17
 * E-mail: miguel.zapatae@gmail.com
 */

public interface RestServices {
    /**
     * [POST] - Consulta Correo
     * @param dummy: Correo Electronico del Usuario
     *
     * @return
     */
    @FormUrlEncoded
    @POST("public/auth_loggin")
    Call<AuthLoginResponse> loginUser(
            @Field("value") String dummy
    );
}
