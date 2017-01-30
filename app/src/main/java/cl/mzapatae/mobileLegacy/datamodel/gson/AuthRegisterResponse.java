package cl.mzapatae.mobileLegacy.datamodel.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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

        public String getIdResource() {
            return idResource;
        }

        public void setIdResource(String idResource) {
            this.idResource = idResource;
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
