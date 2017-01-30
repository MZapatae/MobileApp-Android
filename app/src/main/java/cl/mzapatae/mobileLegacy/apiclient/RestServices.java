package cl.mzapatae.mobileLegacy.apiclient;

import cl.mzapatae.mobileLegacy.datamodel.gson.AuthLoginResponse;
import cl.mzapatae.mobileLegacy.datamodel.gson.AuthRegisterResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 17-01-17
 * E-mail: miguel.zapatae@gmail.com
 */

public interface RestServices {
    /**
     * [POST] - Registro de Usuario
     * @param usuario: Correo Electronico del Usuario
     * @param password: Contraseña del usuario
     *
     * @return idresource: Id del usuario creado
     */
    @FormUrlEncoded
    @POST("public/auth_register")
    Call<AuthRegisterResponse> registerUser(
            @Field("usuario") String usuario,
            @Field("password") String password
    );

    /**
     * [POST] - Login de Usuario
     * @param dummyParameter: Parametro de Login a pasar
     * @return token_user: Token del usuario de la aplicacion
     */
    @FormUrlEncoded
    @POST("public/auth_loggin")
    Call<AuthLoginResponse> loginUser(
            @Field("dummy") String dummyParameter
    );
}
