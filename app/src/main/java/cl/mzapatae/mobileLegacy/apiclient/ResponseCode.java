package cl.mzapatae.mobileLegacy.apiclient;

import android.content.Context;

import cl.mzapatae.mobileLegacy.R;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 *          Created on: 17-01-17
 *          E-mail: miguel.zapatae@gmail.com
 */

public enum ResponseCode {
    REQUEST_OK(200),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405),
    REQUEST_TIMEOUT(408),
    URI_TOO_LONG(414),
    TOO_MANY_REQUESTS(429),
    INTERNAL_SERVER_ERROR(500),
    NOT_IMPLEMENTED(501),
    BAD_GATEWAY(502),
    SERVICE_UNAVALIABLE(503),
    GATEWAY_TIMEOUT(504),
    INSUFFICIENT_STORAGE(507);

    private final int value;

    ResponseCode(final int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
