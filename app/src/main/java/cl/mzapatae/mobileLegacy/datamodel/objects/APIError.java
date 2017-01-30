package cl.mzapatae.mobileLegacy.datamodel.objects;

import java.util.ArrayList;
import java.util.List;

import cl.mzapatae.mobileLegacy.datamodel.gson.ErrorResponse;
import cl.mzapatae.mobileLegacy.datamodel.gson.MetaResponse;
/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 18-01-17
 * E-mail: miguel.zapatae@gmail.com
 */

public class APIError {
    private MetaResponse meta;
    private List<ErrorResponse> errors;

    public APIError() {

    }

    public APIError(int code, String message, List<ErrorResponse> errorList) {
        this.meta = new MetaResponse();
        this.errors = new ArrayList<>();

        this.meta.setCode(code);
        this.meta.setMessage(message);
        this.errors.addAll(errorList);
    }

    public int status() {
        return meta.getCode();
    }

    public String message() {
        return meta.getMessage();
    }

    public List<ErrorResponse> errorList() {
        return errors;
    }
}
