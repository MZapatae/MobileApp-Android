package cl.mzapatae.mobileLegacy.datamodel.objects;

import cl.mzapatae.mobileLegacy.datamodel.gson.MetaResponse;
/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 18-01-17
 * E-mail: miguel.zapatae@gmail.com
 */

public class APIError {
    private MetaResponse meta;

    public APIError() {

    }

    public APIError(int code, String message) {
        this.meta = new MetaResponse();
        this.meta.setCode(code);
        this.meta.setMessage(message);
    }

    public int status() {
        return meta.getCode();
    }

    public String message() {
        return meta.getMessage();
    }
}
