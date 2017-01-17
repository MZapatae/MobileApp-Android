package cl.mzapatae.mobileLegacy.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 *          Created on: 17-01-17
 *          E-mail: miguel.zapatae@gmail.com
 */

public class FormValidator {

    public static boolean isValidEmail(String email) {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return (matcher.matches() && !isEmptyText(email));
    }

    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$"; //Minimum 6 characters at least 1 Alphabet and 1 Number
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return (matcher.matches() && !isEmptyText(password));
    }

    public static boolean isValidName(String name) {
        String regex = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return (matcher.find() && !isEmptyText(name));
    }

    public static boolean isValidLastName(String lastName) {
        String regex = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(lastName);
        return (matcher.find() && !isEmptyText(lastName));
    }

    public static boolean isValidUsername(String username) {
        String regex = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$"; //Valid Username between 4 and 15 characters
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return (matcher.matches());
    }

    public static boolean isValidMobilePhone(String phone) {
        //String regex = "^\\+[1-9]{1,2}[0-9]{3,14}$"; //Valid Phone Chile in format +56 9 12345678
        String regex = "^[0-9]{8}$"; //Valid Phone Chile in format 53456912
        return phone.matches(regex);
    }

    private static boolean isEmptyText(String text) {
        return text.trim().isEmpty();
    }

    private static boolean isValidPatternEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}

