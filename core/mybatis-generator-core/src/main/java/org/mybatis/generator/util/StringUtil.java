package org.mybatis.generator.util;

/**
 */
public class StringUtil {


    public static String uncapitalize(String str) {
        int strLen;
        return str != null && (strLen = str.length()) != 0?(new StringBuffer(strLen)).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString():str;
    }

    public static String lowerCase(String str) {
        return str == null?null:str.toLowerCase();
    }

    public static String capitalize(String str) {
        int strLen;
        return str != null && (strLen = str.length()) != 0?(new StringBuffer(strLen)).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1)).toString():str;
    }

}
