package cl.mzapatae.mobileApp.datamodel.gson;

import com.google.gson.annotations.SerializedName;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 17-01-17
 * E-mail: miguel.zapatae@gmail.com
 */

public class AuthLoginResponse {
    @SerializedName("meta") private MetaResponse metaResponse;
    @SerializedName("response") private Response response;

    public class Response {
        @SerializedName("token_user") private String tokenUser;

        public String getTokenUser() {
            return tokenUser;
        }

        public void setTokenUser(String tokenUser) {
            this.tokenUser = tokenUser;
        }
    }

    public MetaResponse getMetaResponse() {
        return metaResponse;
    }

    public void setMetaResponse(MetaResponse metaResponse) {
        this.metaResponse = metaResponse;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
