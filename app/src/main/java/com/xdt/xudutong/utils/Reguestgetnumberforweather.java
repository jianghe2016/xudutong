package com.xdt.xudutong.utils;

/**
 * import java.util.regex.Matcher;
 * import java.util.regex.Pattern;
 * <p>
 * /**
 * Created by Administrator on 2017/6/16.
 */

public class Reguestgetnumberforweather {
    public static Float Reguestgetnumberforweather(String str) {
        final String[] split = str.split(" ");
        final String s = split[1];
        final String[] split1 = s.split("℃");
        final String s1 = split1[0];
        Float aFloat = Float.valueOf(s1);
        return aFloat;
    }
}
