package cl.mzapatae.mobileApp.utils;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

import cl.mzapatae.mobileApp.R;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 *          Created on: 17-01-17
 *          E-mail: miguel.zapatae@gmail.com
 */

public class DialogManager {
    public static void showSimpleAlert(Context context, int message_resource) {
        new MaterialDialog.Builder(context)
                .title(R.string.dialog_title_error)
                .content(message_resource)
                .positiveText(R.string.dialog_button_accept)
                .show();
    }

    public static void showSimpleAlert(Context context, String message) {
        new MaterialDialog.Builder(context)
                .title(R.string.dialog_title_error)
                .content(message)
                .positiveText(R.string.dialog_button_accept)
                .show();
    }

    public static void showSimpleMessage(Context context, String message) {
        new MaterialDialog.Builder(context)
                .title(R.string.dialog_title_alert)
                .content(message)
                .positiveText(R.string.dialog_button_accept)
                .show();
    }


}
