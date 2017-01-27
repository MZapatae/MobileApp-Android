package cl.mzapatae.mobileLegacy.utils;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import cl.mzapatae.mobileLegacy.BuildConfig;

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
        String mIv = BuildConfig.AES_IV;
        mIvspec = new IvParameterSpec(mIv.getBytes());
        String mSecretKey = BuildConfig.AES_KEY;
        mKeyspec = new SecretKeySpec(mSecretKey.getBytes(), "AES");

        try {
            mCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public byte[] encrypt(String text) throws Exception {
        if (text == null || text.length() == 0) throw new Exception("Empty string");
        byte[] encrypted = null;

        try {
            mCipher.init(Cipher.ENCRYPT_MODE, mKeyspec, mIvspec);
            encrypted = mCipher.doFinal(text.getBytes("UTF-8"));
        } catch (Exception e) {
            throw new Exception("[encrypt] " + e.getMessage());
        }
        return encrypted;
    }


    public byte[] decrypt(byte[] text) throws Exception {
        byte[] decrypted = null;
        try {
            mCipher.init(Cipher.DECRYPT_MODE, mKeyspec, mIvspec);
            decrypted = mCipher.doFinal(text);
        } catch (Exception e) {
            throw new Exception("[decrypt] " + e.getMessage());
        }
        return decrypted;
    }

    private static byte[] hexToBytes(String str) {
        if (str == null) {
            return null;

        } else if (str.length() < 2) {
            return null;

        } else {
            int len = str.length() / 2;
            byte[] buffer = new byte[len];
            for (int i = 0; i < len; i++) {
                buffer[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
            }
            return buffer;
        }
    }

    private static String byteArrayToHexString(byte[] array) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : array) {
            int intVal = b & 0xff;
            if (intVal < 0x10) hexString.append("0");
            hexString.append(Integer.toHexString(intVal));
        }
        return hexString.toString();
    }

    /*

    public static byte[] encryptMsg(String message, String secret)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {

        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, generateKey(BuildConfig.AES_KEY));
        byte[] cipherText = cipher.doFinal(message.getBytes("UTF-8"));
        return cipherText;

    }

    public static String decryptMsg(byte[] cipherText, SecretKey secret)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidParameterSpecException,
            InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException,
            UnsupportedEncodingException {

        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secret);
        return new String(cipher.doFinal(cipherText), "UTF-8");
    }

    private static SecretKey generateKey(String secret) throws NoSuchAlgorithmException, InvalidKeyException {
        return new SecretKeySpec(secret.getBytes(), "AES");
    }

*/


}
