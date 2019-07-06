package com.coin.wallet.utils;

public class StringTemplateUtils {

    public static String limit(String str, Integer count) {
        if (org.springframework.util.StringUtils.isEmpty(str)) {
            return "";
        }
        if (str.length() > count) {
            return str.substring(0, count);
        }
        return str;
    }
}
