package cl.mzapatae.mobileApp.datamodel.gson;

import com.google.gson.annotations.SerializedName;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 30-01-17
 * E-mail: miguel.zapatae@gmail.com
 */

public class ErrorResponse {
    @SerializedName("code") private int code;
    @SerializedName("field") private String field;
    @SerializedName("message") private String message;

    public ErrorResponse() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
