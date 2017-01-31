package cl.mzapatae.mobileApp.datamodel.gson;


import com.google.gson.annotations.SerializedName;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 17-01-17
 * E-mail: miguel.zapatae@gmail.com
 */

public class MetaResponse {
    @SerializedName("code") private int code;
    @SerializedName("message") private String message;

    public MetaResponse() {

    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
