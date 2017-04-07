package cl.mzapatae.mobileApp.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.Log;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 07-04-17
 * E-mail: miguel.zapatae@gmail.com
 */

public class VectorialImage {

    public static Drawable setVectorialDrawable(Context context, int resourceImage, int tintColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                Drawable drawable = ResourcesCompat.getDrawable(context.getResources(), resourceImage, null);
                return drawable;
            } catch (Exception e) {
                Log.e("Vectorial Image", "Post Lollipop Exception: " + e.getMessage());
                return null;
            }
        } else {
            try {
                Drawable drawable = VectorDrawableCompat.create(context.getResources(), resourceImage, null);
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, tintColor);
                return drawable;
            } catch (Exception e) {
                Log.e("Vectorial Image", "Pre Lollipop Exception: " + e.getMessage());
                return null;
            }
        }
    }
}
