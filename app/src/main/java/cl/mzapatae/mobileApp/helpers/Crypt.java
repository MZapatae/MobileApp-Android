package cl.mzapatae.mobileApp.helpers;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import cl.mzapatae.mobileApp.BuildConfig;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 27-01-17
 * E-mail: miguel.zapatae@gmail.com
 */

public class Crypt {
    private IvParameterSpec mIvspec;
    private SecretKeySpec mKeyspec;
    private Cipher mCipher;


    public Crypt() {
        mIvspec = new IvParameterSpec(BuildConfig.AES_IV.getBytes());
        mKeyspec = new SecretKeySpec(BuildConfig.AES_KEY.getBytes(), "AES");

        try {
            mCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public byte[] encrypt(String text) throws Exception {
        if (text == null || text.length() == 0) throw new Exception("Empty string");
        byte[] encrypted;

        try {
            mCipher.init(Cipher.ENCRYPT_MODE, mKeyspec, mIvspec);
            encrypted = mCipher.doFinal(text.getBytes("UTF-8"));
        } catch (Exception e) {
            throw new Exception("[encrypt] " + e.getMessage());
        }
        return encrypted;
    }

    public byte[] decrypt(byte[] text) throws Exception {
        byte[] decrypted;
        try {
            mCipher.init(Cipher.DECRYPT_MODE, mKeyspec, mIvspec);
            decrypted = mCipher.doFinal(text);
        } catch (Exception e) {
            throw new Exception("[decrypt] " + e.getMessage());
        }
        return decrypted;
    }
}
