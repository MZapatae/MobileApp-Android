package cl.mzapatae.mobileLegacy.datamodel.gson;

import com.google.gson.annotations.SerializedName;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 *          Created on: 17-01-17
 *          E-mail: miguel.zapatae@gmail.com
 */

public class AuthLoginResponse {
    @SerializedName("meta") private MetaResponse metaResponse;

    public MetaResponse getMetaResponse() {
        return metaResponse;
    }
}
