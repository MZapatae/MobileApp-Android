package cl.mzapatae.mobileApp.utils;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

import cl.mzapatae.mobileApp.R;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 17-01-17
 * E-mail: miguel.zapatae@gmail.com
 */

public class DialogManager {
    public static MaterialDialog.Builder createErrorDialog(Context context, String message) {
        return new MaterialDialog.Builder(context)
                .title(R.string.dialog_title_error)
                .content(message)
                .autoDismiss(false)
                .positiveText(R.string.dialog_button_accept);
    }

    public static MaterialDialog.Builder createMessageDialog(Context context, String message) {
        return new MaterialDialog.Builder(context)
                .title(R.string.dialog_title_alert)
                .content(message)
                .autoDismiss(false)
                .positiveText(R.string.dialog_button_accept);
    }


    public static MaterialDialog.Builder createLoadingDialog(Context context) {
        return new MaterialDialog.Builder(context)
                .title(R.string.dialog_title_loading)
                .content(R.string.dialog_message_loading)
                .autoDismiss(false)
                .progress(true, 0);
    }
}
