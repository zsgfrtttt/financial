package com.csz.common.util;

public class TextUtil {

    public static boolean isEmpty(String string){
        if (string == null || string.equals("")) {
            return true;
        }
        return false;
    }
}
