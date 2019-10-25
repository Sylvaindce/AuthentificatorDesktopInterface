package fr.sylvaindce.Tools;

import org.apache.commons.codec.binary.Base64;

public class EncryptUtils {

    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final char[] encrypt_key = "tuYlqGNGh4yD5FIvynsNlYjsF7XIv0uh".toCharArray();

    public static String encode(String text) {
        if (text == null) {
            return "";
        }
        try {
            text = EncryptUtils.xorMessage(text);
            return new String(new Base64().encode(text.getBytes(EncryptUtils.DEFAULT_ENCODING)));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String decode(String text) {
        if (text == null) {
            return "";
        }
        try {
            text = new String(new Base64().decode(text.getBytes(EncryptUtils.DEFAULT_ENCODING)));
            return EncryptUtils.xorMessage(text);
        } catch (Exception e) {
            return "";
        }
    }

    private static String xorMessage(String message) {
        if (message == null) {
            return "";
        }
        try {
            char[] msg = message.toCharArray();
            int ml = msg.length;
            int kl = EncryptUtils.encrypt_key.length;
            char[] result = new char[ml];

            for (int i = 0; i < ml; i++) {
                result[i] = (char)(msg[i] ^ EncryptUtils.encrypt_key[i % kl]);
            }
            return new String(result);
        } catch (Exception e) {
            return "";
        }
    }
}