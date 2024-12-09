package com.wing.yjyw.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Description:
 */
public class MapUtil {
    public static String mapConvert(String mapData) {
        String[] s = mapData.split("\\.");
        int i = Integer.parseInt(s[0]);
        double j = Double.parseDouble("0." + s[1]);
        int i1 = i / 100;   //度
        int i2 = i % 100;   //分
        double j1 = j * 60; //秒
        BigDecimal bigDecimal = new BigDecimal(i1 + (i2 / (double) 60) + (j1 / 3600));
        bigDecimal = bigDecimal.setScale(6, RoundingMode.HALF_UP);
        double v = bigDecimal.doubleValue();
        return "" + v;
    }
}
