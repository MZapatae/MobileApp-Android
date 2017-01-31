package cl.mzapatae.mobileApp.datamodel.gson;

import com.google.gson.annotations.SerializedName;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 30-01-17
 * E-mail: miguel.zapatae@gmail.com
 */

public class AuthRegisterResponse {
    @SerializedName("meta") private MetaResponse metaResponse;
    @SerializedName("response") private Response response;

    public class Response {
        @SerializedName("idresource") private String idResource;
        @SerializedName("token_user") private String tokenUser;

        public String getIdResource() {
            return idResource;
        }

        public void setIdResource(String idResource) {
            this.idResource = idResource;
        }

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
