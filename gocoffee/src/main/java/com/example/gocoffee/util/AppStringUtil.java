package com.example.gocoffee.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppStringUtil {
    public String concatString(String... str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            if(i == str.length - 1) {
                sb.append(str[i]);
                break;
            }
            sb.append(str[i]).append(", ");
        }
        return sb.toString();
    }
}
