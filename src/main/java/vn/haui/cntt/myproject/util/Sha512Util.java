package vn.haui.cntt.myproject.util;

import org.apache.commons.codec.digest.HmacUtils;

public class Sha512Util {
    public static String hmac(String key, String data) {
        String hmac = new HmacUtils("HmacSHA512", key).hmacHex(data);
        return hmac;
    }
}
