package com.easy.junit.client;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jackzhong on 2015/12/26.
 */
public final class SignatureUtils {

    //日志
    public static final Logger logger = LoggerFactory.getLogger(SignatureUtils.class);

    public static final String CHARSET = "UTF-8";
    public static final String MD5 = "MD5";

    public static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

    public static byte[] toBytes(String string) {
        try {
            return string.getBytes(CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 encoding not supported by platform", e);
        }
    }

    public static String encodeHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_CHARS[v >>> 4];
            hexChars[j * 2 + 1] = HEX_CHARS[v & 0x0F];
        }
        return new String(hexChars);
    }

    public synchronized static String salt32Md5(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(toBytes(data));
            return encodeHex(digest.digest());
        } catch (NoSuchAlgorithmException nsae) {
            throw new IllegalStateException(nsae);
        }
    }

    public static String signature(String jsonStr, String appSecret) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        return salt32Md5(jsonStr + "|" + appSecret);
    }
}
